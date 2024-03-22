package com.example.logitrackandroid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.logitrackandroid.databinding.RvItemForPendingOrderBinding
import com.example.logitrackandroid.model.PendingOrderModel

class PendingOrderAdapter(
    private val dataList: MutableList<PendingOrderModel>,
    private val context: Context
) : RecyclerView.Adapter<PendingOrderAdapter.PendingOrderViewHolder>() {

    inner class PendingOrderViewHolder(val binding: RvItemForPendingOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        // Optionally, you can define any additional functions here related to your ViewHolder
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingOrderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvItemForPendingOrderBinding.inflate(layoutInflater, parent, false)
        return PendingOrderViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: PendingOrderViewHolder, position: Int) {
        val pendingOrderData = dataList[position]
        val binding = holder.binding

        // Bind data to views
        binding.receiverName.text = pendingOrderData.receiverName
        binding.orderID.text = pendingOrderData.orderId

        // Set onClickListener for viewOrderButton
        binding.viewOrderBotton.setOnClickListener {
            // Handle click event for viewOrderButton
        }

        // Set onClickListener for completeOrderButton
        binding.completeOrderBotton.setOnClickListener {
            // Handle click event for completeOrderButton
        }

        // Set onClickListener for deleteButton
        binding.deleteButton.setOnClickListener {
            // Show a confirmation dialog using SweetAlertDialog
            SweetAlertDialog(binding.root.context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Won't be able to recover this file!")
                .setConfirmText("Yes, delete it!")
                .setConfirmClickListener { sDialog ->
                    // Remove the item from the list
                    dataList.removeAt(holder.adapterPosition)
                    // Notify the adapter of the item removal
                    notifyItemRemoved(holder.adapterPosition)
                    // Dismiss the dialog
                    sDialog.dismissWithAnimation()
                }
                .show()
        }
    }
}
