package com.example.task.fragments.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task.R
import com.example.task.data.User
import com.example.task.databinding.CustomRowBinding

class ListAdapter:RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: CustomRowBinding):RecyclerView.ViewHolder(itemView.root) {
        private var id:TextView = itemView.idTxt
        private var firstName:TextView = itemView.firstNameTxt
        private var lastName:TextView = itemView.lastNameTxt
        private var age:TextView = itemView.ageTxt
        fun bind(user: User){
            id.text = user.id.toString()
            firstName.text = user.firstName
            lastName.text = user.lastName
            age.text = user.age.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding = CustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.bind(currentItem)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(users: List<User>){
        this.userList = users
        notifyDataSetChanged()
    }
}