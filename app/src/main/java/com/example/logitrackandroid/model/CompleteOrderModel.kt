package com.example.logitrackandroid.model

data class CompleteOrderModel(
    var receiverName: String,
    var orderId: String,
    var viewOrderButton: String,
    var successImageResource: Int,
    var failedImageResource: Int
)
