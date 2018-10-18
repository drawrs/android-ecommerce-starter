package com.khilman.ecommerceudacoding.activities.order_confirmation

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.khilman.ecommerceudacoding.R
import com.khilman.ecommerceudacoding.activities.order_detail.OrderDetailActivity
import com.khilman.ecommerceudacoding.activities.order_detail.OrderDetailInteractor
import com.khilman.ecommerceudacoding.activities.product_detail.ProductDetailActivity
import com.khilman.ecommerceudacoding.adapters.AdapterOrderConfirmationProducts
import com.khilman.ecommerceudacoding.network.model.order_response.Data
import com.khilman.ecommerceudacoding.network.model.shopping_cart_response.ResponseShoppingCart
import com.khilman.ecommerceudacoding.utils.MyConstants
import com.khilman.ecommerceudacoding.utils.MyHelpers
import kotlinx.android.synthetic.main.activity_order_confirmation.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class OrderConfirmationActivity : AppCompatActivity() , OrderConfirmationView {

    private lateinit var pref: SharedPreferences
    private lateinit var presenter: OrderConfirmationPresenter
    private var userId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirmation)
        supportActionBar?.title = "Order Confirmation"

        //todo: init component
        pref = this.getSharedPreferences(MyConstants.PREF_NAME, 0)
        presenter = OrderConfirmationPresenter(this, OrderConfirmationInteractor())
        userId = pref.getString(MyConstants.PREF_USER_ID, null)

        //todo: get shopping cart data from api
        presenter.showCartProducts(userId)
        presenter.getUserProfile(userId)

        //todo: event
        btnCheckOut.onClick {
            presenter.submitToOrder(userId)
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

    override fun showData(shoppingCartResponse: ResponseShoppingCart?) {
        val shoppingCartData = shoppingCartResponse?.data
        //todo: set data to widget
        rvCartProducts.layoutManager = LinearLayoutManager(this)
        rvCartProducts.adapter = AdapterOrderConfirmationProducts(this, shoppingCartData)
        rvCartProducts.isNestedScrollingEnabled = true

        //todo: set the total price
        tvTotalPrice.text = "Total Price : ${MyHelpers().rupiahFormat(shoppingCartResponse?.totalPrice!!.toLong())}"
    }

    override fun setUserProfile(profileData: com.khilman.ecommerceudacoding.network.model.profile_response.Data?) {
        tvShippingAddress.text = "${profileData?.selectedShippingAddress}"
    }

    override fun navigateToOrderDetail(order: Data?) {
        order
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        startActivity(intentFor<OrderDetailActivity>(MyConstants.ORDER_ID to order?.id.toString()).singleTop().clearTop().clearTask())
    }
}
