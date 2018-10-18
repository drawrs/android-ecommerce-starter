package com.khilman.ecommerceudacoding.activities.edit_shipping_address

interface EditShippingAddressView {
    fun showProgress()
    fun hideProgress()
    fun showMessage(message: String)
}