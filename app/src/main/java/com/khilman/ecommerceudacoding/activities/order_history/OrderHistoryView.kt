package com.khilman.ecommerceudacoding.activities.order_history

import com.khilman.ecommerceudacoding.network.model.order_history_response.DataItem

interface OrderHistoryView {
    fun showProgress()
    fun hideProgress()
    fun showMessage(message: String)
    fun showHistoryOrder(orderHistoryData: List<DataItem?>?)
}