package com.khilman.ecommerceudacoding.activities.shopping_cart

import com.khilman.ecommerceudacoding.network.model.shopping_cart_response.DataItem

interface ShoppingCartView {
    fun showProgress()
    fun hideProgress()
    fun showMessage(message: String)
    fun showData(shoppingCartData: List<DataItem?>?)
    fun successDeleteItem()
    fun navigateToOrderConfirmation()
}