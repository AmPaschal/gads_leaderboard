package com.ampaschal.gadsleaderboard.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.ampaschal.gadsleaderboard.R
import com.ampaschal.gadsleaderboard.network.Status
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LeaderFragment : Fragment() {

    private val mainViewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.leader_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val leaderPagerAdapter = LeaderPagerAdapter((childFragmentManager))
        val viewPager = view.findViewById<ViewPager>(R.id.pager)
        viewPager.adapter = leaderPagerAdapter

        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)

        val btSubmit = view.findViewById<Button>(R.id.bt_submit)
        btSubmit.setOnClickListener {
            findNavController().navigate(R.id.action_leaderFragment_to_submitFragment)
        }

        mainViewModel.submitResponseStatus.observe(viewLifecycleOwner, Observer {
            if (it == Status.SUCCESS) {
                val action = LeaderFragmentDirections.actionLeaderFragmentToResultDialogFragment(true)
                findNavController().navigate(action)
            }
        })
    }
}
