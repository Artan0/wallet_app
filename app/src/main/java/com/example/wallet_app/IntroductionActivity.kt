package com.example.wallet_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.example.wallet_app.adapters.IntroductionPagerAdapter
import com.example.wallet_app.fragments.IntroductionFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class IntroductionActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var getStartedButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction)

        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
        getStartedButton = findViewById(R.id.getStartedButton)

        val fragmentList = listOf(
            IntroductionFragment("Welcome to X Wallet!", "Welcome to the first E-Wallet app in North Macedonia."),
            IntroductionFragment("Track all of your transactions.", "Track everything and all of the transactions made."),
            IntroductionFragment("Send money to anywhere.", "Send and receive money to anywhere in the world.")
        )

        val introductionPagerAdapter = IntroductionPagerAdapter(this, fragmentList)
        viewPager.adapter = introductionPagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
            }
        })

        getStartedButton.setOnClickListener {
            navigateToNextActivity()
        }
    }

    private fun navigateToNextActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
