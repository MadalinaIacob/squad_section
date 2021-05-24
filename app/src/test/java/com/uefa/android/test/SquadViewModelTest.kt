package com.uefa.android.test

import com.uefa.android.test.repository.IRepository
import com.uefa.android.test.viewmodel.SquadViewModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class SquadViewModelTest {

    @Mock
    lateinit var repository: IRepository
    private lateinit var squadViewModel: SquadViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        squadViewModel = SquadViewModel(repository)
    }

    @Test
    fun test() {
        squadViewModel.getSquad()
        assertEquals("Squat Model", squadViewModel.squadModel.value?.name, "Barcelona")
    }


}