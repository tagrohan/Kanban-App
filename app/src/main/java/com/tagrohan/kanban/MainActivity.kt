package com.tagrohan.kanban

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.snackbar.Snackbar
import com.tagrohan.kanban.databinding.ActivityMainBinding
import com.tagrohan.kanban.databinding.ProfileDrawerBinding
import com.tagrohan.kanban.views.Todo

class MainActivity : AppCompatActivity() {

    companion object {
        var isProfileOpen: Boolean = false
    }

    lateinit var closingAnim: ClosingAnim
    lateinit var binding: ActivityMainBinding
    lateinit var header: ProfileDrawerBinding
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        header = ProfileDrawerBinding.bind(binding.header.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.profileIcon.setOnClickListener {
            binding.activityMain.transitionToEnd()
            isProfileOpen = true
        }

        header.avatarButton.setOnClickListener {
            var snackbar =
                Snackbar.make(it, "Hello from Snackbar", Snackbar.LENGTH_LONG)
            snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.themeOne))
            snackbar.setTextColor(ContextCompat.getColor(this, R.color.white))
            snackbar.show()

        }
    }


    override fun onBackPressed() {

        when {
            Todo.isOpen -> {
                closingAnim.close()
            }
            isProfileOpen -> {
                binding.activityMain.transitionToStart()
                isProfileOpen = false
            }
            else -> {
                super.onBackPressed()
            }
        }
    }

    interface ClosingAnim {
        fun close()
    }
}

