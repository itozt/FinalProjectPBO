import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class MainGudangGUI extends JFrame {
    private Gudang gudang;
    private JTable barangTable;
    private DefaultTableModel tableModel;
    private JTextField namaField, hargaField, stokField, searchField;
    private JComboBox<String> kategoriComboBox;

    public MainGudangGUI() {
        gudang = new Gudang(); 
        // Membuat instance objek Gudang untuk menyimpan data barang.
    
        // Set up the main frame
        setTitle("Sistem Manajemen Gudang");      // Mengatur judul jendela utama aplikasi.
        setSize(1100, 650);                // Mengatur ukuran jendela utama.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Mengatur agar aplikasi berhenti saat jendela ditutup.
        setLayout(new BorderLayout());                  // Menggunakan layout BorderLayout untuk pengaturan komponen.
    
        // Create top panel for inputs and search bar
        JPanel topPanel = new JPanel(new BorderLayout());   // Membuat panel utama bagian atas untuk menampung input dan pencarian.
        JPanel inputPanel = createInputPanel();             // Membuat panel input menggunakan metode createInputPanel().
        JPanel searchPanel = createSearchPanel();           // Membuat panel pencarian menggunakan metode createSearchPanel().
        topPanel.add(inputPanel, BorderLayout.WEST);        // Menambahkan panel input di sisi kiri (West) dari panel atas.
        topPanel.add(searchPanel, BorderLayout.EAST);       // Menambahkan panel pencarian di sisi kanan (East) dari panel atas.
    
        add(topPanel, BorderLayout.NORTH); // Menambahkan panel atas ke bagian utara (North) jendela utama.
    
        // Create table panel
        JPanel tablePanel = createTablePanel(); // Membuat panel tabel menggunakan metode createTablePanel().
        add(tablePanel, BorderLayout.CENTER);   // Menambahkan panel tabel ke bagian tengah (Center) jendela utama.
    
        // Menambahkan contoh data awal
        tambahDataAwal(); 
    }
    

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Tambah Barang"));

        inputPanel.add(new JLabel("Nama Barang:"));
        namaField = new JTextField();
        inputPanel.add(namaField);

        inputPanel.add(new JLabel("Kategori:"));
        kategoriComboBox = new JComboBox<>(new String[]{
            "Makanan/Minuman", "Obat", "Alat Rumah Tangga", "Alat Elektronik", "Pakaian"
        });
        inputPanel.add(kategoriComboBox);

        inputPanel.add(new JLabel("Harga Satuan:"));
        hargaField = new JTextField();
        inputPanel.add(hargaField);

        inputPanel.add(new JLabel("Stok:"));
        stokField = new JTextField();
        inputPanel.add(stokField);

        JButton tambahButton = new JButton("Tambah Barang");
        tambahButton.addActionListener(e -> tambahBarang());
        inputPanel.add(new JLabel());
        inputPanel.add(tambahButton);

        return inputPanel;
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new GridLayout(2, 1));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search Bar"));

        searchField = new JTextField();
        searchField.addActionListener(e -> searchBarang());
        searchPanel.add(searchField);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> cancelSearch());
        searchPanel.add(cancelButton);

        return searchPanel;
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());  // Membuat panel tabel dengan layout BorderLayout untuk menampung tabel.
    
        // Create table model
        String[] columnNames = {"Nama", "Kategori", "Harga Satuan", "Stok", "Tambah Stok", "Kurangi Stok", "Update"};
        // Menentukan nama kolom yang akan ditampilkan pada tabel.
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Menentukan kolom yang dapat diedit. 
                // Hanya kolom ke-4, ke-5, dan ke-6 yang berisi tombol yang dapat diedit.
                return column >= 4;
            }
        };
        barangTable = new JTable(tableModel);
        // Membuat JTable menggunakan model tabel yang sudah ditentukan.
    
        // Menambahkan button renderer dan editor
        // Menambahkan renderer untuk kolom "Tambah Stok" agar bisa menampilkan tombol.
        barangTable.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer()); 
        // Menambahkan editor untuk kolom "Tambah Stok" agar tombol dapat diklik dan menambah stok barang.
        barangTable.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JButton("Tambah")));
    
        // Menambahkan renderer untuk kolom "Kurangi Stok" agar bisa menampilkan tombol.
        barangTable.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        // Menambahkan editor untuk kolom "Kurangi Stok" agar tombol dapat diklik dan mengurangi stok barang.
        barangTable.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JButton("Kurangi")));
    
        // Menambahkan renderer untuk kolom "Update" agar bisa menampilkan tombol.
        barangTable.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        // Menambahkan editor untuk kolom "Update" agar tombol dapat diklik dan memperbarui data barang.
        barangTable.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JButton("Update")));
    
        // Tambahkan tabel ke scroll pane
        JScrollPane scrollPane = new JScrollPane(barangTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
    
        return tablePanel;
    }
    

    private void tambahBarang() {
        try {
            String nama = namaField.getText();
            String kategori = (String) kategoriComboBox.getSelectedItem();
            double harga = Double.parseDouble(hargaField.getText());
            int stok = Integer.parseInt(stokField.getText());

            Barang barang = new Barang(nama, kategori, harga, stok);
            gudang.tambahBarang(barang);

            // Update tabel
            tableModel.addRow(new Object[]{nama, kategori, harga, stok, "Tambah Stok", "Kurangi Stok", "Update"});

            // Clear input fields
            clearInputFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Masukkan angka yang valid untuk harga dan stok!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchBarang() {
        String searchQuery = searchField.getText().toLowerCase();
        tableModel.setRowCount(0); // clear tabel

        for (Barang barang : gudang.getDaftarBarang()) {
            if (barang.getNama().toLowerCase().contains(searchQuery)) {
                tableModel.addRow(new Object[]{
                        barang.getNama(),
                        barang.getKategori(),
                        barang.getHargaSatuan(),
                        barang.getStok(),
                        "Tambah Stok",
                        "Kurangi Stok",
                        "Update"
                });
            }
        }
    }

    private void cancelSearch() {
        searchField.setText(""); // membersihkan search bar
        refreshTable(); // Reload full table data
    }

    private void tambahDataAwal() {
        // Makanan/Minuman
        gudang.tambahBarang(new Barang("Indomie Mie Goreng", "Makanan/Minuman", 3000.0, 90));
        gudang.tambahBarang(new Barang("Teh Botol Sosro", "Makanan/Minuman", 4000.0, 80));
    
        // Obat
        gudang.tambahBarang(new Barang("OBH Combi", "Obat", 10000.0, 10));
        gudang.tambahBarang(new Barang("Tolak Angin", "Obat", 4000.0, 1000));
        gudang.tambahBarang(new Barang("Balsem", "Obat", 5000.0, 300));
    
        // Alat Rumah Tangga
        gudang.tambahBarang(new Barang("Sapu Lidi", "Alat Rumah Tangga", 10000.0, 42));
        gudang.tambahBarang(new Barang("Cikrak", "Alat Rumah Tangga", 10000.0, 38));
        gudang.tambahBarang(new Barang("Sapu Ijuk", "Alat Rumah Tangga", 12000.0, 70));
    
        // Alat Elektronik
        gudang.tambahBarang(new Barang("TV LED", "Alat Elektronik", 3000000.0, 23));
        gudang.tambahBarang(new Barang("Samsung A23 5G", "Alat Elektronik", 3200000.0, 15));
    
        // Pakaian
        gudang.tambahBarang(new Barang("Jacket Kulit", "Pakaian", 350000.0, 25));
        gudang.tambahBarang(new Barang("Celana Jeans", "Pakaian", 150000.0, 50));
    
        // Refresh tabel setelah menambah data baru
        refreshTable();
    }    

    private void refreshTable() {
        // Clear existing rows
        tableModel.setRowCount(0);

        // Refill tabel dengan data terkini
        for (Barang barang : gudang.getDaftarBarang()) {
            tableModel.addRow(new Object[]{
                    barang.getNama(),
                    barang.getKategori(),
                    barang.getHargaSatuan(),
                    barang.getStok(),
                    "Tambah Stok",
                    "Kurangi Stok",
                    "Update"
            });
        }
    }

    private void clearInputFields() {
        namaField.setText("");
        kategoriComboBox.setSelectedIndex(0);
        hargaField.setText("");
        stokField.setText("");
    }

    private class ButtonRenderer extends JButton implements TableCellRenderer {
        // ButtonRenderer adalah kelas yang mengubah sel pada tabel menjadi tombol.
        
        public ButtonRenderer() {
            setOpaque(true); // Menetapkan properti tombol agar tampak tidak transparan (opaque).
        }
    
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            // Mengonfigurasi tombol agar tampil pada sel tabel.
            setText(value.toString());  // Mengatur teks tombol dengan nilai yang ada di sel tersebut
            return this;  // Mengembalikan tombol ini sebagai komponen yang akan ditampilkan di sel tabel.
        }
    }
    

    // ButtonEditor class merupakan custom editor untuk sel tabel yang berisi tombol.
private class ButtonEditor extends DefaultCellEditor {
    private JButton button;   // Tombol yang akan muncul dalam sel
    private String label;     // Label untuk tombol (misalnya: "Tambah Stok", "Kurangi Stok", dll)
    private int selectedRow;  // Menyimpan baris yang dipilih

    // Konstruktor ButtonEditor, menginisialisasi tombol dan menambahkan listener
    public ButtonEditor(JButton checkBox) {
        super(new JCheckBox());                              // Memanggil konstruktor superclass
        button = new JButton();                              // Membuat tombol baru
        button.setOpaque(true);                     // Mengatur tombol agar tidak transparan
        button.addActionListener(e -> fireEditingStopped()); // Memberikan aksi ketika tombol ditekan
    }

    // Mendapatkan komponen untuk sel editor dan mengatur label pada tombol
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                  boolean isSelected, int row, int column) {
        label = value.toString(); // Mengambil label dari nilai sel
        selectedRow = row;        // Menyimpan baris yang dipilih
        button.setText(label);    // Mengatur teks tombol sesuai dengan nilai sel
        return button;            // Mengembalikan tombol sebagai komponen editor
    }

    // Menangani aksi setelah tombol diklik
    @Override
    public Object getCellEditorValue() {
        if (label.equals("Tambah Stok")) {
            tambahStok(selectedRow);  // Menambah stok jika tombol "Tambah Stok" diklik
        } else if (label.equals("Kurangi Stok")) {
            kurangiStok(selectedRow); // Mengurangi stok jika tombol "Kurangi Stok" diklik
        } else if (label.equals("Update")) {
            updateBarang(selectedRow); // Memperbarui barang jika tombol "Update" diklik
        }
        return label;  // Mengembalikan label sebagai nilai sel
    }

    // Menambahkan stok barang yang dipilih
    private void tambahStok(int row) {
        String jumlahStr = JOptionPane.showInputDialog("Masukkan jumlah stok yang akan ditambahkan:");
        try {
            int jumlah = Integer.parseInt(jumlahStr);                           // Mengambil input jumlah stok
            String nama = tableModel.getValueAt(row, 0).toString();      // Mendapatkan nama barang
            gudang.kurangiBarang(nama, -jumlah);                                // Menambahkan stok dengan mengurangi nilai negatif
            refreshTable();                                                     // Merefresh tabel setelah perubahan
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(button, "Masukkan angka yang valid!"); // Menampilkan error jika input tidak valid
        }
    }

    // Mengurangi stok barang yang dipilih
    private void kurangiStok(int row) {
        String jumlahStr = JOptionPane.showInputDialog("Masukkan jumlah stok yang akan dikurangi:");
        try {
            int jumlah = Integer.parseInt(jumlahStr);                           // Mengambil input jumlah stok
            String nama = tableModel.getValueAt(row, 0).toString();      // Mendapatkan nama barang
            gudang.kurangiBarang(nama, jumlah);                                 // Mengurangi stok sesuai input
            refreshTable();                                                     // Merefresh tabel setelah perubahan
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(button, "Masukkan angka yang valid!"); // Menampilkan error jika input tidak valid
        }
    }

    // Memperbarui data barang berdasarkan input pengguna
    private void updateBarang(int row) {
        String nama = tableModel.getValueAt(row, 0).toString(); // Mendapatkan nama barang yang dipilih

        // Membuat panel untuk input kategori dan harga baru
        JPanel updatePanel = new JPanel(new GridLayout(3, 2));
        JTextField hargaField = new JTextField(tableModel.getValueAt(row, 2).toString()); // Menampilkan harga saat ini
        JComboBox<String> kategoriComboBox = new JComboBox<>(new String[]{
                "Makanan/Minuman", "Obat", "Alat Rumah Tangga", "Alat Elektronik", "Pakaian"
        });
        kategoriComboBox.setSelectedItem(tableModel.getValueAt(row, 1).toString()); // Menampilkan kategori saat ini

        // Menambahkan komponen input ke panel
        updatePanel.add(new JLabel("Kategori:"));
        updatePanel.add(kategoriComboBox);
        updatePanel.add(new JLabel("Harga Satuan:"));
        updatePanel.add(hargaField);

        // Menampilkan dialog untuk konfirmasi update
        int result = JOptionPane.showConfirmDialog(null, updatePanel, "Update Barang", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String kategori = (String) kategoriComboBox.getSelectedItem();  // Mengambil kategori baru
                double harga = Double.parseDouble(hargaField.getText());        // Mengambil harga baru
                gudang.updateBarang(nama, kategori, harga);                     // Memperbarui barang di gudang
                refreshTable();                                                 // Merefresh tabel setelah pembaruan
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(button, "Masukkan angka yang valid!"); // Menampilkan error jika harga tidak valid
            }
        }
    }
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGudangGUI().setVisible(true));
    }
}
