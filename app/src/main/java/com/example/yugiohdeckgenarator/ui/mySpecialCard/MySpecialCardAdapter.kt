package com.example.yugiohdeckgenarator.ui.mySpecialCard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.yugiohdeckgenarator.data.entity.myCard
import com.example.yugiohdeckgenarator.databinding.MyCardRowBinding
import com.example.yugiohdeckgenarator.utils.downloadFromUrl

class MySpecialCardAdapter : RecyclerView.Adapter<MySpecialCardAdapter.SpecialCardHolder>() {

    private var list = emptyList<myCard>()

    class SpecialCardHolder(val binding : MyCardRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialCardHolder {
        val binding =  MyCardRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SpecialCardHolder(binding)
    }

    override fun onBindViewHolder(holder: SpecialCardHolder, position: Int) {
        val card = list[position]
        holder.binding.apply {
            nameTv.text = card.cardName
            descTv.text = card.desc
            atkTv.text = card.atk
            defTv.text = card.def
            CardImage.downloadFromUrl(card.myImage)
        }
        holder.binding.root.setOnClickListener {
            val action = card.myImage?.let { url ->
                MySpecialCardFragmentDirections.actionMySpecialCardFragmentToDetailFragment(
                    url
                )
            }
            it.findNavController().navigate(action!!)
        }
    }

    override fun getItemCount() = list.size

    fun setData(newlist : List<myCard>){
        list= newlist
        notifyDataSetChanged()
    }
}