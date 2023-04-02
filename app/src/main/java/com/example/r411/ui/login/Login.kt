package com.example.r411.ui.login

import android.content.Context
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
import androidx.lifecycle.lifecycleScope
import com.example.r411.HomeActivity
import com.example.r411.databinding.FragmentLoginBinding
import com.example.r411.worker.DataDownloadWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL
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

    private suspend fun checkIsUserInDatabase(): Int {
        return withContext(Dispatchers.IO) {
            val target = URL(DataDownloadWorker.API + "initiator/")
            with(target.openConnection() as HttpURLConnection) {
                val stringBuilder = StringBuilder()
                inputStream.bufferedReader().use {
                    it.lines().forEach { line ->
                        stringBuilder.append(line)
                    }
                }
                val data = JSONArray(stringBuilder.toString())
                for (i in 0 until data.length()) {
                    val item = data.getJSONObject(i)
                    if(item.getString("email") == binding.mail.text.toString() && item.getString("password") == binding.password.text.toString()) {
                        val sharedPref = requireActivity().getSharedPreferences("LOGGED_USER", Context.MODE_PRIVATE)
                        val editor = sharedPref.edit()
                        editor.putInt("id", item.getInt("id"))
                        editor.putString("name", item.getString("name"))
                        editor.putInt("levelId", item.getInt("levelId"))
                        editor.apply()
                        return@withContext item.getInt("id")
                    }
                }
                return@withContext -1
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.loginButton.setOnClickListener {
            val validMail = checkMail();
            val validPassword = checkPassword();
            var userIdInDB = -1
            lifecycleScope.launch {
                userIdInDB = checkIsUserInDatabase()
                if (validMail && validPassword) run {
                    if(userIdInDB == -1) {
                        Toast.makeText(context, "Utilisateur non trouvé", Toast.LENGTH_SHORT).show()
                        return@run
                    }
                    val intent = Intent(context, HomeActivity::class.java)
                    startActivity(intent)
                }
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