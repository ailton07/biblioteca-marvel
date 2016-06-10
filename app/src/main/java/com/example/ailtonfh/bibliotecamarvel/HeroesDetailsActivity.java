package com.example.ailtonfh.bibliotecamarvel;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ailtonfh.bibliotecamarvel.api.APIClient;
import com.example.ailtonfh.bibliotecamarvel.api.GetComicsDelegate;
import com.example.ailtonfh.bibliotecamarvel.comicsModels.ComicsModel;

import com.example.ailtonfh.bibliotecamarvel.heroesModels.Result;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import retrofit2.Response;

public class HeroesDetailsActivity extends AppCompatActivity implements GetComicsDelegate {

    private ImageLoader imageLoader;
    ProgressDialog dialog;
    APIClient client;
    Result result;
    List<com.example.ailtonfh.bibliotecamarvel.comicsModels.Result> comics;
    private AdapterComics adapterComics;
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes_details);

        // Recebimento do Objet passado
        Bundle bundle = getIntent().getExtras();
        String resultAsJson = bundle.getString("result");
        result = Result.fromJson(resultAsJson);

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));

        // Progress Bar
        dialog = ProgressDialog.show(HeroesDetailsActivity.this, "",
                "Carregando. Por favor, aguarde...", true);

        // Uso da API
        client = new APIClient(this);
        client.getComics(result.getId().toString());

        // Setando campos já carregados
        TextView textView_nome = (TextView) findViewById(R.id.heroes_nome);
        textView_nome.setText(result.getName());
        setTitle(result.getName());

        final ImageView imageView = (ImageView) findViewById(R.id.heroes_imagem);

        imageLoader = ImageLoader.getInstance();

        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).build();
        imageLoader.displayImage(result.getThumbnail().getPath()+"/landscape_incredible."+result.getThumbnail().getExtension(), imageView, options);

        TextView textView_descricao = (TextView) findViewById(R.id.heroes_descricao);
        if(result.getDescription() != "") {
            textView_descricao.setText(result.getDescription());
        }

        // Dialog
        builder = new AlertDialog.Builder(this);

        // ImageView
        ListView comicsListView = (ListView) findViewById(R.id.listViewComics);
        assert comicsListView != null;
        comicsListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                com.example.ailtonfh.bibliotecamarvel.comicsModels.Result item = (com.example.ailtonfh.bibliotecamarvel.comicsModels.Result) parent.getItemAtPosition(position);

                final AlertDialog dialog = builder.create();
                LayoutInflater inflater = getLayoutInflater();
                View dialogLayout = inflater.inflate(R.layout.thumbnail_image, null);

                ImageView imageViewDestino =(ImageView) dialogLayout.findViewById(R.id.thumbnail_IMAGEVIEW);
                imageLoader = ImageLoader.getInstance();

                DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).build();
                imageLoader.displayImage(item.getThumbnail().getPath()+"/portrait_uncanny."+item.getThumbnail().getExtension(), imageViewDestino, options);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setView(dialogLayout);

                dialog.show();


            }
        });

    }


    @Override
    public void manageComics(Response<ComicsModel> response) {
        //Log.e("Comic", response.body().getData().getTotal().toString());
        comics = response.body().getData().getResults();

        ListView heroesListView = (ListView) findViewById(R.id.listViewComics);
        adapterComics = new AdapterComics(comics, this, imageLoader);

        heroesListView.setAdapter(adapterComics);


        dialog.cancel();



    }

    @Override
    public void manageComicsError(Response<ComicsModel> response) {
        Log.e("API Error", "ERROOO Comics  0 " + response.code());
        APIClient client0 = new APIClient(this);
        client0.getComics(result.getId().toString());


    }
}
