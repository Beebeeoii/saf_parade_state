package com.beebeeoii.paradestate

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.card.MaterialCardView
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.text.SimpleDateFormat

private lateinit var platoon1: MaterialCardView
private lateinit var platoon2: MaterialCardView
private lateinit var platoon3: MaterialCardView
private lateinit var platoon4: MaterialCardView
private lateinit var coy_hq: MaterialCardView
private lateinit var generate_coy_parade_state: MaterialCardView

class LastParadeTab : Fragment() {
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
            var paradeState: String? = null

            val f = SimpleDateFormat("ddMMyy")
            val date = f.format(calendarView.date)

            val filename = "${date}_1_last"
            var fileInputStream: FileInputStream?
            try {
                fileInputStream = activity?.openFileInput(filename)

                var inputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()

                while ({ paradeState = bufferedReader.readLine(); paradeState }() != null) {
                    stringBuilder.append(paradeState)
                    stringBuilder.append("\n")
                }

                val data = Bundle()
                data.putString("data", stringBuilder.toString())
                paradeStateDialog.arguments = data
                paradeStateDialog.show(activity!!.supportFragmentManager, ParadeStateDialog.TAG)
            } catch (e: Exception) {
                paradeStateDialog.show(activity!!.supportFragmentManager, ParadeStateDialog.TAG)
            }
        }

        platoon2.setOnClickListener {
            val paradeStateDialog = ParadeStateDialog.newInstance()
            var paradeState: String? = null

            val f = SimpleDateFormat("ddMMyy")
            val date = f.format(calendarView.date)

            val filename = "${date}_2_last"
            var fileInputStream: FileInputStream?
            try {
                fileInputStream = activity?.openFileInput(filename)

                var inputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()

                while ({ paradeState = bufferedReader.readLine(); paradeState }() != null) {
                    stringBuilder.append(paradeState)
                    stringBuilder.append("\n")
                }

                val data = Bundle()
                data.putString("data", stringBuilder.toString())
                paradeStateDialog.arguments = data
                paradeStateDialog.show(activity!!.supportFragmentManager, ParadeStateDialog.TAG)
            } catch (e: Exception) {
                paradeStateDialog.show(activity!!.supportFragmentManager, ParadeStateDialog.TAG)
            }
        }

        platoon3.setOnClickListener {
            val paradeStateDialog = ParadeStateDialog.newInstance()
            var paradeState: String? = null

            val f = SimpleDateFormat("ddMMyy")
            val date = f.format(calendarView.date)

            val filename = "${date}_3_last"
            var fileInputStream: FileInputStream?
            try {
                fileInputStream = activity?.openFileInput(filename)

                var inputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()

                while ({ paradeState = bufferedReader.readLine(); paradeState }() != null) {
                    stringBuilder.append(paradeState)
                    stringBuilder.append("\n")
                }

                val data = Bundle()
                data.putString("data", stringBuilder.toString())
                paradeStateDialog.arguments = data
                paradeStateDialog.show(activity!!.supportFragmentManager, ParadeStateDialog.TAG)
            } catch (e: Exception) {
                paradeStateDialog.show(activity!!.supportFragmentManager, ParadeStateDialog.TAG)
            }
        }

        platoon4.setOnClickListener {
            val paradeStateDialog = ParadeStateDialog.newInstance()
            var paradeState: String? = null

            val f = SimpleDateFormat("ddMMyy")
            val date = f.format(calendarView.date)

            val filename = "${date}_4_last"
            var fileInputStream: FileInputStream?
            try {
                fileInputStream = activity?.openFileInput(filename)

                var inputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()

                while ({ paradeState = bufferedReader.readLine(); paradeState }() != null) {
                    stringBuilder.append(paradeState)
                    stringBuilder.append("\n")
                }

                val data = Bundle()
                data.putString("data", stringBuilder.toString())
                paradeStateDialog.arguments = data
                paradeStateDialog.show(activity!!.supportFragmentManager, ParadeStateDialog.TAG)
            } catch (e: Exception) {
                paradeStateDialog.show(activity!!.supportFragmentManager, ParadeStateDialog.TAG)
            }
        }

        coy_hq.setOnClickListener {
            val paradeStateDialog = ParadeStateDialog.newInstance()
            var paradeState: String? = null

            val f = SimpleDateFormat("ddMMyy")
            val date = f.format(calendarView.date)

            val filename = "${date}_5_last"

            var fileInputStream: FileInputStream?
            try {
                fileInputStream = activity?.openFileInput(filename)

                var inputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()

                while ({ paradeState = bufferedReader.readLine(); paradeState }() != null) {
                    stringBuilder.append(paradeState)
                    stringBuilder.append("\n")
                }

                val data = Bundle()
                data.putString("data", stringBuilder.toString())
                paradeStateDialog.arguments = data
                paradeStateDialog.show(activity!!.supportFragmentManager, ParadeStateDialog.TAG)
            } catch (e: Exception) {
                paradeStateDialog.show(activity!!.supportFragmentManager, ParadeStateDialog.TAG)
            }
        }

        generate_coy_parade_state.setOnClickListener {
            var platoonTotalStrengths = mutableListOf<Int>()
            val platoonCurrentStrengths = mutableListOf<Int>()
            val platoonReportSicks = mutableListOf<String>()
            val platoonMedicalStatuses = mutableListOf<String>()
            val platoonOthers = mutableListOf<String>()

            val f = SimpleDateFormat("ddMMyy")
            val date = f.format(calendarView.date)

            for (i in 1..5) {
                val filename = "${date}_${i}_last"

                val paradeState = readFile(filename)
                val paradeStateInfoPuller = ParadeStateInfoPuller(paradeState)

                val platoonCurrentStrength = paradeStateInfoPuller.getStrength().split("/")[0].trim().toInt()
                val platoonTotalStrength = paradeStateInfoPuller.getStrength().split("/")[1].trim().toInt()
                platoonCurrentStrengths.add(platoonCurrentStrength)
                platoonTotalStrengths.add(platoonTotalStrength)
                platoonReportSicks.add(paradeStateInfoPuller.getReportingSick())
                platoonMedicalStatuses.add(paradeStateInfoPuller.getStatuses())
                platoonOthers.add(paradeStateInfoPuller.getOthers())
            }

            val companyParadeState = "${bold(date)}\n" +
                    "Last Parade\n" +
                    "926 A TOTAL STRENGTH\n" +
                    "${platoonCurrentStrengths.sum()}/${platoonTotalStrengths.sum()}\n" +
                    "\n" +
                    "COY HQ: ${platoonCurrentStrengths[4]}/${platoonTotalStrengths[4]}\n" +
                    "PLATOON 1: ${platoonCurrentStrengths[0]}/${platoonTotalStrengths[0]}\n" +
                    "PLATOON 2: ${platoonCurrentStrengths[1]}/${platoonTotalStrengths[1]}\n" +
                    "PLATOON 3: ${platoonCurrentStrengths[2]}/${platoonTotalStrengths[2]}\n" +
                    "PLATOON 4: ${platoonCurrentStrengths[3]}/${platoonTotalStrengths[3]}\n" +
                    "\n" +
                    "${bold("REPORTING SICK")}\n" +
                    "COY HQ:\n" +
                    "${platoonReportSicks[4]}\n" +
                    "\n" +
                    "PLT 1:\n" +
                    "${platoonReportSicks[0]}\n" +
                    "\n" +
                    "PLT 2:\n" +
                    "${platoonReportSicks[1]}\n" +
                    "\n" +
                    "PLT 3:\n" +
                    "${platoonReportSicks[2]}\n" +
                    "\n" +
                    "PLT 4:\n" +
                    "${platoonReportSicks[3]}\n" +
                    "\n" +
                    "${bold("MEDICAL STATUS")}\n" +
                    "COY HQ:\n" +
                    "${platoonMedicalStatuses[4]}\n" +
                    "\n" +
                    "PLT 1:\n" +
                    "${platoonMedicalStatuses[0]}\n" +
                    "\n" +
                    "PLT 2:\n" +
                    "${platoonMedicalStatuses[1]}\n" +
                    "\n" +
                    "PLT 3:\n" +
                    "${platoonMedicalStatuses[2]}\n" +
                    "\n" +
                    "PLT 4:\n" +
                    "${platoonMedicalStatuses[3]}\n" +
                    "\n" +
                    "${bold("OTHERS")}\n" +
                    "COY HQ:\n" +
                    "${platoonOthers[4]}\n" +
                    "\n" +
                    "PLT 1:\n" +
                    "${platoonOthers[0]}\n" +
                    "\n" +
                    "PLT 2:\n" +
                    "${platoonOthers[1]}\n" +
                    "\n" +
                    "PLT 3:\n" +
                    "${platoonOthers[2]}\n" +
                    "\n" +
                    "PLT 4:\n" +
                    "${platoonOthers[3]}\n"

            val clipboardManager = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("text", companyParadeState)
            clipboardManager.setPrimaryClip(clipData)

            Toast.makeText(context, "Parade State copied!", Toast.LENGTH_SHORT).show()
        }

        return rootView
    }

    private fun readFile(filename: String): String {
        var paradeState: String? = null
        val fileInputStream: FileInputStream?

        try {
            fileInputStream = activity?.openFileInput(filename)

            var inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder: StringBuilder = StringBuilder()

            while ({ paradeState = bufferedReader.readLine(); paradeState }() != null) {
                stringBuilder.append(paradeState)
                stringBuilder.append("\n")
            }

            return stringBuilder.toString()
        } catch (e: Exception) {
            Toast.makeText(context, "Cannot read file: $filename", Toast.LENGTH_SHORT).show()
            return ""
        }
    }

    private fun bold(text: String): String {
        return "*${text}*"
    }

    private fun italicise(text: String): String {
        return "_${text}_"
    }
}