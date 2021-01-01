package com.beebeeoii.paradestate.helper

class CompanyParadeStateFormatter(private val date: String, private val paradeType: String, platoonCurrentStrengths: MutableList<Int>, platoonTotalStrengths: MutableList<Int>, platoonReportSicks: MutableList<String>, platoonMedicalStatuses: MutableList<String>, platoonOthers: MutableList<String>) {

    private val platoon1CurrentStrength = platoonCurrentStrengths[0]
    private val platoon2CurrentStrength = platoonCurrentStrengths[1]
    private val platoon3CurrentStrength = platoonCurrentStrengths[2]
    private val platoon4CurrentStrength = platoonCurrentStrengths[3]
    private val platoonHQCurrentStrength = platoonCurrentStrengths[4]
    private val companyCurrentStrength = platoonCurrentStrengths.sum()

    private val platoon1TotalStrength = platoonTotalStrengths[0]
    private val platoon2TotalStrength = platoonTotalStrengths[1]
    private val platoon3TotalStrength = platoonTotalStrengths[2]
    private val platoon4TotalStrength = platoonTotalStrengths[3]
    private val platoonHQTotalStrength = platoonTotalStrengths[4]
    private val companyTotalStrength = platoonTotalStrengths.sum()

    private val platoon1ReportSick = platoonReportSicks[0]
    private val platoon2ReportSick = platoonReportSicks[1]
    private val platoon3ReportSick = platoonReportSicks[2]
    private val platoon4ReportSick = platoonReportSicks[3]
    private val platoonHQReportSick = platoonReportSicks[4]

    private val platoon1MedicalStatus = platoonMedicalStatuses[0]
    private val platoon2MedicalStatus = platoonMedicalStatuses[1]
    private val platoon3MedicalStatus = platoonMedicalStatuses[2]
    private val platoon4MedicalStatus = platoonMedicalStatuses[3]
    private val platoonHQMedicalStatus = platoonMedicalStatuses[4]

    private val platoon1Other = platoonOthers[0]
    private val platoon2Other = platoonOthers[1]
    private val platoon3Other = platoonOthers[2]
    private val platoon4Other = platoonOthers[3]
    private val platoonHQOther = platoonOthers[4]

    fun generateParadeState(): String {

        return "${date.bold()}\n" +
                "$paradeType\n" +
                "926 A TOTAL STRENGTH\n" +
                "$companyCurrentStrength/$companyTotalStrength\n" +
                "\n" +
                "COY HQ: $platoonHQCurrentStrength/$platoonHQTotalStrength\n" +
                "PLATOON 1: $platoon1CurrentStrength/$platoon1TotalStrength\n" +
                "PLATOON 2: $platoon2CurrentStrength/$platoon2TotalStrength\n" +
                "PLATOON 3: $platoon3CurrentStrength/$platoon3TotalStrength\n" +
                "PLATOON 4: $platoon4CurrentStrength/$platoon4TotalStrength\n" +
                "\n" +
                "${"REPORTING SICK".bold()}\n" +
                "COY HQ:\n" +
                "$platoonHQReportSick\n" +
                "\n" +
                "PLT 1:\n" +
                "$platoon1ReportSick\n" +
                "\n" +
                "PLT 2:\n" +
                "$platoon2ReportSick\n" +
                "\n" +
                "PLT 3:\n" +
                "$platoon3ReportSick\n" +
                "\n" +
                "PLT 4:\n" +
                "$platoon4ReportSick\n" +
                "\n" +
                "${"MEDICAL STATUS".bold()}\n" +
                "COY HQ:\n" +
                "$platoonHQMedicalStatus\n" +
                "\n" +
                "PLT 1:\n" +
                "$platoon1MedicalStatus\n" +
                "\n" +
                "PLT 2:\n" +
                "$platoon2MedicalStatus\n" +
                "\n" +
                "PLT 3:\n" +
                "$platoon3MedicalStatus\n" +
                "\n" +
                "PLT 4:\n" +
                "$platoon4MedicalStatus\n" +
                "\n" +
                "${"OTHERS".bold()}\n" +
                "COY HQ:\n" +
                "$platoonHQOther\n" +
                "\n" +
                "PLT 1:\n" +
                "$platoon1Other\n" +
                "\n" +
                "PLT 2:\n" +
                "$platoon2Other\n" +
                "\n" +
                "PLT 3:\n" +
                "$platoon3Other\n" +
                "\n" +
                "PLT 4:\n" +
                "$platoon4Other\n"
    }

    private fun String.bold(): String {
        return "*${this}*"
    }
}