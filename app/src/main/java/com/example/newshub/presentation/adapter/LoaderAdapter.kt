package com.example.newshub.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newshub.databinding.LoadStateFooterViewItemBinding

class LoaderAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoaderAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = LoadStateFooterViewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LoadStateViewHolder(binding, retry)
    }

    inner class LoadStateViewHolder(
        private val binding: LoadStateFooterViewItemBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.errorMsg.text = loadState.error.localizedMessage
                if (loadState.endOfPaginationReached) {
                    binding.errorMsg.text =
                        "You've conquered the newsfeed! Time for a cat video break?"
                }
            }
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.retryButton.isVisible = loadState is LoadState.Error
            binding.errorMsg.isVisible = loadState is LoadState.Error
        }

//        companion object {
//            fun create(parent: ViewGroup, retry: () -> Unit): LoadStateViewHolder {
//                val view = LayoutInflater.from(parent.context)
//                    .inflate(R.layout.repos_load_state_footer_view_item, parent, false)
//                val binding = ReposLoadStateFooterViewItemBinding.bind(view)
//                return ReposLoadStateViewHolder(binding, retry)
//            }
//        }
    }


}