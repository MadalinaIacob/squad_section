package com.uefa.android.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uefa.android.test.util.Utils


open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pref = applicationContext.getSharedPreferences(Utils.PREF_NAME, 0)
        val selectedTheme = pref.getString(Utils.PREF_THEME, null)
        if (selectedTheme.equals(Utils.UCL_THEME)) {
            setTheme(R.style.UclTheme)
        } else {
            setTheme(R.style.UelTheme)
        }
    }
}