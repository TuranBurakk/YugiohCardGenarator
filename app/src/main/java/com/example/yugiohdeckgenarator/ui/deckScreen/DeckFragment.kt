package com.example.yugiohdeckgenarator.ui.deckScreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.yugiohdeckgenarator.base.BaseFragment
import com.example.yugiohdeckgenarator.databinding.FragmentDeckBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DeckFragment : BaseFragment<FragmentDeckBinding>(FragmentDeckBinding::inflate) {

    private val viewModel: DeckViewModel by viewModels()
    private val adapter by lazy { DeckAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),2)


        viewModel.card.observe(viewLifecycleOwner){
            it?.data?.let { it1 -> adapter.setData(it1) }
        }

    }

}