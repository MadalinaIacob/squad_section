package com.uefa.android.test.views

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import com.uefa.android.test.BaseActivity
import com.uefa.android.test.R
import com.uefa.android.test.databinding.ActivityDashboardBinding
import com.uefa.android.test.viewmodel.SquadViewModel
import com.uefa.android.test.views.adapters.TabsPagerAdapter
import com.uefa.android.test.views.fragments.*
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.koin.android.viewmodel.ext.android.viewModel


class DashboardActivity : BaseActivity() {

    private val squadViewModel by viewModel<SquadViewModel>()
    private lateinit var viewDataBinding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        setToolbar()

        val tabsPagerAdapter = initTabPager()
        viewPager.adapter = tabsPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
        viewPager.currentItem = tabLayout.tabCount
        squadViewModel.getSquad()
        squadViewModel.squadModel.observe(this, {
            if (it != null) {
                viewDataBinding.squadUiModel = it
            }
        })
    }

    private fun initTabPager(): TabsPagerAdapter {
        val tabsPagerAdapter = TabsPagerAdapter(supportFragmentManager)
        tabsPagerAdapter.addFragment(OverviewFragment(), resources.getString(R.string.overview))
        tabsPagerAdapter.addFragment(MatchesFragment(), resources.getString(R.string.matches))
        tabsPagerAdapter.addFragment(GroupsFragment(), resources.getString(R.string.groups))
        tabsPagerAdapter.addFragment(StatsFragment(), resources.getString(R.string.stats))
        tabsPagerAdapter.addFragment(SquadFragment(), resources.getString(R.string.squad))
        return tabsPagerAdapter
    }

    private fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}
