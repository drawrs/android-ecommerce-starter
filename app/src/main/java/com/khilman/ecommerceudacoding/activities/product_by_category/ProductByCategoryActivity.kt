package com.khilman.ecommerceudacoding.activities.product_by_category

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.khilman.ecommerceudacoding.R
import com.khilman.ecommerceudacoding.adapters.AdapterHomeProducts
import com.khilman.ecommerceudacoding.network.model.home_products_response.DataItemProduct
import com.khilman.ecommerceudacoding.utils.MyConstants
import kotlinx.android.synthetic.main.activity_product_by_category.*

class ProductByCategoryActivity : AppCompatActivity(), ProductByCategoryView {

    private lateinit var presenter: ProductByCategoryPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_by_category)
        supportActionBar?.title = intent.getStringExtra(MyConstants.CATEGORY_TITLE)
        presenter = ProductByCategoryPresenter(this, ProductByCategoryInteractor())

        val selectedCategoryId = intent.getStringExtra(MyConstants.CATEGORY_ID)
        presenter.getProductsByCategory(selectedCategoryId)

        this.showProgress()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hidProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showMessage(message: String) {
        to(message)
    }

    override fun showDataProucts(data: List<DataItemProduct?>) {
        if (data.size <= 0){
            tvEmptyNotice.visibility = View.VISIBLE
        } else {
            tvEmptyNotice.visibility = View.GONE
        }
        rvListProducts.layoutManager = LinearLayoutManager(this)
        rvListProducts.adapter = AdapterHomeProducts(this, data)
    }
}
