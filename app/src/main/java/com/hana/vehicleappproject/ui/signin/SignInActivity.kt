package com.hana.vehicleappproject.ui.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.hana.vehicleappproject.R
import com.hana.vehicleappproject.data.retrofit.ApiConfig
import com.hana.vehicleappproject.data.login.LoginRequest
import com.hana.vehicleappproject.ui.HomeFragment
import com.hana.vehicleappproject.ui.signup.SignUpActivity
import kotlinx.coroutines.launch

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val signInButton = findViewById<Button>(R.id.signinButton)
        val signUpButton = findViewById<Button>(R.id.signupButton)

        signInButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Harap isi email dan password", Toast.LENGTH_SHORT).show()
            } else {
                authenticateUser(email, password)
            }
        }

        signUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun authenticateUser(email: String, password: String) {
        lifecycleScope.launch {
            try {
                val response = ApiConfig.api.login(LoginRequest(email, password))
                if (response.isSuccessful && response.body()?.success == true) {
                    Toast.makeText(this@SignInActivity, "Login berhasil!", Toast.LENGTH_SHORT).show()

                    // Navigasi ke HomeActivity atau MainActivity
                    val intent = Intent(this@SignInActivity, HomeFragment::class.java)
                    intent.putExtra("TOKEN", response.body()?.token)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        this@SignInActivity,
                        response.body()?.message ?: "Login gagal",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@SignInActivity, "Terjadi kesalahan: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
