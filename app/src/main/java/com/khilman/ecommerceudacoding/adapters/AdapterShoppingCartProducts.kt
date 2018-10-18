package com.khilman.ecommerceudacoding.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khilman.ecommerceudacoding.R
import com.khilman.ecommerceudacoding.activities.product_detail.ProductDetailActivity
import com.khilman.ecommerceudacoding.activities.shopping_cart.ShoppingCartPresenter
import com.khilman.ecommerceudacoding.utils.MyHelpers
import com.khilman.www.formchecklistapp.network.InitRetrofit
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_shopping_chart.view.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.yesButton

class AdapterShoppingCartProducts (val context: Context): RecyclerView.Adapter<AdapterShoppingCartProducts.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_shopping_chart, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 0
    }
    override fun onBindViewHolder(holder: AdapterShoppingCartProducts.ViewHolder, position: Int) {
        //todo: do something

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //todo: do something
    }


}