package com.example.newmonage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newmonage.api.MonageApi
import com.example.newmonage.api.RetrofitHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var transactions: ArrayList<Transaksi> = arrayListOf()
    lateinit var AddBtn: FloatingActionButton
    lateinit var listTransaksi: RecyclerView

    //
    val apiKey =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImljYXBwaW1nY2FzZ3hjeGd0aGJpIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NzA1NzUxOTYsImV4cCI6MTk4NjE1MTE5Nn0.XxDLVw5GRojK4emEVUuTMmJt6RaXQzJoy5DLMoXH7Bw"
    val token = "Bearer $apiKey"

    val MonageApi = RetrofitHelper.getInstance().create(MonageApi::class.java)
    //

    private var adapter : AdapterTransaksi = AdapterTransaksi()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AddBtn = findViewById(R.id.Btn)
        listTransaksi = findViewById(R.id.recyclerview)
        // need to add layoutManager to the recycleView
        listTransaksi.layoutManager = LinearLayoutManager(this)
        listTransaksi.adapter = adapter
        //




        AddBtn.setOnClickListener {
            val intent = Intent(this, AddTransaksi::class.java)
            startActivity(intent)
        }
    }

    fun setList() {
        adapter.addTransactions(transactions)
        adapter.notifyDataSetChanged()
    }

    fun getItem() {

        //ini pak
        CoroutineScope(Dispatchers.Main).launch {
            val response = MonageApi.get(token = token, apiKey = apiKey)
            // clear list before adding so no data duplicated
            transactions.clear()
            response.body()?.forEach {
                transactions.add(
                    Transaksi(
                        id = it.id,
                        tanggal = it.tanggal,
                        label = it.label,
                        amount = it.amount,
                    )
                )
            }

            setList()
            updateDashboard()
        }
    }

    fun updateDashboard() {

        val saldo = findViewById<TextView>(R.id.saldo)
        val pemasukan = findViewById<TextView>(R.id.pemasukan)
        val pengeluaran = findViewById<TextView>(R.id.pengeluaran)

        val totalAmount = transactions.map { it.amount }.sum()
        val budgetAmount = transactions.filter { it.amount > 0 }.map { it.amount }.sum()
        val expenseAmount = totalAmount - budgetAmount

        saldo.text = "Rp %.2f".format(totalAmount)
        pemasukan.text = "Rp %.2f".format(budgetAmount)
        pengeluaran.text = "Rp %.2f".format(expenseAmount)
    }

    override fun onResume() {
        super.onResume()
        getItem()
    }

}






