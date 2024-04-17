package com.example.cahtapplication.ui.auth.fargments.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cahtapplication.R
import com.example.cahtapplication.base.BaseFragment
import com.example.cahtapplication.databinding.FragmentLoginBinding
import com.example.cahtapplication.ui.auth.AuthActivity
import com.example.cahtapplication.ui.home.HomeActivity


class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
    }

    override fun initViewModel(): LoginViewModel =
        ViewModelProvider(this)[LoginViewModel::class.java]


    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }

    override fun observeLiveData() {
        super.observeLiveData()
        viewModel.events.observe(viewLifecycleOwner) {
            when (it) {
                is LoginScreenEvents.NavigateToRegister -> {
                    findNavController().navigate(R.id.action_loginFragment_to_registerFragment)

                }

                is LoginScreenEvents.NavigateToHome -> {
                    val intent = Intent(activity, HomeActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}
