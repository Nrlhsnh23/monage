package com.example.newmonage


import java.io.Serializable

data class Transaksi(
    val id: String,
    val tanggal: String,
    val label: String,
    val amount: Double,
    val description: String): Serializable {

    }
