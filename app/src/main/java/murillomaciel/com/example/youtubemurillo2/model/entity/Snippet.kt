package murillomaciel.com.example.youtubemurillo2.model.entity

data class Snippet(
        val publishedAt: String,
        val channelId: String,
        val title: String,
        val description: String,
        val channelTitle: String,
        val liveBroadcastContent: String,
        val thumbnails: SnippetThumbnails,
)