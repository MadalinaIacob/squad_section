package com.uefa.android.test.di

import com.uefa.android.test.viewmodel.SquadViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    // Specific viewModel pattern to tell Koin how to build SquadViewModel
    viewModel {
        SquadViewModel(repository = get())
    }

}