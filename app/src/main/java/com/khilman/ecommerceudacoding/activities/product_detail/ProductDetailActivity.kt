package com.khilman.ecommerceudacoding.activities.product_detail

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.daimajia.slider.library.SliderTypes.TextSliderView
import com.khilman.ecommerceudacoding.R
import com.khilman.ecommerceudacoding.activities.shopping_cart.ShoppingCartActivity
import com.khilman.ecommerceudacoding.network.model.product_detail_response.Data
import com.khilman.ecommerceudacoding.utils.*
import com.khilman.www.formchecklistapp.network.InitRetrofit
import kotlinx.android.synthetic.main.activity_product_detail.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ProductDetailActivity : AppCompatActivity(), BaseSliderView.OnSliderClickListener, ProductDetailView {


    private lateinit var presenter: ProductDetailPresenter
    private lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        //todo: hide the container view
        containerView.visibility = View.INVISIBLE
        presenter = ProductDetailPresenter(this@ProductDetailActivity, this, ProductDetailInteractor())
        pref = SessionManager(this).getInstance()

        //todo: get product detail
        val productId = intent.getStringExtra(MyConstants.PRODUCT_ID)
        presenter.getProduct(productId)
    }
    override fun onSliderClick(slider: BaseSliderView?) {

    }

    override fun showMessage(message: String) {
        toast(message)
    }

    override fun showData(productData: Data?) {
        //todo: set actionbar title
        supportActionBar?.title = productData?.title

        //todo: set data into widget
        tvProductTitle.text = productData?.title
        tvPriceProduct.text = "${MyHelpers().rupiahFormat(productData?.price!!.toLong())}"
        rbRatingProduct.rating = productData?.rating!!.toFloat()
        ratingCaptions.text = "${productData.rating} Ratings"
        tvTitleCategory.text = "Category : ${productData.category?.title}"
        tvQtyProduct.text = "Qty : ${productData.qty} item"
        wvProductDescription.loadData(productData?.description, "text/html; charset=utf-8", "UTF-8")

        //todo: show slider
        productData.productPhotos?.forEachIndexed { position, photos ->
            //todo: add to slider
            val textSliderView = TextSliderView(this)
            // initialize a SliderLayout
            textSliderView
                    .image("${InitRetrofit.API_BASE_IMAGES}${photos?.fileName}")
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                    .setOnSliderClickListener(this)

            //add your extra information
            textSliderView.bundle(Bundle())
            textSliderView.bundle
                    .putString("extra", productData?.title)

            sliderProductPhotos.addSlider(textSliderView)
        }

        //todo: handle event
        btnAddToCart.onClick {
            presenter.addToShoppingCart(pref.getString(MyConstants.PREF_USER_ID, "0").toString(), productData.id.toString(), "1")
        }
    }

    override fun hideProgress() {
        progressBar.hide()
    }

    override fun showProgress() {
        progressBar.show()
    }

    override fun navigateToShoppingCart() {
        startActivity<ShoppingCartActivity>()
    }
}
