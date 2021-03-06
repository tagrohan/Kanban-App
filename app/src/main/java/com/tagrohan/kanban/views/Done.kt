package com.tagrohan.kanban.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tagrohan.kanban.controllers.Recycler
import com.tagrohan.kanban.databinding.FragmentDoneBinding
import com.tagrohan.kanban.vm.SharedViewModel


class Done : Fragment() {

    lateinit var binding: FragmentDoneBinding
    lateinit var model: SharedViewModel
    lateinit var recycler: Recycler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        model.done.observe(viewLifecycleOwner) {
            recycler = Recycler(it)
            recycler.notifyDataSetChanged()
            binding.doneRecycler.adapter = recycler
        }
    }


}