package com.example.absolutegame.presentation.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.absolutegame.Application
import com.example.absolutegame.databinding.FragmentProfileBinding
import com.example.absolutegame.di.ViewModelFactory
import com.example.absolutegame.presentation.auth.login.LoginActivity
import com.example.absolutegame.presentation.profile.ProfileViewModel

class ProfileFragment : Fragment() {

    private var binding: FragmentProfileBinding? = null
    private val viewModel by activityViewModels<ProfileViewModel> {
        ViewModelFactory.getInstance((requireActivity().application as Application).provider,requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()

        viewModel.loadProfile()

        binding?.buttonLogout?.setOnClickListener {
            viewModel.logout()
        }
    }

    private fun observeLiveData() {
        viewModel.username.observe(viewLifecycleOwner, ::handleUsername)
        viewModel.email.observe(viewLifecycleOwner, ::handleEmail)
        viewModel.loading.observe(viewLifecycleOwner, ::handleLoading)
        viewModel.error.observe(viewLifecycleOwner, ::handleError)
        viewModel.logout.observe(viewLifecycleOwner, ::handleLogout)
    }

    private fun handleUsername(username: String?) {
        binding?.etUsername?.setText(username)
    }

    private fun handleEmail(email: String?) {
        binding?.etEmail?.setText(email)
    }

    private fun handleLoading(isLoading: Boolean) {
        binding?.flipperButton?.displayedChild = if (isLoading) 1 else 0
    }

    private fun handleError(error: String?) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    private fun handleLogout(isLogout: Boolean) {
        if (isLogout) {
            val context = requireContext()
            LoginActivity.startActivity(context)
            activity?.finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}