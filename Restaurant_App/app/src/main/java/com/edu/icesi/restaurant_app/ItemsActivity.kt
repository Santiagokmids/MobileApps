package com.edu.icesi.restaurant_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.edu.icesi.restaurant_app.databinding.ActivityItemsBinding

class ItemsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityItemsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.aBtn.setOnClickListener(::selectItem)
        binding.bBtn.setOnClickListener(::selectItem)
        binding.cBtn.setOnClickListener(::selectItem)
    }

    fun selectItem(view:View){
        val button = view as Button
        var text = button.text

        val intent = Intent()
        intent.putExtra("name", text)
        setResult(RESULT_OK, intent)
        finish()
    }
}