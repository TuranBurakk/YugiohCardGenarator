package com.example.yugiohdeckgenarator.ui.mySpecialCardScreen

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.yugiohdeckgenarator.base.BaseFragment
import com.example.yugiohdeckgenarator.data.entity.UserData
import com.example.yugiohdeckgenarator.data.entity.myCard
import com.example.yugiohdeckgenarator.databinding.FragmentMySpecialCardBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class MySpecialCardFragment : BaseFragment<FragmentMySpecialCardBinding>(FragmentMySpecialCardBinding::inflate) {

    private val auth by lazy { FirebaseAuth.getInstance() }
    private val db by lazy { FirebaseFirestore.getInstance() }
    private val adapter by lazy { MySpecialCardAdapter() }
    private val list = java.util.ArrayList<myCard>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.RecyclerView.adapter = adapter
        binding.RecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        getData()

    }

    private fun getData(){
        db.collection("user").document(auth.currentUser!!.uid).get().addOnSuccessListener {
            val firebaseList = it.toObject<UserData>()
            if (firebaseList != null){
                for (item in firebaseList.myCardList!!){
                    val image = item.myImage
                    val name = item.cardName
                    val atk = item.atk
                    val desc = item.desc
                    val def = item.def
                    val downloadCard = myCard(desc,name,image,atk, def)
                    list.add(downloadCard)
                }
                adapter.setData(list)
            }
        }
    }
}