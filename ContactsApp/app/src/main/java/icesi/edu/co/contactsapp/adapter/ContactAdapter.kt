package icesi.edu.co.contactsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import icesi.edu.co.contactsapp.R
import icesi.edu.co.contactsapp.model.Contact
import icesi.edu.co.contactsapp.viewHolder.ContactViewHolder

class ContactAdapter : Adapter<ContactViewHolder>() {

    private var contacts : ArrayList<Contact> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val layoutInflater:LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.contact, parent, false)

        val holder = ContactViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.nameContact.text = contacts[position].name
        holder.phoneContact.text = contacts[position].phone

    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    fun addContact(contact:Contact) {
        contacts.add(contact)
        notifyItemInserted(contacts.lastIndex)
    }

}