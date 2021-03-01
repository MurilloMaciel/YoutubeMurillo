package murillomaciel.com.example.youtubemurillo2.model

import murillomaciel.com.example.youtubemurillo2.model.entity.Result
import retrofit2.Response

interface YoutubeApiService {

    suspend fun getVideos(query: String): Response<Result>
}