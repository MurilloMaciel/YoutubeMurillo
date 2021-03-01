package murillomaciel.com.example.youtubemurillo2.model

import murillomaciel.com.example.youtubemurillo2.BuildConfig
import murillomaciel.com.example.youtubemurillo2.model.entity.Result
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val CHANNEL_ID = "UCVHFbqXqoYvEWM1Ddxl0QDg" // android developer
const val YOUTUBE_API_KEY = "AIzaSyBvX_0TKZ7FXE8jd0uQ1BeIWz8Q1UeZACI"
const val BASE_URL = "https://www.googleapis.com/youtube/v3/" // solicitação http

class YoutubeApiServiceImpl : YoutubeApiService {

    private val okHttpClient = OkHttpClient.Builder()

    private var api: YoutubeApi? = null

    override suspend fun getVideos(query: String) = getApi().getVideos(query = query)

    private suspend fun createLogInterceptor() {
        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
            )
        }
    }

    private suspend fun getApi(): YoutubeApi {
        if (api == null) {
            createLogInterceptor()
            api = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient.build())
                    .build()
                    .create(YoutubeApi::class.java)
        }
        return api!!
    }
}