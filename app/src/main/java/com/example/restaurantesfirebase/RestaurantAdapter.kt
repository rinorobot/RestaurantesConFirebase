package com.example.restaurantesfirebase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RestaurantAdapter(val restaurantes: List<Restaurante>, val itemClickListener:RestaurantViewHolder.OnRestaurantClinkListener): RecyclerView.Adapter<RestaurantViewHolder>(){





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RestaurantViewHolder(layoutInflater.inflate(R.layout.item_restaurante,parent,false))
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val item = restaurantes[position].fotos[0]
        holder.bind(restaurantes[position].nombre,item,restaurantes[position].calificacion,restaurantes[position].anio,restaurantes[position].costo,restaurantes[position].id)
        holder.view.setOnClickListener {
            itemClickListener.onItemClick(restaurantes[position].nombre,restaurantes[position].fotos[0],restaurantes[position].calificacion,restaurantes[position].anio,restaurantes[position].costo,restaurantes[position].id,restaurantes[position].resenia,restaurantes[position].fotos[0],restaurantes[position].fotos[1],restaurantes[position].fotos[2],restaurantes[position].x,restaurantes[position].y)

        }
    }

    override fun getItemCount(): Int {
        return restaurantes.size

    }


}