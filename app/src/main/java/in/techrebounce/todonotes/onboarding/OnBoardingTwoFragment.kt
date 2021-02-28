package `in`.techrebounce.todonotes.onboarding

import `in`.techrebounce.todonotes.R
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class OnBoardingTwoFragment : Fragment() {
    lateinit var buttonDone: Button
    lateinit var buttonBack: Button
    lateinit var onOptionClick: OnOptionClick

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onOptionClick = context as OnOptionClick
    }

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
        buttonDone = view.findViewById(R.id.buttonDone)
        buttonBack = view.findViewById(R.id.buttonBack)
        clickListener()
    }

    private fun clickListener() {
        buttonBack.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                onOptionClick.onOptionBack()
            }

        })

        buttonDone.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                onOptionClick.onOptionDone()
            }

        })
    }

    interface OnOptionClick {
        fun onOptionBack()
        fun onOptionDone()
    }

}