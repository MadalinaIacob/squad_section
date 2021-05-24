package com.uefa.android.test.model

import android.os.Parcelable
import com.uefa.android.test.model.PlayerModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SquadModel(var name: String = "",
                      var round: String = "",
                      var players: List<PlayerModel> = listOf(),
                      var playersB: List<PlayerModel> = listOf()) : Parcelable
