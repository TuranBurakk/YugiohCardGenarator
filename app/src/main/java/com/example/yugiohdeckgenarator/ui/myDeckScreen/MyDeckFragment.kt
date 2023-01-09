package com.example.yugiohdeckgenarator.ui.myDeckScreen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.yugiohdeckgenarator.base.BaseFragment
import com.example.yugiohdeckgenarator.databinding.FragmentMyDeckBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyDeckFragment : BaseFragment<FragmentMyDeckBinding>(FragmentMyDeckBinding::inflate), DeleteDeck {

    private val auth by lazy { FirebaseAuth.getInstance() }
    private val db by lazy { FirebaseFirestore.getInstance() }
    private val adapter by lazy { MyDeckAdapter(this) }
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

    override fun delete(item: String, position: Int) {
        db.collection("user").document(auth.currentUser!!.uid).update(args.deck,FieldValue.arrayRemove(item))
        Toast.makeText(requireContext(),"Card delete Refresh screen",Toast.LENGTH_LONG).show()
    }

}