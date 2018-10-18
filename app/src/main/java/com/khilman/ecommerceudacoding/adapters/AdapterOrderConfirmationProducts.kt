package com.khilman.ecommerceudacoding.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khilman.ecommerceudacoding.R
import com.khilman.ecommerceudacoding.network.model.shopping_cart_response.DataItem
import com.khilman.ecommerceudacoding.utils.MyHelpers
import com.khilman.www.formchecklistapp.network.InitRetrofit
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_order_list.view.*

class AdapterOrderConfirmationProducts(val context: Context,
                                        val shoppingCartData: List<DataItem?>?): RecyclerView.Adapter<AdapterOrderConfirmationProducts.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_order_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return shoppingCartData?.size!!
    }
    override fun onBindViewHolder(holder: AdapterOrderConfirmationProducts.ViewHolder, position: Int) {
        val cartData = shoppingCartData?.get(position)
        holder.bindData(cartData)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(cartData: DataItem?) {
            itemView.tvProductPrice.text = cartData?.product?.title
            itemView.tvProductPrice.text = "${MyHelpers().rupiahFormat(cartData?.product?.price!!.toLong())}"
            itemView.etQty.text = "${cartData?.qty.toString()} item"

            Picasso.with(itemView.context)
                    .load("${InitRetrofit.API_BASE_IMAGES}${cartData?.product?.productCover}")
                    .into(itemView.ivProductCover)
        }
    }
}