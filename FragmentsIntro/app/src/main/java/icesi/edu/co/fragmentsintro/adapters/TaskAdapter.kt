package icesi.edu.co.fragmentsintro.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import icesi.edu.co.fragmentsintro.R
import icesi.edu.co.fragmentsintro.model.TaskRepository
import icesi.edu.co.fragmentsintro.viewHolders.TaskViewHolder

class TaskAdapter : RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return TaskRepository.taskLiveData.value!!.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = TaskRepository.taskLiveData.value?.get(position)

        task?.let {
            holder.description.text = task.description

            holder.actionBtn.setOnClickListener{
                TaskRepository.toggleAsDone(position)
            }

            if (task.done){
                holder.taskRoot.setBackgroundColor(Color.GRAY)

            }else{
                holder.taskRoot.setBackgroundColor(Color.WHITE)
            }

            holder.actionBtn.setOnLongClickListener{
                TaskRepository.removeTask(position)
                true
            }
        }
    }
}