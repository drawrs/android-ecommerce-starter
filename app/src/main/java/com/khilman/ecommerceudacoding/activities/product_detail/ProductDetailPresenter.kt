package com.khilman.ecommerceudacoding.activities.product_detail

import android.view.View
import com.khilman.ecommerceudacoding.network.model.product_detail_response.ResponseProductDetail
import com.khilman.www.formchecklistapp.network.InitRetrofit
import kotlinx.android.synthetic.main.activity_product_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailPresenter(val activity: ProductDetailActivity,
                              val view: ProductDetailView,
                              val interactor: ProductDetailInteractor): ProductDetailInteractor.OnInsertProductFinished {

    fun getProduct(prductId: String){
        //todo: start progress bar
        view.showProgress()

        //todo: get data from api
        InitRetrofit().getInstance()
                .requestGetDetailProduct(productId = prductId)
                .enqueue(object : Callback<ResponseProductDetail> {
                    override fun onFailure(call: Call<ResponseProductDetail>, t: Throwable) {
                        t.printStackTrace()
                        view.showMessage("Error : ${t.message}")
                        view.hideProgress()
                    }

                    override fun onResponse(call: Call<ResponseProductDetail>, response: Response<ResponseProductDetail>) {
                        if (response.isSuccessful){
                            val productData = response.body()?.data
                            //todo: passing to view model
                            view.showData(productData)
                            //todo: make container visible
                            activity.containerView.visibility = View.VISIBLE
                        } else {
                            view.showMessage("Error : ${response.code()} ${response.message()}")
                        }
                        //todo: hide progressbar
                        view.hideProgress()
                    }
                })
    }

    fun addToShoppingCart(userId: String, productId: String, qty: String){
        interactor.insertShoppingCart(userId, productId, qty, this)
    }

    override fun onInsertSuccess(message: String) {
        view?.apply {
            showMessage(message)
            navigateToShoppingCart()
        }
    }

    override fun onInsertError(message: String) {
        view.showMessage(message)
    }
}