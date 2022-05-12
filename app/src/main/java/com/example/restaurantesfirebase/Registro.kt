package com.example.restaurantesfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Registro : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        auth = FirebaseAuth.getInstance()

        val btnRegistrarse = findViewById<Button>(R.id.btnRegistrarse)
        val btnIrInicio = findViewById<Button>(R.id.btnRegresar)

        btnRegistrarse.setOnClickListener {
            registrarse()
        }

        btnIrInicio.setOnClickListener {
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }


    }

    fun registrarse(){
        val nombre: String = findViewById<EditText>(R.id.etNameRegistro)?.text.toString().trim()
        val apellido: String = findViewById<EditText>(R.id.etApellidoRegistro)?.text.toString().trim()
        val correo: String = findViewById<EditText>(R.id.etCorreoRegistro)?.text.toString().trim().toLowerCase()
        val password: String = findViewById<EditText>(R.id.etPasswordRegistro)?.text.toString().trim()

        auth.createUserWithEmailAndPassword(correo,password).addOnCompleteListener {
            if (it.isSuccessful){

                db.collection("users").document(correo).set(
                    hashMapOf("nombre" to nombre,"apellido" to apellido, "correo" to correo, "password" to password)
                )

                Toast.makeText(this,"Registro exitoso", Toast.LENGTH_LONG).show()
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)

            }
            else{
                Toast.makeText(this,"Error en el registro"+it.exception.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

}