package com.example.absolutegame.presentation.auth.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.absolutegame.Application
import com.example.absolutegame.presentation.home.HomeActivity
import com.example.absolutegame.databinding.ActivityRegisterBinding
import com.example.absolutegame.di.ViewModelFactory

class RegisterActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, RegisterActivity::class.java))
        }
    }

    private var binding: ActivityRegisterBinding? = null
    private val viewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance((application as Application).provider, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        observeLiveData()

        binding?.buttonRegister?.setOnClickListener {
            viewModel.register(
                username = binding?.etUsername?.text?.toString().orEmpty(),
                password = binding?.etPassword?.text?.toString().orEmpty(),
                confirmPassword = binding?.etConfirmPassword?.text?.toString().orEmpty(),
                email = binding?.etEmail?.text?.toString().orEmpty(),
            )
        }
    }

    private fun observeLiveData() {
        viewModel.loading.observe(this, ::handleLoading)
        viewModel.error.observe(this, ::handleError)
        viewModel.register.observe(this) { handleRegister() }
    }

    private fun handleLoading(isLoading: Boolean) {
        binding?.flipperButton?.displayedChild = if (isLoading) 1 else 0
    }

    private fun handleError(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun handleRegister() {
        Toast.makeText(this, "Register succeed!", Toast.LENGTH_SHORT).show()
        HomeActivity.startActivity(this)
        finish()
    }
}