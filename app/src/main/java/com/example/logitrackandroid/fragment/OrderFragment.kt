package com.example.logitrackandroid.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.logitrackandroid.adapter.OrderPagerAdapter
import com.example.logitrackandroid.databinding.FragmentOrderBinding
import com.google.android.material.tabs.TabLayout

class OrderFragment : Fragment() {

    private lateinit var binding: FragmentOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOrderBinding.inflate(inflater,container,false)

        // Set up ViewPager with TabLayout
        val viewPager: ViewPager = binding.viewPager
        val tabLayout: TabLayout = binding.tabLayout

        // Create and set adapter for ViewPager
        val adapter = OrderPagerAdapter(childFragmentManager)
        viewPager.adapter = adapter

        // Connect TabLayout with ViewPager
        tabLayout.setupWithViewPager(viewPager)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }


}