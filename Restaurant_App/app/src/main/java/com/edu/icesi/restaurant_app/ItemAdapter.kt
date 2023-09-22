package com.edu.icesi.restaurant_app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.edu.icesi.restaurant_app.model.Item

class ItemAdapter : Adapter<ItemView>() {

    val items = ArrayList<Item>()

    init {
        items.add(Item(name = "Pizza", price = 20000.0))
        items.add(Item(name = "Changua", price = 15000.0))
    }

    //Método para construir los esqueletos de los item de la lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemView {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item, null, false)
        val itemView = ItemView(view)

        return itemView
    }

    override fun getItemCount(): Int {
        return items.size
    }

    //Se van a cargar los datos en los esqueletos
    override fun onBindViewHolder(holder: ItemView, position: Int) {

        val data = items[position]

        holder.nameItem.text = data.name
        holder.priceItem.text = "$${data.price}"
    }

    fun addItem(item:Item){
        items.add(item)
        notifyDataSetChanged()
    }
}