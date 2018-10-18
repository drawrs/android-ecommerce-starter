package com.khilman.ecommerceudacoding.activities.shopping_cart

import com.khilman.ecommerceudacoding.network.model.shopping_cart_response.DataItem

class ShoppingCartPresenter(val view: ShoppingCartView,
                            val interactor: ShoppingCartInteractor):
        ShoppingCartInteractor.OnGetShoppingCartFinished,
        ShoppingCartInteractor.OnUpdateShoppingCartFinished,
        ShoppingCartInteractor.OnDeleteItemCartFinished,
        ShoppingCartInteractor.OnOrderFinished {



    fun showShoppingCartList(userId: String?){
        interactor.getShoppingCartList(userId, this)
    }

    fun updateShippingQtyValue(cartId: Int?, currentQty: Int){
        interactor.setNewQtyItemCart(cartId, currentQty, this)
    }

    fun removeItemFromCart(cartId: Int?){
        interactor.deleteItemFromCart(cartId, this)
    }

    override fun onSuccess(shoppingCartData: List<DataItem?>?) {
        view?.apply {
            showData(shoppingCartData)
            hideProgress()
        }
    }

    override fun onError(message: String) {
        view?.apply {
            showMessage(message)
            hideProgress()
        }
    }

    override fun onUpdateQtyError(message: String) {
        view?.apply {
            showMessage(message)
            hideProgress()
        }
    }

    override fun onUpdateQtySuccess(message: String) {
        view?.apply {
            //showMessage(message)
            hideProgress()
        }
    }

    override fun onDeleteSuccess(message: String) {
        view?.apply {
            showMessage(message)
            successDeleteItem()
            hideProgress()
        }
    }

    override fun onDeleteError(message: String) {
        view?.apply {
            showMessage(message)
            hideProgress()
        }
    }

    override fun onOrderSuccess(message: String) {
        view?.apply {
            showMessage(message)
            hideProgress()
        }
    }

    override fun onOrderError(message: String) {
        view?.apply {
            showMessage(message)
            hideProgress()
        }
    }
}