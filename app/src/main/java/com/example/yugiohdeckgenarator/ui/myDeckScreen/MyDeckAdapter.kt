package com.example.yugiohdeckgenarator.ui.myDeckScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yugiohdeckgenarator.data.entity.Card
import com.example.yugiohdeckgenarator.data.entity.CardListIn
import com.example.yugiohdeckgenarator.data.entity.FireCardData
import com.example.yugiohdeckgenarator.databinding.DeckRowBinding
import com.example.yugiohdeckgenarator.utils.downloadFromUrl

class MyDeckAdapter : RecyclerView.Adapter<MyDeckAdapter.MyDeckHolder>() {

    private var list = listOf<Card>()

    class MyDeckHolder(val binding: DeckRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDeckHolder {
        val view = LayoutInflater.from(parent.context)
        return MyDeckHolder(DeckRowBinding.inflate(view,parent,false))
    }

    override fun onBindViewHolder(holder: MyDeckHolder, position: Int) {
        holder.binding.imageView.downloadFromUrl(list[position].cardImage?.get(0)?.image)
    }

    override fun getItemCount() = list.size

    fun setData(newList : List<Card>){
        if (newList != null) {
            list = newList
        }
        notifyDataSetChanged()
    }
}