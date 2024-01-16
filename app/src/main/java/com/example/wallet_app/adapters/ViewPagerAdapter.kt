package com.example.wallet_app.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.wallet_app.fragments.CryptoHoldingsFragment
import com.example.wallet_app.fragments.TransactionHistoryFragment

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CryptoHoldingsFragment()
            1 -> TransactionHistoryFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }

    override fun getCount(): Int {
        return 2 // Number of tabs
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Crypto Holdings"
            1 -> "Transaction History"
            else -> null
        }
    }
}