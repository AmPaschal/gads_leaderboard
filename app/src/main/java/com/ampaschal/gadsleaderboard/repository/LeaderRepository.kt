package com.ampaschal.gadsleaderboard.repository

import com.ampaschal.gadsleaderboard.network.FormResponseData
import com.ampaschal.gadsleaderboard.network.Resource

interface LeaderRepository {

    suspend fun getLearningLeaders(): Resource<List<LeaderData>>

    suspend fun getSkillLeaders(): Resource<List<LeaderData>>

    suspend fun submitFormResponse(responseData: FormResponseData): Resource<Any>
}