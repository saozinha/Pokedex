package com.oslourencos.pokedex.models;

/**
 * Created by conceicao on 22/06/17.
 */

public class Pokemon {
    private int number;
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumber() {
        // pegar o numero da url - usamos split para separar
        String [] urlpartes = url.split("/");
        // pega a ultima posicao que ira conter o numero
        return Integer.parseInt(urlpartes[urlpartes.length -1]);
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
