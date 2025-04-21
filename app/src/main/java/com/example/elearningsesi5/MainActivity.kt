package com.example.elearningsesi5

import android.app.*
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var tvDate: TextView
    lateinit var tvTime: TextView
    lateinit var btnDate: Button
    lateinit var btnTime: Button

    var selectedDate: String = ""
    var selectedTime: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDate = findViewById(R.id.tvDate)
        tvTime = findViewById(R.id.tvTime)
        btnDate = findViewById(R.id.btnDatePicker)
        btnTime = findViewById(R.id.btnTimePicker)

        btnDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this, { _, y, m, d ->
                selectedDate = "$d/${m + 1}/$y"
                tvDate.text = selectedDate

                // Kalau waktu sudah dipilih, langsung tampilkan konfirmasi
                if (selectedTime.isNotEmpty()) showConfirmationDialog()
            }, year, month, day)

            datePicker.show()
        }

        btnTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePicker = TimePickerDialog(this, { _, h, m ->
                selectedTime = String.format("%02d:%02d", h, m)
                tvTime.text = selectedTime

                // Kalau tanggal sudah dipilih, langsung tampilkan konfirmasi
                if (selectedDate.isNotEmpty()) showConfirmationDialog()
            }, hour, minute, true)

            timePicker.show()
        }
    }

    private fun showConfirmationDialog() {
        val message = "${getString(R.string.confirm_dialog_message)}\n\nTanggal: $selectedDate\nWaktu: $selectedTime"

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.confirm_dialog_title))
            .setMessage(message)
            .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                Toast.makeText(this, getString(R.string.confirmed), Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                Toast.makeText(this, getString(R.string.not_confirmed), Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .create()
            .show()
    }
}