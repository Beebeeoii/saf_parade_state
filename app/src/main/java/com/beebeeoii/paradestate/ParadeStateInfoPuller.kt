package com.beebeeoii.paradestate

class ParadeStateInfoPuller(paradeState: String) {

    var paradeStateList: MutableList<String> = paradeState
            .replace("*", "")
            .split("\n").toMutableList()

    val RANKS = arrayOf("PTE", "LCP", "CPL", "CFC", "3SG", "2SG", "2LTA", "LTA")
    val SECTIONS = arrayOf("HQ", "SECTION 1", "SECTION 2", "SECTION 3", "GPMG")

    init {
        paradeStateList.removeAll(arrayOf(" ", ""))
        println(paradeStateList)
    }

    fun getDate(): String {
        val lineOfInterest = paradeStateList[0]
        return lineOfInterest.trim()
    }

    fun getPlatoon(): String {
        val lineOfInterest = paradeStateList.filter { line -> line.contains("TOTAL STRENGTH", ignoreCase = true) }[0]
        val landmarkIndex = lineOfInterest.indexOf("TOTAL STRENGTH", ignoreCase = true)

        return if (lineOfInterest.substring(landmarkIndex - 2, landmarkIndex - 1).toIntOrNull() != null) {
            lineOfInterest.substring(landmarkIndex - 2, landmarkIndex - 1)
        } else {
            "5"
        }
    }

    fun getStrength(): String {
        val lineOfInterest = paradeStateList.filter { line -> line.contains("TOTAL STRENGTH", ignoreCase = true) }[0]
        val landmarkIndex = lineOfInterest.indexOf("TOTAL STRENGTH", ignoreCase = true)

        return lineOfInterest.substring(landmarkIndex + 16)
    }

    fun getParadeType(): String {
        val lineOfInterest = paradeStateList[1]
        return lineOfInterest.substring(0, lineOfInterest.indexOf(" "))
    }

    fun getReportingSick(): String {
        var reportSick: String
        val reportSickIndexStart = paradeStateList.indexOf(paradeStateList.filter { line -> line.contains("Reporting Sick", ignoreCase = true) }[0]) + 1
        val reportSickIndexEnd = paradeStateList.indexOf(paradeStateList.filter { line -> line.contains("MEDICAL STATUS") }[0]) - 1

        if (reportSickIndexStart > reportSickIndexEnd) {
            reportSick = "NIL"
            return reportSick
        } else {
            reportSick = ""
            var counter = 1
            paradeStateList.subList(reportSickIndexStart, reportSickIndexEnd + 1).forEach paradestate@{ line ->
                var isLineAdded = false
                RANKS.forEach ranks@{
                    if (line.contains(it, ignoreCase = true)) {
                        reportSick += "${counter}) $line\n"
                        isLineAdded = true
                        counter ++
                        return@paradestate
                    }
                }

                SECTIONS.forEach sections@{
                    if (line.contains(it, ignoreCase = true)) {
                        isLineAdded = true
                        return@sections
                    }
                }

                if (line.contains("NIL", ignoreCase = true)) {
                    isLineAdded = true
                }

                if (!isLineAdded) {
                    reportSick += "$line\n"
                    isLineAdded = true
                }
            }

            if (reportSick == "") {
                reportSick = "NIL"
            }
            return reportSick
        }
    }

    fun getStatuses(): String {
        var medicalStatus: String
        val medicalStatusIndexStart = paradeStateList.indexOf(paradeStateList.filter { line -> line.contains("MEDICAL STATUS", ignoreCase = true) }[0]) + 1
        val medicalStatusSickIndexEnd = paradeStateList.indexOf(paradeStateList.filter { line -> line.contains("MEDICAL APPOINTMENTS", ignoreCase = true) }[0]) - 1

        if (medicalStatusIndexStart > medicalStatusSickIndexEnd) {
            medicalStatus = "NIL"
            return medicalStatus
        } else {
            medicalStatus = ""
            var counter = 1
            paradeStateList.subList(medicalStatusIndexStart, medicalStatusSickIndexEnd + 1).forEach paradestate@{ line ->
                var isLineAdded = false
                RANKS.forEach ranks@{
                    if (line.contains(it, ignoreCase = true)) {
                        medicalStatus += "${counter}) $line\n"
                        isLineAdded = true
                        counter ++
                        return@paradestate
                    }
                }

                SECTIONS.forEach sections@{
                    if (line.contains(it, ignoreCase = true)) {
                        isLineAdded = true
                        return@sections
                    }
                }

                if (line.contains("NIL", ignoreCase = true)) {
                    isLineAdded = true
                }

                if (!isLineAdded) {
                    medicalStatus += "$line\n"
                    isLineAdded = true
                }
            }

            if (medicalStatus == "") {
                medicalStatus = "NIL"
            }
            return medicalStatus
        }
    }

    fun getMedicalAppointments(): String {
        var medicalAppointment: String
        val medicalAppointmentIndexStart = paradeStateList.indexOf(paradeStateList.filter { line -> line.contains("MEDICAL APPOINTMENTS", ignoreCase = true) }[0]) + 1
        val medicalAppointmentIndexEnd = paradeStateList.indexOf(paradeStateList.filter { line -> line.contains("OTHERS", ignoreCase = true) }[0]) - 1

        if (medicalAppointmentIndexStart > medicalAppointmentIndexEnd) {
            medicalAppointment = "NIL"
            return medicalAppointment
        } else {
            medicalAppointment = ""
            var counter = 1
            paradeStateList.subList(medicalAppointmentIndexStart, medicalAppointmentIndexEnd + 1).forEach paradestate@{ line ->
                var isLineAdded = false
                RANKS.forEach {
                    if (line.contains(it, ignoreCase = true)) {
                        medicalAppointment += "${counter}) $line\n"
                        isLineAdded = true
                        counter ++
                        return@paradestate
                    }
                }

                SECTIONS.forEach sections@{
                    if (line.contains(it, ignoreCase = true)) {
                        isLineAdded = true
                        return@sections
                    }
                }

                if (line.contains("NIL", ignoreCase = true)) {
                    isLineAdded = true
                }

                if (!isLineAdded) {
                    medicalAppointment += "$line\n"
                    isLineAdded = true
                }
            }

            if (medicalAppointment == "") {
                medicalAppointment = "NIL"
            }
            return medicalAppointment
        }
    }

    fun getOthers(): String {
        var other: String
        val otherTitleIndex = paradeStateList.indexOf(paradeStateList.filter { line -> line.contains("OTHERS", ignoreCase = true) }[0])
        if (otherTitleIndex == paradeStateList.size - 1) {
            other = "NIL"
            return other
        } else {
            other = ""
            var counter = 1
            paradeStateList.subList(otherTitleIndex + 1, paradeStateList.size).forEach paradestate@{ line ->
                var isLineAdded = false
                RANKS.forEach {
                    if (line.contains(it, ignoreCase = true)) {
                        other += "${counter}) $line\n"
                        isLineAdded = true
                        counter ++
                        return@paradestate
                    }
                }

                SECTIONS.forEach sections@{
                    if (line.contains(it, ignoreCase = true)) {
                        isLineAdded = true
                        return@sections
                    }
                }

                if (line.contains("NIL", ignoreCase = true)) {
                    isLineAdded = true
                }

                if (!isLineAdded) {
                    other += "$line\n"
                    isLineAdded = true
                }
            }

            if (other == "") {
                other = "NIL"
            }
            return other
        }
    }
}