package com.edu.icesi.restaurant_app

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.edu.icesi.restaurant_app.databinding.ItemBinding

class ItemView(root:View) : ViewHolder(root) {

    private val binding = ItemBinding.bind(root)

    val nameItem = binding.nameItem
    val priceItem = binding.priceItem
}