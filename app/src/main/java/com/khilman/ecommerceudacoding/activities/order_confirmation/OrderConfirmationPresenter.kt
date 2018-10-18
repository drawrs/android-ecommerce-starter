package com.khilman.ecommerceudacoding.activities.order_confirmation

import com.khilman.ecommerceudacoding.network.model.order_response.Data
import com.khilman.ecommerceudacoding.network.model.shopping_cart_response.ResponseShoppingCart

class OrderConfirmationPresenter (val view: OrderConfirmationView,
                                  val interactor: OrderConfirmationInteractor) :
        OrderConfirmationInteractor.OnGetCartProductFinished,
        OrderConfirmationInteractor.OnOrderFinished,
        OrderConfirmationInteractor.OnGetProfileFinishedListener {

    fun showCartProducts(userId: String?){
        interactor.getCartProductList(userId, this)
    }

    fun getUserProfile(userId: String?){
        interactor.getUserProfile(userId, this)
    }

    fun submitToOrder(userId: String?){
        interactor.insertDataOrder(userId, this)
    }

    override fun onSuccess(shoppingCartResponse: ResponseShoppingCart?) {
        view?.apply {
            showData(shoppingCartResponse)
        }
    }

    override fun onError(message: String) {
        view?.apply {
            showMessage(message)
            hideProgress()
        }
    }

    override fun onOrderSuccess(order: Data?) {
        view?.apply {
            navigateToOrderDetail(order)
            hideProgress()
        }
    }
    override fun onSuccess(profileData: com.khilman.ecommerceudacoding.network.model.profile_response.Data?) {
        view.setUserProfile(profileData)
    }
    override fun onOrderError(message: String) {
        view?.apply {
            showMessage(message)
            hideProgress()
        }
    }

}