import java.util.ArrayList;

class Gudang{
    private ArrayList<Barang> daftarBarang; // Daftar untuk menyimpan semua barang dalam gudang.

    // Konstruktor untuk menginisialisasi objek Gudang dan membuat daftar barang kosong.
    public Gudang(){
        this.daftarBarang = new ArrayList<>();
    }

    // Metode untuk menambahkan barang baru ke dalam gudang.
    public void tambahBarang(Barang barang){
        daftarBarang.add(barang); // Menambahkan objek Barang ke daftar.
    }

    // Metode untuk mengurangi jumlah stok barang berdasarkan nama barang dan jumlah yang dikurangi.
    public void kurangiBarang(String nama, int jumlah){
        boolean ditemukan = false; 
        for (Barang barang : daftarBarang){                // Iterasi melalui daftar barang.
            if (barang.getNama().equalsIgnoreCase(nama)){  // Mengecek apakah nama barang cocok
                ditemukan = true;
                if (barang.getStok() >= jumlah){           // Mengecek apakah stok cukup untuk dikurangi.
                    barang.setStok(barang.getStok() - jumlah); // Mengurangi stok barang.
                } else{
                    System.out.println("Jumlah stok tidak mencukupi."); // Pesan jika stok kurang.
                }
                return; // Keluar dari metode setelah stok diperbarui.
            }
        }
        if (!ditemukan){
            System.out.println("Barang dengan nama '" + nama + "' tidak ditemukan."); // Pesan jika barang tidak ditemukan.
        }
    }

    // Metode untuk memperbarui kategori dan harga barang berdasarkan nama.
    public void updateBarang(String nama, String kategoriBaru, double hargaBaru){
        boolean ditemukan = false; 
        for (Barang barang : daftarBarang){                 // Iterasi melalui daftar barang.
            if (barang.getNama().equalsIgnoreCase(nama)){   // Mengecek apakah nama barang cocok
                ditemukan = true;
                barang.setKategori(kategoriBaru);   // Memperbarui kategori barang.
                barang.setHargaSatuan(hargaBaru);   // Memperbarui harga satuan barang.
                return;                             // Keluar dari metode setelah barang diperbarui.
            }
        }
        if (!ditemukan){
            System.out.println("Barang dengan nama '" + nama + "' tidak ditemukan."); // Pesan jika barang tidak ditemukan.
        }
    }

    // Metode untuk mencari barang berdasarkan nama.
    public Barang cariBarang(String nama){
        for (Barang barang : daftarBarang){                 // Iterasi melalui daftar barang.
            if (barang.getNama().equalsIgnoreCase(nama)){   // Mengecek apakah nama barang cocok
                return barang;                              // Mengembalikan objek Barang yang ditemukan.
            }
        }
        return null; // Mengembalikan null jika barang tidak ditemukan.
    }

    // Metode untuk mendapatkan seluruh daftar barang dalam gudang.
    public ArrayList<Barang> getDaftarBarang(){
        return daftarBarang; // Mengembalikan daftar barang.
    }
}
