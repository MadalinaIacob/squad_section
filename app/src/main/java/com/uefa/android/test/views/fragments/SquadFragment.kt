package com.uefa.android.test.views.fragments

import com.uefa.android.test.viewmodel.SquadViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.uefa.android.test.R
import com.uefa.android.test.databinding.FragmentSquadBinding
import com.uefa.android.test.views.adapters.SquadAdapter
import kotlinx.android.synthetic.main.fragment_squad.*
import org.koin.android.viewmodel.ext.android.viewModel

class SquadFragment : Fragment(){

    private val squadViewModel by viewModel<SquadViewModel>()
    private lateinit var squadAdapter: SquadAdapter
    private lateinit var viewDataBinding: FragmentSquadBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding  = DataBindingUtil.inflate(inflater, R.layout.fragment_squad, container, false)
        val mRootView = viewDataBinding.root
        viewDataBinding.lifecycleOwner = this
        return mRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAdapter()
        viewDataBinding.viewModel = squadViewModel
        squadViewModel.getPlayers()
        squadViewModel.playersList.observe(viewLifecycleOwner, {
            if (it.isNotEmpty() && it != null) {
                squadAdapter.updatePlayersList(it)
            }
        })

    }

    private fun initAdapter() {
        squadAdapter = SquadAdapter(context)
        recyclerViewSquad.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerViewSquad.adapter = squadAdapter
    }

}
