package com.ampaschal.gadsleaderboard.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ampaschal.gadsleaderboard.LeaderType
import com.ampaschal.gadsleaderboard.R
import com.ampaschal.gadsleaderboard.network.Status
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [LearningLeaderFragment.OnListFragmentInteractionListener] interface.
 */
class LearningLeaderFragment : Fragment() {

    private val mainViewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_learning_leader_list, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.list_learning_leaders)
        val recyclerAdapter = LeaderRecyclerViewAdapter(LeaderType.TOP_LEARNER)

        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerAdapter
        }

        mainViewModel.learningLeaders.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Status.SUCCESS -> recyclerAdapter.setData(it.data)
                Status.ERROR -> Log.e(javaClass.simpleName, "An exception thrown: " + it.message)
                Status.LOADING -> Log.e(javaClass.simpleName, "Loading...")
            }

        })
        return view
    }


}
