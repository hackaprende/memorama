package com.hackaprende.hackamemory.game

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hackaprende.hackamemory.R
import com.hackaprende.hackamemory.databinding.CardItemBinding

class CardAdapter(private val onItemClickListener: (MemoryCard) -> Unit) :
    ListAdapter<MemoryCard, CardAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<MemoryCard>() {
        override fun areItemsTheSame(oldItem: MemoryCard, newItem: MemoryCard): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MemoryCard, newItem: MemoryCard): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(CardItemBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(private val binding: CardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(memoryCard: MemoryCard) {
            if (memoryCard.isChecked) {
                binding.cardImage.setImageResource(memoryCard.imageId)
                binding.cardImage.setOnClickListener(null)
            } else {
                binding.cardImage.setImageResource(R.drawable.all_card_backs)
                binding.cardImage.setOnClickListener {
                    onItemClickListener(memoryCard)
                }
            }
        }
    }
}
