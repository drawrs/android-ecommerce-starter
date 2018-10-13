package com.khilman.ecommerceudacoding.activities.home

import com.khilman.ecommerceudacoding.network.model.home_categories_response.DataItemCategory
import com.khilman.ecommerceudacoding.network.model.home_categories_response.ResponseHomeCategories
import com.khilman.ecommerceudacoding.network.model.home_products_response.DataItemProduct
import com.khilman.ecommerceudacoding.network.model.home_products_response.ResponseHomeProducts
import com.khilman.ecommerceudacoding.network.model.home_promotions_response.DataItemPromotion
import com.khilman.ecommerceudacoding.network.model.home_promotions_response.ResponseHomePromotions
import com.khilman.www.formchecklistapp.network.InitRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeInteractor {
    interface OnGetHomeDataFinished {
        fun onError(message: String)
        fun onGetCategoriesSuccess(data: List<DataItemCategory?>?)
        fun onGetPromotionsSuccess(data: List<DataItemPromotion?>?)
        fun onGetHomeProductsSuccess(data: List<DataItemProduct?>?)
    }
    fun getCategories(listener: OnGetHomeDataFinished){
        InitRetrofit().getInstance()
                .requestCategories()
                .enqueue(object : Callback<ResponseHomeCategories>{
                    override fun onFailure(call: Call<ResponseHomeCategories>, t: Throwable) {
                        t.printStackTrace()
                        listener.onError("Failed to connect : ${t.message}")
                    }
                    override fun onResponse(call: Call<ResponseHomeCategories>, response: Response<ResponseHomeCategories>) {
                        if (response.isSuccessful){
                            if (response.body()?.isSuccess!!){
                                listener.onGetCategoriesSuccess(response.body()?.data)
                            } else {
                                listener.onError(response.body()?.message!!)
                            }
                        } else {
                            listener.onError("Error : ${response.code()} ${response.message()}")
                        }
                    }

                })
    }

    fun getPromotions(listener: OnGetHomeDataFinished){
        InitRetrofit().getInstance()
                .requestPromotions()
                .enqueue(object : Callback<ResponseHomePromotions>{
                    override fun onFailure(call: Call<ResponseHomePromotions>, t: Throwable) {
                        t.printStackTrace()
                        listener.onError("Failed to connect : ${t.message}")
                    }

                    override fun onResponse(call: Call<ResponseHomePromotions>, response: Response<ResponseHomePromotions>) {
                        if (response.isSuccessful){
                            if (response.body()?.isSuccess!!){
                                listener.onGetPromotionsSuccess(response.body()?.data)
                            } else {
                                listener.onError(response.body()?.message!!)
                            }
                        } else {
                            listener.onError("Error : ${response.code()} ${response.message()}")
                        }
                    }

                })
    }
    fun getHomeProducts(keyword: String?, listener: OnGetHomeDataFinished){
        InitRetrofit().getInstance()
                .requestHomeProducts(keyword)
                .enqueue(object : Callback<ResponseHomeProducts>{
                    override fun onFailure(call: Call<ResponseHomeProducts>, t: Throwable) {
                        t.printStackTrace()
                        listener.onError("Failed to connect : ${t.message}")
                    }

                    override fun onResponse(call: Call<ResponseHomeProducts>, response: Response<ResponseHomeProducts>) {
                        if (response.isSuccessful){
                            if (response.body()?.isSuccess!!){
                                listener.onGetHomeProductsSuccess(response.body()?.data)
                            } else {
                                listener.onError(response.body()?.message!!)
                            }
                        } else {
                            listener.onError("Error : ${response.code()} ${response.message()}")
                        }
                    }

                })
    }
}