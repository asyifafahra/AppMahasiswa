package com.asyifafahra.mahasiswaapp.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.asyifafahra.mahasiswaapp.R
import com.asyifafahra.mahasiswaapp.UpdateMahasiswaActivity
import com.asyifafahra.mahasiswaapp.helper.MahasiswaDatabaseHelper
import com.asyifafahra.mahasiswaapp.model.ModelMahasiswa

class MahasiswaAdapter(
    private var datas : List<ModelMahasiswa>,
    context : Context
) : RecyclerView.Adapter<MahasiswaAdapter.DataViewHolder>(){

    private var db : MahasiswaDatabaseHelper = MahasiswaDatabaseHelper(context)

    class DataViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView) {
        val txtItemNama : TextView = itemView.findViewById(R.id.txtItemNama)
        val txtItemNim : TextView = itemView.findViewById(R.id.txtItemNim)
        val txtItemJurusan : TextView = itemView.findViewById(R.id.txtItemJurusan)
        val btnEdit : ImageView = itemView.findViewById(R.id.btnEdit)
        val btnDelete : ImageView = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mahasiswa,
            parent,false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val mahasiswaData = datas[position]
        holder.txtItemNama.text = mahasiswaData.namaMahasiswa
        holder.txtItemNim.text = mahasiswaData.nim
        holder.txtItemJurusan.text = mahasiswaData.jurusan

        //update

        holder.btnEdit.setOnClickListener{
            val intent = Intent(holder.itemView.context, UpdateMahasiswaActivity::class.java).apply{
                putExtra("data_id", mahasiswaData.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.btnDelete.setOnClickListener{
            AlertDialog.Builder(holder.itemView.context).apply {
                setTitle("Confirm")
                setMessage("Do you want continue?")
                setIcon(R.drawable.baseline_delete_24)

                setPositiveButton("yes"){
                        dialogInterface, i->
                    db.deleteData(mahasiswaData.id)
                    refreshData(db.getAllData())
                    Toast.makeText(holder.itemView.context,"Data berhasil dihapus",
                        Toast.LENGTH_SHORT).show()
                    dialogInterface.dismiss()
                }
                setNeutralButton("No"){
                        dialogInterface, i->
                    dialogInterface.dismiss()
                }
            }.show()
        }

    }

    fun refreshData(newdata : List<ModelMahasiswa>){
        datas = newdata
        notifyDataSetChanged()
    }
}