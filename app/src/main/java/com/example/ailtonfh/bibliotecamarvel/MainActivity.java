package com.example.ailtonfh.bibliotecamarvel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


import com.example.ailtonfh.bibliotecamarvel.api.APIClient;
import com.example.ailtonfh.bibliotecamarvel.api.GetHeroesDelegate;
import com.example.ailtonfh.bibliotecamarvel.heroesModels.Models;
import com.example.ailtonfh.bibliotecamarvel.heroesModels.Result;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements GetHeroesDelegate {

    ProgressDialog dialog;
    APIClient client;
    private ImageLoader imageLoader;
    private AdapterHeroes adapterHeroes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView heroesListView = (ListView) findViewById(R.id.listView);
        heroesListView.setOnItemClickListener(new OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Result item = (Result) parent.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, HeroesDetailsActivity.class);

                String resultAsJson = item.toJson();
                intent.putExtra("result", resultAsJson);

                startActivity(intent);

            }
        });

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));

        // Progress Bar
        dialog = ProgressDialog.show(MainActivity.this, "",
                "Carregando. Por favor, aguarde...", true);

        client = new APIClient(this);
        client.getHeroes();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //Pega o Componente.
        SearchView mSearchView = (SearchView) menu.findItem(R.id.search)
                .getActionView();
        //Define um texto de ajuda:
        mSearchView.setQueryHint("Nome do Herói");

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("well", newText);

                adapterHeroes.getFilter().filter(newText);

                return false;
            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void manageHeroes(Response<Models> response) {
        List<Result> results = response.body().getData().getResults();

        ListView heroesListView = (ListView) findViewById(R.id.listView);
        adapterHeroes = new AdapterHeroes(results, this, imageLoader);

        heroesListView.setAdapter(adapterHeroes);

        dialog.cancel();


    }

    public void manageHeroesError(Response<Models> response) {
        Log.e("API Error", "ERROOO  0 " + response.code());
        // Gera nova requisicao até o resultado correto ser retornado
        APIClient client0 = new APIClient(this);
        client0.getHeroes();
    }

}

