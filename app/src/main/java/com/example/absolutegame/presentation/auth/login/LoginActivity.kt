package com.example.absolutegame.presentation.auth.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.absolutegame.Application
import com.example.absolutegame.presentation.auth.register.RegisterActivity
import com.example.absolutegame.databinding.ActivityLoginBinding
import com.example.absolutegame.di.ViewModelFactory
import com.example.absolutegame.helper.applyLanguage
import com.example.absolutegame.presentation.home.HomeActivity

class LoginActivity : AppCompatActivity() {

    companion object {
        fun provideIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }

        fun startActivity(context: Context) {
            context.startActivity(provideIntent(context))
        }
    }

    private var binding: ActivityLoginBinding? = null
    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance((application as Application).provider, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        observeViewModel()

        viewModel.checkLogin()

        binding?.buttonLogin?.setOnClickListener {
            viewModel.authenticate(
                username = binding?.etUsername?.text?.toString().orEmpty(),
                password = binding?.etPassword?.text?.toString().orEmpty(),
            )
        }

        binding?.buttonChangeLanguage?.setOnClickListener {
            applyLanguage("en", provideIntent(this))
        }

        binding?.buttonRegister?.setOnClickListener {
            register()
        }
    }

    private fun observeViewModel() {
        viewModel.authentication.observe(this, ::handleAuthentication)
        viewModel.error.observe(this, ::handleError)
        viewModel.loading.observe(this, ::handleLoading)
    }

    private fun handleAuthentication(token: String) {
        if (token.isNotEmpty() && token.isNotBlank()) {
            viewModel.saveToken(token)
            HomeActivity.startActivity(this)
            this.finish()
        }
    }

    private fun handleError(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun handleLoading(isLoading: Boolean) {
        binding?.buttonLogin?.visibility = if (isLoading) View.GONE else View.VISIBLE
        binding?.progress?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun register() {
        RegisterActivity.startActivity(this)
    }
}