# 📱 Tugas E-Learning - Input Control Android

## 👤 Identitas 
- **Nama**: Salman Azhar Latisio  
- **NIM**: 23552011046  
- **Kelas**: TIF RP 23 CNS C  

---

## 📌 Deskripsi Aplikasi
Aplikasi Android ini dibuat untuk menampilkan `DatePickerDialog` dan `AlertDialog`.

---

## ✅ Fitur yang Diimplementasikan
- 🗓️ **Date Picker** (Pemilihan Tanggal)
- ⚠️ **Alert Dialog** (Konfirmasi pemilihan Tanggal dan waktu)

---

## 🧩 Penjelasan Fitur dan Kode

## 🗓️ 1. Date Picker (Tanggal)
Menampilkan dialog pemilihan tanggal, lalu hasilnya ditampilkan di `TextView`.

```kotlin
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
```

## 📸 Screenshot Fitur Date Picker
![WhatsApp Image 2025-04-21 at 12 22 45_6dc9bc7c](https://github.com/user-attachments/assets/ce878567-e2f2-4233-a079-ad1e4dd0d210)

## ⚠️ 2. Alert Dialog
Menampilkan dialog konfirmasi setelah memilih tanggal dan waktu di text view.
```kotlin
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
```
## 📸 Screenshot Fitur Alert Dialog
![alert](https://github.com/user-attachments/assets/ec45b01e-6176-42ac-8b1a-248992e80b60)
