package com.oslourencos.pokedex.pokeApi;

import com.oslourencos.pokedex.models.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by conceicao on 22/06/17.
 */


public interface PokeApiService {

    // pokeapi.co/api/v2/pokemon/?limit=20&offset=80
    // indicamos o limit e offset das imagens
    // para isso usamos  as anotações @Query("Limit") e  @Query("Offset")
    // os paramentros de @query devem ser os mesmos da API
    // as anotações @query é para dizer ao retrofit que sao parametros da url
    @GET("pokemon")
    Call<PokemonResponse> CarregarListaPokemon(@Query("Limit") int limit, @Query("offset") int offset);

}
