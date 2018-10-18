package com.khilman.ecommerceudacoding.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khilman.ecommerceudacoding.R
import com.khilman.ecommerceudacoding.activities.product_detail.ProductDetailActivity
import com.khilman.ecommerceudacoding.utils.MyConstants
import com.khilman.ecommerceudacoding.utils.MyHelpers
import com.khilman.www.formchecklistapp.network.InitRetrofit
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class AdapterHomeProducts(val context: Context): RecyclerView.Adapter<AdapterHomeProducts.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): AdapterHomeProducts.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 0
    }

    override fun onBindViewHolder(holder: AdapterHomeProducts.ViewHolder, position: Int) {

    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)  {

    }
}