package com.khilman.ecommerceudacoding.activities.order_history

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.khilman.ecommerceudacoding.R
import com.khilman.ecommerceudacoding.adapters.AdapterOrderHistoryProducts
import com.khilman.ecommerceudacoding.network.model.order_history_response.DataItem
import com.khilman.ecommerceudacoding.utils.MyConstants
import com.khilman.ecommerceudacoding.utils.hide
import com.khilman.ecommerceudacoding.utils.show
import kotlinx.android.synthetic.main.activity_order_history.*
import org.jetbrains.anko.toast

class OrderHistoryActivity : AppCompatActivity(), OrderHistoryView {
    private lateinit var pref: SharedPreferences
    private lateinit var presenter: OrderHistoryPresenter

    private var userId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history)
        supportActionBar?.title = "My Order"

        //todo: init component
        pref = this.getSharedPreferences(MyConstants.PREF_NAME, 0)
        presenter = OrderHistoryPresenter(this, OrderHistoryInteractor())
        userId = pref.getString(MyConstants.PREF_USER_ID, null)

        //todo: get history
        presenter.getOrderHistory(userId.toString())
    }

    override fun showProgress() {
        progressBar.show()
    }

    override fun hideProgress() {
        progressBar.hide()
    }

    override fun showMessage(message: String) {
        toast(message)
    }

    override fun showHistoryOrder(orderHistoryData: List<DataItem?>?) {
        rvOrderHistory.layoutManager = LinearLayoutManager(this)
        rvOrderHistory.adapter = AdapterOrderHistoryProducts(this, orderHistoryData)
    }
}
