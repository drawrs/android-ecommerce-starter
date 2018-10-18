package com.khilman.ecommerceudacoding.activities.product_by_category

import com.khilman.ecommerceudacoding.network.model.home_products_response.DataItemProduct

class ProductByCategoryPresenter (val view: ProductByCategoryView, val interactor: ProductByCategoryInteractor): ProductByCategoryInteractor.OnGetProductFinished {

    fun getProductsByCategory(categoryId: String){
        interactor.getProductByCategory(categoryId, this)
    }
    override fun onError(message: String) {
        view?.apply {
            showMessage(message)
            hidProgress()
        }
    }

    override fun onSuccess(data: List<DataItemProduct?>) {
        view?.apply {
            showDataProucts(data)
            hidProgress()
        }
    }
}