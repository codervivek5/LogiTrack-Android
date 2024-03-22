package com.example.logitrackandroid.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.logitrackandroid.R
import com.example.logitrackandroid.databinding.FragmentButtomSheetPendingOrderBinding

class BottomSheetPendingOrderFragment : Fragment() {

    private  var binding: FragmentButtomSheetPendingOrderBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buttom_sheet_pending_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup views and listeners here
        binding!!.ViewOnMapBotton.setOnClickListener {
            // Add your click listener code here
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // Handle arguments if needed
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clear the binding object reference to prevent memory leaks
        binding = null
    }

}