package com.example.yugiohdeckgenarator.ui.myDeckScreen

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.yugiohdeckgenarator.base.BaseFragment
import com.example.yugiohdeckgenarator.databinding.FragmentMyDeckBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyDeckFragment : BaseFragment<FragmentMyDeckBinding>(FragmentMyDeckBinding::inflate) {


    private val adapter by lazy { MyDeckAdapter() }
    private val args : MyDeckFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.myDeckRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        binding.myDeckRecyclerView.adapter = adapter
        binding.addCardFloatingActionButton.setOnClickListener {
            findNavController().navigate(MyDeckFragmentDirections.actionMyDeckFragmentToAddCardFragment())
        }
        binding.btnBack.setOnClickListener {
            findNavController().navigate(MyDeckFragmentDirections.actionMyDeckFragmentToDeckListFragment())
        }
        args.let {
          val deck = it.deck
            deck.listCard?.let { it1 -> adapter.setData(it1) }
        }
    }

}