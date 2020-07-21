package com.app.creativetask.ui.main.homescreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.app.creativetask.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Emad Mohamed Oun on 7/21/2020.
 * Rytalo
 * emad.3oon@gmail.com
 */

class HomeActivity : AppCompatActivity() {

    private val orderTabLayout: TabLayout get() = tabLayout

    private val orderViewPager: ViewPager2 get() = viewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        setUpOrdersViewPager()
    }

    private fun setUpOrdersViewPager() {
        orderTabLayout.tabGravity = TabLayout.GRAVITY_CENTER
        orderTabLayout.tabMode = TabLayout.MODE_SCROLLABLE

        val fragmentAdapter =
            OrdersViewPagerAdapter(this)
        orderViewPager.adapter = fragmentAdapter

        TabLayoutMediator(
            orderTabLayout,
            orderViewPager
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text =
                        getString(R.string.canceled_tab_txt)
                }
                1 -> {
                    tab.text =
                        getString(R.string.accepted_tab_txt)
                }

                2 -> {
                    tab.text =
                        getString(R.string.waiting_tab_txt)
                }
            }
        }.attach()

        orderViewPager.setCurrentItem(2, false)
    }
}