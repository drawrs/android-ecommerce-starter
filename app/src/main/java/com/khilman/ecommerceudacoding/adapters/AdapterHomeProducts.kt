package com.khilman.ecommerceudacoding.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khilman.ecommerceudacoding.R
import com.khilman.ecommerceudacoding.activities.product_detail.ProductDetailActivity
import com.khilman.ecommerceudacoding.network.model.home_products_response.DataItemProduct
import com.khilman.ecommerceudacoding.utils.MyConstants
import com.khilman.ecommerceudacoding.utils.MyHelpers
import com.khilman.www.formchecklistapp.network.InitRetrofit
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class AdapterHomeProducts(val context: Context, val homeProducts: List<DataItemProduct?>?): RecyclerView.Adapter<AdapterHomeProducts.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): AdapterHomeProducts.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return homeProducts?.size!!
    }

    override fun onBindViewHolder(holder: AdapterHomeProducts.ViewHolder, position: Int) {
        val product = homeProducts?.get(position)
        holder.bind(product)

        holder.itemView.onClick {
            context.startActivity<ProductDetailActivity>(MyConstants.PRODUCT_ID to product?.id.toString())
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)  {
        fun bind(product: DataItemProduct?) {
            itemView.tvProductTitle.text = product?.title
            itemView.tvProductPrice.text = "${MyHelpers().rupiahFormat(product?.price!!.toLong())}"

            Picasso.with(context)
                    .load("${InitRetrofit.API_BASE_IMAGES}${product?.productCover}")
                    .into(itemView.ivProductCover)
        }
    }
}