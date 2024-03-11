package com.ricardo.aplicacionnotas

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.ricardo.aplicacionnotas.database.AppDatabase
import com.ricardo.aplicacionnotas.database.Notas
import com.ricardo.aplicacionnotas.login.Login
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class addNote : AppCompatActivity() {

    private lateinit var titulo : TextView
    private lateinit var nota : EditText
    private lateinit var guardar : Button
    private lateinit var volver : Button
    private lateinit var db : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        db = AppDatabase(this)
        guardar = findViewById(R.id.btnGuardar)
        volver = findViewById(R.id.btnVolver)
        titulo = findViewById(R.id.editTitulo)
        nota = findViewById(R.id.editNota)

        guardar.setOnClickListener(){

            CoroutineScope(Dispatchers.IO).launch {

                db.notasDAO().insertNotas(Notas(title = titulo.text.toString(), note = nota.text.toString(), idUser = Login.datos.id.toLong()))

            }
            showToast("Nota guardada", this@addNote)

            var i = Intent(this, Main::class.java)
            startActivity(i)

        }

        volver.setOnClickListener(){

            var i = Intent(this, Main::class.java)
            startActivity(i)


        }


    }

    private fun showToast(message: String, context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}