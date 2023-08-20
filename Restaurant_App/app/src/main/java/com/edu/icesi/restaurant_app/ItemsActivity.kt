package com.edu.icesi.restaurant_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.edu.icesi.restaurant_app.databinding.ActivityItemsBinding

class ItemsActivity : AppCompatActivity() {

    var itemName = ""

    private val binding by lazy {
        ActivityItemsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.aBtn.setOnClickListener(::selectItem)
        binding.bBtn.setOnClickListener(::selectItem)
        binding.cBtn.setOnClickListener(::selectItem)

        binding.addBtn.setOnClickListener{
            val intent = Intent()
            intent.putExtra("name", itemName)
            setResult(RESULT_OK, intent)

            finish()
        }
    }

    fun selectItem(view:View){
        val button = view as Button
        itemName = button.text.toString()

        if(itemName == "Ajiaco"){
            binding.checkA.visibility = View.VISIBLE
            binding.checkB.visibility = View.GONE
            binding.checkC.visibility = View.GONE

        }else if(itemName == "Hamburguesa"){
            binding.checkB.visibility = View.VISIBLE
            binding.checkA.visibility = View.GONE
            binding.checkC.visibility = View.GONE

        }else{
            binding.checkC.visibility = View.VISIBLE
            binding.checkA.visibility = View.GONE
            binding.checkB.visibility = View.GONE
        }
    }
}