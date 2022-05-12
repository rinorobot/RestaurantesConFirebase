package com.example.restaurantesfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        val btnLista = findViewById<Button>(R.id.btnVerRestaurantes)
        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)
        val tvNombreBienvenido = findViewById<TextView>(R.id.tvNombreBienvenido)
        val  tvCorreoBienvenido = findViewById<TextView>(R.id.tvCorreoBienvenido)
        val tvApellidoBienvenido = findViewById<TextView>(R.id.tvApellidoBienvenido)

        val correo = auth.currentUser?.email.toString()

        db.collection("users").document(correo).get().addOnSuccessListener {
           tvNombreBienvenido.text = it.get("nombre") as String?
            tvCorreoBienvenido.text = it.get("correo") as String?
            tvApellidoBienvenido.text = it.get("apellido") as String?
        }


        btnCerrarSesion.setOnClickListener {
            auth = FirebaseAuth.getInstance()
            auth.signOut()
            Toast.makeText(this,"Se ha cerrado la sesión.", Toast.LENGTH_LONG).show()
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }

        btnLista.setOnClickListener {
            val intent = Intent(this,ListaRestaurantes::class.java)
            startActivity(intent)
        }
    }
}