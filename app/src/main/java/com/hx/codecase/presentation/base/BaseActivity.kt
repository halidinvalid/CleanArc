package com.hx.codecase.presentation.base

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hx.codecase.R

abstract class BaseActivity : AppCompatActivity() {

    @IdRes
    open val containerId = R.id.frameLayoutMain

    @LayoutRes
    open val layoutRes = R.layout.activity_container

    open fun provideInitialFragment(): Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        provideInitialFragment()?.let {
            if (savedInstanceState == null) {
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(containerId, it)
                fragmentTransaction.commit()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}