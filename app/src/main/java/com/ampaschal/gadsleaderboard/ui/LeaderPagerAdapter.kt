package com.ampaschal.gadsleaderboard.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class LeaderPagerAdapter(fm: FragmentManager):
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> LearningLeaderFragment()
            1 -> SkillLeaderFragment()
            else -> LearningLeaderFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Learning Leaders"
            1 -> "Skill Leaders"
            else -> "Learning Leaders"
        }
    }

    override fun getCount(): Int = 2
}