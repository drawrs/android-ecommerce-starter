package com.khilman.ecommerceudacoding.activities.edit_shipping_address

import com.khilman.ecommerceudacoding.network.model.edit_shipping_address_respone.Data

class EditShippingAddressPresenter(val view: EditShippingAddressView,
                                   val interactor: EditShippingAddressInteractor): EditShippingAddressInteractor.OnUpdateAddressFinished {

    fun updateShippingAdrress(shippingAddressId: String?,
                              addressTitle: String,
                              city: String,
                              province: String,
                              zipCode: String,
                              fullAddress: String,
                              mainAdrress: String) {
        //todo: validation
        when {
            addressTitle.isEmpty() -> this.onError("Address title can't be empty !")
            city.isEmpty() -> this.onError("City can't be empty !")
            province.isEmpty() -> this.onError("Province can't be empty !")
            zipCode.isEmpty() -> this.onError("Full address can't be empty !")
            else -> {
                //todo: update data
                interactor.saveNewShippingAddress(shippingAddressId,
                        addressTitle,
                        city,
                        province,
                        zipCode,
                        fullAddress,
                        mainAdrress,
                        this)
            }
        }

    }

    override fun onSuccess(addressData: Data?) {
        view?.apply {
            showMessage("DataCart successfully saved!")
            hideProgress()
        }
    }

    override fun onError(message: String) {
        view?.apply {
            showMessage(message)
            hideProgress()
        }
    }

}