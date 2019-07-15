package com.tpm.retrobic.adapter.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tpm.retrobic.model.Post
import kotlinx.android.synthetic.main.posts.view.*

class PostViewHolder(var view : View,private val onItemClick :(post: Post)->Unit ): RecyclerView.ViewHolder(view) {

    fun setPost(post: Post)
    {
        view.apply {
            tvUserId.text = post.userId
            tvPostId.text = post.id
            tvTitle.text = post.title
            tvBody.text = post.body
        }
        view.setOnClickListener {
            onItemClick(post)
        }
    }
}