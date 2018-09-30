package io.hudepohl.mvvm.util

import android.support.v7.widget.RecyclerView


abstract class RecyclerViewAdapter<Item, ItemViewHolder : RecyclerView.ViewHolder>(
        private var items: List<Item> = ArrayList()
) : RecyclerView.Adapter<ItemViewHolder>() {

    fun setItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size
    protected fun getItemAtPosition(position: Int) = items[position]
}