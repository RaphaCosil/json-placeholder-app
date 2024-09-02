package com.example.retrofittest.presentation.ui.activity

import android.content.ContentValues.TAG
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittest.R
import com.example.retrofittest.data.model.PostData
import com.example.retrofittest.databinding.ActivityMainBinding
import com.example.retrofittest.presentation.ui.view.RecycleAdapter
import com.example.retrofittest.presentation.ui.view.SpaceItemDecoration
import com.example.retrofittest.presentation.viewmodel.PostsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.HttpException
import java.io.IOException

class HomeActivity : AppCompatActivity() {

    private lateinit var recycleAdapter: RecycleAdapter
    private lateinit var binding: ActivityMainBinding
    private val viewModel: PostsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBar.isVisible = true
        try {
            viewModel.posts.observe(this) { postList ->
                if (!postList.isNullOrEmpty()) {
                    setupRecyclerView(postList.toMutableList())
                    binding.progressBar.isVisible = false
                } else {
                    binding.progressBar.isVisible = false
                    Toast.makeText(this@HomeActivity, "Response not successful", Toast.LENGTH_SHORT).show()
                }
            }
            viewModel.getPosts()
        } catch(e: IOException) {
            binding.progressBar.isVisible = false
            Log.e(TAG, "IOException, you might not have internet connection")
            Toast.makeText(this@HomeActivity, "You might not have internet connection", Toast.LENGTH_SHORT).show()
        } catch(e: HttpException) {
            binding.progressBar.isVisible = false
            Log.e(TAG, "HttpException, unexpected response")
            Toast.makeText(this@HomeActivity, "Unexpected response", Toast.LENGTH_SHORT).show()
        }
        binding.floatingActionButton.setOnClickListener {
            showPostDialog(this)
        }
    }
    private fun setupRecyclerView(listOfPosts : MutableList<PostData>? = null) = binding.recyclerView.apply {
        recycleAdapter = RecycleAdapter()
        recycleAdapter.postsList = listOfPosts ?: mutableListOf()
        adapter = recycleAdapter
        addItemDecoration(SpaceItemDecoration(resources.getDimension(R.dimen.recycler_view_item_space).toInt()))
        layoutManager = LinearLayoutManager(this@HomeActivity)
    }
    private fun showPostDialog(context: Context) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null)
        val builder = AlertDialog.Builder(context, R.style.CustomDialogTheme)
            .setView(dialogView)
        val dialog = builder.create()
        dialog.show()

        val buttonSubmit = dialogView.findViewById<Button>(R.id.buttonSubmit)
        val buttonCancel = dialogView.findViewById<Button>(R.id.buttonCancel)
        val editTextTitle = dialogView.findViewById<EditText>(R.id.editTextTitle)
        val editTextBody = dialogView.findViewById<EditText>(R.id.editTextBody)

//        buttonSubmit.setOnClickListener {
//            if(editTextTitle.text.isEmpty() || editTextBody.text.isEmpty()) {
//                Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//            else {
//                val title = editTextTitle.text.toString()
//                val body = editTextBody.text.toString()
//                submitPost(title, body)
//                dialog.dismiss()
//            }
//        }

        buttonCancel.setOnClickListener {
            dialog.dismiss()
        }
    }

//    private fun submitPost(title: String, body: String) {
//        lifecycleScope.launch {
//            binding.progressBar.isVisible = true
//            try {
//                CreatePostUseCase(retrofitRepository).invoke(PostData(id = 101,title = title, body = body, userId = 1))
//                binding.progressBar.isVisible = false
//                Log.e(TAG, "Post created successfully")
//                Toast.makeText(this@HomeActivity, "Post created successfully", Toast.LENGTH_SHORT).show()
//            } catch(e: IOException) {
//                binding.progressBar.isVisible = false
//                Log.e(TAG, "IOException, you might not have internet connection")
//                Toast.makeText(this@HomeActivity, "No internet connection", Toast.LENGTH_SHORT).show()
//                return@launch
//            } catch(e: HttpException) {
//                binding.progressBar.isVisible = false
//                Log.e(TAG, "HttpException, unexpected response")
//                Toast.makeText(this@HomeActivity, "Unexpected response", Toast.LENGTH_SHORT).show()
//                return@launch
//            }
//            catch (e: Exception) {
//                binding.progressBar.isVisible = false
//                Log.e(TAG, "Exception, something went wrong")
//                Toast.makeText(this@HomeActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
//                return@launch
//            }
//        }
//    }
}