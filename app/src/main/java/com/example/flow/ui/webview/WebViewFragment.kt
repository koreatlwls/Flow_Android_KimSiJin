package com.example.flow.ui.webview

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.flow.R
import com.example.flow.databinding.FragmentWebViewBinding
import com.example.flow.ui.BaseFragment

class WebViewFragment : BaseFragment<FragmentWebViewBinding>(R.layout.fragment_web_view) {

    private val args: WebViewFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initWebView()
    }

    override fun initBinding() {
        binding.fragment = this
    }

    private fun initWebView() {
        binding.webView.loadUrl(args.movieUrl)
        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)

                binding.circularProgressIndicator.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                binding.circularProgressIndicator.visibility = View.GONE
            }

            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                view.loadUrl(request.url.toString())
                return true
            }
        }


        binding.webView.settings.apply {
            loadWithOverviewMode = true
            useWideViewPort = true
            setSupportZoom(false)
            javaScriptEnabled = true
        }
    }

    fun moveBackFromWebView() {
        binding.root.findNavController().popBackStack()
    }

}