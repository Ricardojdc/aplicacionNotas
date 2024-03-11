package com.ricardo.aplicacionnotas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ricardo.aplicacionnotas.database.AppDatabase
import com.ricardo.aplicacionnotas.login.Login
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class viewNotes : AppCompatActivity() {

    private lateinit var volver : Button
    private lateinit var recycler : RecyclerView
    private lateinit var adapter : Adapter
    private lateinit var db : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_notes)

        db = AppDatabase(this)

        volver = findViewById(R.id.btnVolver)

        recycler = findViewById(R.id.rvItems)
        recycler.layoutManager = LinearLayoutManager(this)

        val dataList = generateDataList()
        val adapter = MyAdapter(generateDataList())
        recycler.adapter = adapter

        //Action listener

        volver.setOnClickListener(){

            var i = Intent(this, Main::class.java)
            startActivity(i)

        }


    }

    /**
     * Función para rellenar la lista mutable con los datos de notas de un usuario concreto
     */

    private fun generateDataList(): List<dataModel> {

        var dataList = mutableListOf<dataModel>()

        CoroutineScope(Dispatchers.IO).launch {

            var notas = db.notasDAO().getNotasByUser(Login.datos.id.toLong())

            notas.forEach { nota ->
                val dataModel = dataModel(
                    nota.id,
                    nota.title,
                    nota.note,
                    nota.idUser
                )
                dataList.add(dataModel)
            }

        }

        return dataList
    }
    

}