package com.example.task.fragments.update

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.task.R
import com.example.task.databinding.FragmentUpdateBinding
import com.example.task.model.User
import com.example.task.viewmodel.UserViewModel

class updateFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //var user = arguments?.getParcelable("user", User::class.java)
        //Toast.makeText(context, user?.firstName, Toast.LENGTH_LONG).show()


    }
    
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        val bundle = this.arguments

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        if (bundle != null) {
            var model = bundle.getParcelable<Parcelable>("user") as User

            binding.firstNameUpdateET.setText(model.firstName)
            binding.lastNameUpdateET.setText(model.lastName)
            binding.ageUpdateET.setText(model.age.toString())

            binding.updateButton.setOnClickListener{
                insertDataToDatabase(model.id)
            }
        }
        else{
            Toast.makeText(context, "(fragmentConfig as User).firstName", Toast.LENGTH_LONG).show()
        }


        return binding.root
    }

    private fun insertDataToDatabase(userId: Int) {
        val firstName = binding.firstNameUpdateET.text.toString()
        val lastName = binding.lastNameUpdateET.text.toString()
        val age = binding.ageUpdateET.text

        if(inputCheck(firstName, lastName, age)){
            val user = User(userId, firstName, lastName, Integer.parseInt(age.toString()))

            userViewModel.updateUser(user)
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_LONG).show()
            //navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
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

