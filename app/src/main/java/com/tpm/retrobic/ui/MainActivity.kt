package com.tpm.retrobic.ui

import android.app.Person
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.tpm.retrobic.R
import com.tpm.retrobic.adapter.PostAdapter
import com.tpm.retrobic.api.PostAPI
import com.tpm.retrobic.api.RestAdapter
import com.tpm.retrobic.model.Post
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var posts : List<Post>
    lateinit var pList: List<Post>

    private val postAdapter : PostAdapter by lazy { PostAdapter(pList,this::onItemClick) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadPost()
    }

    private fun onItemClick(post : Post)
    {
        val intent = PostDetailsActivity.newIntent(this,post.id)
        startActivity(intent)
    }

    fun loadPost()
    {
        val apiCall = RestAdapter.getClient().create(PostAPI::class.java)
        val postCall = apiCall.getAllPosts()
        postCall.enqueue(object: Callback<List<Post>>
        {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if(response.isSuccessful)
                {
                    val postList = response.body()
                    if(postList!=null)
                    {
                        pList = postList
                        Log.d("pList","exit")
                        rcPost.apply {
                            adapter = postAdapter
                            layoutManager = LinearLayoutManager(this@MainActivity)
                        }
                    }
                    else Log.d("pList","null")

                }
                else Log.d("response","Unsuccessful")
            }

        })
    }
}
