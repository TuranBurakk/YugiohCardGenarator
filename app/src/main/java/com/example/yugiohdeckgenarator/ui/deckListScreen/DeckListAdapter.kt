package com.example.yugiohdeckgenarator.ui.deckListScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.yugiohdeckgenarator.data.entity.CardListIn
import com.example.yugiohdeckgenarator.databinding.DeckListRowBinding

class DeckListAdapter : RecyclerView.Adapter<DeckListAdapter.DeckListHolder>() {

        private var list = listOf<CardListIn>()

    class DeckListHolder(val binding : DeckListRowBinding ) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckListHolder {
        val view = LayoutInflater.from(parent.context)
        return DeckListHolder(DeckListRowBinding.inflate(view,parent,false))
    }

    override fun onBindViewHolder(holder: DeckListHolder, position: Int) {
        holder.binding.listNameTV.text = list[position].name.toString()
        holder.binding.root.setOnClickListener {
            it.findNavController().navigate(DeckListFragmentDirections.actionDeckListFragmentToMyDeckFragment())
        }
    }

    override fun getItemCount() = list.size

    fun setData(newList : List<CardListIn>){
        if (newList != null) {
            list = newList
        }
        notifyDataSetChanged()
    }
}