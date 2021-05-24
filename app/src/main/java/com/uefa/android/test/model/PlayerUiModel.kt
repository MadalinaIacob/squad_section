package com.uefa.android.test.model

data class PlayerUiModel(var itemType: ItemType,
                         var playerType: String = "",
                         var playerName: String = "",
                         var playerCountry: String = "",
                         var playerNumber: Int = 0,
                         var isLastItem: Boolean = false,
                         var listB: String = "")


