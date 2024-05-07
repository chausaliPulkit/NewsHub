package com.example.newshub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newshub.databinding.FragmentSavedNewsBinding
import com.example.newshub.presentation.viewmodel.NewsViewModel


class SavedNewsFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: FragmentSavedNewsBinding
    private val country = "in"
    private var page = 1
    private var isLoading = false
    private var pages = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedNewsBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
    }


    private fun initRecyclerView() {
        binding.savedRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }

    }

    private fun setSearchView() {
        binding.savedNewsSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                TODO("Implement it")
            }

            override fun onQueryTextChange(p0: String?): Boolean {
//                MainScope().launch {
                TODO("Implement it")
//                }
            }

        })
        binding.savedNewsSearchView.setOnCloseListener { TODO("Implement it") }
    }

}