package com.example.yugiohdeckgenarator.ui.addCardScreen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.yugiohdeckgenarator.base.BaseFragment
import com.example.yugiohdeckgenarator.databinding.FragmentAddCardBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCardFragment : BaseFragment<FragmentAddCardBinding>(FragmentAddCardBinding::inflate) , AddDeck {

    private val auth by lazy { FirebaseAuth.getInstance() }
    private val db by lazy { FirebaseFirestore.getInstance() }
    private val adapter by lazy { AddCardAdapter(this) }
    private val viewModel : AddCardViewModel by viewModels()
    private val args : AddCardFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardRecyclerView.adapter = adapter
        binding.cardRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)

        viewModel.card.observe(viewLifecycleOwner){
            val card = it
            card?.data?.let { it1 -> adapter.setData(it1) }
        }
        binding.floatingActionButton.setOnClickListener {
            findNavController().popBackStack()

        }


    }

    override fun addItem(item: String, position: Int) {
            Toast.makeText(requireContext(),"Completed add card to deck",Toast.LENGTH_LONG).show()
            db.collection("user").document(auth.currentUser!!.uid).update(args.listName,FieldValue.arrayUnion(item))
    }

}