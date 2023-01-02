package com.example.yugiohdeckgenarator.ui

import android.os.Bundle
import android.view.View
import com.example.yugiohdeckgenarator.base.BaseFragment
import com.example.yugiohdeckgenarator.databinding.FragmentMyDeckBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyDeckFragment : BaseFragment<FragmentMyDeckBinding>(FragmentMyDeckBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}