package com.beebeeoii.paradestate

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

private lateinit var platoonTextView: TextView
private lateinit var firstLastParadeTextView: TextView
private lateinit var strengthTextView: TextView
private lateinit var reportSickTextView: TextView
private lateinit var medicalStatusTextView: TextView
private lateinit var medicalAppointmentTextView: TextView
private lateinit var otherTextView: TextView
private lateinit var paradeStateInput: TextInputEditText
private lateinit var saveParadeStateButton: MaterialButton
private lateinit var closeDialogButton: MaterialButton

class ParadeStateDialog : DialogFragment() {

    val RANKS = arrayOf("PTE", "LCP", "CPL", "CFC", "3SG", "2SG", "2LTA", "LTA")

    companion object {
        const val TAG = "ParadeStateDialog"

        fun newInstance(): ParadeStateDialog {
            val args = Bundle()
            val fragment = ParadeStateDialog()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.dialog_parade_state, container, false)
        platoonTextView = rootView.findViewById(R.id.platoon)
        firstLastParadeTextView = rootView.findViewById(R.id.first_last_parade)
        strengthTextView = rootView.findViewById(R.id.strength)
        reportSickTextView = rootView.findViewById(R.id.report_sick)
        medicalStatusTextView = rootView.findViewById(R.id.medical_status)
        medicalAppointmentTextView = rootView.findViewById(R.id.medical_appointment)
        otherTextView = rootView.findViewById(R.id.other)
        paradeStateInput = rootView.findViewById(R.id.parade_state_input)
        saveParadeStateButton = rootView.findViewById(R.id.save_parade_state)
        closeDialogButton = rootView.findViewById(R.id.close_dialog)

        val paradeState = arguments!!.getString("data")
        if (paradeState != null) {
            updateUI(paradeState)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners(view)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
        )
    }

    private fun setupClickListeners(view: View) {
        saveParadeStateButton.setOnClickListener {
            val filename = updateUI(paradeStateInput.text.toString())
            println(filename)

            //SAVE TO FILE - "DDMMYY_(PLATOON)_(FIRST/LAST)"
            saveParadeStateToFile(filename, paradeStateInput.text.toString())
        }

        closeDialogButton.setOnClickListener {
            dismiss()
        }
    }

