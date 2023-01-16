package com.example.yugiohdeckgenarator.ui.deckScreen

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.yugiohdeckgenarator.R
import com.example.yugiohdeckgenarator.base.BaseFragment
import com.example.yugiohdeckgenarator.databinding.FragmentDeckBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import java.util.zip.Inflater


@AndroidEntryPoint
class DeckFragment : BaseFragment<FragmentDeckBinding>(FragmentDeckBinding::inflate) {

    private val viewModel: DeckViewModel by viewModels()
    private val adapter by lazy { DeckAdapter() }
    private val auth by  lazy { FirebaseAuth.getInstance() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),2)

        viewModel.card.observe(viewLifecycleOwner){
            it?.data?.let { it1 -> adapter.setData(it1) }
        }
    }



    override fun onStart() {
        super.onStart()
        showBottomBar()
    }


}