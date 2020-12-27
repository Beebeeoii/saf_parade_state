package com.beebeeoii.paradestate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

lateinit var calendarView: CalendarView
private lateinit var viewPager: ViewPager2
private lateinit var tabLayout: TabLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = FirstLastParadePagerAdapter(this)

        tabLayout = findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "First Parade"
                1 -> tab.text = "Last Parade"
            }
        }.attach()

        calendarView = findViewById(R.id.calendar_view)
        val calendar = Calendar.getInstance()

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            calendarView.date = calendar.timeInMillis
        }
    }
}