package `in`.techrebounce.todonotes.view

import `in`.techrebounce.todonotes.utils.AppConstant
import `in`.techrebounce.todonotes.R
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    private val TAG = "DetailActivity"
    lateinit var textViewTitle: TextView
    lateinit var textViewDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        bindViews()
        setupIntentData()
    }

    private fun bindViews() {
        textViewTitle = findViewById(R.id.textViewTitle)
        textViewDescription = findViewById(R.id.textViewDescription)
    }

    private fun setupIntentData() {
        val intent = intent
        val title = intent.getStringExtra(AppConstant.TITLE)
        val description = intent.getStringExtra(AppConstant.DESCRIPTION)
        textViewTitle.text = title
        textViewDescription.text = description
    }
}