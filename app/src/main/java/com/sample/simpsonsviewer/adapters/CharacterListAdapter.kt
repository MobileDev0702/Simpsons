package com.sample.simpsonsviewer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.simpsonsviewer.databinding.RowItemBinding
import com.sample.simpsonsviewer.entities.Response

class CharacterListAdapter: ListAdapter<Response.CharacterItem, CharacterListAdapter.ViewHolder>(DiffCallback) {

    private var itemClickListener: ((item: Response.CharacterItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RowItemBinding = RowItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        holder.recycle()
    }

    fun setItemClickListener(listener: ((item: Response.CharacterItem) -> Unit)) {
        this.itemClickListener = listener
    }

    object DiffCallback: DiffUtil.ItemCallback<Response.CharacterItem>() {
        override fun areItemsTheSame(
            oldItem: Response.CharacterItem,
            newItem: Response.CharacterItem
        ): Boolean {
            return oldItem.firstUrl == newItem.firstUrl
        }

        override fun areContentsTheSame(
            oldItem: Response.CharacterItem,
            newItem: Response.CharacterItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: RowItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Response.CharacterItem) {
            binding.tvItem.text = item.firstUrl.substring(23)
            binding.root.setOnClickListener {
                itemClickListener?.invoke(item)
            }
        }

        fun recycle() {
            Glide.with(binding.root.context).clear(binding.tvItem)
        }
    }
}