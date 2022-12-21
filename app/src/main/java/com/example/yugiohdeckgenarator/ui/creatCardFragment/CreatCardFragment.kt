package com.example.yugiohdeckgenarator.ui.creatCardFragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import com.example.yugiohdeckgenarator.base.BaseFragment
import com.example.yugiohdeckgenarator.data.entity.myCard
import com.example.yugiohdeckgenarator.databinding.FragmentCreatCardBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CreatCardFragment :
    BaseFragment<FragmentCreatCardBinding>(FragmentCreatCardBinding::inflate) {

    private val storage by lazy { FirebaseStorage.getInstance() }
    private val db by lazy { FirebaseFirestore.getInstance() }
    private val auth by lazy { FirebaseAuth.getInstance() }
    var cardImage: Uri? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = binding.cardName.text.toString()
        val desc = binding.cardDesc.text.toString()
        val atk = binding.atkEt.text.toString()
        val def = binding.defEt.text.toString()

        binding.cardImage.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
        }
        binding.setImage.setOnClickListener {
            val uuid = UUID.randomUUID()
            val imageName = "${uuid}.jpg"
            val refrance = storage.reference
            val imageRefrance = refrance.child("images").child(imageName)

            imageRefrance.putFile(cardImage!!).addOnSuccessListener {
                val uploadImageReferance = storage.reference.child("images").child(imageName)
                uploadImageReferance.downloadUrl.addOnSuccessListener { uri ->
                    val downloadUrl = uri.toString()
                    db.collection("user").document(auth.currentUser!!.uid)
                        .set(myCard(desc, name, downloadUrl, atk, def))

                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 100) {
            cardImage = data?.data
            binding.cardImage.setImageURI(cardImage)
        }
    }
}
