package com.githubusers.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.githubusers.app.R
import com.githubusers.app.data.UserListItem

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    inner class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<UserListItem>(){
        override fun areItemsTheSame(oldItem: UserListItem, newItem: UserListItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserListItem, newItem: UserListItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.user_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val user = differ.currentList[position]

        holder.itemView.apply {
            Glide.with(this).load(user.avatar_url).into(holder.itemView.findViewById(R.id.avatar))
            holder.itemView.findViewById<TextView>(R.id.name).text = user.login
            holder.itemView.findViewById<TextView>(R.id.url).text = user.html_url
            setOnItemClickListener {
                onItemClickListener?.let { it(user) }
            }
        }
    }

    private var onItemClickListener: ((UserListItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (UserListItem) -> Unit) {
        onItemClickListener = listener
    }
}