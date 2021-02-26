package `in`.techrebounce.todonotes.onboarding

import `in`.techrebounce.todonotes.R
import `in`.techrebounce.todonotes.utils.PrefConstant
import `in`.techrebounce.todonotes.utils.StoreSession
import `in`.techrebounce.todonotes.view.LoginActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager


class OnBoardingActivity : AppCompatActivity(), OnBoardingOneFragment.OnNextClick, OnBoardingTwoFragment.OnOptionClick {
    lateinit var viewPager: ViewPager
//    lateinit var sharedPreferences: SharedPreferences
//    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        bindViews()
        setupSharedPreferences()
    }

    private fun setupSharedPreferences() {
//        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREFERENCE_NAME, MODE_PRIVATE)
        StoreSession.init(this)
    }

    private fun bindViews() {
        viewPager = findViewById(R.id.viewPager2)
        val adapter = FragmentAdapter(supportFragmentManager)
        viewPager.adapter = adapter
    }

    override fun onClick() {
        viewPager.currentItem = 1
    }

    override fun onOptionBack() {
        viewPager.currentItem = 0
    }

    override fun onOptionDone() {
        StoreSession.write(PrefConstant.ON_BOARDED_SUCCESSFULLY, true)
//        editor = sharedPreferences.edit()
//        editor.putBoolean(PrefConstant.ON_BOARDED_SUCCESSFULLY, true)
//        editor.apply()

        val intent = Intent(this@OnBoardingActivity, LoginActivity::class.java)
        startActivity(intent)
    }
}