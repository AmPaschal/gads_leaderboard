package com.ampaschal.gadsleaderboard.network

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface LearnersApi {

    @GET("api/hours")
    suspend fun getLearningLeaders(): List<LearningLeader>

    @GET("/api/skilliq")
    suspend fun getSkillLeaders(): List<SkillLeader>

    @POST("https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    suspend fun submitFormResponse(
        @Field("entry.1877115667") firstName: String,
        @Field("entry.2006916086") lastName: String,
        @Field("entry.1824927963") email: String,
        @Field("entry.284483984") projectLink: String
    )
}