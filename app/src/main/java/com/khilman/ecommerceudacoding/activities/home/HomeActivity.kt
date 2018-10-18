package com.khilman.ecommerceudacoding.activities.home

import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.daimajia.slider.library.SliderTypes.TextSliderView
import com.daimajia.slider.library.Tricks.ViewPagerEx
import com.khilman.ecommerceudacoding.R
import com.khilman.ecommerceudacoding.activities.login.LoginActivity
import com.khilman.ecommerceudacoding.activities.order_history.OrderHistoryActivity
import com.khilman.ecommerceudacoding.activities.product_detail.ProductDetailActivity
import com.khilman.ecommerceudacoding.activities.profile.ProfileActivity
import com.khilman.ecommerceudacoding.activities.shopping_cart.ShoppingCartActivity
import com.khilman.ecommerceudacoding.adapters.AdapterHomeCategories
import com.khilman.ecommerceudacoding.adapters.AdapterHomeProducts
import com.khilman.ecommerceudacoding.network.model.home_categories_response.DataItemCategory
import com.khilman.ecommerceudacoding.network.model.home_products_response.DataItemProduct
import com.khilman.ecommerceudacoding.network.model.home_promotions_response.DataItemPromotion
import com.khilman.ecommerceudacoding.utils.MyConstants
import com.khilman.ecommerceudacoding.utils.SessionManager
import com.khilman.ecommerceudacoding.utils.hide
import com.khilman.ecommerceudacoding.utils.show
import com.khilman.www.formchecklistapp.network.InitRetrofit
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.onRefresh

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener, HomeView {


    private lateinit var presenter: HomePresenter
    private lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initComponent()
        initEvent()
    }

    private fun initComponent() {
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        pref = SessionManager(this).getInstance()
        presenter = HomePresenter(this, HomeInteractor())
        presenter.getHomeData()
    }

    private fun initEvent() {
        swipeRefreshLayout.onRefresh {
            presenter.getHomeData()
        }
        etSearchBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val keyword = s.toString()
                //todo: get home product with keyword
                presenter.getHomeProducts(keyword)
                //todo: show hide other section
                if (keyword.length > 0) {
                    sectionSlider.hide()
                    sectionCategories.hide()
                } else {
                    sectionSlider.show()
                    sectionCategories.show()
                    //presenter.getHomeProducts(keyword)
                }
            }
        })
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        when (item.itemId) {
            R.id.action_shoping_cart -> {
                navigateToShoppingCart()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                // Handle
            }
            R.id.nav_logout -> {
                presenter.userLogout(pref)
            }
            R.id.nav_profile -> {
                navigateToProfile()
            }
            R.id.nav_order -> {
                navigateToMyOrder()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun showMessage(message: String) {
        toast(message)
    }

    override fun showProgress() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideProgress() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showPromotions(promotionProducts: List<DataItemPromotion?>?) {
        promotionProducts?.forEachIndexed { position, product ->
            //todo: add to slider
            val textSliderView = TextSliderView(this)
            // initialize a SliderLayout
            textSliderView
                    .description(product?.title)
                    .image("${InitRetrofit.API_BASE_IMAGES}${product?.productCover}")
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                    .setOnSliderClickListener(this)

            //add your extra information
            textSliderView.bundle(Bundle())
            textSliderView.bundle
                    .putString(MyConstants.PRODUCT_ID, product?.id.toString())

            slider.addSlider(textSliderView)
        }
    }

    override fun showCategories(promotionCategories: List<DataItemCategory?>?) {
        val adapter = AdapterHomeCategories(this, promotionCategories)
        val layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        recyclerViewListCategory.adapter = adapter
        recyclerViewListCategory.layoutManager = layoutManager as RecyclerView.LayoutManager?
        recyclerViewListCategory.isNestedScrollingEnabled = true
    }

    override fun showProducts(homeProducts: List<DataItemProduct?>?) {
        val adapter = AdapterHomeProducts(this, homeProducts)
        val layoutManager = GridLayoutManager(this, 3)
        recyclerViewListTank.adapter = adapter
        recyclerViewListTank.layoutManager = layoutManager
        recyclerViewListTank.isNestedScrollingEnabled = true
    }

    override fun showEmptyNotice() {
        tvEmptyProductsNotice.show()
    }
    override fun hideEmptyNotice() {
        tvEmptyProductsNotice.hide()
    }
    override fun navigateToLogin() {
        startActivity(intentFor<LoginActivity>().newTask().clearTask())
    }
    override fun onSliderClick(slider: BaseSliderView?) {
        //todo: intent to detail product
        startActivity<ProductDetailActivity>(MyConstants.PRODUCT_ID to slider?.bundle?.getString(MyConstants.PRODUCT_ID))
    }

    override fun onPageScrollStateChanged(state: Int) {
        //todo: you can do something
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        //todo: you can do something
    }

    override fun onPageSelected(position: Int) {
        //todo: you can do something
    }

    private fun navigateToShoppingCart() {
        startActivity<ShoppingCartActivity>()
    }

    private fun navigateToMyOrder() {
        startActivity<OrderHistoryActivity>()
    }

    private fun navigateToProfile() {
        startActivity<ProfileActivity>()
    }
}
