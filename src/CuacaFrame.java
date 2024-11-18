import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.table.DefaultTableModel;
import org.json.JSONObject;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author rahdi
 */
public class CuacaFrame extends javax.swing.JFrame {

    /**
     * Creates new form CuacaFrame
     */
    public CuacaFrame() {
        initComponents();
         jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = jTextField1.getText();
                if (!city.isEmpty()) {
                    getWeather(city);
                } else {
                    JOptionPane.showMessageDialog(null, "Masukkan nama kota!");
                }
            }
        });
        jComboBox1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedCity = (String) jComboBox1.getSelectedItem();
            if (selectedCity != null && !selectedCity.isEmpty()) {
                getWeather(selectedCity); // Memanggil fungsi pengecekan cuaca
            }
        }
    });
    }
    private String translateWeatherCondition(String weather) {
        switch (weather.toLowerCase()) {
            case "clear":
                return "Matahari";
            case "clouds":
                return "Berawan";
            case "rain":
                return "Hujan";
            case "snow":
                return "Salju";
            case "thunderstorm":
                return "Badai";
            default:
                return "Kondisi tidak diketahui";
        }
    }
private void getWeather(String city) {
    try {
        String apiKey = "e665b751b1bc7fac55e604e3fb6cd436"; // Ganti dengan API Key Anda
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        conn.disconnect();

        JSONObject json = new JSONObject(content.toString());
        String weather = json.getJSONArray("weather").getJSONObject(0).getString("main");

        // Terjemahkan kondisi cuaca ke bahasa Indonesia
        String translatedWeather = translateWeatherCondition(weather);      
        // Tampilkan kondisi cuaca yang diterjemahkan
        jLabel2.setText("Kondisi Cuaca: " + translatedWeather);
        // Tampilkan gambar cuaca
        jLabel3.setIcon(getWeatherImage(weather));
        
        // Tambahkan data cuaca ke JTable
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{city, translatedWeather});

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}
 private ImageIcon getWeatherImage(String weather) {
        String imagePath;
            switch (weather.toLowerCase()) {
                case "clear":
                    imagePath = "/images/Matahari.png";
                    break;
                case "clouds":
                    imagePath = "/images/Berawan.png";
                    break;
                case "rain":
                    imagePath = "/images/Hujan.png";
                    break;
                case "snow":
                    imagePath = "/images/Salju.png";
                    break;
                case "thunderstorm":
                    imagePath = "/images/Badai.png";
                    break;
                default:
                    imagePath = "/images/Matahari.png"; // Gambar default jika kondisi tidak dikenali
                    break;
            }
            return new ImageIcon(getClass().getResource(imagePath));
    }
    private boolean isCityInComboBox(String city) {
        for (int i = 0; i < jComboBox1.getItemCount(); i++) {
            if (jComboBox1.getItemAt(i).equalsIgnoreCase(city)) {
                return true;
            }
        }
        return false;
    }
    private void saveTableDataToCSV() {
        try {
            FileWriter csvWriter = new FileWriter("dataCuaca.csv");

            // Header CSV
            csvWriter.append("Kota, Kondisi Cuaca\n");

            // Data dari JTable
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                csvWriter.append(jTable1.getValueAt(i, 0).toString());
                csvWriter.append(",");
                csvWriter.append(jTable1.getValueAt(i, 1).toString());
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan ke CSV");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
     private void loadTableDataFromCSV() {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader("dataCuaca.csv"));
            String row;
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0); // Hapus data lama di JTable

            csvReader.readLine(); // Lewati header
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                model.addRow(data);
            }
            csvReader.close();
            JOptionPane.showMessageDialog(this, "Data berhasil dimuat dari CSV");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 400));

        jPanel1.setMinimumSize(new java.awt.Dimension(600, 400));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 300));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Masukan Alamat");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(17, 23, 17, 23);
        jPanel1.add(jLabel1, gridBagConstraints);

        jTextField1.setPreferredSize(new java.awt.Dimension(500, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(15, 51, 15, 51);
        jPanel1.add(jTextField1, gridBagConstraints);

        jButton1.setText("Cek Cuaca");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(17, 23, 17, 23);
        jPanel1.add(jButton1, gridBagConstraints);

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(17, 23, 17, 23);
        jPanel1.add(jComboBox1, gridBagConstraints);

        jButton2.setText("Simpan");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        jPanel1.add(jButton2, gridBagConstraints);

        jButton3.setText("Favorite");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanel1.add(jButton3, gridBagConstraints);

        jButton4.setText("Muat Data");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        jPanel1.add(jButton4, gridBagConstraints);

        jLabel2.setText("Cuaca");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Icons");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel1.add(jLabel3, gridBagConstraints);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(200, 200));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kota", "Cuaca"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(jScrollPane1, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     String city = jTextField1.getText();
        if (!city.isEmpty()) {
            getWeather(city); // Memanggil fungsi pengecekan cuaca
        } else {
            JOptionPane.showMessageDialog(null, "Masukkan nama kota!");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    saveTableDataToCSV(); // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    String city = jTextField1.getText();
            if (!city.isEmpty() && !isCityInComboBox(city)) {
                jComboBox1.addItem(city);
            }
}

private boolean isFavorit(String lokasi) {
    for (int i = 0; i < jComboBox1.getItemCount(); i++) {
        if (jComboBox1.getItemAt(i).equals(lokasi)) {
            return true;
        }
    }
    return false;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
    jTextField1.setText("");
    jLabel2.setIcon(null);        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    loadTableDataFromCSV();// TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CuacaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CuacaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CuacaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CuacaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CuacaFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
