 package murillomaciel.com.example.youtubemurillo2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import murillomaciel.com.example.youtubemurillo2.R;
import murillomaciel.com.example.youtubemurillo2.adapter.AdapterVideo;
import murillomaciel.com.example.youtubemurillo2.api.YoutubeService;
import murillomaciel.com.example.youtubemurillo2.helper.RetrofitConfig;
import murillomaciel.com.example.youtubemurillo2.helper.YoutubeConfig;
import murillomaciel.com.example.youtubemurillo2.listener.RecyclerItemClickListener;
import murillomaciel.com.example.youtubemurillo2.model.Items;
import murillomaciel.com.example.youtubemurillo2.model.Resultado;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

 public class MainActivity extends AppCompatActivity {

     //widgets
     private RecyclerView recyclerVideos;
     private AdapterVideo adapterVideo;
     private MaterialSearchView searchView;

     //retrofit
     private Retrofit retrofit;

     //parametros de recuperar videos
     private String part = "snippet";
     private String order = "date";
     private String maxResults = "20";
     private String key = YoutubeConfig.CHAVE_YOUTUBE_API;
     private String channelId = YoutubeConfig.CANAL_ID;
     private String q = "";
     private List<Items> videos = new ArrayList<>();
     private Resultado resultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicia os componentes
        recyclerVideos = findViewById(R.id.recyclerVideos_id);
        searchView = findViewById(R.id.searchView_id);

        //Configurações iniciais
        retrofit = RetrofitConfig.getRetrofit();


        //configurar a toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_id);
        toolbar.setTitle("youtube");
        setSupportActionBar(toolbar);

        //recupera videos
        recuperarVideos("");



        //Configura metodos do search view
        ConfigSearchView();

    }

    private void ConfigSearchView()
    {
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recuperarVideos(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                recuperarVideos("");
            }
        });
    }


    private void recuperarVideos(String pesquisa)
    {
//        Video video1 = new Video();
//        video1.setTitulo("Video 1 bom");
//        videos.add(video1);
//        Video video2 = new Video();
//        video2.setTitulo("Video 1 ruim");
//        videos.add(video2);

        q = pesquisa.replaceAll(" ","+");

        YoutubeService youtubeService = retrofit.create(YoutubeService.class);
        youtubeService.RecuperarVideos(part,order,maxResults,key,channelId,q).enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response)
            {
                Log.i("resultado", "resultado = " + response.toString());
                if (response.isSuccessful())
                {
                    resultado = response.body();
                    videos = resultado.items;
                    Log.i("resultado", "resultado = " + videos.get(0).snippet.thumbnails.high.url);
                    ConfigurarRecyclerView();

//                    Log.i("resultado", "resultado = " + resultado.items.get(0).snippet.title);
//                    Log.i("resultado", "resultado = " + resultado.items.get(0).snippet.title);



                }

            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {

            }
        });

    }


    public void ConfigurarRecyclerView()
    {
        adapterVideo = new AdapterVideo(videos,this);

        recyclerVideos.setHasFixedSize(true);
        recyclerVideos.setLayoutManager(new LinearLayoutManager(this));
        recyclerVideos.setAdapter(adapterVideo);

        recyclerVideos.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerVideos, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position)
            {
                Items video = videos.get(position);
                String idVideo = video.id.videoId;

                Intent intent  = new Intent(MainActivity.this, PlayerActivity.class);
                intent.putExtra("idVideo", idVideo);
                startActivity(intent);


            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));

    }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         MenuInflater inflater = getMenuInflater();
         inflater.inflate(R.menu.menu_main, menu);
         MenuItem item = menu.findItem(R.id.menu_search_id);
         searchView.setMenuItem(item);
         return true;
     }
 }


