package com.khilman.ecommerceudacoding.adapters

import android.content.Context
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khilman.ecommerceudacoding.R
import com.khilman.ecommerceudacoding.activities.order_detail.OrderDetailActivity
import com.khilman.ecommerceudacoding.network.model.order_history_response.DataItem
import com.khilman.ecommerceudacoding.utils.MyConstants
import com.khilman.ecommerceudacoding.utils.MyHelpers
import kotlinx.android.synthetic.main.item_order_history.view.*
import org.jetbrains.anko.backgroundDrawable
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class AdapterOrderHistoryProducts (val context: Context,
                                   val orderHistory: List<DataItem?>?): RecyclerView.Adapter<AdapterOrderHistoryProducts.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_order_history, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return orderHistory?.size!!
    }
    override fun onBindViewHolder(holder: AdapterOrderHistoryProducts.ViewHolder, position: Int) {
        val order = orderHistory?.get(position)
        holder.bindData(order)

        holder.itemView.onClick {
            context.startActivity<OrderDetailActivity>(MyConstants.ORDER_ID to order?.id.toString())
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(order: DataItem?) {
            itemView.tvOrderTitle.text = "${order?.orderCode} - ${MyHelpers().dateFormat(order?.createdAt)}"
            itemView.labelStatusOrder.text = "${order?.orderStatus}"

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val bgUnSuccess = itemView.context.getDrawable(R.drawable.label_order_unsuccess)
                val bgSuccess = itemView.context.getDrawable(R.drawable.label_order_success)

                when (order?.orderStatus){
                    "UNPAID" -> itemView.labelStatusOrder.backgroundDrawable = bgUnSuccess
                    "PENDING_CONFIRMATION" -> itemView.labelStatusOrder.backgroundDrawable = bgUnSuccess
                    "PAID" -> itemView.labelStatusOrder.backgroundDrawable = bgSuccess
                    "SHIPPING" -> itemView.labelStatusOrder.backgroundDrawable = bgSuccess
                    "COMPLETED" -> itemView.labelStatusOrder.backgroundDrawable = bgSuccess
                    else -> itemView.labelStatusOrder.backgroundDrawable = bgUnSuccess
                }
            }
        }
    }
}