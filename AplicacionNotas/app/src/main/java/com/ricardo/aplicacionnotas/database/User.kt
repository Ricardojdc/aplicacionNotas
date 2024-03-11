package com.ricardo.aplicacionnotas.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")

/**
 * Constructor de la clase de datos
 */

data class User (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "pass")
    val pass: String,

    @ColumnInfo(name = "profile_image")
    val profileImage: ByteArray? = null

){


    // Función redefinida para mostrar los datos correctamente

    override fun toString(): String {

        return "Nombre: "+name +" Pass: "+pass
    }
}