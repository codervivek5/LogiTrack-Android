package com.example.logitrackandroid.model

data class PendingOrderModel(
    var receiverName: String,
    var orderId: String,
    var viewOrderButton: String,
    var completeOrderButton: String,
    var deleteImageResource: Int
) {
    // You can add more properties as needed

}