    private fun updateUI(paradeState: String): String {
        var platoon: String
        var strength: String
        var firstLastParade: String
        var reportSick: String
        var medicalStatus: String
        var medicalAppointment: String
        var other: String

        val paradeStateList = paradeState
                .replace("*", "")
                .split("\n").toMutableList()
        paradeStateList.removeAll(arrayOf(" ", ""))
        println(paradeStateList)

        var lineOfInterest = paradeStateList.filter { line -> line.contains("TOTAL STRENGTH", ignoreCase = true) }[0]
        var landmarkIndex = lineOfInterest.indexOf("TOTAL STRENGTH", ignoreCase = true)

        //PLATOON SECTION
        platoon = if (lineOfInterest.substring(landmarkIndex - 2, landmarkIndex - 1).toIntOrNull() != null) {
            lineOfInterest.substring(landmarkIndex - 2, landmarkIndex - 1)
        } else {
            "COY HQ"
        }
        platoonTextView.text = platoon

        //STRENGTH SECTION
        strength = lineOfInterest.substring(landmarkIndex + 16)
        strengthTextView.text = strength

        //FIRST LAST PARADE SECTION
        lineOfInterest = paradeStateList[1]
        firstLastParade = lineOfInterest.substring(0, lineOfInterest.indexOf(" "))
        firstLastParadeTextView.text = firstLastParade

        //REPORTING SICK SECTION
        val reportSickIndexStart = paradeStateList.indexOf(paradeStateList.filter { line -> line.contains("Reporting Sick", ignoreCase = true) }[0]) + 1
        val reportSickIndexEnd = paradeStateList.indexOf(paradeStateList.filter { line -> line.contains("MEDICAL STATUS") }[0]) - 1

        if (reportSickIndexStart > reportSickIndexEnd) {
            reportSick = "NIL"
            reportSickTextView.text = reportSick
        } else {
            reportSick = ""
            var counter = 1
            paradeStateList.subList(reportSickIndexStart, reportSickIndexEnd + 1).forEach { line ->
                RANKS.forEach {
                    if (line.contains(it)) {
                        reportSick += "${counter}) $line\n"
                        counter ++
                    }
                }
            }

            if (reportSick == "") {
                reportSick = "NIL"
            }
            reportSickTextView.text = reportSick
        }

        //MEDICAL STATUS SECTION
        val medicalStatusIndexStart = paradeStateList.indexOf(paradeStateList.filter { line -> line.contains("MEDICAL STATUS", ignoreCase = true) }[0]) + 1
        val medicalStatusSickIndexEnd = paradeStateList.indexOf(paradeStateList.filter { line -> line.contains("MEDICAL APPOINTMENTS", ignoreCase = true) }[0]) - 1

        if (medicalStatusIndexStart > medicalStatusSickIndexEnd) {
            medicalStatus = "NIL"
            medicalStatusTextView.text = medicalStatus
        } else {
            medicalStatus = ""
            var counter = 1
            paradeStateList.subList(medicalStatusIndexStart, medicalStatusSickIndexEnd + 1).forEach { line ->
                RANKS.forEach {
                    if (line.contains(it)) {
                        medicalStatus += "${counter}) $line\n"
                        counter ++
                    }
                }
            }

            if (medicalStatus == "") {
                medicalStatus = "NIL"
            }
            medicalStatusTextView.text = medicalStatus
        }

        //MEDICAL APPOINTMENT SECTION
        val medicalAppointmentIndexStart = paradeStateList.indexOf(paradeStateList.filter { line -> line.contains("MEDICAL APPOINTMENTS", ignoreCase = true) }[0]) + 1
        val medicalAppointmentIndexEnd = paradeStateList.indexOf(paradeStateList.filter { line -> line.contains("OTHERS", ignoreCase = true) }[0]) - 1

        if (medicalAppointmentIndexStart > medicalAppointmentIndexEnd) {
            medicalAppointment = "NIL"
            medicalAppointmentTextView.text = medicalAppointment
        } else {
            medicalAppointment = ""
            var counter = 1
            paradeStateList.subList(medicalAppointmentIndexStart, medicalAppointmentIndexEnd + 1).forEach { line ->
                RANKS.forEach {
                    if (line.contains(it)) {
                        medicalAppointment += "${counter}) $line\n"
                        counter ++
                    }
                }
            }

            if (medicalAppointment == "") {
                medicalAppointment = "NIL"
            }
            medicalAppointmentTextView.text = medicalAppointment
        }

        //OTHER SECTION
        val otherTitleIndex = paradeStateList.indexOf(paradeStateList.filter { line -> line.contains("OTHERS", ignoreCase = true) }[0])
        if (otherTitleIndex == paradeStateList.size - 1) {
            other = "NIL"
            otherTextView.text = other
        } else {
            other = ""
            var counter = 1
            paradeStateList.subList(otherTitleIndex + 1, paradeStateList.size).forEach { line ->
                RANKS.forEach {
                    if (line.contains(it)) {
                        other += "${counter}) $line\n"
                        counter ++
                    }
                }
            }

            if (other == "") {
                other = "NIL"
            }
            otherTextView.text = other
        }

        return "${paradeStateList[0].trim()}_${platoon}_$firstLastParade"
    }

    private fun saveParadeStateToFile(filename: String, data: String) {
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = activity?.openFileOutput(filename.toLowerCase(), Context.MODE_PRIVATE)!!
            fileOutputStream.write(data.toByteArray())
        } catch (e: FileNotFoundException){
            e.printStackTrace()
        }catch (e: NumberFormatException){
            e.printStackTrace()
        }catch (e: IOException){
            e.printStackTrace()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}