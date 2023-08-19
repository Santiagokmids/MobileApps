package com.edu.icesi.restaurant_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.recyclerview.widget.LinearLayoutManager
import com.edu.icesi.restaurant_app.databinding.ActivityMainBinding
import com.edu.icesi.restaurant_app.model.Item

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //Configuración de la recycler
        adapter = ItemAdapter()
        binding.itemList.layoutManager = LinearLayoutManager(this)//para que se muestre lineal
        binding.itemList.setHasFixedSize(true) //para que la altura se ajuste, se genere scroll
        binding.itemList.adapter = adapter

        val launcher = registerForActivityResult(
            StartActivityForResult(),
            ::onDataReceived
        )

        binding.addItemBtn.setOnClickListener {
            val intent = Intent(this, ItemsActivity::class.java)
            launcher.launch(intent)
        }
    }

    fun onDataReceived(result: ActivityResult){

        if(result.resultCode == RESULT_OK){

            val itemSelected = result.data?.extras?.getString("name")

            itemSelected?.let {
                Log.e(">>>", it)
                adapter.addItem(Item(it,17500.0))
            }

        }else if(result.resultCode == RESULT_CANCELED){

        }
    }
}