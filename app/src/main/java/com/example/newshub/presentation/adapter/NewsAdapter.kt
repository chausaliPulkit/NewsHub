package com.example.newshub.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newshub.data.model.Article
import com.example.newshub.databinding.NewsListItemBinding
import java.text.SimpleDateFormat

class NewsAdapter() : PagingDataAdapter<Article, NewsAdapter.NewsViewHolder>(callBack) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding =
            NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = getItem(position)
        if (article != null) {
            holder.bind(article)
        }
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }


    inner class NewsViewHolder(private val binding: NewsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            binding.apply {
                newsTitle.text = article.title
                newsDescription.text = article.description
                newsPublishedDate.text = formattedDate(article.publishedAt!!)
                newsSource.text = article.source!!.name


                Glide.with(newsImage.context)
                    .load(article.urlToImage)
                    .into(newsImage)


                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(article)
                    }
                }
            }
        }

        private fun formattedDate(time: String): String {
            // Parse the time string into a Date object
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val date = formatter.parse(time)

            // Format the Date object into the desired format
            return SimpleDateFormat("dd-MM-yy HH:mm aa").format(date)
        }
    }

    companion object {
        private val callBack = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

        }
    }
}


