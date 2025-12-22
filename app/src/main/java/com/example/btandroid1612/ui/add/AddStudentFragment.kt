package com.example.btandroid1612.ui.add

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.btandroid1612.databinding.FragmentAddStudentBinding
import com.example.btandroid1612.viewmodel.StudentViewModel
import com.example.btandroid1612.model.Student
import com.example.btandroid1612.R


class AddStudentFragment : Fragment(R.layout.fragment_add_student) {

    private val vm: StudentViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentAddStudentBinding.bind(view)
        binding.vm = vm
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnSave.setOnClickListener {
            val mssv = binding.edtMssv.text.toString().trim()
            val name = binding.edtName.text.toString().trim()
            val phone = binding.edtPhone.text.toString().trim()
            val address = binding.edtAddress.text.toString().trim()

            if (mssv.isEmpty() || name.isEmpty()) {
                Toast.makeText(requireContext(), "Thiếu MSSV hoặc Họ tên", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val ok = vm.addStudent(Student(mssv, name, phone, address))
            if (!ok) {
                Toast.makeText(requireContext(), "MSSV đã tồn tại", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            findNavController().popBackStack()
        }
    }
}