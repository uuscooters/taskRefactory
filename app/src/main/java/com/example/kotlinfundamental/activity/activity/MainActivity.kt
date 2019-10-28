package com.example.kotlinfundamental.activity.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.kotlinfundamental.R
import com.example.kotlinfundamental.activity.fragment.CalendarFragment
import com.example.kotlinfundamental.activity.fragment.ContactFragment
import com.example.kotlinfundamental.activity.fragment.IndexFragment
import com.example.kotlinfundamental.activity.fragment.SettingFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment()
    }

    private fun initFragment() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ContactFragment(), "Contact")
        adapter.addFragment(CalendarFragment(), "Calendar")
        adapter.addFragment(IndexFragment(), "Inbox")
        adapter.addFragment(SettingFragment(), "Setting")

        viewPager.adapter = adapter

        tabLayout.setupWithViewPager(viewPager)
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

        private var fragmentList: ArrayList<Fragment> = ArrayList()
        private var fragmentTitleList: ArrayList<String> = ArrayList()

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getItem(position: Int): Fragment {

            return fragmentList[position]
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            fragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return fragmentTitleList[position]
        }
    }

}
