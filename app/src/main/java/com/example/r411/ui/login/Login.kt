package com.example.r411.ui.login

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.r411.HomeActivity
import com.example.r411.databinding.FragmentLoginBinding
import java.util.regex.Pattern

class Login : Fragment() {

    companion object {
        fun newInstance() = Login()
    }

    private lateinit var viewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

    }

    private fun checkMail(): Boolean {
        if (binding.mail.text.toString().isEmpty()) {
            binding.mail.error = "L'email est vide"
            return false
        }
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        if (!pattern.matcher(binding.mail.text.toString()).matches()) {
            binding.mail.error = "Ce n'est pas une adresse email"
            return false
        }
        return true;
    }

    private fun checkPassword(): Boolean {
        if (binding.password.text.toString().isEmpty()) {
            binding.password.error = "Le mot de passe est vide"
            return false
        }
        return true;
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.loginButton.setOnClickListener {
            val validMail = checkMail();
            val validPassword = checkPassword();
            if (validMail && validPassword) run {
                //TODO Check BDD
                val intent = Intent(this.context, HomeActivity::class.java)
                startActivity(intent)
            }
        }
        binding.mail.doOnTextChanged { _, _, _, _ -> binding.mail.error = null }
        binding.password.doOnTextChanged { _, _, _, _ -> binding.password.error = null }
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}