package com.ricardo.aplicacionnotas.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotasDAO {

    //Operaciones asociadas a la clase Notas

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotas(notas: Notas)

    @Query("SELECT * FROM Notas WHERE idUser = :userId")
    suspend fun getNotasByUser(userId: Long): List<Notas>

    @Query("DELETE FROM Notas WHERE idUser = :userId")
    suspend fun deleteNotasByUserId(userId: String)

    @Query("DELETE FROM Notas WHERE id = :id")
    suspend fun deleteNotaById(id: Long)

    @Query("DELETE FROM Notas")
    suspend fun deleteAllNotas()

}