package icesi.edu.co.fragmentsintro.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import icesi.edu.co.fragmentsintro.databinding.TaskBinding

class TaskViewHolder(root:View) : ViewHolder(root) {

    private val binding = TaskBinding.bind(root)

    val description = binding.taskDescription
    val actionBtn = binding.actionBtn
    val taskRoot = binding.taskRoot
}