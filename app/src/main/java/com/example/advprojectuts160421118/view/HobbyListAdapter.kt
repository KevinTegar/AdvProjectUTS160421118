package com.example.advprojectuts160421118.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.advprojectuts160421118.databinding.HobbyListItemBinding
import com.example.advprojectuts160421118.model.Hobby
import com.squareup.picasso.Picasso

class HobbyListAdapter (val hobbyList:ArrayList<Hobby>)
    : RecyclerView.Adapter<HobbyListAdapter.HobbyViewHolder>(){
    class HobbyViewHolder(var binding: HobbyListItemBinding)
        :RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HobbyViewHolder {
        val binding = HobbyListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return HobbyViewHolder(binding)

    }
    override fun onBindViewHolder(holder: HobbyViewHolder, position: Int) {
        holder.binding.txtIsi.text = hobbyList[position].desc
        holder.binding.txtJudul.text = hobbyList[position].judul
        holder.binding.txtNama.text = hobbyList[position].nama
        Picasso.get().load(hobbyList[position].photoUrl).into(holder.binding.image)
        holder.binding.btnPrevious.setOnClickListener {
            val action = HobbyListFragmentDirections.actionHobbyListFragmentToDetailHobbyFragment(hobbyList[position].judul,hobbyList[position].nama,hobbyList[position].isi,hobbyList[position].photoUrl)
            Navigation.findNavController(it).navigate(action)
        }


    }
    override fun getItemCount(): Int {
        return hobbyList.size
    }


    fun updateHobbyList(newHobbyList: ArrayList<Hobby>) {
        hobbyList.clear()
        hobbyList.addAll(newHobbyList)
        notifyDataSetChanged()
    }

}