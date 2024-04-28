package com.example.newshub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        binding.newsInfoWebView.apply {
            webViewClient = WebViewClient()
            if (selectedArticle.url != "") {
                loadUrl(selectedArticle.url)
            }
        }
    }
}