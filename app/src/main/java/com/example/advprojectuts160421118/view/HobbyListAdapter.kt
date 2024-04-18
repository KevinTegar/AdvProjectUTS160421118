package com.example.advprojectuts160421118.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.advprojectuts160421118.databinding.HobbyListItemBinding
import com.example.advprojectuts160421118.model.Hobby

class HobbyListAdapter (val hobbyList:ArrayList<Hobby>)
    : RecyclerView.Adapter<HobbyListAdapter.CarViewHolder>(){
    class CarViewHolder(var binding: HobbyListItemBinding)
        :RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = HobbyListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return CarViewHolder(binding)

    }
    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.binding.txtIsi.text = hobbyList[position].id.toString()
        holder.binding.txtJudul.text = hobbyList[position].judul
        holder.binding.txtNama.text = hobbyList[position].nama
        holder.binding.btnDetail.setOnClickListener {
            val action = StudentListFragmentDirections.actionStudentDetail()
            Navigation.findNavController(it).navigate(action)
        }
    }
    override fun getItemCount(): Int {
        return hobbyList.size
    }


    fun updateStudentList(newCarList: ArrayList<Hobby>) {
        hobbyList.clear()
        hobbyList.addAll(newCarList)
        notifyDataSetChanged()
    }
}