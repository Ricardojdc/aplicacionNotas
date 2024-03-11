package com.ricardo.aplicacionnotas.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ricardo.aplicacionnotas.R
import com.ricardo.aplicacionnotas.dataModel

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

    inner class YourViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        private val titulo: TextView = itemView.findViewById(R.id.titulo)
        private val descripcion: TextView = itemView.findViewById(R.id.nota)

        fun bind(data: dataModel) {
            // Bind data to views

            titulo.text = data.titulo
            descripcion.text = data.nota
            // You can load image into fotoItem here using a library like Picasso or Glide
        }
    }
}
