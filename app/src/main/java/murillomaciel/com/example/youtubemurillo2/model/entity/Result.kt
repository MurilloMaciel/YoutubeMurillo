package murillomaciel.com.example.youtubemurillo2.model.entity

data class Result(
        val regionCode: String,
        val pageInfo: PageInfo,
        val items: List<Items>,
)