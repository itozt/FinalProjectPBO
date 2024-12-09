# Final Project PBO - Program Manajemen Gudang
<hr>

# Judul Program
**Manajemen Gudang dengan Pemrograman Berorientasi Objek dan Antarmuka Grafis**

# Deskripsi Program
Program ini dirancang untuk mengelola inventaris gudang secara efektif menggunakan prinsip Pemrograman Berorientasi Objek (OOP). Dengan dukungan antarmuka grafis, pengguna dapat dengan mudah menambah, mengubah, mengurangi stok barang, serta memperbarui informasi barang. Program ini memanfaatkan fitur GUI untuk mempermudah interaksi pengguna dengan data gudang.

# Tujuan Program
- *Efisiensi Manajemen Gudang* : Mempermudah pengelolaan data barang dalam gudang melalui sistem digital.
- *Penggunaan Prinsip OOP* : Menyediakan solusi yang terstruktur dan modular menggunakan konsep OOP seperti enkapsulasi, pewarisan, dan polimorfisme.
- *Interaksi yang Mudah* : Menyediakan antarmuka grafis yang intuitif untuk pengguna sehingga proses manajemen barang dapat dilakukan tanpa perlu intervensi langsung pada kode.
- *Fleksibilitas Operasi* : Mendukung fitur-fitur utama seperti penambahan, pengurangan stok, pembaruan data, dan pencarian barang.

# Fitur-Fitur Program dan Fungsinya
- **Tambah Barang**  
  Fungsi : Memungkinkan pengguna menambahkan data barang baru ke daftar gudang.  
  Kegunaan : Memperbarui inventaris gudang dengan barang baru, termasuk detail seperti nama, kategori, harga, dan stok awal.
  
- **Update Barang**  
  Fungsi : Memperbarui informasi barang seperti kategori dan harga.  
  Kegunaan : Menjaga data barang tetap akurat sesuai kebutuhan operasional.
  
- **Tambah/Kurangi Stok**  
  Fungsi : Menambah atau mengurangi jumlah stok barang.  
  Kegunaan : Mengelola jumlah barang dalam gudang berdasarkan aktivitas masuk dan keluar.
  
- **Pencarian Barang**  
  Fungsi : Mempermudah pencarian barang berdasarkan nama melalui filter tabel.  
  Kegunaan : Menghemat waktu dalam menemukan barang tertentu di gudang.
  
- **Tampilan Tabel Barang**  
  Fungsi : Menampilkan daftar barang dalam bentuk tabel, lengkap dengan kolom aksi seperti Tambah Stok, Kurangi Stok, dan Update.  
  Kegunaan : Memberikan visualisasi data yang jelas dan mempermudah akses informasi barang.
  
- **Refresh Tabel**  
  Fungsi : Memperbarui tampilan tabel setiap kali ada perubahan data barang.  
  Kegunaan : Menjamin tabel selalu menampilkan informasi terkini.
  
- **Subclass ButtonRenderer dan ButtonEditor**  
  Fungsi : Mengelola rendering dan aksi tombol dalam tabel.  
  Kegunaan : Menyediakan pengalaman interaktif untuk pengguna dengan tombol-tombol fungsi di tabel GUI.

# Elemen OOP dalam Program
- **Enkapsulasi**  
  Data seperti atribut nama, kategori, hargaSatuan, dan stok hanya bisa diakses melalui getter dan setter. Atribut private melindungi data dari modifikasi langsung oleh pengguna.
  
- **Inheritance**  
  Subclass seperti ButtonRenderer dan ButtonEditor mewarisi sifat dari kelas induk seperti JButton dan DefaultCellEditor. Pewarisan ini memungkinkan penggunaan kembali logika umum dan pengembangan logika spesifik.
  
- **Polimorfisme**  
  Subclass menimpa metode seperti getTableCellRendererComponent untuk menciptakan perilaku yang sesuai kebutuhan.
  
- **Abstraksi**  
  Implementasi antarmuka seperti TableCellRenderer menyederhanakan logika yang kompleks dalam pengelolaan GUI.

# Source Code GitHub
- Kelas Barang : [Link github Barang.java](https://github.com/itozt/FinalProjectPBO/blob/main/Barang.java)
- Kelas Gudang : [Link Github Gudang.java](https://github.com/itozt/FinalProjectPBO/blob/main/Gudang.java)
- Kelas MainGudangGUI : [Link Github MainGudangGUI.java](https://github.com/itozt/FinalProjectPBO/blob/main/MainGudangGUI.java)
- Repository GitHub : [Link Github Repository](https://github.com/itozt/FinalProjectPBO)

# Diagram Kelas
![Screenshot 2024-12-09 123200](https://github.com/user-attachments/assets/75a7283a-675c-433c-b35f-f6b54c2f9dd5)
