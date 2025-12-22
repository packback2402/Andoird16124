package com.example.btandroid1612.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.btandroid1612.R
import com.example.btandroid1612.model.Student

class StudentAdapter(
    private var data: List<Student>,
    private val onClick: (Student) -> Unit,
    private val onDelete: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.VH>() {

    fun submit(newData: List<Student>) {
        data = newData
        notifyDataSetChanged()
    }

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val tvMssv: TextView = v.findViewById(R.id.tvMssv)
        val tvName: TextView = v.findViewById(R.id.tvName)
        val btnDelete: Button = v.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val s = data[position]
        holder.tvMssv.text = s.mssv
        holder.tvName.text = s.fullName

        holder.itemView.setOnClickListener { onClick(s) }
        holder.btnDelete.setOnClickListener { onDelete(s) }
    }

    override fun getItemCount(): Int = data.size
}