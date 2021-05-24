package com.uefa.android.test.repository

import com.uefa.android.test.model.SquadModel
import com.uefa.android.test.util.AppResult

interface IRepository {
    suspend fun getSquad() : AppResult<SquadModel>
}
