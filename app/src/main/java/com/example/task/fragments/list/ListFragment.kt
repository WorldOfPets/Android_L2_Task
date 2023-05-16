package com.example.task.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task.R
import com.example.task.viewmodel.UserViewModel
import com.example.task.databinding.FragmentListBinding
import com.example.task.model.Role


class listFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val adapter = ListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.delete_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId){
                    R.id.menu_delete -> {
                        deleteAllUsers()
                        true
                    }
                    else -> false
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.addRole(Role(1, "Admin"))
        userViewModel.addRole(Role(1, "User"))
        userViewModel.readAllData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        return binding.root
    }
    private fun deleteAllUsers(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            userViewModel.deleteAllUsers()
        }
        builder.setNegativeButton("No"){_, _ -> }
        builder.setTitle("Delete all users?")
        builder.setMessage("Are you sure you want to delete all users?")
        builder.create().show()
    }
}