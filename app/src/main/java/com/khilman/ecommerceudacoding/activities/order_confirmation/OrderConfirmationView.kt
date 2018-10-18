package com.khilman.ecommerceudacoding.activities.order_confirmation

import com.khilman.ecommerceudacoding.network.model.order_response.Data
import com.khilman.ecommerceudacoding.network.model.shopping_cart_response.ResponseShoppingCart

interface OrderConfirmationView {
    fun showProgress()
    fun hideProgress()
    fun showMessage(message: String)
    fun showData(shoppingCartResponse: ResponseShoppingCart?)
    fun navigateToOrderDetail(order: Data?)
    fun setUserProfile(profileData: com.khilman.ecommerceudacoding.network.model.profile_response.Data?)
}