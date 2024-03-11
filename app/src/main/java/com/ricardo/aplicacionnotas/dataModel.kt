package com.ricardo.aplicacionnotas

// Modelo de datos para cargar en la recyclerView

data class dataModel(
    val id: Long,
    val titulo: String,
    val nota: String,
    val idUser: Long
)