package com.hana.vehicleappproject.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hana.vehicleappproject.R

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val signUpButton = findViewById<Button>(R.id.signupButton)

        // Logic for Sign Up
        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Harap isi nama, email dan password", Toast.LENGTH_SHORT).show()
            } else {
                // Add registration logic here (Firebase/Auth API)
                Toast.makeText(this, "Registrasi berhasil!", Toast.LENGTH_SHORT).show()
                finish() // Back to SignInActivity
            }
        }
    }
}
