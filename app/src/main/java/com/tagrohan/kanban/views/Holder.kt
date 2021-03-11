package com.tagrohan.kanban.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tagrohan.kanban.R
import com.tagrohan.kanban.controllers.ViewPagerAdapter
import com.tagrohan.kanban.databinding.FragmentHolderBinding

class Holder : Fragment() {

    lateinit var binding: FragmentHolderBinding
    lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHolderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabLayout = requireActivity().findViewById(R.id.tab_layout)
        init()
    }

    private fun init() {
        //fragments
        val arrayListOf = arrayListOf(
            Idea(),
            Todo(),
            Doing(),
            Done()
        )

        val viewPagerAdapter =
            ViewPagerAdapter(arrayListOf, requireActivity().supportFragmentManager, lifecycle)
        binding.viewPager.adapter = viewPagerAdapter
        binding.viewPager.currentItem = 1
        TabLayoutMediator(tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Idea"
                1 -> tab.text = "Todo"
                2 -> tab.text = "Doing"
                3 -> tab.text = "Done"
            }
        }.attach()



    }

}