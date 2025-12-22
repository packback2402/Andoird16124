package com.example.btandroid1612.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.btandroid1612.model.Student

class StudentViewModel : ViewModel() {

    val students = MutableLiveData(mutableListOf<Student>())

    init {
        students.value = mutableListOf(
            Student("SV001", "Nguyễn Văn A", "0123456789", "Hà Nội"),
            Student("SV002", "Trần Thị B", "0987654321", "Đà Nẵng"),
            Student("SV003", "Lê Văn C", "0911222333", "TP.HCM")
        )
    }

    fun addStudent(student: Student): Boolean {
        val list = students.value ?: mutableListOf()
        if (list.any { it.mssv == student.mssv }) return false
        list.add(student)
        students.value = list
        return true
    }

    fun getStudentByMssv(mssv: String): Student? =
        students.value?.firstOrNull { it.mssv == mssv }


    fun updateStudent(oldMssv: String, newStudent: Student): Boolean {
        val list = students.value ?: return false

        // Nếu MSSV bị đổi thì check trùng
        if (oldMssv != newStudent.mssv &&
            list.any { it.mssv == newStudent.mssv }) {
            return false
        }

        val index = list.indexOfFirst { it.mssv == oldMssv }
        if (index == -1) return false

        list[index] = newStudent
        students.value = list
        return true
    }

    fun deleteStudent(mssv: String) {
        students.value = students.value
            ?.filter { it.mssv != mssv }
            ?.toMutableList()
    }
}