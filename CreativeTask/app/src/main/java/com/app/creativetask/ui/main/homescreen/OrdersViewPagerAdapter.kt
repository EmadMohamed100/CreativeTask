package com.app.creativetask.ui.main.homescreen

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.creativetask.ui.main.ordersscreen.OrdersFragment

/**
 * Created by Emad Mohamed Oun on 6/23/2020.
 * Rytalo
 * emad.3oon@gmail.com
 */

class OrdersViewPagerAdapter(
    activity: AppCompatActivity
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return OrdersFragment.newInstance(position)
    }

}