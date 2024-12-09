public class Barang{
    private String nama;        // Nama barang
    private String kategori;    // Kategori barang (contoh: Elektronik, Pakaian, dll.)
    private double hargaSatuan; // Harga per satuan barang
    private int stok;           // Jumlah stok barang yang tersedia

    // Konstruktor untuk inisialisasi objek Barang dengan data awal
    public Barang(String nama, String kategori, double hargaSatuan, int stok){
        this.nama = nama;
        this.kategori = kategori;
        this.hargaSatuan = hargaSatuan;
        this.stok = stok;
    }

    // Getter dan setter digunakan untuk mengakses dan mengubah atribut secara aman

    public String getNama(){
        return nama; // Mengembalikan nama barang
    }

    public void setNama(String nama){
        this.nama = nama; // Mengubah nama barang
    }

    public String getKategori(){
        return kategori; // Mengembalikan kategori barang
    }

    public void setKategori(String kategori){
        this.kategori = kategori; // Mengubah kategori barang
    }

    public double getHargaSatuan(){
        return hargaSatuan; // Mengembalikan harga satuan barang
    }

    public void setHargaSatuan(double hargaSatuan){
        this.hargaSatuan = hargaSatuan; // Mengubah harga satuan barang
    }

    public int getStok(){
        return stok; // Mengembalikan jumlah stok barang
    }

    public void setStok(int stok){
        this.stok = stok; // Mengubah jumlah stok barang
    }

    // Metode toString untuk memberikan representasi String dari objek Barang
    @Override
    public String toString(){
        return "Nama: " + nama + 
               ", Kategori: " + kategori + 
               ", Harga Satuan: " + hargaSatuan + 
               ", Stok: " + stok;
        // Mengembalikan informasi lengkap barang dalam format string
    }
}
