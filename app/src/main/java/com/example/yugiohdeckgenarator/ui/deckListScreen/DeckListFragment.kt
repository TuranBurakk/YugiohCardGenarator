package com.example.yugiohdeckgenarator.ui.deckListScreen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yugiohdeckgenarator.base.BaseFragment
import com.example.yugiohdeckgenarator.data.entity.Card
import com.example.yugiohdeckgenarator.data.entity.CardListIn
import com.example.yugiohdeckgenarator.data.entity.UserData
import com.example.yugiohdeckgenarator.databinding.FragmentDeckListBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject


class DeckListFragment : BaseFragment<FragmentDeckListBinding>(FragmentDeckListBinding::inflate) {

        private val auth by lazy { FirebaseAuth.getInstance() }
        private val db by lazy { FirebaseFirestore.getInstance() }
        private val adapter by lazy { DeckListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.deckRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.deckRecyclerView.adapter = adapter
        getDeck()
        binding.floatingActionButton.setOnClickListener {
            binding.deckRecyclerView.visibility = View.GONE
            binding.DeckNameET.visibility = View.VISIBLE
            binding.floatingActionButton.visibility = View.GONE
            binding.confirmationFloatingActionButton.visibility = View.VISIBLE
        }

        binding.confirmationFloatingActionButton.setOnClickListener {
            val deckName = binding.DeckNameET.text.toString()
            if (binding.DeckNameET.text != null){
            db.collection("user").document(auth.currentUser!!.uid).update(deckName , listOf<Card>())
                db.collection("user").document(auth.currentUser!!.uid).update("cardList",FieldValue.arrayUnion(CardListIn(null, name = deckName)))
            binding.deckRecyclerView.visibility = View.VISIBLE
            binding.DeckNameET.visibility = View.GONE
            binding.floatingActionButton.visibility = View.VISIBLE
            binding.confirmationFloatingActionButton.visibility = View.GONE
            getDeck()
            }
            else
            {
                Toast.makeText(requireContext(),"Enter deck name",Toast.LENGTH_LONG).show()
            }
        }


    }
    private fun getDeck(){
        db.collection("user").document(auth.currentUser!!.uid).get().addOnSuccessListener {
            val deck = it.toObject<UserData>()
            deck?.cardList?.let { it1 -> adapter.setData(it1) }
        }
    }

}