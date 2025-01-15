package com.example.json_placeholder_app.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.json_placeholder_app.R
import com.example.json_placeholder_app.databinding.ActivityMainBinding
import com.example.json_placeholder_app.presentation.ui.activity.fragment.CreatePostFragment
import com.example.json_placeholder_app.presentation.ui.activity.fragment.CreatePublicationFragment
import com.example.json_placeholder_app.presentation.ui.activity.fragment.FindUsersFragment
import com.example.json_placeholder_app.presentation.ui.activity.fragment.HomeFragment
import com.example.json_placeholder_app.presentation.ui.activity.fragment.UserProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<HomeFragment>(R.id.fragment)
        }

        binding.btHome.setImageResource(R.drawable.icon_home_fill)
        binding.btCreate.setImageResource(R.drawable.icon_create_line)
        binding.btSearch.setImageResource(R.drawable.icon_search_line)
        binding.btUserProfile.setImageResource(R.drawable.icon_user_line)

        binding.btHome.setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<HomeFragment>(R.id.fragment)
            }
            binding.btHome.setImageResource(R.drawable.icon_home_fill)
            binding.btCreate.setImageResource(R.drawable.icon_create_line)
            binding.btSearch.setImageResource(R.drawable.icon_search_line)
            binding.btUserProfile.setImageResource(R.drawable.icon_user_line)
        }

        binding.btCreate.setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<CreatePublicationFragment>(R.id.fragment)
            }
            binding.btHome.setImageResource(R.drawable.icon_home_line)
            binding.btCreate.setImageResource(R.drawable.icon_create_fill)
            binding.btSearch.setImageResource(R.drawable.icon_search_line)
            binding.btUserProfile.setImageResource(R.drawable.icon_user_line)
        }

        binding.btSearch.setOnClickListener {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<FindUsersFragment>(R.id.fragment)
            }
            binding.btHome.setImageResource(R.drawable.icon_home_line)
            binding.btCreate.setImageResource(R.drawable.icon_create_line)
            binding.btSearch.setImageResource(R.drawable.icon_search_fill)
            binding.btUserProfile.setImageResource(R.drawable.icon_user_line)
        }

        binding.btUserProfile.setOnClickListener {
            val fragment = UserProfileFragment()
            val bundle = Bundle()
            bundle.putInt("userId", 1)
            fragment.arguments = bundle
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.fragment, fragment)
            }
            binding.btHome.setImageResource(R.drawable.icon_home_line)
            binding.btCreate.setImageResource(R.drawable.icon_create_line)
            binding.btSearch.setImageResource(R.drawable.icon_search_line)
            binding.btUserProfile.setImageResource(R.drawable.icon_user_fill)
        }
    }
}