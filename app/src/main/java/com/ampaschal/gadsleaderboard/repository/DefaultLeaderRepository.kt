package com.ampaschal.gadsleaderboard.repository

import com.ampaschal.gadsleaderboard.network.*
import java.lang.Exception

class DefaultLeaderRepository(private val learnersApi: LearnersApi,
                              private val responseHandler: ResponseHandler): LeaderRepository {

    override suspend fun getLearningLeaders(): Resource<List<LeaderData>> {
        return try {
            val learningLeaderList = learnersApi.getLearningLeaders().sortedByDescending { leader -> leader.hours }
                .map { leader -> getLearningLeaderData(leader) }
            return responseHandler.handleSuccess(learningLeaderList)
        } catch (ex: Exception) {
            responseHandler.handleException(ex)
        }
    }

    override suspend fun getSkillLeaders(): Resource<List<LeaderData>> {
        return try {
            val skillLeaderList = learnersApi.getSkillLeaders().sortedByDescending { leader -> leader.score }
                .map { leader -> getSkillLeaderData(leader) }
            return responseHandler.handleSuccess(skillLeaderList)
        } catch (ex: Exception) {
            responseHandler.handleException(ex)
        }
    }

    override suspend fun submitFormResponse(responseData: FormResponseData): Resource<Any> {
        return try {
            learnersApi.submitFormResponse(responseData.firstName, responseData.lastName,
                responseData.email, responseData.projectLink)
            return responseHandler.handleSuccess(Any())
        } catch (ex: Exception) {
            responseHandler.handleException(ex)
        }
    }

    private fun getLearningLeaderData(leader: LearningLeader): LeaderData {
        return LeaderData().apply {
            name = leader.name
            detail = "${leader.hours} learning hours, ${leader.country}"
        }
    }

    private fun getSkillLeaderData(leader: SkillLeader): LeaderData {
        return LeaderData().apply {
            name = leader.name
            detail = "${leader.score} Skill IQ Score, ${leader.country}"
        }
    }
}