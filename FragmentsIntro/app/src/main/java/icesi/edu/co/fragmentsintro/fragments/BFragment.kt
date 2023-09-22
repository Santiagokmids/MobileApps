package icesi.edu.co.fragmentsintro.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import icesi.edu.co.fragmentsintro.adapters.TaskAdapter
import icesi.edu.co.fragmentsintro.databinding.BFragmentBinding
import icesi.edu.co.fragmentsintro.model.TaskRepository

class BFragment : Fragment() {

    private val adapter = TaskAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding:BFragmentBinding = BFragmentBinding.inflate(inflater, container, false)

        binding.taskList.layoutManager = LinearLayoutManager(activity)
        binding.taskList.adapter = adapter
        binding.taskList.setHasFixedSize(true)

        //Observación
        TaskRepository.taskLiveData.observe(viewLifecycleOwner){
            adapter.notifyDataSetChanged()
        }

        return binding.root
    }

    companion object{
        fun newInstance(): BFragment {
            return BFragment()
        }
    }
}