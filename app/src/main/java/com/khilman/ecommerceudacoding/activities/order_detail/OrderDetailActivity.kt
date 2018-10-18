package com.khilman.ecommerceudacoding.activities.order_detail

import android.content.SharedPreferences
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.khilman.ecommerceudacoding.R
import com.khilman.ecommerceudacoding.activities.preview_document.PreviewDocumentActivity
import com.khilman.ecommerceudacoding.adapters.AdapterOrderDetailProducts
import com.khilman.ecommerceudacoding.network.model.detail_order_response.Data
import com.khilman.ecommerceudacoding.utils.MyConstants
import com.khilman.ecommerceudacoding.utils.MyHelpers
import com.khilman.www.formchecklistapp.network.InitRetrofit
import com.squareup.picasso.Picasso
import com.vansuita.pickimage.bean.PickResult
import com.vansuita.pickimage.bundle.PickSetup
import com.vansuita.pickimage.dialog.PickImageDialog
import com.vansuita.pickimage.listeners.IPickResult
import kotlinx.android.synthetic.main.activity_order_detail.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.io.File

class OrderDetailActivity : AppCompatActivity(), OrderDetailView, IPickResult {

    private lateinit var pref: SharedPreferences
    private lateinit var presenter: OrderDetailPresenter
    private var userId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)
        supportActionBar?.title = "Detail Order"

        //todo: init component
        pref = this.getSharedPreferences(MyConstants.PREF_NAME, 0)
        presenter = OrderDetailPresenter(this, OrderDetailInteractor())
        userId = pref.getString(MyConstants.PREF_USER_ID, null)

        presenter.getOrder(intent.getStringExtra("ORDER_ID"))

        //todo: event
        btnSelectFile.onClick {
            actionSelectOrTakeFile()
        }

    }

    private fun actionSelectOrTakeFile() {
        PickImageDialog.build(PickSetup()).show(this@OrderDetailActivity)
    }


    override fun showProgress() {

    }

    override fun hideProgress() {
    }

    override fun showMessage(message: String) {
        toast(message)
    }

    override fun showOrder(oderData: Data?) {
        oderData
        rvOrderedProduct.layoutManager = LinearLayoutManager(this)
        rvOrderedProduct.adapter = AdapterOrderDetailProducts(this, oderData?.products)

        tvOrderStatus.text = oderData?.orderStatus
        tvTotalPrice.text = "Total Price : ${MyHelpers().rupiahFormat(oderData?.totalPrice!!.toLong())}"
        tvShippingAddress.text = oderData.shippingAddress

        if (oderData?.paymentProof != null){
            val documenUrl = "${InitRetrofit.API_BASE_DOCUMENT}${oderData?.paymentProof}"
            this.showPaymentProofPreview(documenUrl)
        }
    }

    // Untuk menyimpan foto
    private var uri: Uri? = null
    var filePath: String? = null
    private var file: File? = null
    lateinit var fileName: String

    override fun onPickResult(result: PickResult?) {
        // photo
        uri = result?.getUri()
        Log.d("logUriContent", uri.toString())
        filePath = getRealPathFromURIPath(uri)
        // TODO: Create File
        file = File(filePath)
        // TODO: Get File Name
        fileName = filePath?.substring(filePath?.lastIndexOf("/")!! +1).toString()
        //fileName?.let { tvDocumentName.text = it }

        presenter.updatePaymentProof(file, fileName, intent.getStringExtra(MyConstants.ORDER_ID))
    }

    private fun getRealPathFromURIPath(contentURI: Uri?): String? {
        var cursor = this.contentResolver.query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI?.getPath()
        } else {
            cursor.moveToFirst()
            var idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            return cursor.getString(idx)
        }
    }

    override fun showPaymentProofPreview(documenUrl: String?) {
        Picasso.with(this)
                .load(documenUrl)
                .into(ivPaymentProofPreview)

        ivPaymentProofPreview.visibility = View.VISIBLE
        btnSelectFile.visibility = View.GONE

        //todo: event
        ivPaymentProofPreview.onClick {
            startActivity<PreviewDocumentActivity>("DOCUMENT_URL" to documenUrl)
        }
    }
}
