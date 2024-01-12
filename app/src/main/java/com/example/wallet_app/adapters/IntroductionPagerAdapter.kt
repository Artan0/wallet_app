package com.example.wallet_app.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.wallet_app.fragments.IntroductionFragment

class IntroductionPagerAdapter(activity: AppCompatActivity, private val fragmentList: List<Fragment>) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}
