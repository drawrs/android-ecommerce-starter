package com.khilman.ecommerceudacoding.activities.home

import com.khilman.ecommerceudacoding.network.model.home_categories_response.DataItemCategory
import com.khilman.ecommerceudacoding.network.model.home_products_response.DataItemProduct
import com.khilman.ecommerceudacoding.network.model.home_promotions_response.DataItemPromotion

interface HomeView {
    fun showMessage(message: String)
    fun showProgress()
    fun hideProgress()
    fun showPromotions(promotionProducts: List<DataItemPromotion?>?)
    fun showCategories(promotionCategories: List<DataItemCategory?>?)
    fun showProducts(homeProducts: List<DataItemProduct?>?)
    fun navigateToLogin()
    fun hideEmptyNotice()
    fun showEmptyNotice()
}