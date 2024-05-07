package com.example.newshub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newshub.databinding.FragmentNewsBinding
import com.example.newshub.presentation.adapter.LoaderAdapter
import com.example.newshub.presentation.adapter.NewsAdapter
import com.example.newshub.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.launch


class NewsFragment : Fragment() {
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var binding: FragmentNewsBinding
    private val country = "in"
    private var page = 1
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        newsAdapter = (activity as MainActivity).newsAdapter
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_article", it)
            }
            findNavController().navigate(
                R.id.action_newsFragment_to_newsInfoFragment, bundle
            )
        }
        initRecyclerView()
        viewNewsListUtil()
    }

    private fun viewNewsListUtil() {
        lifecycleScope.launch {
            viewModel.newsHeadlines.collect {
                newsAdapter.submitData(it)
            }
        }
    }

    private fun setSearchView() {
        binding.newsSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
//                MainScope().launch {
                return false
//                }
            }

        })
        binding.newsSearchView.setOnCloseListener { TODO("Implement it") }
    }

    // on the basis of response Code it will show list
//    private fun viewNewsList() {
//        viewModel.getNewsHeadlines(country, page)
//        viewModel.newsHeadlines.observe(viewLifecycleOwner) { response ->
//            when (response) {
//                is Resource.Success -> {
//                    hideProgressBar()
//                    response.data?.let {
//                        newsAdapter.submitData(lifecycle, it.c)
//                        if (it.totalResults % 20 == 0) {
//                            pages = it.totalResults / 20
//                        } else {
//                            pages = it.totalResults / 20 + 1
//                        }
//                        isLastPage = page == pages
//                    }
//                }
//
//                is Resource.Error -> {
//                    hideProgressBar()
//                    response.message?.let {
//                        Toast.makeText(activity, "An error occurred : $it", Toast.LENGTH_LONG)
//                            .show()
//                    }
//                }
//
//                is Resource.Loading -> {
//                    showProgressBar()
//                }
//            }
//        }
//    }

    //    initialize recycler view
    private fun initRecyclerView() {
        binding.newsRecyclerView.apply {
            adapter = newsAdapter.withLoadStateHeaderAndFooter(
                header = LoaderAdapter { newsAdapter.retry() },
                footer = LoaderAdapter { newsAdapter.retry() }
            )
            layoutManager = LinearLayoutManager(activity)

        }

    }

    private fun showProgressBar() {
        isLoading = true
        binding.newsProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        binding.newsProgressBar.visibility = View.GONE
    }

}