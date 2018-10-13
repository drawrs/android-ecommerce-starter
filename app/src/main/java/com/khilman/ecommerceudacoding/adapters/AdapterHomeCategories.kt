package com.khilman.ecommerceudacoding.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khilman.ecommerceudacoding.R
import com.khilman.ecommerceudacoding.activities.product_by_category.ProductByCategoryActivity
import com.khilman.ecommerceudacoding.network.model.home_categories_response.DataItemCategory
import com.khilman.www.formchecklistapp.network.InitRetrofit
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_category.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class AdapterHomeCategories(val context: Context, val promotionCategories: List<DataItemCategory?>?): RecyclerView.Adapter<AdapterHomeCategories.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): AdapterHomeCategories.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return promotionCategories?.size!!
    }

    override fun onBindViewHolder(holder: AdapterHomeCategories.ViewHolder, position: Int) {
        val category = promotionCategories?.get(position)
        holder.bind(category)

        holder.itemView.onClick {
            context.startActivity<ProductByCategoryActivity>("CATEGORY_ID" to category?.id.toString(),
                    "CATEGORY_TITLE" to category?.title)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(category: DataItemCategory?) {
            itemView.tvTitleCategory.text = category?.title
            itemView.tvQtyProduct.text = "10 Product"

            Picasso.with(context)
                    .load("${InitRetrofit.API_BASE_IMAGES}${category?.cover}")
                    .into(itemView.ivProductCover)
        }

    }
}