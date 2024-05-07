package com.example.newshub

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.newshub.databinding.FragmentNewsInfoBinding


class NewsInfoFragment : Fragment() {
    private lateinit var binding: FragmentNewsInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentNewsInfoBinding.bind(view)
        val args: NewsInfoFragmentArgs by navArgs()
        val selectedArticle = args.selectedArticle
        val webClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                // Show a progress bar while loading
                binding.progressBar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                // Hide the progress bar after loading
                binding.progressBar.visibility = View.GONE

            }
        }
        binding.newsInfoWebView.apply {
//            settings.javaScriptEnabled = true
            webViewClient = webClient


            if (selectedArticle.url != "") {
                loadUrl(selectedArticle.url)
            }
        }
    }
}