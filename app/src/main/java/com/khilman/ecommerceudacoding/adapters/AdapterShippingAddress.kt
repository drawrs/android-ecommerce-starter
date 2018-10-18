package com.khilman.ecommerceudacoding.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khilman.ecommerceudacoding.R
import com.khilman.ecommerceudacoding.activities.edit_shipping_address.EditShippingAddressActivity
import com.khilman.ecommerceudacoding.network.model.profile_response.ShippingAddressItem
import com.khilman.ecommerceudacoding.utils.MyConstants
import kotlinx.android.synthetic.main.item_shipping_address.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class AdapterShippingAddress(val context: Context, val shippingAddresses: List<ShippingAddressItem?>?): RecyclerView.Adapter<AdapterShippingAddress.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterShippingAddress.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_shipping_address, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return shippingAddresses?.size!!
    }

    override fun onBindViewHolder(holder: AdapterShippingAddress.ViewHolder, position: Int) {
        val addressData = shippingAddresses?.get(position)
        holder.bind(addressData)

        holder.itemView.btnEditAddress.onClick {
            context.startActivity<EditShippingAddressActivity>(
                    MyConstants.ADDRESS_ID to addressData?.id.toString(),
                    MyConstants.ADDRESS_TITLE to addressData?.title,
                    MyConstants.ADDRESS_CITY to addressData?.city,
                    MyConstants.ADDRESS_PROVINCE to addressData?.province,
                    MyConstants.ADDRESS_ZIP_CODE to addressData?.zipCode.toString(),
                    MyConstants.ADDRESS_FULL_ADDRESS to addressData?.address,
                    MyConstants.ADDRESS_IS_MAIN_ADDRESS to addressData?.isMainAddress
            )
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(addressData: ShippingAddressItem?) {
            itemView.tvAddressTitle.text = addressData?.title
            itemView.tvCityAndRegion.text = "${addressData?.city}, ${addressData?.province}, ${addressData?.zipCode}"
            itemView.tvFullAddress.text = addressData?.address

            if (addressData?.isMainAddress.equals("1")){
                itemView.tvMainAddressNotice.visibility = View.VISIBLE
            } else {
                itemView.tvMainAddressNotice.visibility = View.GONE
            }
        }

    }

}

