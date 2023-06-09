package com.example.task.fragments.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Layout.Directions
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.MenuHost
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.task.R
import com.example.task.model.User
import com.example.task.databinding.CustomRowBinding
import com.example.task.databinding.FragmentUpdateBinding
import com.example.task.fragments.update.updateFragment
import com.example.task.model.UserWithRole

class ListAdapter:RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<UserWithRole>()

    class MyViewHolder(itemView: CustomRowBinding):RecyclerView.ViewHolder(itemView.root) {
        private var id:TextView = itemView.idTxt
        private var firstName:TextView = itemView.firstNameTxt
        private var lastName:TextView = itemView.lastNameTxt
        private var age:TextView = itemView.ageTxt
        private var rowLayout:ConstraintLayout = itemView.rowLayout
        fun ItemAdapter(onClickListener:(User)->Unit) {}
        @SuppressLint("ResourceType")
        fun bind(user: UserWithRole){
            id.text = user.id.toString()
            firstName.text = user.firstName
            lastName.text = user.roleName.roleName
            age.text = user.age.toString()
            rowLayout.setOnClickListener {
                val bundle = Bundle()
                var add_user = User(user.id, user.firstName, user.lastName, user.age, user.roleName.id)
                bundle.putParcelable("user", add_user)
                itemView.findNavController().navigate(R.id.action_listFragment_to_updateFragment, bundle)

            }
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
    fun setData(users: List<UserWithRole>){
        this.userList = users
        notifyDataSetChanged()
    }
}