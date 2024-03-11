package com.ricardo.aplicacionnotas.login

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ricardo.aplicacionnotas.R
import com.ricardo.aplicacionnotas.database.AppDatabase
import com.ricardo.aplicacionnotas.database.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

class Registro : AppCompatActivity() {

    private lateinit var login : EditText
    private lateinit var pass : EditText
    private lateinit var registro : Button
    private lateinit var volver : Button
    private lateinit var db : AppDatabase




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        login = findViewById(R.id.edit_nombre)
        pass = findViewById(R.id.edit_pass)
        registro = findViewById(R.id.btn_registro)
        volver = findViewById(R.id.btn_volver)
        db = AppDatabase(this)

        // Botón de registro

        registro.setOnClickListener(){

            val context = this@Registro

            CoroutineScope(Dispatchers.IO).launch {

                var existe = db.userDao().getUserByName(login.text.toString())

                if(existe.isEmpty()){

                    db.userDao().insertUser(User(name = login.text.toString(), pass = pass.text.toString()))

                    showToast("Registro correcto",this@Registro)

                    val i = Intent(this@Registro, Login::class.java)
                    startActivity(i)

                }else{

                    showToast("El usuario ya existe",this@Registro)

                }


            }


        }

        // Botón para volver al login

        volver.setOnClickListener(){

            var i = Intent(this, Login::class.java)
            startActivity(i)

        }

    }

    // Función para mostrar mensaje emergente

    private fun showToast(message: String, context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}