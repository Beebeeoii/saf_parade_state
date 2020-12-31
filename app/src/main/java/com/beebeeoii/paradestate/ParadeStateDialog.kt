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
            var filename = updateUI(paradeStateInput.text.toString().trim())
            println(filename)

            //SAVE TO FILE - "DDMMYY_(PLATOON)_(FIRST/LAST)"
            saveParadeStateToFile(filename, paradeStateInput.text.toString())
        }

        closeDialogButton.setOnClickListener {
            dismiss()
        }
    }

    private fun updateUI(paradeState: String): String {
        val paradeStateInfoPuller = ParadeStateInfoPuller(paradeState)

        val platoon = paradeStateInfoPuller.getPlatoon()
        val firstLastParade = paradeStateInfoPuller.getParadeType()

        println(platoon)
        println(firstLastParade)

        platoonTextView.text = platoon
        strengthTextView.text = paradeStateInfoPuller.getStrength()
        firstLastParadeTextView.text = firstLastParade
        reportSickTextView.text = paradeStateInfoPuller.getReportingSick()
        medicalStatusTextView.text = paradeStateInfoPuller.getStatuses()
        medicalAppointmentTextView.text = paradeStateInfoPuller.getMedicalAppointments()
        otherTextView.text = paradeStateInfoPuller.getOthers()

        return "${paradeStateInfoPuller.getDate()}_${platoon}_$firstLastParade"
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