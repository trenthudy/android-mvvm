package io.hudepohl.mvvm.ui.main

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import io.hudepohl.mvvm.R
import io.hudepohl.mvvm.ui.BaseActivity
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
                binding.message.text = when (it.itemId) {
                    MainActivityTab.BEATLES.menuItemId -> "bealtes"
                    R.id.navigation_item_github -> "github"
                    R.id.navigation_item_settings -> "settings"
                    else -> "idk"
                }
                true
            }

            selectedItemId = defaultTab.menuItemId
        }

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
}
