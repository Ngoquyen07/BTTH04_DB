package com.example.btth04_db.db.repostories

import com.example.btth04_db.db.dao.PersonDao
import com.example.btth04_db.db.entities.Person


class PersonRepo(private val personDao: PersonDao) {
    fun getAll() = personDao.getAll()

    suspend fun upsert(person: Person) {
        personDao.upsert(person) // Không cần CoroutineScope
    }

    suspend fun delete(id: Int) {
        personDao.delete(id)
    }
}
