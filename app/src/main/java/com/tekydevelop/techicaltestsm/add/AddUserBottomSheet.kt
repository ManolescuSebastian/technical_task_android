package com.tekydevelop.techicaltestsm.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tekydevelop.domain.model.User
import com.tekydevelop.techicaltestsm.R
import com.tekydevelop.techicaltestsm.databinding.AddUserBottomSheetBinding
import com.tekydevelop.techicaltestsm.utils.UserStatus
import com.tekydevelop.techicaltestsm.utils.Utils
import kotlinx.coroutines.flow.collectLatest


class  AddUserBottomSheet : BottomSheetDialogFragment() {

    private var _binding: AddUserBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val addUserViewModel: AddUserViewModel by activityViewModels()

    private lateinit var selectedGender: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddUserBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        initActions()
        initObservers()
    }

    private fun initUI() {
        val items = resources.getStringArray(R.array.genders_string_array)
        val adapter = ArrayAdapter(requireContext(), R.layout.gender_dropdown_item, items)
        binding.genderAutocomplete.setAdapter(adapter)

        binding.genderAutocomplete.setOnItemClickListener{ adapterView: AdapterView<*>, _: View, i: Int, _: Long ->
            selectedGender = adapterView.adapter.getItem(i).toString()
        }
    }

    private fun initActions() {
        binding.statusSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.statusLabel.text = resources.getString(R.string.active)
            } else {
                binding.statusLabel.text = resources.getString(R.string.inactive)
            }
        }

        binding.addUserAction.setOnClickListener {
            if(::selectedGender.isInitialized && selectedGender.isNotEmpty()){
                addUserData()
            }else{
                Toast.makeText(requireContext(), "Please select gender", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            addUserViewModel.isUserAdded.collectLatest {
                when(it){
                    UserStatus.ADDED -> {
                        dismiss()
                        Toast.makeText(requireContext(), "User added!", Toast.LENGTH_SHORT).show()
                    }

                    UserStatus.FAILED -> {
                        Toast.makeText(requireContext(), "Error! User not added!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun addUserData(){
        if(Utils.validateEmailAddress(binding.emailInput.text.toString())){
            addUserViewModel.addUser(userData())
        }else{
            Toast.makeText(requireContext(), "Invalid Email", Toast.LENGTH_SHORT).show()
        }
    }

    private fun userData(): User {
        val name = binding.nameInput.text.toString()
        val email = binding.emailInput.text.toString()
        val status = if (binding.statusSwitch.isChecked) "active" else "inactive"
        return User(null, name, email, selectedGender, status)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}