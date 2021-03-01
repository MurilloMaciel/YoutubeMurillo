package murillomaciel.com.example.youtubemurillo2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import murillomaciel.com.example.youtubemurillo2.Event
import murillomaciel.com.example.youtubemurillo2.model.YoutubeApiService
import murillomaciel.com.example.youtubemurillo2.model.entity.Items

const val EMPTY_SEARCH = ""

class MainViewModel(
        private val youtubeService: YoutubeApiService
) : ViewModel() {

    val teste = 1

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        coroutineContext.job.cancel()
        _showError.postValue(Event(true))
    }

    val videos = mutableListOf<Items>()

    private val _refreshVideos = MutableLiveData<Event<List<Items>>>()
    val refreshVideos: LiveData<Event<List<Items>>> = _refreshVideos

    private val _showToolbar = MutableLiveData<Event<Boolean>>()
    val showToolbar: LiveData<Event<Boolean>> = _showToolbar

    private val _showError = MutableLiveData<Event<Boolean>>()
    val showError: LiveData<Event<Boolean>> = _showError

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    private fun searchVideos(query: String) {
        job = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val response = youtubeService.getVideos(query)
            if (response.isSuccessful) {
                onReceiveVideos(response.body()?.items ?: listOf())
            } else {
                _showError.postValue(Event(true))
            }
        }
    }

    private fun onReceiveVideos(videos: List<Items>) {
        this.videos.clear()
        this.videos.addAll(videos)
        _refreshVideos.postValue(Event(this.videos))
    }

    fun onQuerySubmit(query: String?) {
        searchVideos(query ?: EMPTY_SEARCH)
    }

    fun onQueryClose() {
        refreshVideos()
    }

    fun refreshVideos() {
        searchVideos(EMPTY_SEARCH)
        _showError.postValue(Event(false))
    }

    fun onShowVideoPlayer() {
        _showToolbar.postValue(Event(false))
    }

    fun onCloseVideoPlayer() {
        _showToolbar.postValue(Event(true))
    }
}