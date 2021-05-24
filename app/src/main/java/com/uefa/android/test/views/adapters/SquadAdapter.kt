package com.uefa.android.test.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.uefa.android.test.R
import com.uefa.android.test.databinding.FooterRowBinding
import com.uefa.android.test.databinding.HeaderRowBinding
import com.uefa.android.test.databinding.PlayerRowBinding
import com.uefa.android.test.model.ItemType
import com.uefa.android.test.model.PlayerUiModel


class SquadAdapter(val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var playersList: List<PlayerUiModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ItemType.TYPE_HEADER.ordinal)  {
            return HeaderViewHolder(DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.header_row, parent, false))
        }
        if(viewType == ItemType.TYPE_FOOTER.ordinal) {
            return FooterViewHolder(DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.footer_row, parent, false))
        }
        return PlayerViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.player_row, parent, false))
    }

    override fun getItemCount(): Int {
        return playersList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            playersList[position].itemType === ItemType.TYPE_HEADER -> {
                (holder as HeaderViewHolder).onBind(position)
            }
            playersList[position].itemType === ItemType.TYPE_FOOTER -> {
                (holder as FooterViewHolder).onBind(position)
            }
            else -> {
                (holder as PlayerViewHolder).onBind(position)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return playersList[position].itemType.ordinal
    }

    fun updatePlayersList(countries: List<PlayerUiModel>) {
        this.playersList = countries
        notifyDataSetChanged()
    }

    inner class PlayerViewHolder(private val viewBinding: PlayerRowBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun onBind(position: Int) {
            val row = playersList[position]
            viewBinding.player = row
        }
    }

    inner class HeaderViewHolder(private val viewBinding: HeaderRowBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun onBind(position: Int) {
            val row = playersList[position]
            viewBinding.player = row
        }
    }

    inner class FooterViewHolder(private val viewBinding: FooterRowBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun onBind(position: Int) {
            val row = playersList[position]
            viewBinding.player = row
        }
    }
}


