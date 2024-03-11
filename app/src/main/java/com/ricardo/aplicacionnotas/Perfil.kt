package com.ricardo.aplicacionnotas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.ricardo.aplicacionnotas.login.Login

class Perfil : AppCompatActivity() {

    private lateinit var editar : Button
    private lateinit var volver : Button
    private lateinit var usuario : TextView
    private lateinit var contraseña: TextView
    private lateinit var imagen: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        editar = findViewById(R.id.btnEdit)
        volver = findViewById(R.id.btnVolver)

        usuario = findViewById(R.id.username_value)
        usuario.text = Login.datos.login

        imagen = findViewById(R.id.profile_image)

        imagen.setImageBitmap(Login.datos.image)

        //Action listeners


        editar.setOnClickListener(){

            var i = Intent(this, EditPerfil::class.java)
            startActivity(i)

        }

        volver.setOnClickListener(){

            var i = Intent(this, Main::class.java)
            startActivity(i)

        }


    }
}