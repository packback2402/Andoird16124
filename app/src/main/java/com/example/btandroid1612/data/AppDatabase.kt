package com.example.btandroid1612.data

import androidx.room.*
import com.example.btandroid1612.model.Student

@Dao
interface StudentDao {

    @Query("SELECT * FROM students")
    fun getAll(): List<Student>

    @Query("SELECT * FROM students WHERE mssv = :mssv LIMIT 1")
    fun getByMssv(mssv: String): Student?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(student: Student)

    @Update
    fun update(student: Student)

    @Query("DELETE FROM students WHERE mssv = :mssv")
    fun deleteByMssv(mssv: String)
}