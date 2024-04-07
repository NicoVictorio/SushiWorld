package com.ubaya.sushiworld.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.ContactsContract.Profile
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ubaya.sushiworld.R
import com.ubaya.sushiworld.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogout.setOnClickListener(){
            var sharedFile= "com.ubaya.sushiworld"
            var shared: SharedPreferences = this.requireActivity().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
            var editor: SharedPreferences.Editor = shared.edit()
            editor.putInt(LoginActivity.USERID, -1)
            editor.putString(LoginActivity.PHOTOURL, "")
            editor.putString(LoginActivity.EMAIL, "")
            editor.putString(LoginActivity.PASSWORD, "")
            editor.putString(LoginActivity.USERNAME, "")
            editor.putString(LoginActivity.NAMADEPAN, "")
            editor.putString(LoginActivity.NAMABELAKANG, "")

            editor.apply()

            val intent = Intent(this.activity, LoginActivity::class.java)
            startActivity(intent)
            this.requireActivity().finish()
        }
    }
}