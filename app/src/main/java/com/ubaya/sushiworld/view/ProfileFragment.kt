package com.ubaya.sushiworld.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ubaya.sushiworld.R
import com.ubaya.sushiworld.databinding.FragmentProfileBinding
import com.ubaya.sushiworld.viewmodel.UserViewModel

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedFile= "com.ubaya.sushiworld"
        val shared: SharedPreferences = requireActivity().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
        var userId = shared.getInt(LoginActivity.USERID.toString(), -1)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.txtNama.text = "Hai, " + shared.getString(LoginActivity.NAMADEPAN, "") + " " +
                                shared.getString(LoginActivity.NAMABELAKANG, "");

        binding.btnSaveChanges.setOnClickListener{
            var namaDepanBaru = binding.txtNamaDepanBaru.text
            var namaBelakangBaru = binding.txtNamaBelakangBaru.text
            var passwordBaru = binding.txtPasswordBaru.text

            viewModel.edit(passwordBaru.toString(), namaDepanBaru.toString(), namaBelakangBaru.toString(), userId.toString())

            binding.btnLogout.performClick()
        }

        binding.btnLogout.setOnClickListener{
            val editor: SharedPreferences.Editor = shared.edit()
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