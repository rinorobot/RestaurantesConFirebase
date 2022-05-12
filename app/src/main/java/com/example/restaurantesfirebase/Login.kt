package com.example.restaurantesfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegistrarse = findViewById<Button>(R.id.btnIrARegistro)

        btnLogin?.setOnClickListener {
            login()
        }

        btnRegistrarse.setOnClickListener {
            val intent = Intent(this,Registro::class.java)
            startActivity(intent)
        }
    }

    fun login(){
        val correo: String = findViewById<EditText>(R.id.etNombre)?.text.toString().trim()
        val password: String = findViewById<EditText>(R.id.etPassword)?.text.toString().trim()


        auth.signInWithEmailAndPassword(correo,password).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(this,"Login exitoso", Toast.LENGTH_LONG).show()
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)


            }
            else{
                Toast.makeText(this,"Error en el login"+it.exception.toString(), Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onStart() {
        super.onStart()

        val user: FirebaseUser? = auth.currentUser
        if (user!=null){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }


}