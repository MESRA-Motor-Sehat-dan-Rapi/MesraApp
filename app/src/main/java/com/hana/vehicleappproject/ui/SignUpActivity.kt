package com.hana.vehicleappproject.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.hana.vehicleappproject.R
import com.hana.vehicleappproject.data.retrofit.RetrofitInstance
import com.hana.vehicleappproject.data.signup.SignUpRequest
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val signUpButton = findViewById<Button>(R.id.signupButton)

        // Logika untuk Sign Up
        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Harap isi email dan password", Toast.LENGTH_SHORT).show()
            } else {
                registerUser(email, password)
            }
        }
    }

    private fun registerUser(email: String, password: String) {
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.register(SignUpRequest(email, password))
                if (response.isSuccessful && response.body()?.success == true) {
                    Toast.makeText(this@SignUpActivity, "Registrasi berhasil!", Toast.LENGTH_SHORT).show()
                    finish() // Kembali ke SignInActivity
                } else {
                    Toast.makeText(
                        this@SignUpActivity,
                        response.body()?.message ?: "Registrasi gagal",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@SignUpActivity, "Terjadi kesalahan: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
