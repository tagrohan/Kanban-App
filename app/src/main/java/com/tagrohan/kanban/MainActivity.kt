package com.tagrohan.kanban

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
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
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        header = ProfileDrawerBinding.bind(binding.header.root)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(binding.root)

        binding.profileIcon.setOnClickListener {
//            binding.mainMotionLayout.transitionToEnd()
            isProfileOpen = true
        }

        binding.drawerOpener.setOnClickListener {
            if (!binding.drawerLayout.isOpen) {
                binding.drawerLayout.open()
            }
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
            binding.drawerLayout.isOpen ->
                binding.drawerLayout.close()
            isProfileOpen -> {
//                binding.mainMotionLayout.transitionToStart()
                isProfileOpen = false
            }
            Todo.isOpen ->
                closingAnim.close()
            else -> {
                super.onBackPressed()
            }
        }
    }
    interface ClosingAnim {
        fun close()
    }
}

