package murillomaciel.com.example.youtubemurillo2.api;

import murillomaciel.com.example.youtubemurillo2.model.Resultado;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YoutubeService
{

    /*

    utilizando @Query cria automaticamente aquela URL

    https://www.googleapis.com/youtube/v3/
    search
    ?part=snippet
    &order=date
    &maxResults=20
    &key=AIzaSyBvX_0TKZ7FXE8jd0uQ1BeIWz8Q1UeZACI
    &channelId=UCVHFbqXqoYvEWM1Ddxl0QDg
    &q=pesquisa+algo

    https://www.googleapis.com/youtube/v3/search?part=snippet&order=date&maxResults=20&key=AIzaSyBvX_0TKZ7FXE8jd0uQ1BeIWz8Q1UeZACI&channelId=UCVHFbqXqoYvEWM1Ddxl0QDg

     */

    @GET("search")
    Call<Resultado> RecuperarVideos (
            @Query("part") String part,
            @Query("order") String order,
            @Query("maxResults") String maxResults,
            @Query("key") String key,
            @Query("channelId") String channelId,
            @Query("q") String q );
}
