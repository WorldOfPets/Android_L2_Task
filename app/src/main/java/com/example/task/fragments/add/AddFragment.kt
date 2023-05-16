package com.example.task.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.task.R
import com.example.task.model.User
import com.example.task.viewmodel.UserViewModel
import com.example.task.databinding.FragmentAddBinding
import com.example.task.model.Role


class addFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.addButton.setOnClickListener {
            insertDataToDatabase()
        }
        return binding.root
    }

    private fun insertDataToDatabase() {
        val firstName = binding.firstNameEditText.text.toString()
        val lastName = binding.lastNameEditText.text.toString()
        val age = binding.ageEditText.text

        if(inputCheck(firstName, lastName, age)){
            userViewModel.getRole("Admin")
            userViewModel.readRole!!.observe(viewLifecycleOwner){
                Toast.makeText(requireContext(), it.roleName, Toast.LENGTH_LONG).show()
                val user = User(0, firstName, lastName, Integer.parseInt(age.toString()), it.id)
                userViewModel.addUser(user)
                Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_addFragment_to_listFragment)
            }
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable) : Boolean{
        println(TextUtils.isEmpty(firstName))
        println(TextUtils.isEmpty(lastName))
        println(age.isEmpty())
        return !(TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || age.isEmpty())
    }
}