package com.example.btandroid1612.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.btandroid1612.data.AppDatabase
import com.example.btandroid1612.model.Student

class StudentViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase
        .getInstance(application)
        .studentDao()

    private val _students = MutableLiveData<List<Student>>()
    val students: LiveData<List<Student>> = _students

    init {
        loadStudents()
    }

    private fun loadStudents() {
        _students.value = dao.getAll()
    }

    fun addStudent(student: Student): Boolean {
        return try {
            dao.insert(student)
            loadStudents()
            true
        } catch (e: Exception) {
            false // MSSV trùng
        }
    }

    fun getStudentByMssv(mssv: String): Student? {
        return dao.getByMssv(mssv)
    }

    fun updateStudent(oldMssv: String, newStudent: Student): Boolean {
        return try {
            if (oldMssv != newStudent.mssv) {
                // đổi MSSV → xóa cũ, thêm mới
                dao.deleteByMssv(oldMssv)
                dao.insert(newStudent)
            } else {
                dao.update(newStudent)
            }
            loadStudents()
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteStudent(mssv: String) {
        dao.deleteByMssv(mssv)
        loadStudents()
    }
}