package com.example.fragment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var edtTitle  :EditText
    private lateinit var edtCount : EditText
    private lateinit var btnAdd : Button
    private lateinit var btnRemove : Button

   private val counterFragments = ArrayList<CounterFragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
        initListener()
    }
    private fun initViews(){
        setContentView(R.layout.activity_main)
        edtTitle = findViewById(R.id.edtTitle)
        edtCount = findViewById(R.id.edtCount)
        btnAdd = findViewById(R.id.btnAdd)
        btnRemove = findViewById(R.id.btnRemove)
    }
    private fun initListener(){
        btnAdd.setOnClickListener{
            val counterFragment = CounterFragment()

            counterFragments.add(counterFragment)
            val inputBundle = Bundle()
            inputBundle.putString("title",edtTitle.text.toString())
            inputBundle.putInt("count",edtCount.text.toString().toInt())

            counterFragment.arguments = inputBundle

            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.MainContainer,counterFragment,null)
            fragmentTransaction.commit()
        }

        btnRemove.setOnClickListener {
            if (counterFragments.size == 0){
                return@setOnClickListener
            }
            supportFragmentManager.beginTransaction()
                .remove(counterFragments.removeLast())
                .commit()
        }
    }
}