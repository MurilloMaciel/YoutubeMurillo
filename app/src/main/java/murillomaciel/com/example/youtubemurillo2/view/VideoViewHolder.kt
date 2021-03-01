package murillomaciel.com.example.youtubemurillo2.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import murillomaciel.com.example.youtubemurillo2.model.entity.Items
import kotlinx.android.synthetic.main.view_video.view.*

class VideoViewHolder(itemView: View, private val listener: VideoListener) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener { listener.onClick(adapterPosition) }
    }

    fun bind(item: Items) = with(itemView) {
        Picasso.get().load(item.snippet.thumbnails.high.url).into(iv_thumbnail)
        tv_title.text = item.snippet.title
    }
}