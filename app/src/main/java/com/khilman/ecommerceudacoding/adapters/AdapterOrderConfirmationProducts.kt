package com.khilman.ecommerceudacoding.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khilman.ecommerceudacoding.R
import com.khilman.ecommerceudacoding.utils.MyHelpers
import com.khilman.www.formchecklistapp.network.InitRetrofit
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_order_list.view.*

class AdapterOrderConfirmationProducts(val context: Context): RecyclerView.Adapter<AdapterOrderConfirmationProducts.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_order_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 0
    }
    override fun onBindViewHolder(holder: AdapterOrderConfirmationProducts.ViewHolder, position: Int) {
        //todo: do something
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //todo: do something
    }
}