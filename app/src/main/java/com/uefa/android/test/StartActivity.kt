package com.uefa.android.test

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uefa.android.test.util.Utils
import com.uefa.android.test.views.DashboardActivity
import kotlinx.android.synthetic.main.activity_start.*


class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        uclButton.setOnClickListener {
            openUclVersion()
        }
        uelButton.setOnClickListener {
            openUelVersion()
        }
    }

    private fun openUelVersion() {
        Utils.saveThemeToPreferences(applicationContext, Utils.UEL_THEME)
        startDashboardActivity()
    }

    private fun openUclVersion() {
        Utils.saveThemeToPreferences(applicationContext, Utils.UCL_THEME)
        startDashboardActivity()
    }

    private fun startDashboardActivity() {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
    }
}