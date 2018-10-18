package com.khilman.ecommerceudacoding.activities.shopping_cart

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.khilman.ecommerceudacoding.R
import com.khilman.ecommerceudacoding.activities.order_confirmation.OrderConfirmationActivity
import com.khilman.ecommerceudacoding.adapters.AdapterShoppingCartProducts
import com.khilman.ecommerceudacoding.network.model.shopping_cart_response.DataItem
import com.khilman.ecommerceudacoding.utils.MyConstants
import com.khilman.ecommerceudacoding.utils.SessionManager
import kotlinx.android.synthetic.main.activity_shopping_cart.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.toast

class ShoppingCartActivity : AppCompatActivity(), ShoppingCartView {

    private lateinit var pref: SharedPreferences
    private lateinit var presenter: ShoppingCartPresenter

    private var userId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)
        supportActionBar?.title = "Shopping Cart"

        //todo: init component
        pref = SessionManager(this).getInstance()
        presenter = ShoppingCartPresenter(this, ShoppingCartInteractor())
        userId = pref.getString(MyConstants.PREF_USER_ID, null)

        //todo: get shopping cart data from api
        presenter.showShoppingCartList(userId)

        //todo: event
        btnCheckOut.onClick {
            navigateToOrderConfirmation()
        }
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showMessage(message: String) {
        toast(message)
    }

    override fun showData(shoppingCartData: List<DataItem?>?) {
        //todo: set data to widget
        rvShoppingCart.layoutManager = LinearLayoutManager(this)
        rvShoppingCart.adapter = AdapterShoppingCartProducts(this, shoppingCartData, presenter)

        //todo: if data was empty
        if (shoppingCartData?.isEmpty()!!){
            tvEmptyNotice.visibility = View.VISIBLE
        }
    }

    override fun successDeleteItem() {
        presenter.showShoppingCartList(pref.getString(MyConstants.PREF_USER_ID, null))
    }

    override fun navigateToOrderConfirmation() {
        startActivity(intentFor<OrderConfirmationActivity>().singleTop().clearTop())
    }
}
