package com.ampaschal.gadsleaderboard.koin

import com.ampaschal.gadsleaderboard.network.ResponseHandler
import com.ampaschal.gadsleaderboard.repository.DefaultLeaderRepository
import com.ampaschal.gadsleaderboard.repository.LeaderRepository
import com.ampaschal.gadsleaderboard.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import kotlin.random.Random

val appModule = module {
    viewModel { MainViewModel(get()) }
    factory<LeaderRepository> { DefaultLeaderRepository(get(), get()) }
    factory { ResponseHandler() }
}