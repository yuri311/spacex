package com.yuri.spacex.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuri.spacex.dataobjects.Launch
import com.yuri.spacex.dataobjects.Rocket
import com.yuri.spacex.repository.ApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LaunchesViewModel : ViewModel() {
    private var launchList: List<Launch>? = emptyList()

    private val launchDetailsLiveData: MutableLiveData<Launch> by lazy {
        MutableLiveData<Launch>()
    }

    private val rocketDetailsLiveData: MutableLiveData<Rocket> by lazy {
        MutableLiveData<Rocket>()
    }

    private val launchListLiveData: MutableLiveData<List<Launch>> by lazy {
        MutableLiveData<List<Launch>>().also {
            fetchAllLaunches()
        }
    }

    private fun fetchAllLaunches() {
        viewModelScope.launch(Dispatchers.IO) {
            launchList = ApiRepository.getAllLaunches()
            launchListLiveData.postValue(launchList)
        }
    }

    fun fetchLaunch(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            launchDetailsLiveData.postValue(ApiRepository.getOneLaunch(id))
        }
    }

    fun fetchRocket(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            rocketDetailsLiveData.postValue(ApiRepository.getOneRocket(id))
        }
    }

    fun sortByDate() {
        viewModelScope.launch(Dispatchers.IO) {
            launchListLiveData.postValue(launchList?.sortedByDescending { it.dateUnix })
        }
    }

    fun sortByStatus() {
        viewModelScope.launch(Dispatchers.IO) {
            launchListLiveData.postValue(launchList?.sortedByDescending { it.success })
        }
    }

    fun getLaunchListLiveData(): LiveData<List<Launch>> {
        return launchListLiveData
    }

    fun getLaunchDetailsLiveData(): LiveData<Launch> {
        return launchDetailsLiveData
    }

    fun getRocketLiveData(): LiveData<Rocket> {
        return rocketDetailsLiveData
    }
}
