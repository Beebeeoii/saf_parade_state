package com.beebeeoii.paradestate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        return rootView
    }

}