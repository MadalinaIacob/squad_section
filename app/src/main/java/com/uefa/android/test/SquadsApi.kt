package com.uefa.android.test

import com.uefa.android.test.model.SquadModel
import retrofit2.Response
import retrofit2.http.GET

interface SquadsApi {

    @GET("/api/v1")
    suspend fun getAllSquads(): Response<List<SquadModel>>
}