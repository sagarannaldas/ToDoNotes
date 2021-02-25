package `in`.techrebounce.todonotes.onboarding

import `in`.techrebounce.todonotes.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager


class OnBoardingActivity : AppCompatActivity() {
    lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        bindViews()
    }

    private fun bindViews() {
        viewPager = findViewById(R.id.viewPager2)
        val adapter = FragmentAdapter(supportFragmentManager)
        viewPager.adapter = adapter
    }
}