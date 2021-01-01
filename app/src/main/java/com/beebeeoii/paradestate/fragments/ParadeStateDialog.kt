package com.beebeeoii.paradestate.fragments

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.beebeeoii.paradestate.R
import com.beebeeoii.paradestate.helper.ParadeStateInfoPuller
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

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
        if (paradeState != "") {
            if (paradeState != null) {
                updateUI(paradeState)
            }
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
            val filename = updateUI(paradeStateInput.text.toString().trim())
            saveParadeStateToFile(filename, paradeStateInput.text.toString())

            paradeStateInput.text?.clear()
            paradeStateInput.clearFocus()
            paradeStateInput.inputType = InputType.TYPE_NULL

            Snackbar.make(view, "Parade state saved!", Snackbar.LENGTH_SHORT).show()
        }

        closeDialogButton.setOnClickListener {
            dismiss()
        }
    }

    private fun updateUI(paradeState: String): String {
        val paradeStateInfoPuller = ParadeStateInfoPuller(paradeState)
        val platoon: String
        val paradeType: String
        val strength: String
        val reportingSick: String
        val statuses: String
        val medicalAppointments: String
        val others: String

        try {
            platoon = paradeStateInfoPuller.getPlatoon()
            paradeType = paradeStateInfoPuller.getParadeType()
            strength = paradeStateInfoPuller.getStrength()
            reportingSick = paradeStateInfoPuller.getReportingSick()
            statuses = paradeStateInfoPuller.getStatuses()
            medicalAppointments = paradeStateInfoPuller.getMedicalAppointments()
            others = paradeStateInfoPuller.getOthers()
        } catch (e: java.lang.Exception) {
            Toast.makeText(context, "Saved parade state is invalid. Please update!", Toast.LENGTH_SHORT).show()
            return ""
        }

        platoonTextView.text = platoon
        strengthTextView.text = strength
        firstLastParadeTextView.text = paradeType
        reportSickTextView.text = reportingSick
        medicalStatusTextView.text = statuses
        medicalAppointmentTextView.text = medicalAppointments
        otherTextView.text = others

        return "${paradeStateInfoPuller.getDate()}_${platoon}_$paradeType"
    }

    private fun saveParadeStateToFile(filename: String, data: String) {
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = activity?.openFileOutput(filename.toLowerCase(Locale.ROOT), Context.MODE_PRIVATE)!!
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