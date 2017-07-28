package com.oslourencos.pokedex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.oslourencos.pokedex.models.Pokemon;
import com.oslourencos.pokedex.models.PokemonResponse;
import com.oslourencos.pokedex.pokeApi.ListaPokemonAdapter;
import com.oslourencos.pokedex.pokeApi.PokeApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "POKEDEX" ;
    private Retrofit retrofit;
    public String url = "http://pokeapi.co/api/v2/";

    private RecyclerView recycleview;
    private ListaPokemonAdapter listaAdapter;
    private int offset;
    private boolean carregarMais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycleview = (RecyclerView) findViewById(R.id.recyclewiew);
        listaAdapter = new ListaPokemonAdapter(this);
        recycleview.setAdapter(listaAdapter);
        recycleview.setHasFixedSize(true);

        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recycleview.setLayoutManager(layoutManager);

        // detectando o movimento de scroll do recycleview
        // recuperando o ultimo valor da lista
        recycleview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // tentando desccobrir se o scroll está no final
                if(dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if(carregarMais) {

                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            // chegamos ao final
                            Log.i(TAG, "Final do RecycleView");
                            carregarMais = false;
                            offset += 20;
                            CarregarDados(offset);
                        }

                    }
                }
            }
        });


        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        carregarMais = true;
        offset = 0;
        CarregarDados(offset);

    }

    private void CarregarDados(int offset) {
        PokeApiService service = retrofit.create(PokeApiService.class);
        Call<PokemonResponse> responseCall = service.CarregarListaPokemon(20,offset);

        responseCall.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {

                carregarMais = true;

                if(response.isSuccessful()) {

                    PokemonResponse pokemonresponse = response.body();
                    ArrayList<Pokemon> listPokemons = pokemonresponse.getResults();

                   listaAdapter.addPokemon(listPokemons);

                } else {
                    Log.e(TAG , "onResponse : " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                carregarMais = true;
                Log.e(TAG , "onFailure : " + t.getMessage());
            }
        });

    }
}
