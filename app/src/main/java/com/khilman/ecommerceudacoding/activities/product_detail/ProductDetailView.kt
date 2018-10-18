package com.khilman.ecommerceudacoding.activities.product_detail

import com.khilman.ecommerceudacoding.network.model.product_detail_response.Data

interface ProductDetailView {
    fun showMessage(message: String)
    fun showData(productData: Data?)
    fun hideProgress()
    fun showProgress()
    fun navigateToShoppingCart()
}