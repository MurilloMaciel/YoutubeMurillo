package murillomaciel.com.example.youtubemurillo2.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import murillomaciel.com.example.youtubemurillo2.R
import murillomaciel.com.example.youtubemurillo2.model.entity.Items

class VideoAdapter(
        private var videos: List<Items>,
        private val listener: VideoListener
) : RecyclerView.Adapter<VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.view_video, parent, false)
        return VideoViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videos[position])
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    fun updateVideos(videos: List<Items>) {
        this.videos = videos
        notifyDataSetChanged()
    }
}