package com.decimalab.photomemory.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.decimalab.photomemory.ui.images.ImagesFragment
import com.decimalab.photomemory.ui.settings.SettingsFragment

/**
 * Displays the pages for the main screen.
 */
class MainPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = listOf(ImagesFragment(), SettingsFragment())
    private val titles = listOf("Images", "Settings")

    override fun getCount(): Int = fragments.size
    override fun getItem(position: Int): Fragment = fragments[position]
    override fun getPageTitle(position: Int): CharSequence? = titles[position]
}