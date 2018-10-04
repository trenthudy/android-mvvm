package io.hudepohl.mvvm.ui.main

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import io.hudepohl.mvvm.R
import io.hudepohl.mvvm.ui.BaseActivity
import io.hudepohl.mvvm.ui.main.tabs.MainActivityTab
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {

    @Inject
    lateinit var defaultTab: MainActivityTab
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = viewModel
        setSupportActionBar(binding.toolbar)
        binding.navigation.apply {

            setOnNavigationItemSelectedListener {
                onTabChange(it.itemId)
                true
            }
        }

        onTabChange(defaultTab.menuItemId)

        viewModel.errorMessages.observe(this, Observer { errorMessage ->
            errorMessage?.let {

                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onTabChange(@IdRes tabId: Int) {
        val newTab = when (tabId) {
            R.id.navigation_item_beatles -> MainActivityTab.BEATLES
            R.id.navigation_item_github -> MainActivityTab.GITHUB
            else -> throw RuntimeException("Could not find MainActivityTab for selected tabId")
        }

        updateFragmentContainer(newTab)
    }

    private fun updateFragmentContainer(tab: MainActivityTab) {

        supportFragmentManager
            .fragments
            .forEach {

                supportFragmentManager
                    .beginTransaction()
                    .remove(it)
                    .commit()
            }

        binding.mainFragmentContainer.removeAllViews()

        LayoutInflater
            .from(this)
            .inflate(
                tab.layout,
                binding.mainFragmentContainer,
                true)

        binding.toolbar.title = getString(tab.title)
    }
}
