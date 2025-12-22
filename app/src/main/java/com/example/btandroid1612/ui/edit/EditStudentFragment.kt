package com.example.btandroid1612.ui.edit

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.btandroid1612.R
import com.example.btandroid1612.databinding.FragmentEditStudentBinding
import com.example.btandroid1612.model.Student
import com.example.btandroid1612.viewmodel.StudentViewModel

class EditStudentFragment : Fragment(R.layout.fragment_edit_student) {

    private val vm: StudentViewModel by activityViewModels()
    private val args: EditStudentFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentEditStudentBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner

        val oldMssv = args.mssv
        val s = vm.getStudentByMssv(oldMssv)

        if (s == null) {
            Toast.makeText(requireContext(), "Không tìm thấy sinh viên", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
            return
        }

        // Fill dữ liệu ban đầu
        binding.edtMssv.setText(s.mssv)
        binding.edtName.setText(s.fullName)
        binding.edtPhone.setText(s.phone)
        binding.edtAddress.setText(s.address)

        binding.btnUpdate.setOnClickListener {
            val newMssv = binding.edtMssv.text.toString().trim()
            val name = binding.edtName.text.toString().trim()
            val phone = binding.edtPhone.text.toString().trim()
            val address = binding.edtAddress.text.toString().trim()

            if (newMssv.isEmpty() || name.isEmpty()) {
                Toast.makeText(requireContext(), "Thiếu MSSV hoặc Họ tên", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val ok = vm.updateStudent(
                oldMssv,
                Student(newMssv, name, phone, address)
            )

            if (!ok) {
                Toast.makeText(
                    requireContext(),
                    "MSSV bị trùng hoặc cập nhật thất bại",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            findNavController().popBackStack()
        }
    }
}