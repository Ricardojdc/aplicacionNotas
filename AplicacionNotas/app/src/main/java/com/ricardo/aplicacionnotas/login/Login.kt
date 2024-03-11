package com.ricardo.aplicacionnotas.login

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.ricardo.aplicacionnotas.Main
import com.ricardo.aplicacionnotas.database.AppDatabase
import com.ricardo.aplicacionnotas.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



class Login : AppCompatActivity() {

    companion object datos{

        var login =""
        var password = ""
        var id = ""
        var image : Bitmap? = null
        var portrait : ImageView? = null

    }

    private lateinit var login: EditText
    private lateinit var pass: EditText
    private lateinit var acceso: Button
    private lateinit var registro: Button
    private lateinit var db: AppDatabase
    private lateinit var image : Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Inicialización de las variables de la vista

        login = findViewById(R.id.edit_nombre)
        pass = findViewById(R.id.edit_pass)

        acceso = findViewById(R.id.btn_login)
        registro = findViewById(R.id.btn_registrarse)

        db = AppDatabase(this)


        // Listeners de los botones y los campos de texto de la vista

        acceso.setOnClickListener(){


            CoroutineScope(Dispatchers.IO).launch {

                var existe = db.userDao().getUserByCredentials(login.text.toString(),pass.text.toString())

                if(existe.isEmpty()){

                    showToast("El usuario no existe",this@Login)

                }else{

                    if(existe[0].profileImage != null){

                        val bitmap = byteArrayToBitmap(existe[0].profileImage!!)
                        datos.image = bitmap
                    }



                    datos.login = login.text.toString()
                    datos.password = pass.text.toString()

                    datos.id = existe[0].id.toString()

                    showToast("Login correcto",this@Login)
                    val i = Intent(this@Login, Main::class.java)
                    startActivity(i)

                }

            }

        }

        registro.setOnClickListener(){

            val i = Intent(this, Registro::class.java)
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


    fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

}