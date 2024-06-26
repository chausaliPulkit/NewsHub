package com.example.newshub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newshub.databinding.FragmentSavedNewsBinding
import com.example.newshub.presentation.adapter.SavedNewsAdapter
import com.example.newshub.presentation.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar


class SavedNewsFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: FragmentSavedNewsBinding
    private lateinit var savedNewsAdapter: SavedNewsAdapter
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
        savedNewsAdapter = (activity as MainActivity).savedNewsAdapter
        savedNewsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_article", it)
            }
            findNavController().navigate(
                R.id.action_savedNewsFragment_to_newsInfoFragment, bundle
            )
        }
        initRecyclerView()
        viewModel.getSavedNewsArticle().observe(viewLifecycleOwner) {
            savedNewsAdapter.differ.submitList(it)
        }

        val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = savedNewsAdapter.differ.currentList[position]
                viewModel.deleteArticleFromDb(article)
                Snackbar.make(view, "Deleted Successfully", Snackbar.LENGTH_LONG)
                    .apply {
                        setAction("Undo") {
                            viewModel.saveArticleLocally(article)
                        }
                        show()
                    }
            }

        }
        ItemTouchHelper(itemTouchHelperCallBack).apply {
            attachToRecyclerView(binding.savedRecyclerView)
        }
    }


    private fun initRecyclerView() {
        binding.savedRecyclerView.apply {
            adapter = savedNewsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }

//    private fun setSearchView() {
//        binding.savedNewsSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(p0: String?): Boolean {
//                TODO("Implement it")
//            }
//
//            override fun onQueryTextChange(p0: String?): Boolean {
////                MainScope().launch {
//                TODO("Implement it")
////                }
//            }
//
//        })
//        binding.savedNewsSearchView.setOnCloseListener { TODO("Implement it") }
//    }

}