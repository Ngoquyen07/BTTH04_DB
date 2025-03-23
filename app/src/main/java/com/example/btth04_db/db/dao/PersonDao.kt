package com.example.btth04_db.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.btth04_db.db.entities.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Query("SELECT * FROM persons")
    fun getAll(): Flow<List<Person>>

    @Upsert
    suspend fun upsert(person: Person)

    @Query("DELETE FROM persons WHERE id = :id")
    suspend fun delete(id: Int)

}