package murillomaciel.com.example.youtubemurillo2.model

import retrofit2.http.GET
import retrofit2.http.Query
import murillomaciel.com.example.youtubemurillo2.model.entity.Result
import retrofit2.Response

interface YoutubeApi {

    @GET("search")
    suspend fun getVideos(
        @Query("part") part: String = "snippet",
        @Query("order") order: String = "date",
        @Query("maxResults") maxResults: String = "10",
        @Query("key") key: String = YOUTUBE_API_KEY,
        @Query("channelId") channelId: String = CHANNEL_ID,
        @Query("q") query: String,
    ) : Response<Result>
}