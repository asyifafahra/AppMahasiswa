package com.asyifafahra.mahasiswaapp

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.asyifafahra.mahasiswaapp.databinding.ActivityAddMahasiswaBinding
import com.asyifafahra.mahasiswaapp.helper.MahasiswaDatabaseHelper
import com.asyifafahra.mahasiswaapp.model.ModelMahasiswa
import com.google.android.material.snackbar.Snackbar

class AddMahasiswaActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddMahasiswaBinding
    private lateinit var db: MahasiswaDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMahasiswaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = MahasiswaDatabaseHelper(this)

        binding.saveButton.setOnClickListener{
            val nama = binding.namaEditText.text.toString()
            val nim = binding.nimEditText.text.toString()
            val jurusan = binding.jurusanEditText.text.toString()

            val data = ModelMahasiswa(0, nama,nim,jurusan)

            db.insertData(data)
            finish()
            Toast.makeText(this, "Data disimpan", Toast.LENGTH_SHORT).show()
        }
    }
}