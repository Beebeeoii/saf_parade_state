package com.beebeeoii.paradestate.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.beebeeoii.paradestate.fragments.FirstParadeTab
import com.beebeeoii.paradestate.fragments.LastParadeTab

class FirstLastParadePagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FirstParadeTab()
            1 -> LastParadeTab()
            else -> FirstParadeTab()
        }
    }
}