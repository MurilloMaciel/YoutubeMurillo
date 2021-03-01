package murillomaciel.com.example.youtubemurillo2.model.entity

import java.io.Serializable

data class Video(
        val title: String,
        val description: String,
        val date: String,
        val thumbnail: String,
        val videoId: String,
) : Serializable