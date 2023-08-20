package icesi.edu.co.contactsapp.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import icesi.edu.co.contactsapp.databinding.ContactBinding

class ContactViewHolder(root:View) : ViewHolder(root) {

    private val binding = ContactBinding.bind(root)

    val imageContact = binding.imgContact

    val nameContact = binding.nameContact

    val phoneContact = binding.phoneContact

}