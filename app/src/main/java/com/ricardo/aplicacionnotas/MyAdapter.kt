package com.ricardo.aplicacionnotas


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ricardo.aplicacionnotas.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyAdapter(private val dataList: List<dataModel>) :
    RecyclerView.Adapter<MyAdapter.YourViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YourViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item, parent, false)
        return YourViewHolder(view)
    }

    override fun onBindViewHolder(holder: YourViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class YourViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        private var id  = ""
        private val borrar: Button = itemView.findViewById(R.id.btnBorrar)
        private val titulo: TextView = itemView.findViewById(R.id.titulo)
        private val descripcion: TextView = itemView.findViewById(R.id.nota)
        lateinit var db : AppDatabase

        fun bind(data: dataModel) {

            titulo.text = data.titulo
            descripcion.text = data.nota
            id = data.id.toString()

            borrar.setOnClickListener(){

                db = AppDatabase(itemView.context)

                CoroutineScope(Dispatchers.IO).launch {

                    db.notasDAO().deleteNotaById(id.toLong())

                    }

                    var i = Intent(itemView.context, viewNotes::class.java)
                    itemView.context.startActivity(i)

                }


            }

        }




    }

