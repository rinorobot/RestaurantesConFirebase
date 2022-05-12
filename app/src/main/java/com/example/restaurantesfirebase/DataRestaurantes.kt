package com.example.restaurantesfirebase

import com.google.gson.annotations.SerializedName


data class DataRestaurantes(@SerializedName("status") var status: String, @SerializedName("message") var lista_restaurantes: List<Restaurante>)

