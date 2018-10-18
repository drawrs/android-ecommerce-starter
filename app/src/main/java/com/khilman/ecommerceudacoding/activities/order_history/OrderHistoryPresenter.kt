package com.khilman.ecommerceudacoding.activities.order_history

import com.khilman.ecommerceudacoding.network.model.order_history_response.DataItem

class OrderHistoryPresenter(val view: OrderHistoryView, val interactor: OrderHistoryInteractor): OrderHistoryInteractor.OnGetOrderHistoryFinished {

    fun getOrderHistory(userId: String){
        interactor.getOrderHistory(userId, this)
    }
    override fun onError(message: String) {
        view.showMessage(message)
    }

    override fun onSuccess(orderHistoryData: List<DataItem?>?) {
        view?.apply {
            showHistoryOrder(orderHistoryData)
        }
    }
}