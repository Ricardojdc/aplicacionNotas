package com.ricardo.aplicacionnotas.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    //Operaciones asociadas a la clase User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("UPDATE usuarios SET name = :newName, name = :newEmail WHERE name = :oldName")
    suspend fun updateUserName(oldName: String, newName: String, newEmail: String)

    @Query("DELETE FROM usuarios WHERE name = :name")
    suspend fun deleteUserByName(name: String)

    @Query("SELECT * FROM usuarios WHERE name like :nombre LIMIT 1")
    suspend fun getUserByName(nombre: String): List<User>

    @Query("SELECT * FROM usuarios WHERE name like :nombre and pass like :pass LIMIT 1")
    suspend fun getUserByCredentials(nombre: String, pass: String): List<User>

    @Query("SELECT * FROM usuarios")
    suspend fun getAllUsers(): List<User>

    @Query("UPDATE usuarios SET profile_image = :profileImage WHERE id = :userId")
    suspend fun updateProfileImage(userId: Long, profileImage: ByteArray)

    @Query("UPDATE usuarios SET pass = :newPass WHERE name = :username")
    suspend fun updatePasswordByUsername(username: String, newPass: String)



}