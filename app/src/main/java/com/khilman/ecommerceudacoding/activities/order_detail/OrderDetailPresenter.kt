package com.khilman.ecommerceudacoding.activities.order_detail

import com.khilman.ecommerceudacoding.network.model.detail_order_response.Data
import com.khilman.www.formchecklistapp.network.InitRetrofit
import java.io.File

class OrderDetailPresenter (val view: OrderDetailView, val interactor: OrderDetailInteractor) :
        OrderDetailInteractor.OnGetDetailsFinished, OrderDetailInteractor.OnUploadDocumentFinished {

    fun getOrder(orderId: String){
        interactor.getOrderDetail(orderId, this)
    }
    fun updatePaymentProof(file: File?, fileName: String, orderId: String){
        interactor.updatePaymentProof(file, fileName, orderId, this)
    }

    override fun onSuccess(oderData: Data?) {
        view?.apply {
            showOrder(oderData)
        }
    }

    override fun onError(message: String) {
        view?.apply {
            showMessage(message)
        }
    }

    override fun onUploadSuccess(oderData: com.khilman.ecommerceudacoding.network.model.upload_paymentproof_response.Data?) {
        val documenUrl = "${InitRetrofit.API_BASE_DOCUMENT}${oderData?.paymentProof}"
        view?.apply {
            showMessage("sukses")
            showPaymentProofPreview(documenUrl)
        }
    }

    override fun onUploadError(message: String) {
        view?.apply {
            showMessage(message)
        }
    }
}