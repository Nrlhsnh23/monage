package com.example.newmonage

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat

class AdapterTransaksi() : RecyclerView.Adapter<AdapterTransaksi.TransactionHolder>() {
    private var transactions = arrayListOf<Transaksi>()
    class TransactionHolder(view: View) : RecyclerView.ViewHolder(view){
        val tanggal : TextView = view.findViewById(R.id.tanggal)
        val label : TextView = view.findViewById(R.id.label)
        val jumlah : TextView = view.findViewById(R.id.jumlah)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.transaksi_layout, parent, false)
        return TransactionHolder(view)
    }

    // need to append the data to the transactions
    fun addTransactions(data: ArrayList<Transaksi>) {
        // clear list before adding so no data duplicated
        transactions.clear()
        transactions.addAll(data)
    }

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        val transaksi = transactions[position]
        val context = holder.jumlah.context

        if (transaksi.amount >= 0) {
            holder.jumlah.text = "+ Rp%.2f".format(transaksi.amount)
            holder.jumlah.setTextColor(ContextCompat.getColor(context, R.color.green))
        } else {
            holder.jumlah.text = "- Rp%.2f".format(Math.abs(transaksi.amount))
            holder.jumlah.setTextColor(ContextCompat.getColor(context, R.color.red))
        }
        holder.label.text = transaksi.label
        holder.tanggal.text = transaksi.tanggal
    }
    override fun getItemCount(): Int {
        return transactions.size
    }
}


