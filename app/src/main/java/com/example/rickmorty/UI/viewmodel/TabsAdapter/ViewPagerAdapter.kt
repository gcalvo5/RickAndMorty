package com.example.rickmorty.UI.viewmodel.TabsAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import javax.inject.Inject

class ViewPagerAdapter @Inject constructor(supportFragmentManager: FragmentManager) : FragmentPagerAdapter(supportFragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    private val mFragmentList = ArrayList<Fragment>()
    private val mTitleList = ArrayList<String>()

    override fun getCount(): Int {
        return mFragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitleList[position]
    }
    fun addFragment(fragment: Fragment, title: String){
        mFragmentList.add(fragment)
        mTitleList.add(title)
    }
}