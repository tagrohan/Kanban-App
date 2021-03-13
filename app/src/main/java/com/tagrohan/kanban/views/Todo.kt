package com.tagrohan.kanban.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.tagrohan.kanban.MainActivity
import com.tagrohan.kanban.R
import com.tagrohan.kanban.controllers.Recycler
import com.tagrohan.kanban.databinding.FragmentTodoBinding
import com.tagrohan.kanban.models.TodoData
import com.tagrohan.kanban.vm.SharedViewModel

class Todo : Fragment() {
    private lateinit var mainActivity: MainActivity
    private lateinit var model: SharedViewModel
    private lateinit var binding: FragmentTodoBinding
    private lateinit var recycler: Recycler

    companion object {
        var isOpen = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTodoBinding.inflate(inflater, container, false)
        mainActivity = activity as MainActivity
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        bottomDialogCallBack()
        binding.floatingActionButton.shrink()


        //observer

        model.todo.observe(viewLifecycleOwner) {
            recycler = Recycler(it)
//            recycler.notifyDataSetChanged()
            binding.todoRecycler.adapter = recycler
        }



        binding.todoSave.setOnClickListener {
            try {
                if (binding.todoTitle.text.toString()
                        .isNotBlank() && binding.category.text.toString().isNotBlank()
                ) {
                    notifySnackBar(binding.todoTitle.text.toString())
                    model.stageChecking(
                        TodoData(
                            binding.todoTitle.text.toString(),
                            binding.todoDescription.text.toString(),
                            binding.category.text.toString().toInt()
                        )
                    )
                    binding.motionLayout.transitionToStart()
                    isOpen = false
                } else {
                    notifySnackBar("Enter field carefully")
                }
            } catch (e: NumberFormatException) {
                notifySnackBar("Enter field carefully")
            }
        }
    }


    private fun notifySnackBar(text: String) {
        val snackBar = Snackbar.make(requireView(), text, Snackbar.LENGTH_SHORT)
        snackBar.view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.themeOne))
        snackBar.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        snackBar.show()
    }


    // handling all the logic behind opening and closing dialog(motion layout)
    private fun bottomDialogCallBack() {
        binding.floatingActionButton.setOnClickListener {
            binding.floatingActionButton.extend()
            binding.motionLayout.transitionToEnd()
            isOpen = true
        }

        mainActivity.closingAnim = object : MainActivity.ClosingAnim {
            override fun close() {
                binding.motionLayout.transitionToStart()
                binding.floatingActionButton.shrink()
                isOpen = false
            }
        }
    }
}
