package com.khilman.ecommerceudacoding.activities.home

import android.content.SharedPreferences
import com.khilman.ecommerceudacoding.network.model.home_categories_response.DataItemCategory
import com.khilman.ecommerceudacoding.network.model.home_products_response.DataItemProduct
import com.khilman.ecommerceudacoding.network.model.home_promotions_response.DataItemPromotion
import com.khilman.ecommerceudacoding.utils.MyConstants

class HomePresenter(val view: HomeView, val interactor: HomeInteractor) : HomeInteractor.OnGetHomeDataFinished {

    private var isHomeProductsReady = false
    private var isHomePromotionsReady = false
    private var isHomeCategoriesReady = false

    fun getHomeData(){
        view.showProgress()
        this.getHomeSlider()
        this.getHomeCategories()
        this.getHomeProducts()
    }
    fun getHomeSlider(){
        interactor.getPromotions(this)
    }
    fun getHomeCategories(){
        interactor.getCategories(this)
    }
    fun getHomeProducts(searchKeyword: String? = null){
        interactor.getHomeProducts(searchKeyword, this)
    }
    fun isEverythingReady(): Boolean {
        return isHomePromotionsReady && isHomeCategoriesReady && isHomeProductsReady
    }
    override fun onError(message: String) {
    }

    fun userLogout(pref: SharedPreferences?) {
        val prefEditor = pref?.edit()
        prefEditor?.remove(MyConstants.PREF_FIRST_NAME)
        prefEditor?.remove(MyConstants.PREF_LAST_NAME)
        prefEditor?.remove(MyConstants.PREF_EMAIL)
        prefEditor?.remove(MyConstants.PREF_USER_ID)
        prefEditor?.remove(MyConstants.PREF_IS_LOGGINED)
        //todo: commit
        prefEditor?.commit()
        //todo: action after logout
        view.navigateToLogin()
    }

    override fun onGetCategoriesSuccess(data: List<DataItemCategory?>?) {
        isHomeCategoriesReady = true
        view.showCategories(data)
        if (isEverythingReady()) {
            view.hideProgress()
        }
    }

    override fun onGetPromotionsSuccess(data: List<DataItemPromotion?>?) {
        isHomePromotionsReady = true
        view.showPromotions(data)
        if (isEverythingReady()) {
            view.hideProgress()
        }
    }

    override fun onGetHomeProductsSuccess(data: List<DataItemProduct?>?) {
        isHomeProductsReady = true
        view.showProducts(data)
        if (isEverythingReady()) {
            view.hideProgress()
        }
        //todo: if search was zero result
        if (data?.size!! <= 0){
            view.showEmptyNotice()
        } else {
            view.hideEmptyNotice()
        }
    }

}