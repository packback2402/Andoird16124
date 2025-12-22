package com.example.btandroid1612.ui.list

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.btandroid1612.R
import com.example.btandroid1612.viewmodel.StudentViewModel


class StudentListFragment : Fragment(R.layout.fragment_student_list) {

    private val vm: StudentViewModel by activityViewModels()
    private lateinit var adapter: StudentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val rv = view.findViewById<RecyclerView>(R.id.rvStudents)
        val btnAdd = view.findViewById<Button>(R.id.btnAdd)

        adapter = StudentAdapter(
            data = emptyList(),
            onClick = { s ->
                val action = StudentListFragmentDirections.actionListToEdit(s.mssv)
                findNavController().navigate(action)
            },
            onDelete = { s ->
                vm.deleteStudent(s.mssv)
            }
        )

        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = adapter

        vm.students.observe(viewLifecycleOwner) { list ->
            adapter.submit(list.toList())
        }

        btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_list_to_add)
        }
    }
}