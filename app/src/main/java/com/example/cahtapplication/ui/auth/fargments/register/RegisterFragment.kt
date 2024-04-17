package com.example.cahtapplication.ui.auth.fargments.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.cahtapplication.R
import com.example.cahtapplication.base.BaseFragment
import com.example.cahtapplication.databinding.FragmentRegisterBinding
import com.example.cahtapplication.ui.home.HomeActivity


class RegisterFragment : BaseFragment<RegisterViewModel, FragmentRegisterBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
    }

    override fun observeLiveData(){
        super.observeLiveData()
        viewModel.events.observe(viewLifecycleOwner){
            when(it){
                is RegisterScreenEvents.NavigateToHomeScreen -> {
                    val intent = Intent(activity, HomeActivity::class.java)
                    startActivity(intent)
                }

                else -> {

                }
            }
        }
    }

    override fun initViewModel(): RegisterViewModel {
        return ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun getLayoutId(): Int {
       return R.layout.fragment_register
    }
}