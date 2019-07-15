package com.tpm.retrobic.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.tpm.retrobic.R
import com.tpm.retrobic.api.PostAPI
import com.tpm.retrobic.api.RestAdapter
import com.tpm.retrobic.model.Post
import kotlinx.android.synthetic.main.activity_post_details.*
import retrofit2.Call
import retrofit2.Response

class PostDetailsActivity : AppCompatActivity() {
    lateinit var post : Post

    var pp = mutableListOf<Post>()
    lateinit var postId : String

    companion object
    {
        const val POST_ID = "postID"
        fun newIntent(context: Context,postId : String): Intent
        {
            val intent = Intent(context,PostDetailsActivity::class.java)
            intent.putExtra(POST_ID,postId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_post_details)
        postId = intent.getStringExtra(POST_ID)
        loadPostDetails()
    }
    fun loadPostDetails()
    {

        val apiCallPostDetail = RestAdapter.getClient().create(PostAPI::class.java)
        val postCall = apiCallPostDetail.getPosts(postId)
        postCall.enqueue(object :retrofit2.Callback<Post>
        {
            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.d("onFailure","unsuccess")
            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if(response.isSuccessful)
                {
                    post = response.body()!!

                    if(post != null) {
                        subtvPostId.text = post.id
                        subtvUserId.text = post.userId
                        subtvTitle.text = post.title
                        subtvBody.text = post.body
                        pp.add(post)

                        Log.d("postId",post.toString())
                    }
                }
                else
                    Log.d("response","fail")
            }

        })
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home)
        {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
