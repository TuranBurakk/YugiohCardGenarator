package com.example.yugiohdeckgenarator.ui.deckScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.yugiohdeckgenarator.data.entity.Card
import com.example.yugiohdeckgenarator.databinding.DeckRowBinding
import com.example.yugiohdeckgenarator.utils.downloadFromUrl

class DeckAdapter : RecyclerView.Adapter<DeckAdapter.CardHolder>() {

    private var list = listOf<Card>()

    class CardHolder(val binding : DeckRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val view = LayoutInflater.from(parent.context)
        return CardHolder(DeckRowBinding.inflate(view,parent,false))
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.binding.imageView.downloadFromUrl(
            list[position].cardImage?.get(0)?.image
        )
        holder.binding.root.setOnClickListener {
            val action = DeckFragmentDirections.actionDeckFragmentToDetailFragment(list[position].cardImage?.get(0)?.image.toString())
            it.findNavController().navigate(action)
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