package com.example.json_placeholder_app.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.json_placeholder_app.databinding.ActivityCommentsOfUserBinding
import com.example.json_placeholder_app.domain.entity.CommentEntity
import com.example.json_placeholder_app.presentation.ui.view.adapter.CommentListAdapter
import com.example.json_placeholder_app.presentation.ui.view.style.SpaceItemDecoration
import com.example.json_placeholder_app.presentation.viewmodel.CommentsOfUserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommentsOfUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCommentsOfUserBinding
    private val viewModel: CommentsOfUserViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsOfUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnExit.setOnClickListener {
            finish()
        }
        val postId = intent.getIntExtra("postId", 0)
        try{
            loading(true)
            viewModel.getCommentsByPostId(postId)
            viewModel.comments.observe(this) {
                setupRecycler(it)
                loading(false)
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error fetching comments", Toast.LENGTH_SHORT).show()
            loading(false)
            Log.d("CommentsOfUserActivity", "onCreate: $e")
        }
    }
    private fun setupRecycler(commentList: List<CommentEntity>) = binding.recyclerViewComments .apply {
        val commentListAdapter = CommentListAdapter(
            commentList
        )
        adapter = commentListAdapter
        layoutManager = LinearLayoutManager(context)
        addItemDecoration(SpaceItemDecoration(48))
    }

    private fun loading(loading: Boolean) {
        if (loading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}
