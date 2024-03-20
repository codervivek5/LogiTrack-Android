package com.example.logitrackandroid.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.logitrackandroid.fragment.CompleteOrderFragment
import com.example.logitrackandroid.fragment.PendingOrderFragment

class OrderPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {

        // Return the number of tabs
        return 2 // Assuming there are two tabs: Pending Order and Complete Order
    }

    override fun getItem(position: Int): Fragment {

        // Return the fragment for each tab
        return when (position) {
            0 -> PendingOrderFragment() // Fragment for the first tab (Pending Order)
            1 -> CompleteOrderFragment() // Fragment for the second tab (Complete Order)
            else -> throw IllegalStateException("Invalid tab position")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {

        // Return the title for each tab
        return when (position) {
            0 -> "Pending Order"
            1 -> "Complete Order"
            else -> null
        }
    }
}
