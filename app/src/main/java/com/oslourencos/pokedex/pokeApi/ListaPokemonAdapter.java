package com.oslourencos.pokedex.pokeApi;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.oslourencos.pokedex.R;
import com.oslourencos.pokedex.models.Pokemon;

import java.util.ArrayList;

/**
 * Created by conceicao on 23/06/17.
 */

public class ListaPokemonAdapter extends RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder>{

    private ArrayList<Pokemon> dataset;
    private Context context;

    public ListaPokemonAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itens_pokemon, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ListaPokemonAdapter.ViewHolder holder, int position) {
        Pokemon p = dataset.get(position);
        holder.name.setText(p.getName());

        // recuperar imagem - http://pokeapi.co/media/sprites/pokemon/25.png
        /// adiciona no imageview centralizada
        Glide.with(context)
        .load("http://pokeapi.co/media/sprites/pokemon/"+p.getNumber()+".png")
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(holder.photo);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void addPokemon(ArrayList<Pokemon> listPokemons) {
        dataset.addAll(listPokemons);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView photo;
        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);

            photo = (ImageView) itemView.findViewById(R.id.photo);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }

}
