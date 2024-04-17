package com.example.cahtapplication.base

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.cahtapplication.R

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : Fragment() {
    lateinit var viewModel: VM  //RegisterViewModel
    lateinit var binding: DB  //FragmentRegisterBinding
    private var dialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = initViewModel()
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
    }

    open fun observeLiveData() {
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        }
        viewModel.viewMessageLiveData.observe(viewLifecycleOwner) {
            showDialog(
                it.title,
                it.message,
                it.posButtonTitle,
                it.negButtonTitle,
                it.onPosButtonClick,
                it.onNegButtonClick
            )
        }
    }

    abstract fun initViewModel(): VM

    abstract fun getLayoutId(): Int

    private fun showLoading() {
        val builder = AlertDialog.Builder(activity)
        builder.setView(R.layout.dialog_loading)
        dialog = builder.create()
        dialog?.show()
    }

    private fun hideLoading() {
        dialog?.dismiss()
    }

    private fun showDialog(
        title: String? = null,
        message: String? = null,
        posButtonTitle: String? = null,
        negButtonTitle: String? = null,
        onPosButtonClick: (() -> Unit)? = null,
        onNegButtonClick: (() -> Unit)? = null,

        ) {
        val builder = AlertDialog.Builder(activity)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
        posButtonTitle.let {
            builder.setPositiveButton(
                posButtonTitle
            ) { dialog, _ ->
                dialog?.dismiss()
                onPosButtonClick?.invoke()
            }
        }
        negButtonTitle.let {
            builder.setNegativeButton(
                negButtonTitle
            ) { dialog, _ ->
                dialog?.dismiss()
                onNegButtonClick?.invoke()
            }
        }
        builder.create().show()
    }
}