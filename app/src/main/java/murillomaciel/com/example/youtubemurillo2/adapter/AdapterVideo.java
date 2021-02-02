package murillomaciel.com.example.youtubemurillo2.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import murillomaciel.com.example.youtubemurillo2.R;
import murillomaciel.com.example.youtubemurillo2.model.Items;

public class AdapterVideo extends RecyclerView.Adapter<AdapterVideo.MyViewHolder>
{
    private List<Items> videos = new ArrayList<>();
    private Context context;

    public AdapterVideo(List<Items> videos, Context context)
    {
        this.videos = videos;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adaptervideo, viewGroup, false);
        return new AdapterVideo.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        Items video = videos.get(position);
        myViewHolder.titulo.setText(video.snippet.title);
        String url = "teste";
        url = video.snippet.thumbnails.high.url;

        Picasso.get().load(url).into(myViewHolder.capa);

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView titulo;
        TextView descricao;
        TextView data;
        ImageView capa;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            titulo = itemView.findViewById(R.id.textoTitulo_id);
            capa = itemView.findViewById(R.id.imagemCapa_id);

        }
    }

}
