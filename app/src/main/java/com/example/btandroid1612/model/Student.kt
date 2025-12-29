package com.example.btandroid1612.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey
    val mssv: String,

    val fullName: String,
    val phone: String,
    val address: String
)