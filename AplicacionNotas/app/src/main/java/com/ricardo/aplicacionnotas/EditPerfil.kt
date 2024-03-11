package com.ricardo.aplicacionnotas

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import com.ricardo.aplicacionnotas.database.AppDatabase
import com.ricardo.aplicacionnotas.login.Login
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class EditPerfil : AppCompatActivity() {

    private lateinit var volver : Button
    private lateinit var foto : Button
    private lateinit var username : EditText
    private lateinit var pass : EditText
    private lateinit var guardar : Button
    private lateinit var db : AppDatabase
    private lateinit var image : Bitmap
    private lateinit var  camara : Button

    private val PICK_IMAGE_REQUEST = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_perfil)

        volver = findViewById(R.id.btnVolver)
        foto = findViewById(R.id.addPhoto)
        username = findViewById(R.id.editUsername)
        pass = findViewById(R.id.editPass)
        guardar = findViewById(R.id.btnGuardar)
        camara = findViewById(R.id.btnCamara)
        db = AppDatabase(this)

        username.isClickable = false
        username.isEnabled = false

        username.setText(Login.datos.login)
        pass.setText(Login.datos.password)

        foto.setOnClickListener(){

            CoroutineScope(Dispatchers.IO).launch {
//                image = getBitmapFromDrawable(this@EditPerfil, R.drawable.fondo)
//
//                val profileImageByteArray = convertBitmapToByteArray(image)
//                db.userDao().updateProfileImage(Login.id.toLong(), profileImageByteArray)

                val pickImageIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                pickImageIntent.type = "image/*"
                startActivityForResult(pickImageIntent, PICK_IMAGE_REQUEST)


//                val profileImageByteArray = convertBitmapToByteArray(Login.datos.image!!)
//                db.userDao().updateProfileImage(Login.id.toLong(), profileImageByteArray)
            }

        }

        camara.setOnClickListener(){

            val Intent3 = Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA)
            startActivity(Intent3)

        }


        guardar.setOnClickListener(){

            CoroutineScope(Dispatchers.IO).launch {

                db.userDao().updatePasswordByUsername(Login.datos.login,pass.text.toString())

            }
            var i = Intent(this, Perfil::class.java)
            startActivity(i)

        }

        volver.setOnClickListener(){

            var i = Intent(this, Perfil::class.java)
            startActivity(i)

        }

    }

    fun convertBitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        return stream.toByteArray()
    }

    fun getBitmapFromDrawable(context: Context, resourceId: Int): Bitmap {
        return BitmapFactory.decodeResource(context.resources, resourceId)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {

            data?.data?.let { selectedImageUri ->

                CoroutineScope(Dispatchers.IO).launch {

                    contentResolver.openInputStream(selectedImageUri)?.use { inputStream ->

                        Login.datos.image = BitmapFactory.decodeStream(inputStream)
                        db.userDao().updateProfileImage(Login.id.toLong(), bitmapToByteArray(Login.datos.image!!))
                    }
                }
            }
        }
    }

    fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream)
        return outputStream.toByteArray()
    }
}