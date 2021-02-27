package `in`.techrebounce.todonotes.onboarding.adapter

import `in`.techrebounce.todonotes.onboarding.OnBoardingOneFragment
import `in`.techrebounce.todonotes.onboarding.OnBoardingTwoFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class FragmentAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                OnBoardingOneFragment()
            }
            1 -> {
                OnBoardingTwoFragment()
            }
            else -> {
                null!!
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }
}