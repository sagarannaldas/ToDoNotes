package `in`.techrebounce.todonotes.onboarding

import `in`.techrebounce.todonotes.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class OnBoardingTwoFragment : Fragment() {
    lateinit var textViewNext: TextView
    lateinit var textViewBack: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_on_boarding_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
    }

    private fun bindViews(view: View) {
        textViewNext = view.findViewById(R.id.textViewNext)
        textViewBack = view.findViewById(R.id.textViewBack)
    }

}