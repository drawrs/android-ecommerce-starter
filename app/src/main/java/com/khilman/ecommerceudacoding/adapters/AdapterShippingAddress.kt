package com.khilman.ecommerceudacoding.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khilman.ecommerceudacoding.R
import com.khilman.ecommerceudacoding.activities.edit_shipping_address.EditShippingAddressActivity
import com.khilman.ecommerceudacoding.utils.MyConstants
import kotlinx.android.synthetic.main.item_shipping_address.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class AdapterShippingAddress(val context: Context): RecyclerView.Adapter<AdapterShippingAddress.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterShippingAddress.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_shipping_address, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 0
    }

    override fun onBindViewHolder(holder: AdapterShippingAddress.ViewHolder, position: Int) {
        //todo: do something
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //todo: do something
    }
}

