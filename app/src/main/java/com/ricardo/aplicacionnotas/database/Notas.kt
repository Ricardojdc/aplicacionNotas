package com.ricardo.aplicacionnotas.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "notas",foreignKeys = [ForeignKey(entity = User::class,
    parentColumns = ["id"],
    childColumns = ["idUser"],
    onDelete = ForeignKey.CASCADE)])

/**
 * Constructor de la clase de datos
 */

data class Notas (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "note")
    val note: String,

    @ColumnInfo(name = "idUser")
    val idUser: Long,

    ){

}