package com.ampaschal.gadsleaderboard.ui

import androidx.lifecycle.*
import com.ampaschal.gadsleaderboard.network.FormResponseData
import com.ampaschal.gadsleaderboard.network.Resource
import com.ampaschal.gadsleaderboard.network.Status
import com.ampaschal.gadsleaderboard.repository.LeaderData
import com.ampaschal.gadsleaderboard.repository.LeaderRepository
import kotlinx.coroutines.launch

class MainViewModel(val repository: LeaderRepository) : ViewModel() {

    private val _submitResponseStatus = MutableLiveData<Status>()
    val submitResponseStatus: LiveData<Status> = _submitResponseStatus

    private var responseData: FormResponseData? = null

    val learningLeaders: LiveData<Resource<List<LeaderData>>> = liveData {
        emit(Resource.loading(null))
        emit(repository.getLearningLeaders())
    }

    val skillLeaders: LiveData<Resource<List<LeaderData>>>  = liveData {
        emit(Resource.loading(null))
        emit(repository.getSkillLeaders())
    }

    fun submitFormResponse() {
        _submitResponseStatus.value = Status.LOADING
        viewModelScope.launch {
            responseData?.let {
                val response = repository.submitFormResponse(it)
                _submitResponseStatus.value = response.status
            }

        }

    }

    fun isResponseDataAvailable(): Boolean {
        return responseData != null
    }

    fun saveResponseData(responseData: FormResponseData) {
        this.responseData = responseData
    }
}
