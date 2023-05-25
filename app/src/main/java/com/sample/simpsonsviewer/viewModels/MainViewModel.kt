package com.sample.simpsonsviewer.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.simpsonsviewer.entities.DataResult
import com.sample.simpsonsviewer.entities.LoadResult
import com.sample.simpsonsviewer.entities.Response
import com.sample.simpsonsviewer.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: CharacterRepository): ViewModel() {

    companion object {
        private val TAG = MainViewModel::class.java.simpleName
    }

    private val _characterList = MutableLiveData<List<Response.CharacterItem>>()
    private val _loading = MutableLiveData<LoadResult>()
    private var job: Job? = null

    val characterList: LiveData<List<Response.CharacterItem>>
        get() = _characterList
    val loading: LiveData<LoadResult>
        get() = _loading

    fun fetchCharacterResult() {
        job?.cancel()

        job = viewModelScope.launch {
            _loading.value = LoadResult.LOADING
            withContext(Dispatchers.IO) {
                when(val result = repository.getCharacterResult()) {
                    is DataResult.Success -> {
                        Log.w(TAG, "Data: " + result.data)
                        _characterList.postValue(result.data)
                        _loading.postValue(LoadResult.SUCCESS)
                    }
                    is DataResult.Failure -> {
                        Log.w(TAG, "Error: " + result.exception)
                        _loading.postValue(LoadResult.FAILURE)
                    }
                }
            }
        }
    }
}