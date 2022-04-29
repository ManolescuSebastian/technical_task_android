package com.tekydevelop.techicaltestsm.user

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tekydevelop.domain.model.User
import com.tekydevelop.domain.state.Status
import com.tekydevelop.techicaltestsm.R
import com.tekydevelop.techicaltestsm.add.AddUserViewModel
import com.tekydevelop.techicaltestsm.databinding.UserlistFragmentBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class UserListFragment : Fragment() {

    private var _binding: UserlistFragmentBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by activityViewModels()
    private val addUserViewModel: AddUserViewModel by activityViewModels()

    private lateinit var userAdapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UserlistFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initUserRecyclerView()
        initData()
        initObservers()
    }

    private fun initData() {
        userViewModel.getUsers()
    }

    private fun initAdapter() {
        userAdapter = UsersAdapter {
            deleteUserDialog(it)
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            userViewModel.userData.collectLatest {
                when (it.status) {
                    Status.LOADING -> {
                        Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
                    }
                    Status.ERROR -> {
                        Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS -> {
                        userAdapter.submitList(it.data)
                    }
                }
            }
            return@launch
        }

        lifecycleScope.launchWhenStarted {
            userViewModel.isUserDeleted.collectLatest {
                when (it.status) {
                    Status.LOADING -> {
                        Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
                    }
                    Status.ERROR -> {
                        Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS -> {
                        val user = userAdapter.currentList.toMutableList()
                        user.remove(it.data)
                        userAdapter.submitList(user) {
                            Toast.makeText(requireContext(), "User deleted", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            addUserViewModel.addedUser.collectLatest {
                when (it.status) {
                    Status.LOADING -> {
                        Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
                    }
                    Status.ERROR -> {
                        Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS -> {
                        val user = userAdapter.currentList.toMutableList()
                        user.add(0, it.data)
                        userAdapter.submitList(user) {
                            Toast.makeText(requireContext(), "User added", Toast.LENGTH_SHORT).show()
                        }
                        binding.usersRecycler.smoothScrollToPosition(0)
                    }
                }
            }
        }
    }

    private fun initUserRecyclerView() {
        binding.usersRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = userAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))
        }
    }

    private fun deleteUserDialog(user: User) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle(R.string.delete_user_title)
        alertDialogBuilder.setMessage(R.string.delete_user_message)
        alertDialogBuilder.setPositiveButton(R.string.delete) { _: DialogInterface, _: Int ->
            userViewModel.deleteUser(user)
        }
        alertDialogBuilder.setNegativeButton(R.string.cancel) { _: DialogInterface, _: Int -> }
        alertDialogBuilder.create()
        alertDialogBuilder.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}