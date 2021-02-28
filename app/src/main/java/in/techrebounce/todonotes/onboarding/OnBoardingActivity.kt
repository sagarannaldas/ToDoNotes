package `in`.techrebounce.todonotes.onboarding

import `in`.techrebounce.todonotes.R
import `in`.techrebounce.todonotes.data.local.pref.PrefConstant
import `in`.techrebounce.todonotes.data.local.pref.StoreSession
import `in`.techrebounce.todonotes.login.LoginActivity
import `in`.techrebounce.todonotes.onboarding.adapter.FragmentAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2


class OnBoardingActivity : AppCompatActivity(), OnBoardingOneFragment.OnNextClick, OnBoardingTwoFragment.OnOptionClick {

    lateinit var viewPager2: ViewPager2

    companion object {
        private const val FIRST_ITEM = 0
        private const val LAST_ITEM = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        bindViews()
        setupSharedPreferences()
    }

    private fun setupSharedPreferences() {
        StoreSession.init(this)
    }

    private fun bindViews() {
        viewPager2 = findViewById(R.id.viewPager2)
        val adapter = FragmentAdapter(this)
        viewPager2.adapter = adapter
    }

    override fun onClick() {
        viewPager2.currentItem = LAST_ITEM
    }

    override fun onOptionBack() {
        viewPager2.currentItem = FIRST_ITEM
    }

    override fun onOptionDone() {
        StoreSession.write(PrefConstant.ON_BOARDED_SUCCESSFULLY, true)

        val intent = Intent(this@OnBoardingActivity, LoginActivity::class.java)
        startActivity(intent)
    }
}