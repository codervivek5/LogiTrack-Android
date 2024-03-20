package com.example.logitrackandroid

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity() {
    private val splashTimeOut: Long = 2000 // Splash screen timeout in milliseconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Check if the user is already logged in
        val currentUser = FirebaseAuth.getInstance().currentUser

        // Start the appropriate activity after a delay
        val intent = if (currentUser != null) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, LoginActivity::class.java)
        }

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
            finish()

            if (currentUser != null) {
                showToast("Welcome back to LogiTrack!")
            } else {
                showToast("Welcome to LogiTrack!")
            }
        }, splashTimeOut) // Delay for 2 seconds (adjust as needed)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
