package com.khilman.ecommerceudacoding.adapters

import android.content.Context
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khilman.ecommerceudacoding.R
import com.khilman.ecommerceudacoding.activities.order_detail.OrderDetailActivity
import com.khilman.ecommerceudacoding.utils.MyConstants
import com.khilman.ecommerceudacoding.utils.MyHelpers
import kotlinx.android.synthetic.main.item_order_history.view.*
import org.jetbrains.anko.backgroundDrawable
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class AdapterOrderHistoryProducts (val context: Context): RecyclerView.Adapter<AdapterOrderHistoryProducts.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_order_history, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 0
    }
    override fun onBindViewHolder(holder: AdapterOrderHistoryProducts.ViewHolder, position: Int) {
        //todo: do something
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //todo: do something
    }
}