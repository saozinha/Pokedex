package com.oslourencos.pokedex.models;

import java.util.ArrayList;

/**
 * Created by conceicao on 22/06/17.
 */

public class PokemonResponse {

    private ArrayList<Pokemon> results;

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }
}
