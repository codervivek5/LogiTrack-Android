package com.example.logitrackandroid.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.logitrackandroid.R
import com.example.logitrackandroid.adapter.PendingOrderAdapter
import com.example.logitrackandroid.databinding.FragmentPendingOrderBinding
import com.example.logitrackandroid.model.PendingOrderModel

class PendingOrderFragment : Fragment() {

    private var binding: FragmentPendingOrderBinding? = null
    private lateinit var adapter: PendingOrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPendingOrderBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Dummy data for testing
        val dataList = ArrayList<PendingOrderModel>()
        dataList.add(
            PendingOrderModel(
                receiverName = "John Doe",
                orderId = "ORD12345",
                viewOrderButton = "View Order",
                completeOrderButton = "Complete Order",
                deleteImageResource = R.drawable.delete_icon
            )
        )
        dataList.add(
            PendingOrderModel(
                receiverName = "Vivek Prajapati",
                orderId = "ORD56789",
                viewOrderButton = "View Order",
                completeOrderButton = "Complete Order",
                deleteImageResource = R.drawable.delete_icon
            )
        )
        dataList.add(
            PendingOrderModel(
                receiverName = "Deepak Prajapati",
                orderId = "ORD282748",
                viewOrderButton = "View Order",
                completeOrderButton = "Complete Order",
                deleteImageResource = R.drawable.delete_icon
            )
        )

        // Initialize RecyclerView and adapter
        adapter = PendingOrderAdapter(dataList, requireContext())
        binding?.rvForPendingOrder?.adapter = adapter
        binding?.rvForPendingOrder?.layoutManager = LinearLayoutManager(requireContext())

        // If your data is dynamic, update the adapter's data accordingly
        // adapter.updateData(newDataList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Release binding to avoid memory leaks
        binding = null
    }
}
