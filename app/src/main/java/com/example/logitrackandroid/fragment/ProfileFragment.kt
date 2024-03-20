package com.example.logitrackandroid.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.logitrackandroid.LoginActivity
import com.example.logitrackandroid.R
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var profileName: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Find views
        val buttonLogOut: Button = view.findViewById(R.id.logOut)
        profileName = view.findViewById(R.id.profileName)

        // Set user's name
        val currentUser = auth.currentUser
        val username = currentUser?.displayName ?: "Unknown User"
        profileName.text = username

        // Set click listener for log out button
        buttonLogOut.setOnClickListener {
            auth.signOut()

            // Show toast message for successful logout
            showToast("Logout Successful :)")

            // Redirect to login activity
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        return view
    }

    // Function to show toast message.
    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
