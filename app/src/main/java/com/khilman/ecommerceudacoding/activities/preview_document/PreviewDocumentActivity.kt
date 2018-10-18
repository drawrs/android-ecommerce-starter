package com.khilman.ecommerceudacoding.activities.preview_document

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.github.piasy.biv.BigImageViewer
import com.github.piasy.biv.loader.glide.GlideImageLoader
import com.khilman.ecommerceudacoding.R
import kotlinx.android.synthetic.main.activity_preview_document.*

class PreviewDocumentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BigImageViewer.initialize(GlideImageLoader.with(this)) // Init first before Layout
        setContentView(R.layout.activity_preview_document)
        supportActionBar?.title = "Document Preview"
        val preview_url = intent.getStringExtra("DOCUMENT_URL")
        Log.d("previw url", preview_url)
        ivPreviewDocument.showImage(
                Uri.parse(preview_url)
        )
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.getItemId()) {
            android.R.id.home -> {
                finish()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}
