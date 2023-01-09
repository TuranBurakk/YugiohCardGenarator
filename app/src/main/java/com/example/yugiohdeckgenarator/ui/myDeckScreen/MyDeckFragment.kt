package com.example.yugiohdeckgenarator.ui.myDeckScreen

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.yugiohdeckgenarator.base.BaseFragment
import com.example.yugiohdeckgenarator.databinding.FragmentMyDeckBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyDeckFragment : BaseFragment<FragmentMyDeckBinding>(FragmentMyDeckBinding::inflate) {

    private val auth by lazy { FirebaseAuth.getInstance() }
    private val db by lazy { FirebaseFirestore.getInstance() }
    private val adapter by lazy { MyDeckAdapter() }
    private val args : MyDeckFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        binding.myDeckRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        binding.myDeckRecyclerView.adapter = adapter
        binding.addCardFloatingActionButton.setOnClickListener {
            findNavController().navigate(MyDeckFragmentDirections.actionMyDeckFragmentToAddCardFragment(args.deck))
        }
        binding.btnBack.setOnClickListener {
            findNavController().navigate(MyDeckFragmentDirections.actionMyDeckFragmentToDeckListFragment())
        }

    }

    private fun getData(){
        db.collection("user").document(auth.currentUser!!.uid).get().addOnSuccessListener {
           val myDeck = it[args.deck] as List<String>
            adapter.setData(myDeck)
        }
    }

}