package com.uefa.android.test.repository

import com.uefa.android.test.SquadsApi
import com.uefa.android.test.util.AppResult
import com.uefa.android.test.util.Utils.handleSuccess
import android.content.Context
import android.util.MalformedJsonException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.uefa.android.test.model.SquadModel
import com.uefa.android.test.util.Utils
import retrofit2.Response

class DemoRepositoryImpl(private val api: SquadsApi,
                         private val context: Context) : IRepository {

    override suspend fun getSquad(): AppResult<SquadModel> {
        val jsonFileString = Utils.getJsonDataFromAsset(context, "players.json")
        val gson = Gson()
        val listPlayerType = object : TypeToken<SquadModel>() {}.type
        var squad = SquadModel()
        try {
            squad = gson.fromJson(jsonFileString, listPlayerType)
        } catch (exception: MalformedJsonException) {
        }
        return handleSuccess(Response.success(squad))
    }
}
