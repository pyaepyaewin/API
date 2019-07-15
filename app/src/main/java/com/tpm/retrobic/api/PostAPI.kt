package com.tpm.retrobic.api

import androidx.lifecycle.MutableLiveData
import com.tpm.retrobic.model.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PostAPI {
    @GET("/posts")
    fun getAllPosts(): Call<List<Post>>
    @GET("/posts/{id}/")
    fun getPosts(@Path("id") postId : String) : Call<Post>
}