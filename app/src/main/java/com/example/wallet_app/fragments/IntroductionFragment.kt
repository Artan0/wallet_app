package com.example.wallet_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.wallet_app.R

class IntroductionFragment(private val header: String, private val paragraph: String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_introduction, container, false)

        val headerTextView: TextView = view.findViewById(R.id.headerTextView)
        val paragraphTextView: TextView = view.findViewById(R.id.paragraphTextView)

        headerTextView.text = header
        paragraphTextView.text = paragraph

        return view
    }
}
