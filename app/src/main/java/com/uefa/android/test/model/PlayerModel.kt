package com.uefa.android.test.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayerModel(var type: String = "",
                       var name: String = "",
                       var country: String = "",
                       var number: Int = 0): Parcelable
