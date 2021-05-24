package com.uefa.android.test.viewmodel

import com.uefa.android.test.repository.IRepository
import com.uefa.android.test.util.AppResult
import com.uefa.android.test.util.SingleLiveEvent
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uefa.android.test.model.*
import kotlinx.coroutines.launch

class SquadViewModel(private val repository: IRepository) : ViewModel() {

    val showLoading = ObservableBoolean()
    val playersList = MutableLiveData<List<PlayerUiModel>>()
    val showError = SingleLiveEvent<String>()
    val squadModel = MutableLiveData<SquadUiModel?>()

    fun getPlayers() {
        showLoading.set(true)
        viewModelScope.launch {
            val result = repository.getSquad()
            showLoading.set(false)
            when (result) {
                is AppResult.Success -> {
                    playersList.value = mapBackendModelToUiModel(
                        result.successData.players,
                        result.successData.playersB
                    )
                }
                is AppResult.Error -> showError.value = result.exception.message
            }
        }
    }

    fun getSquad() {
        showLoading.set(true)
        viewModelScope.launch {
            val result = repository.getSquad()
            showLoading.set(false)
            when (result) {
                is AppResult.Success -> {
                    squadModel.value = mapSquadBackendModelToUiModel(result.successData)
                }
                is AppResult.Error -> showError.value = result.exception.message
            }
        }
    }

    private fun mapSquadBackendModelToUiModel(squadModel: SquadModel): SquadUiModel {
        val firstSecondRound = squadModel.round.substringBefore(" ")
        val secondLineRound = squadModel.round.substringAfter(" ")
        return SquadUiModel(squadModel.name, firstSecondRound, secondLineRound)
    }

    private fun mapBackendModelToUiModel(
        backendModels: List<PlayerModel>,
        playersB: List<PlayerModel>
    ): List<PlayerUiModel> {
        val mappedModels = backendModels
            .map {
                PlayerUiModel(
                    ItemType.TYPE_PLAYER,
                    it.type,
                    it.name,
                    it.country,
                    it.number,
                    true
                )
            }.toMutableList() // create ui model for each backend model

        val groupedListByType = mappedModels.groupBy { it.playerType }

        val finalUiList = mutableListOf<PlayerUiModel>()
        for ((key, items) in groupedListByType) {
            finalUiList.add(PlayerUiModel(ItemType.TYPE_HEADER, key))
            for (item in items.withIndex()) {
                if (item.index == items.size - 1) {
                    finalUiList.add(
                        PlayerUiModel(
                            item.value.itemType,
                            item.value.playerType,
                            item.value.playerName,
                            item.value.playerCountry,
                            item.value.playerNumber,
                            true
                        )
                    )
                } else {
                    finalUiList.add(
                        PlayerUiModel(
                            item.value.itemType,
                            item.value.playerType,
                            item.value.playerName,
                            item.value.playerCountry,
                            item.value.playerNumber,
                            false
                        )
                    )
                }
            }
        }

        finalUiList.add(PlayerUiModel(itemType = ItemType.TYPE_FOOTER, listB = "*Player list B"))
        return finalUiList
    }
}