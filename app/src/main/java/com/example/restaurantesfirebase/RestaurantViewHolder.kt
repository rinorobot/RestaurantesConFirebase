package com.example.restaurantesfirebase

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RestaurantViewHolder(val view: View): RecyclerView.ViewHolder(view) {







    interface OnRestaurantClinkListener{
        fun onItemClick(nombre: String,image: String,calif: String,anio: String,costo: String,id:String, resenia: String,foto1:String,foto2:String,foto3:String,x:String,y:String)

    }



    fun bind(nombre: String,image: String,calif:String,anio: String,costo: String,id:String){
        val tvNombre = view.findViewById<TextView>(R.id.tvNombre)
        val tvAnio = view.findViewById<TextView>(R.id.tvAnio)
        val tvCalif = view.findViewById<TextView>(R.id.tvCalif)
        val tvCosto = view.findViewById<TextView>(R.id.tvCosto)
        val ivRestaurante = view.findViewById<ImageView>(R.id.iv_restaurante)


        Picasso.get().load(image).into(ivRestaurante)
        tvNombre.text = nombre
        tvAnio.text = anio
        tvCalif.text = calif
        tvCosto.text = costo

    }

}