package com.tagrohan.kanban.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tagrohan.kanban.controllers.Recycler
import com.tagrohan.kanban.databinding.FragmentDoingBinding
import com.tagrohan.kanban.vm.SharedViewModel


class Doing : Fragment() {

    lateinit var binding: FragmentDoingBinding
    lateinit var model: SharedViewModel
    lateinit var recycler: Recycler

    companion object {
        const val TAG: String = "Doing"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDoingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        model.doing.observe(viewLifecycleOwner) {
            recycler = Recycler(it)
            recycler.notifyDataSetChanged()
            binding.doingRecycler.adapter = recycler
        }
    }
}