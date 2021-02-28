package `in`.techrebounce.todonotes.onboarding.adapter

import `in`.techrebounce.todonotes.onboarding.OnBoardingOneFragment
import `in`.techrebounce.todonotes.onboarding.OnBoardingTwoFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter(fragmentManager: FragmentActivity) : FragmentStateAdapter(fragmentManager) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
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
}