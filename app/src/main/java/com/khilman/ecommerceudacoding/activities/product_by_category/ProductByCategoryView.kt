package com.khilman.ecommerceudacoding.activities.product_by_category

import com.khilman.ecommerceudacoding.network.model.home_products_response.DataItemProduct

interface ProductByCategoryView {
    fun showProgress()
    fun hidProgress()
    fun showMessage(message: String)
    fun showDataProucts(data: List<DataItemProduct?>)
}