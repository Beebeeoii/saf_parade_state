package com.beebeeoii.paradestate.fragments

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.beebeeoii.paradestate.R
import com.beebeeoii.paradestate.calendarView
import com.beebeeoii.paradestate.helper.CompanyParadeStateFormatter
import com.beebeeoii.paradestate.helper.ParadeStateInfoPuller
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

private lateinit var platoon1: MaterialCardView
private lateinit var platoon2: MaterialCardView
private lateinit var platoon3: MaterialCardView
private lateinit var platoon4: MaterialCardView
private lateinit var coy_hq: MaterialCardView
private lateinit var generate_coy_parade_state: MaterialCardView

class FirstParadeTab : Fragment() {

    @SuppressLint("SimpleDateFormat")
    private val dateFormatter = SimpleDateFormat("ddMMyy")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_first_parade_tab, container, false)

        platoon1 = rootView.findViewById(R.id.platoon_1)
        platoon2 = rootView.findViewById(R.id.platoon_2)
        platoon3 = rootView.findViewById(R.id.platoon_3)
        platoon4 = rootView.findViewById(R.id.platoon_4)
        coy_hq = rootView.findViewById(R.id.coy_hq)
        generate_coy_parade_state = rootView.findViewById(R.id.generate_coy_parade_state)

        platoon1.setOnClickListener {
            val paradeStateDialog = ParadeStateDialog.newInstance()

            val date = dateFormatter.format(calendarView.date)
            val filename = "${date}_1_first"

            try {
                val paradeState = readFile(filename)
                val data = Bundle()
                data.putString("data", paradeState)
                paradeStateDialog.arguments = data
                paradeStateDialog.show(activity!!.supportFragmentManager, ParadeStateDialog.TAG)
            } catch (e: Exception) {
                paradeStateDialog.show(activity!!.supportFragmentManager, ParadeStateDialog.TAG)
            }
        }

        platoon2.setOnClickListener {
            val paradeStateDialog = ParadeStateDialog.newInstance()

            val date = dateFormatter.format(calendarView.date)
            val filename = "${date}_2_first"

            try {
                val paradeState = readFile(filename)
                val data = Bundle()
                data.putString("data", paradeState)
                paradeStateDialog.arguments = data
                paradeStateDialog.show(activity!!.supportFragmentManager, ParadeStateDialog.TAG)
            } catch (e: Exception) {
                paradeStateDialog.show(activity!!.supportFragmentManager, ParadeStateDialog.TAG)
            }
        }

        platoon3.setOnClickListener {
            val paradeStateDialog = ParadeStateDialog.newInstance()

            val date = dateFormatter.format(calendarView.date)
            val filename = "${date}_3_first"

            try {
                val paradeState = readFile(filename)
                val data = Bundle()
                data.putString("data", paradeState)
                paradeStateDialog.arguments = data
                paradeStateDialog.show(activity!!.supportFragmentManager, ParadeStateDialog.TAG)
            } catch (e: Exception) {
                paradeStateDialog.show(activity!!.supportFragmentManager, ParadeStateDialog.TAG)
            }
        }

        platoon4.setOnClickListener {
            val paradeStateDialog = ParadeStateDialog.newInstance()

            val date = dateFormatter.format(calendarView.date)
            val filename = "${date}_4_first"

            try {
                val paradeState = readFile(filename)
                val data = Bundle()
                data.putString("data", paradeState)
                paradeStateDialog.arguments = data
                paradeStateDialog.show(activity!!.supportFragmentManager, ParadeStateDialog.TAG)
            } catch (e: Exception) {
                paradeStateDialog.show(activity!!.supportFragmentManager, ParadeStateDialog.TAG)
            }
        }

        coy_hq.setOnClickListener {
            val paradeStateDialog = ParadeStateDialog.newInstance()

            val date = dateFormatter.format(calendarView.date)
            val filename = "${date}_5_first"

            try {
                val paradeState = readFile(filename)
                val data = Bundle()
                data.putString("data", paradeState)
                paradeStateDialog.arguments = data
                paradeStateDialog.show(activity!!.supportFragmentManager, ParadeStateDialog.TAG)
            } catch (e: Exception) {
                paradeStateDialog.show(activity!!.supportFragmentManager, ParadeStateDialog.TAG)
            }
        }

        generate_coy_parade_state.setOnClickListener generateCompanyParadeState@{
            val date = dateFormatter.format(calendarView.date)
            val paradeType = "First Parade"
            val platoonTotalStrengths = mutableListOf<Int>()
            val platoonCurrentStrengths = mutableListOf<Int>()
            val platoonReportSicks = mutableListOf<String>()
            val platoonMedicalStatuses = mutableListOf<String>()
            val platoonOthers = mutableListOf<String>()

            for (i in 1..5) {
                val filename = "${date}_${i}_first"

                val paradeState = readFile(filename)
                if (paradeState == "") {
                    Toast.makeText(context, "Platoon $i parade state cannot be found!", Toast.LENGTH_SHORT).show()
                    return@generateCompanyParadeState
                }
                val paradeStateInfoPuller = ParadeStateInfoPuller(paradeState)

                val platoonCurrentStrength = paradeStateInfoPuller.getStrength().split("/")[0].trim().toInt()
                val platoonTotalStrength = paradeStateInfoPuller.getStrength().split("/")[1].trim().toInt()
                platoonCurrentStrengths.add(platoonCurrentStrength)
                platoonTotalStrengths.add(platoonTotalStrength)
                platoonReportSicks.add(paradeStateInfoPuller.getReportingSick())
                platoonMedicalStatuses.add(paradeStateInfoPuller.getStatuses())
                platoonOthers.add(paradeStateInfoPuller.getOthers())
            }

            val companyParadeStateFormatter = CompanyParadeStateFormatter(date, paradeType, platoonCurrentStrengths, platoonTotalStrengths, platoonReportSicks, platoonMedicalStatuses, platoonOthers)
            val companyParadeState = companyParadeStateFormatter.generateParadeState()

            val clipboardManager = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("text", companyParadeState)
            clipboardManager.setPrimaryClip(clipData)

            Snackbar.make(rootView, "Copied to clipboard!", Snackbar.LENGTH_SHORT).show()
        }

        return rootView
    }

    private fun readFile(filename: String): String {
        var paradeState: String? = null
        val fileInputStream: FileInputStream?

        try {
            fileInputStream = activity?.openFileInput(filename)

            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder: StringBuilder = StringBuilder()

            while ({ paradeState = bufferedReader.readLine(); paradeState }() != null) {
                stringBuilder.append(paradeState)
                stringBuilder.append("\n")
            }

            return stringBuilder.toString()
        } catch (e: Exception) {
            return ""
        }
    }
}