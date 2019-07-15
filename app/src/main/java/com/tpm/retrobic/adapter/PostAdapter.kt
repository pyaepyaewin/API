package com.tpm.retrobic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tpm.retrobic.R
import com.tpm.retrobic.adapter.viewholders.PostViewHolder
import com.tpm.retrobic.model.Post

class PostAdapter(var list: List<Post>,private val onItemClick: (post : Post) -> Unit): RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.posts,parent,false)
        return PostViewHolder(v,onItemClick)
    }

    override fun getItemCount(): Int {
        return list!!.count()
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.setPost(list[position])
    }
}