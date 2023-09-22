package icesi.edu.co.fragmentsintro.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import icesi.edu.co.fragmentsintro.databinding.AFragmentBinding
import icesi.edu.co.fragmentsintro.model.Task
import icesi.edu.co.fragmentsintro.model.TaskRepository

class AFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding:AFragmentBinding = AFragmentBinding.inflate(inflater, container, false)

        binding.addBtn.setOnClickListener{
            val text = binding.addET.text.toString()
            val task = Task(text, false)

            TaskRepository.addTask(task)
        }

        return binding.root
    }

    //Todos los métodos aquí serán estáticos
    companion object{
        fun newInstance(): AFragment {
            return AFragment()
        }
    }
}