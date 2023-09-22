package icesi.edu.co.fragmentsintro.model

import androidx.lifecycle.MutableLiveData

object TaskRepository {

    //Arreglo de tareas
    //MutableLiveData
    private val tasks = arrayListOf<Task>()
    val taskLiveData = MutableLiveData<ArrayList<Task>>(tasks)

    fun addTask(task: Task){
        tasks.add(task)
        taskLiveData.value = tasks
    }

    fun toggleAsDone(pos : Int){
        tasks[pos].done = !tasks[pos].done
        taskLiveData.value = tasks
    }

    fun removeTask(pos : Int){
        tasks.removeAt(pos)
        taskLiveData.value = tasks
    }
}