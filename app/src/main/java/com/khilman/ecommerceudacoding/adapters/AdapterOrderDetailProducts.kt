package com.khilman.ecommerceudacoding.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khilman.ecommerceudacoding.R
import com.khilman.ecommerceudacoding.network.model.detail_order_response.ProductsItem
import com.khilman.ecommerceudacoding.utils.MyHelpers
import com.khilman.www.formchecklistapp.network.InitRetrofit
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_order_list.view.*

class AdapterOrderDetailProducts (val context: Context,
                                  val orderedProducts: List<ProductsItem?>?): RecyclerView.Adapter<AdapterOrderDetailProducts.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_order_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return orderedProducts?.size!!
    }
    override fun onBindViewHolder(holder: AdapterOrderDetailProducts.ViewHolder, position: Int) {
        val product = orderedProducts?.get(position)
        holder.bindData(product)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(product: ProductsItem?) {
            itemView.tvProductPrice.text = product?.title
            itemView.tvProductPrice.text = "${MyHelpers().rupiahFormat(product?.price!!.toLong())}"
            itemView.etQty.text = "${product?.qtyOrder.toString()} item"

            Picasso.with(itemView.context)
                    .load("${InitRetrofit.API_BASE_IMAGES}${product?.cover}")
                    .into(itemView.ivProductCover)
        }
    }
}