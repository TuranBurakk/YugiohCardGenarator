package com.example.yugiohdeckgenarator.ui.addCardScreen

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yugiohdeckgenarator.data.entity.Card
import com.example.yugiohdeckgenarator.databinding.AddCardRowBinding
import com.example.yugiohdeckgenarator.databinding.AddCardRowBinding.inflate
import com.example.yugiohdeckgenarator.utils.downloadFromUrl
import kotlin.math.log

class AddCardAdapter(private var listener: AddDeck? = null) : RecyclerView.Adapter<AddCardAdapter.DeckHolder>() {

    private var list = listOf<Card>()


    class DeckHolder(val binding : AddCardRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckHolder {
        val view = LayoutInflater.from(parent.context)
        return DeckHolder(inflate(view,parent,false))
    }

    override fun onBindViewHolder(holder: DeckHolder, position: Int) {
       val card = list[position].cardImage?.get(0)?.image.toString()
        holder.binding.imageView.downloadFromUrl(card)
        holder.binding.imageView.setOnClickListener {
            listener?.addItem(card,position)
            Log.d("card",card)
        }

    }

    override fun getItemCount(): Int = list.size

    fun setData(newList: List<Card>){
        if (newList != null) {
            list = newList
        }
        notifyDataSetChanged()
    }


}