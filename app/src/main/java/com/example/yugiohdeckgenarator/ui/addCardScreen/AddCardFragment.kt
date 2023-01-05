package com.example.yugiohdeckgenarator.ui.addCardScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.yugiohdeckgenarator.R
import com.example.yugiohdeckgenarator.base.BaseFragment
import com.example.yugiohdeckgenarator.databinding.FragmentAddCardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCardFragment : BaseFragment<FragmentAddCardBinding>(FragmentAddCardBinding::inflate) {

    private val adapter by lazy { AddCardAdapter() }
    private val viewModel : AddCardViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardRecyclerView.adapter = adapter
        binding.cardRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)

        viewModel.card.observe(viewLifecycleOwner){
            it?.data?.let { it1 -> adapter.setData(it1) }
        }
    }
}