package com.asyifafahra.mahasiswaapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asyifafahra.mahasiswaapp.adapter.MahasiswaAdapter
import com.asyifafahra.mahasiswaapp.databinding.ActivityMainBinding
import com.asyifafahra.mahasiswaapp.helper.MahasiswaDatabaseHelper
import com.asyifafahra.mahasiswaapp.model.ModelMahasiswa

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var MahasiswaAdapter : MahasiswaAdapter
    private lateinit var db : MahasiswaDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = MahasiswaDatabaseHelper(this)
        MahasiswaAdapter = MahasiswaAdapter(db.getAllData(),this)

        binding.dataRecycleview.layoutManager = LinearLayoutManager(this)
        binding.dataRecycleview.adapter = MahasiswaAdapter

        binding.addButton.setOnClickListener{
            val intent = Intent(this, AddMahasiswaActivity::class.java)
            startActivity(intent)
        }


    }
    override fun onResume() {
        super.onResume()
        val notes = db.getAllData()
        MahasiswaAdapter.refreshData(notes)
    }
}