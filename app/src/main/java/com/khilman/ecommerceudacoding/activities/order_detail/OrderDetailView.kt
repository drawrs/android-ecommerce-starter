package com.khilman.ecommerceudacoding.activities.order_detail

import com.khilman.ecommerceudacoding.network.model.detail_order_response.Data

interface OrderDetailView {
    fun showProgress()
    fun hideProgress()
    fun showMessage(message: String)
    fun showOrder(oderData: Data?)
    fun showPaymentProofPreview(documenUrl: String?)
}