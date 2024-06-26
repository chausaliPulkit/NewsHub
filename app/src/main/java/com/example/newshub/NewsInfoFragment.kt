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
import com.example.newshub.presentation.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar


class NewsInfoFragment : Fragment() {
    private lateinit var binding: FragmentNewsInfoBinding
    private lateinit var viewModel: NewsViewModel

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
        viewModel = (activity as MainActivity).viewModel
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
                selectedArticle.url?.let { loadUrl(it) }
            }
        }
//    setting click listener on floating save_news button to save news to room database
        binding.fragmentInfoSaveNewsButton.setOnClickListener {
            viewModel.saveArticleLocally(selectedArticle)
            Snackbar.make(
                view,
                "Saved Successfully!",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

}