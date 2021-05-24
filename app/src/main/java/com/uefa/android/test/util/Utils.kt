package com.uefa.android.test.util

import android.content.Context
import android.content.SharedPreferences
import retrofit2.Response
import java.io.IOException

object Utils {
    fun <T : Any> handleApiError(resp: Response<T>): AppResult.Error {
        val error = ApiErrorUtils.parseError(resp)
        return AppResult.Error(Exception(error.message))
    }

    fun <T : Any> handleSuccess(response: Response<T>): AppResult<T> {
        response.body()?.let {
            return AppResult.Success(it)
        } ?: return handleApiError(response)
    }

    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun saveThemeToPreferences(context: Context, theme: String) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, 0)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(PREF_THEME, theme)
        editor.apply()
    }

    const val PREF_THEME = "Theme"
    const val PREF_NAME = "ThemePreferences"
    const val UCL_THEME = "UCL"
    const val UEL_THEME = "UEL"
}