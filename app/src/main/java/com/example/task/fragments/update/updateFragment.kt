package com.example.task.fragments.update

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.task.R
import com.example.task.databinding.FragmentUpdateBinding
import com.example.task.model.Role
import com.example.task.model.User
import com.example.task.viewmodel.UserViewModel

class updateFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private var model: User? = null
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        val bundle = this.arguments
        val menuHost: MenuHost = requireActivity()
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        //setHasOptionsMenu(true)
        menuHost.addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.delete_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId){
                    R.id.menu_delete -> {
                        deleteUser()
                        true
                    }
                    else -> false
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)


        //model = bundle.getParcelable<Parcelable>("user") as User
        if (bundle != null) {
            model = bundle.getParcelable<Parcelable>("user") as User
            binding.firstNameUpdateET.setText(model!!.firstName)
            binding.lastNameUpdateET.setText(model!!.lastName)
            binding.ageUpdateET.setText(model!!.age.toString())

            binding.updateButton.setOnClickListener{
                insertDataToDatabase(model!!.id)
            }
        }
        else{
            Toast.makeText(context, "(fragmentConfig as User).firstName", Toast.LENGTH_LONG).show()
        }




        return binding.root
    }

    private fun deleteUser(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            userViewModel.deleteUser(model!!)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_, _ -> }
        builder.setTitle("Delete ${model!!.firstName}?")
        builder.setMessage("Are you sure you want to delete ${model!!.firstName}?")
        builder.create().show()
    }
    private fun insertDataToDatabase(userId: Int) {
        val firstName = binding.firstNameUpdateET.text.toString()
        val lastName = binding.lastNameUpdateET.text.toString()
        val age = binding.ageUpdateET.text

        if(inputCheck(firstName, lastName, age)){
            val user = User(userId, firstName, lastName, Integer.parseInt(age.toString()), 1)

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

