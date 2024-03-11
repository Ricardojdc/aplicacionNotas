package com.ricardo.aplicacionnotas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.ricardo.aplicacionnotas.login.Login

class Main : AppCompatActivity() {

    private lateinit var perfil: Button
    private lateinit var crearNota: Button
    private lateinit var verNotas: Button
    private lateinit var desconectar: Button
    private lateinit var foto : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        perfil = findViewById(R.id.btnPerfil)
        crearNota = findViewById(R.id.btnAddNote)
        verNotas = findViewById(R.id.btnMyNotes)
        desconectar = findViewById(R.id.desconectar)
        foto = findViewById(R.id.foto)

        foto.setImageBitmap(Login.datos.image)

        perfil.setOnClickListener(){

            var i = Intent(this, Perfil::class.java)
            startActivity(i)

        }

        crearNota.setOnClickListener(){

            var i = Intent(this,addNote::class.java)
            startActivity(i)

        }

        verNotas.setOnClickListener(){

            var i = Intent(this,viewNotes::class.java)
            startActivity(i)
        }

        desconectar.setOnClickListener(){

            finishAffinity()

        }


    }
}