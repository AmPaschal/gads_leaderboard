package com.ampaschal.gadsleaderboard.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ampaschal.gadsleaderboard.LeaderType
import com.ampaschal.gadsleaderboard.R
import com.ampaschal.gadsleaderboard.repository.LeaderData
import kotlinx.android.synthetic.main.fragment_learning_leader_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class LeaderRecyclerViewAdapter(val leaderType: LeaderType) : RecyclerView.Adapter<LeaderRecyclerViewAdapter.ViewHolder>() {

    private var mValues = listOf<LeaderData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutType = if (leaderType.equals(LeaderType.TOP_LEARNER))
            R.layout.fragment_learning_leader_item else
            R.layout.fragment_skill_leader_item

        val view = LayoutInflater.from(parent.context)
            .inflate(layoutType, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.tvLearnerName.text = item.name
        holder.tvLearnerDetail.text = item.detail

    }

    override fun getItemCount(): Int = mValues.size

    fun setData (values: List<LeaderData>?) {
        values?.let {
            this.mValues = values
            notifyDataSetChanged()
        }

    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val tvLearnerName: TextView = mView.tv_learner_name
        val tvLearnerDetail: TextView = mView.tv_learner_details
    }
}
