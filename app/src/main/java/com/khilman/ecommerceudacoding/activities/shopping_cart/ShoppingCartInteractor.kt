package com.khilman.ecommerceudacoding.activities.shopping_cart

import com.khilman.ecommerceudacoding.network.model.delete_shop_cart_response.ResponseDeleteFromCart
import com.khilman.ecommerceudacoding.network.model.order_response.ResponseOrder
import com.khilman.ecommerceudacoding.network.model.response_update_qty_cart.ResponseUpdateQtyCart
import com.khilman.ecommerceudacoding.network.model.shopping_cart_response.DataItem
import com.khilman.ecommerceudacoding.network.model.shopping_cart_response.ResponseShoppingCart
import com.khilman.www.formchecklistapp.network.InitRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShoppingCartInteractor {
    interface OnGetShoppingCartFinished{
        fun onError(message: String)
        fun onSuccess(shoppingCartData: List<DataItem?>?)
    }
    interface OnUpdateShoppingCartFinished {
        fun onUpdateQtyError(message: String)
        fun onUpdateQtySuccess(message: String)
    }
    interface OnDeleteItemCartFinished {
        fun onDeleteSuccess(message: String)
        fun onDeleteError(message: String)
    }
    interface OnOrderFinished {
        fun onOrderSuccess(message: String)
        fun onOrderError(message: String)
    }
    fun getShoppingCartList(userId: String?, listener: OnGetShoppingCartFinished) {
        InitRetrofit().getInstance().requestGetShoppingCarts(userId = userId)
                .enqueue(object : Callback<ResponseShoppingCart> {
                    override fun onFailure(call: Call<ResponseShoppingCart>, t: Throwable) {
                        //todo: handle failed send request
                        t.printStackTrace()
                        listener.onError("Error : ${t.message}")
                    }

                    override fun onResponse(call: Call<ResponseShoppingCart>, response: Response<ResponseShoppingCart>) {
                        //todo : handle server response
                        if (response.isSuccessful){
                            if (response.body()?.isSuccess!!){
                                //todo: passing data
                                val shoppingCartData = response.body()?.data
                                listener.onSuccess(shoppingCartData)
                            } else {
                                listener.onError(response.body()?.message!!)
                            }
                        } else {
                            listener.onError("Failed : ${response.code()} ${response.message()}")
                        }
                    }
                })
    }

    fun setNewQtyItemCart(cartId: Int?, currentQty: Int, listener: OnUpdateShoppingCartFinished) {
        InitRetrofit().getInstance().requestUpdateQtyCartItem(cartId.toString(), currentQty.toString())
                .enqueue(object : Callback<ResponseUpdateQtyCart> {
                    override fun onFailure(call: Call<ResponseUpdateQtyCart>, t: Throwable) {
                        //todo: handle failed send request
                        t.printStackTrace()
                        listener.onUpdateQtyError("Error : ${t.message}")
                    }

                    override fun onResponse(call: Call<ResponseUpdateQtyCart>, response: Response<ResponseUpdateQtyCart>) {
                        //todo : handle server response
                        if (response.isSuccessful){
                            if (response.body()?.isSuccess!!){
                                //todo: passing data
                                val shoppingCartData = response.body()?.data
                                listener.onUpdateQtySuccess(response.body()?.message!!)
                            } else {
                                listener.onUpdateQtyError(response.body()?.message!!)
                            }
                        } else {
                            listener.onUpdateQtyError("Failed : ${response.code()} ${response.message()}")
                        }
                    }

                })
    }

    fun deleteItemFromCart(cartId: Int?, listener: OnDeleteItemCartFinished){
        InitRetrofit().getInstance().requestDeleteCartItem(cartId.toString())
                .enqueue(object : Callback<ResponseDeleteFromCart> {
                    override fun onFailure(call: Call<ResponseDeleteFromCart>, t: Throwable) {
                        //todo: handle failed send request
                        t.printStackTrace()
                        listener.onDeleteError("Error : ${t.message}")
                    }

                    override fun onResponse(call: Call<ResponseDeleteFromCart>, response: Response<ResponseDeleteFromCart>) {
                        //todo : handle server response
                        if (response.isSuccessful){
                            if (response.body()?.isSuccess!!){
                                //todo: passing data
                                //val shoppingCartData = response.body()?.data
                                listener.onDeleteSuccess(response.body()?.message!!)
                            } else {
                                listener.onDeleteError(response.body()?.message!!)
                            }
                        } else {
                            listener.onDeleteError("Failed : ${response.code()} ${response.message()}")
                        }
                    }

                })
    }

}