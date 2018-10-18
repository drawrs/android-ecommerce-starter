package com.khilman.ecommerceudacoding.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khilman.ecommerceudacoding.R
import com.khilman.ecommerceudacoding.activities.product_detail.ProductDetailActivity
import com.khilman.ecommerceudacoding.activities.shopping_cart.ShoppingCartPresenter
import com.khilman.ecommerceudacoding.network.model.shopping_cart_response.DataItem
import com.khilman.ecommerceudacoding.utils.MyHelpers
import com.khilman.www.formchecklistapp.network.InitRetrofit
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_shopping_chart.view.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.yesButton

class AdapterShoppingCartProducts (val context: Context,
                                   val shoppingCartData: List<DataItem?>?,
                                   val presenter: ShoppingCartPresenter): RecyclerView.Adapter<AdapterShoppingCartProducts.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_shopping_chart, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return shoppingCartData?.size!!
    }
    override fun onBindViewHolder(holder: AdapterShoppingCartProducts.ViewHolder, position: Int) {
        val cartData = shoppingCartData?.get(position)
        holder.bindData(cartData)

        var currentQty = holder.itemView.etQty.text.toString().toInt()

        holder.itemView.btnDecrease.onClick {
            if (currentQty > 1){
                currentQty -= 1
            }
            //todo: set new qty to editext
            holder.itemView.etQty.setText(currentQty.toString())
            presenter.updateShippingQtyValue(cartData?.id, currentQty)

        }

        holder.itemView.btnIncrease.onClick {
            if (currentQty <= 20){
                currentQty += 1
            }
            //todo: set new qty to editext
            holder.itemView.etQty.setText(currentQty.toString())
            presenter.updateShippingQtyValue(cartData?.id, currentQty)
        }

        holder.itemView.onClick {
            context.startActivity<ProductDetailActivity>("PRODUCT_ID" to cartData?.product?.id.toString())
        }

        holder.itemView.btnDeleteItem.onClick {
            context.alert {
                title = "Confirmation"
                message = "Delete item ?"
                yesButton {
                    presenter.removeItemFromCart(cartData?.id)
                }
                noButton {  }
            }.show()
        }


    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(cartData: DataItem?) {
            itemView.tvProductTitle.text = cartData?.product?.title
            itemView.tvProductPrice.text = "${MyHelpers().rupiahFormat(cartData?.product?.price!!.toLong())}"
            itemView.etQty.setText(cartData?.qty.toString())

            Picasso.with(itemView.context)
                    .load("${InitRetrofit.API_BASE_IMAGES}${cartData?.product?.productCover}")
                    .into(itemView.ivProductCover)
        }
    }


}