package com.khilman.ecommerceudacoding.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khilman.ecommerceudacoding.R
import com.khilman.ecommerceudacoding.activities.product_by_category.ProductByCategoryActivity
import com.khilman.www.formchecklistapp.network.InitRetrofit
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_category.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class AdapterHomeCategories(val context: Context): RecyclerView.Adapter<AdapterHomeCategories.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): AdapterHomeCategories.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 0
    }

    override fun onBindViewHolder(holder: AdapterHomeCategories.ViewHolder, position: Int) {
        //todo: do something
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //todo: do something
    }
}