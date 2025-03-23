package com.example.btth04_db.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons")
data class Person(
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") var  name : String,
    @ColumnInfo(name = "phonenumber") var phoneNumber : String
)
