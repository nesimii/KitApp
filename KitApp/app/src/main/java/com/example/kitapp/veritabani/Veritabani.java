package com.example.kitapp.veritabani;

import android.graphics.BitmapFactory;
import android.os.StrictMode;

import com.example.kitapp.activity.MainActivity;
import com.example.kitapp.kitap.Kitap;
import com.example.kitapp.uye.Uye;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class Veritabani {

    private Connection baglanti;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
    private String sqlSorgu;

    private void baglantiAc() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String sunucuadresi = "192.168.0.16";
            String veritabani = "KitApp";
            String kullaniciAdi = "nesimi";
            String sifre = "admin";
            String ConnectionURL = "jdbc:jtds:sqlserver://" + sunucuadresi + ";database=" + veritabani
                    + ";user=" + kullaniciAdi + ";password=" + sifre + ";";
            baglanti = DriverManager.getConnection(ConnectionURL);
        } catch (Exception e) {
            System.out.println("Veritabanı bağlantısı kurulamadı");
            e.printStackTrace();
        }
    }

    private void baglantiKapat() {
        try {
            baglanti.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Kitap işlemleri
    public boolean kitapEkle(String ad, String yazar, String kategori, String sayfa, String baski, String isbn, String ozet, byte[] resim) {
        baglantiAc();
        try {
            sqlSorgu = "INSERT INTO KitApp.dbo.kitaplar (kitapAdi,yazari,kategori,sayfaSayisi,baskiSayisi,isbn,ozeti,resim) VALUES (?,?,?,?,?,?,?,?)";
            preparedStatement = baglanti.prepareStatement(sqlSorgu);
            preparedStatement.setString(1, ad);
            preparedStatement.setString(2, yazar);
            preparedStatement.setString(3, kategori);
            preparedStatement.setString(4, sayfa);
            preparedStatement.setString(5, baski);
            preparedStatement.setString(6, isbn);
            preparedStatement.setString(7, ozet);
            preparedStatement.setBytes(8, resim);
            preparedStatement.execute();
            baglantiKapat();
            return true;
        } catch (Exception e) {
            baglantiKapat();
            System.out.println("Kitap eklenemedi");
            e.printStackTrace();
            return false;
        }
    }

    public boolean kitapGuncelle(String id, String ad, String yazar, String kategori, String sayfa, String baski, String isbn, String ozet) {
        try {
            baglantiAc();
            sqlSorgu = "UPDATE KitApp.dbo.kitaplar SET kitapAdi=?,yazari=?,kategori=?,sayfaSayisi=?,baskiSayisi=?,isbn=?,ozeti=? WHERE id=?";
            preparedStatement = baglanti.prepareStatement(sqlSorgu);
            preparedStatement.setString(1, ad);
            preparedStatement.setString(2, yazar);
            preparedStatement.setString(3, kategori);
            preparedStatement.setString(4, sayfa);
            preparedStatement.setString(5, baski);
            preparedStatement.setString(6, isbn);
            preparedStatement.setString(7, ozet);
            preparedStatement.setString(8, id);
            preparedStatement.executeUpdate();
            baglantiKapat();
            return true;
        } catch (SQLException throwables) {
            baglantiKapat();
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean kitapGuncelle(String id, String ad, String yazar, String kategori, String sayfa, String baski, String isbn, String ozet, byte[] resim) {
        try {
            baglantiAc();
            sqlSorgu = "UPDATE KitApp.dbo.kitaplar SET kitapAdi=?,yazari=?,kategori=?,sayfaSayisi=?,baskiSayisi=?,isbn=?,ozeti=?,resim=? WHERE id=?";
            preparedStatement = baglanti.prepareStatement(sqlSorgu);
            preparedStatement.setString(1, ad);
            preparedStatement.setString(2, yazar);
            preparedStatement.setString(3, kategori);
            preparedStatement.setString(4, sayfa);
            preparedStatement.setString(5, baski);
            preparedStatement.setString(6, isbn);
            preparedStatement.setString(7, ozet);
            preparedStatement.setBytes(8, resim);
            preparedStatement.setString(9, id);
            preparedStatement.executeUpdate();
            baglantiKapat();
            return true;
        } catch (SQLException throwables) {
            baglantiKapat();
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean kitapSil(String kitapId) {
        baglantiAc();
        try {
            sqlSorgu = "DELETE KitApp.dbo.kitaplar WHERE id=?";
            preparedStatement = baglanti.prepareStatement(sqlSorgu);
            preparedStatement.setString(1, kitapId);
            preparedStatement.execute();
            baglantiKapat();
            return true;
        } catch (SQLException throwables) {
            baglantiKapat();
            throwables.printStackTrace();
            return false;
        }
    }

    public ArrayList<Kitap> kitaplariListele() {
        baglantiAc();
        ArrayList<Kitap> kitapList = new ArrayList<>();
        Kitap kitap = new Kitap();
        byte[] gelenResimByte;
        try {
            statement = baglanti.createStatement();
            sqlSorgu = "SELECT * FROM KitApp.dbo.kitaplar";
            resultSet = statement.executeQuery(sqlSorgu);
            while (resultSet.next()) {
                kitap.setKitapId(resultSet.getString("id"));
                kitap.setKitapAdi(resultSet.getString("kitapAdi"));
                kitap.setKitapYazari(resultSet.getString("yazari"));
                kitap.setKitapKategori(resultSet.getString("kategori"));
                kitap.setKitapSayfaSayisi(resultSet.getString("sayfaSayisi"));
                kitap.setKitapBaskiSayisi(resultSet.getString("baskiSayisi"));
                kitap.setKitapISBN(resultSet.getString("isbn"));
                kitap.setKitapOzeti(resultSet.getString("ozeti"));

                gelenResimByte = (resultSet.getBytes("resim"));
                kitap.setKitapResim(BitmapFactory.decodeByteArray(gelenResimByte, 0, gelenResimByte.length));
                kitapList.add(kitap);
                kitap = new Kitap();
            }
            baglantiKapat();
            return kitapList;
        } catch (SQLException throwables) {
            baglantiKapat();
            throwables.printStackTrace();
            return null;
        }
    }

    public ArrayList<Kitap> kitapAra(String kategori, String kitapAdii) {
        ArrayList<Kitap> kitapList = new ArrayList<>();
        Kitap kitap = new Kitap();
        byte[] gelenResimByte;

        if (kategori.equals("Kategori Seçiniz")) {
            sqlSorgu = "SELECT * FROM KitApp.dbo.kitaplar where kitapAdi LIKE '" + kitapAdii + "%'";
        } else {
            sqlSorgu = "SELECT * FROM KitApp.dbo.kitaplar where kategori='" + kategori + "' and kitapAdi like '" + kitapAdii + "%'";
        }
        try {
            baglantiAc();
            statement = baglanti.createStatement();
            resultSet = statement.executeQuery(sqlSorgu);
            while (resultSet.next()) {
                kitap.setKitapId(resultSet.getString("id"));
                kitap.setKitapAdi(resultSet.getString("kitapAdi"));
                kitap.setKitapYazari(resultSet.getString("yazari"));
                kitap.setKitapKategori(resultSet.getString("kategori"));
                kitap.setKitapSayfaSayisi(resultSet.getString("sayfaSayisi"));
                kitap.setKitapBaskiSayisi(resultSet.getString("baskiSayisi"));
                kitap.setKitapISBN(resultSet.getString("isbn"));
                kitap.setKitapOzeti(resultSet.getString("ozeti"));

                gelenResimByte = (resultSet.getBytes("resim"));
                kitap.setKitapResim(BitmapFactory.decodeByteArray(gelenResimByte, 0, gelenResimByte.length));
                kitapList.add(kitap);
                kitap = new Kitap();
            }
            baglantiKapat();
            return kitapList;
        } catch (SQLException throwables) {
            baglantiKapat();
            throwables.printStackTrace();
            return null;
        }
    }

    public ArrayList<Kitap> favoriKitap() {
        ArrayList<Kitap> kitapList = new ArrayList<>();
        Kitap kitap = new Kitap();
        byte[] gelenResimByte;

        ArrayList<String> kitapID = new ArrayList<>();

        Random randomSayi = new Random();
        int rastgeleSayi;

        baglantiAc();
        try {
            statement = baglanti.createStatement();

            sqlSorgu = "SELECT * FROM KitApp.dbo.kitaplar WHERE kategori='" + MainActivity.uye.getFavori1() + "'";
            resultSet = statement.executeQuery(sqlSorgu);
            while (resultSet.next()) {
                kitapID.add(resultSet.getString("id"));
            }
            rastgeleSayi = randomSayi.nextInt(kitapID.size());
            sqlSorgu = "SELECT * FROM KitApp.dbo.kitaplar WHERE id=" + kitapID.get(rastgeleSayi) + "";
            kitapID.clear();
            resultSet = statement.executeQuery(sqlSorgu);

            while (resultSet.next()) {
                kitap.setKitapId(resultSet.getString("id"));
                kitap.setKitapAdi(resultSet.getString("kitapAdi"));
                kitap.setKitapYazari(resultSet.getString("yazari"));
                kitap.setKitapKategori(resultSet.getString("kategori"));
                kitap.setKitapSayfaSayisi(resultSet.getString("sayfaSayisi"));
                kitap.setKitapBaskiSayisi(resultSet.getString("baskiSayisi"));
                kitap.setKitapISBN(resultSet.getString("isbn"));
                kitap.setKitapOzeti(resultSet.getString("ozeti"));

                gelenResimByte = (resultSet.getBytes("resim"));
                kitap.setKitapResim(BitmapFactory.decodeByteArray(gelenResimByte, 0, gelenResimByte.length));
                kitapList.add(kitap);
                kitap = new Kitap();
            }
            sqlSorgu = "SELECT * FROM KitApp.dbo.kitaplar WHERE kategori='" + MainActivity.uye.getFavori2() + "'";
            resultSet = statement.executeQuery(sqlSorgu);
            while (resultSet.next()) {
                kitapID.add(resultSet.getString("id"));
            }
            rastgeleSayi = randomSayi.nextInt(kitapID.size());
            sqlSorgu = "SELECT * FROM KitApp.dbo.kitaplar WHERE id=" + kitapID.get(rastgeleSayi) + "";
            kitapID.clear();
            resultSet = statement.executeQuery(sqlSorgu);

            while (resultSet.next()) {
                kitap.setKitapId(resultSet.getString("id"));
                kitap.setKitapAdi(resultSet.getString("kitapAdi"));
                kitap.setKitapYazari(resultSet.getString("yazari"));
                kitap.setKitapKategori(resultSet.getString("kategori"));
                kitap.setKitapSayfaSayisi(resultSet.getString("sayfaSayisi"));
                kitap.setKitapBaskiSayisi(resultSet.getString("baskiSayisi"));
                kitap.setKitapISBN(resultSet.getString("isbn"));
                kitap.setKitapOzeti(resultSet.getString("ozeti"));

                gelenResimByte = (resultSet.getBytes("resim"));
                kitap.setKitapResim(BitmapFactory.decodeByteArray(gelenResimByte, 0, gelenResimByte.length));
                kitapList.add(kitap);
                kitap = new Kitap();
            }
            sqlSorgu = "SELECT * FROM KitApp.dbo.kitaplar WHERE kategori='" + MainActivity.uye.getFavori3() + "'";
            resultSet = statement.executeQuery(sqlSorgu);
            while (resultSet.next()) {
                kitapID.add(resultSet.getString("id"));
            }
            rastgeleSayi = randomSayi.nextInt(kitapID.size());
            sqlSorgu = "SELECT * FROM KitApp.dbo.kitaplar WHERE id=" + kitapID.get(rastgeleSayi) + "";
            kitapID.clear();
            resultSet = statement.executeQuery(sqlSorgu);

            while (resultSet.next()) {
                kitap.setKitapId(resultSet.getString("id"));
                kitap.setKitapAdi(resultSet.getString("kitapAdi"));
                kitap.setKitapYazari(resultSet.getString("yazari"));
                kitap.setKitapKategori(resultSet.getString("kategori"));
                kitap.setKitapSayfaSayisi(resultSet.getString("sayfaSayisi"));
                kitap.setKitapBaskiSayisi(resultSet.getString("baskiSayisi"));
                kitap.setKitapISBN(resultSet.getString("isbn"));
                kitap.setKitapOzeti(resultSet.getString("ozeti"));

                gelenResimByte = (resultSet.getBytes("resim"));
                kitap.setKitapResim(BitmapFactory.decodeByteArray(gelenResimByte, 0, gelenResimByte.length));
                kitapList.add(kitap);
                kitap = new Kitap();
            }


            baglantiKapat();
            return kitapList;
        } catch (Exception e) {
            baglantiKapat();
            System.out.println("hikmetin çükü");
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Kitap> favoriKitaplarim() {
        ArrayList<Kitap> kitapList = new ArrayList<>();
        Kitap kitap = new Kitap();
        byte[] gelenResimByte;

        Random rastgele = new Random();
        int rastgeleSayi;

        baglantiAc();
        try {
            statement = baglanti.createStatement();

            sqlSorgu = "SELECT COUNT(kategori) FROM KitApp.dbo.kitaplar WHERE kategori='" + MainActivity.uye.getFavori1() + "'";
            resultSet = statement.executeQuery(sqlSorgu);
            resultSet.next();
            if (resultSet.getInt(1) > 0) {
                rastgeleSayi = rastgele.nextInt(resultSet.getInt(1)) + 1;
                System.out.println("Rasgele1:" + rastgeleSayi);
                sqlSorgu = "SELECT * FROM KitApp.dbo.kitaplar WHERE kategori='" + MainActivity.uye.getFavori1() + "' and id=" + rastgeleSayi;
                resultSet = statement.executeQuery(sqlSorgu);
                resultSet.next();
                kitap.setKitapId(resultSet.getString("id"));
                kitap.setKitapAdi(resultSet.getString("kitapAdi"));
                kitap.setKitapYazari(resultSet.getString("yazari"));
                kitap.setKitapKategori(resultSet.getString("kategori"));
                kitap.setKitapSayfaSayisi(resultSet.getString("sayfaSayisi"));
                kitap.setKitapBaskiSayisi(resultSet.getString("baskiSayisi"));
                kitap.setKitapISBN(resultSet.getString("isbn"));
                kitap.setKitapOzeti(resultSet.getString("ozeti"));

                gelenResimByte = (resultSet.getBytes("resim"));
                kitap.setKitapResim(BitmapFactory.decodeByteArray(gelenResimByte, 0, gelenResimByte.length));
                kitapList.add(kitap);
                kitap = new Kitap();
            }

            sqlSorgu = "SELECT COUNT(kategori) FROM KitApp.dbo.kitaplar WHERE kategori='" + MainActivity.uye.getFavori2() + "'";
            resultSet = statement.executeQuery(sqlSorgu);
            resultSet.next();
            if (resultSet.getInt(1) > 0) {
                rastgeleSayi = rastgele.nextInt(resultSet.getInt(1)) + 1;
                System.out.println("Rasgele2:" + rastgeleSayi);
                sqlSorgu = "SELECT * FROM KitApp.dbo.kitaplar WHERE kategori='" + MainActivity.uye.getFavori2() + "' and id=" + rastgeleSayi;
                resultSet = statement.executeQuery(sqlSorgu);
                resultSet.next();
                kitap.setKitapId(resultSet.getString("id"));
                kitap.setKitapAdi(resultSet.getString("kitapAdi"));
                kitap.setKitapYazari(resultSet.getString("yazari"));
                kitap.setKitapKategori(resultSet.getString("kategori"));
                kitap.setKitapSayfaSayisi(resultSet.getString("sayfaSayisi"));
                kitap.setKitapBaskiSayisi(resultSet.getString("baskiSayisi"));
                kitap.setKitapISBN(resultSet.getString("isbn"));
                kitap.setKitapOzeti(resultSet.getString("ozeti"));

                gelenResimByte = (resultSet.getBytes("resim"));
                kitap.setKitapResim(BitmapFactory.decodeByteArray(gelenResimByte, 0, gelenResimByte.length));
                kitapList.add(kitap);
                kitap = new Kitap();
            }

            sqlSorgu = "SELECT COUNT(kategori) FROM KitApp.dbo.kitaplar WHERE kategori='" + MainActivity.uye.getFavori3() + "'";
            resultSet = statement.executeQuery(sqlSorgu);
            resultSet.next();
            if (resultSet.getInt(1) > 0) {
                rastgeleSayi = rastgele.nextInt(resultSet.getInt(1)) + 1;
                System.out.println("Rasgele3:" + rastgeleSayi);
                sqlSorgu = "SELECT * FROM KitApp.dbo.kitaplar WHERE id=" + rastgeleSayi;
                resultSet = statement.executeQuery(sqlSorgu);
                resultSet.next();
                kitap.setKitapId(resultSet.getString("id"));
                kitap.setKitapAdi(resultSet.getString("kitapAdi"));
                kitap.setKitapYazari(resultSet.getString("yazari"));
                kitap.setKitapKategori(resultSet.getString("kategori"));
                kitap.setKitapSayfaSayisi(resultSet.getString("sayfaSayisi"));
                kitap.setKitapBaskiSayisi(resultSet.getString("baskiSayisi"));
                kitap.setKitapISBN(resultSet.getString("isbn"));
                kitap.setKitapOzeti(resultSet.getString("ozeti"));

                gelenResimByte = (resultSet.getBytes("resim"));
                kitap.setKitapResim(BitmapFactory.decodeByteArray(gelenResimByte, 0, gelenResimByte.length));
                kitapList.add(kitap);
                kitap = new Kitap();
            }
            baglantiKapat();
            return kitapList;
        } catch (SQLException throwables) {
            baglantiKapat();
            throwables.printStackTrace();
            return null;
        }
    }

    //Üye işlemleri
    public boolean uyeKayit(String ad, String soyad, String telefon, String kullaniciAdii, String sifree, String favori1, String favori2, String favori3) {
        baglantiAc();
        try {
            sqlSorgu = "INSERT INTO KitApp.dbo.uyeler (uyeAdi,uyeSoyadi,uyeTelefon,kullaniciAdi,sifre,favori1,favori2,favori3) VALUES (?,?,?,?,?,?,?,?)";
            preparedStatement = baglanti.prepareStatement(sqlSorgu);
            preparedStatement.setString(1, ad);
            preparedStatement.setString(2, soyad);
            preparedStatement.setString(3, telefon);
            preparedStatement.setString(4, kullaniciAdii);
            preparedStatement.setString(5, sifree);
            preparedStatement.setString(6, favori1);
            preparedStatement.setString(7, favori2);
            preparedStatement.setString(8, favori3);
            preparedStatement.execute();
            baglantiKapat();
            return true;
        } catch (SQLException throwables) {
            baglantiKapat();
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean uyeGirisi(String kullaniciAdii, String sifree) {
        baglantiAc();
        try {
            sqlSorgu = "SELECT * FROM KitApp.dbo.uyeler WHERE kullaniciAdi=? and sifre=?";
            preparedStatement = baglanti.prepareStatement(sqlSorgu);
            preparedStatement.setString(1, kullaniciAdii);
            preparedStatement.setString(2, sifree);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                MainActivity.uye.setId(resultSet.getString("id"));
                MainActivity.uye.setAd(resultSet.getString("uyeAdi"));
                MainActivity.uye.setSoyad(resultSet.getString("uyeSoyadi"));
                MainActivity.uye.setTelefon(resultSet.getString("uyeTelefon"));
                MainActivity.uye.setKullaniciAdi(resultSet.getString("kullaniciAdi"));
                MainActivity.uye.setSifre(resultSet.getString("sifre"));
                MainActivity.uye.setFavori1(resultSet.getString("favori1"));
                MainActivity.uye.setFavori2(resultSet.getString("favori2"));
                MainActivity.uye.setFavori3(resultSet.getString("favori3"));
                System.out.println("Favori1 " + MainActivity.uye.getFavori1());
                System.out.println("Favori2 " + MainActivity.uye.getFavori2());
                System.out.println("Favori3 " + MainActivity.uye.getFavori3());
                baglantiKapat();
                return true;
            } else {
                baglantiKapat();
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            baglantiKapat();
            return false;
        }
    }

    public boolean uyeGuncelle(String id, String ad, String soyad, String telefon, String kullaniciAdii, String sifree, String favori1, String favori2, String favori3) {
        baglantiAc();
        sqlSorgu = "UPDATE KitApp.dbo.uyeler SET uyeAdi=?,uyeSoyadi=?,uyeTelefon=?,kullaniciAdi=?,sifre=?,favori1=?,favori2=?,favori3=? WHERE id=?";
        try {
            preparedStatement = baglanti.prepareStatement(sqlSorgu);
            preparedStatement.setString(1, ad);
            preparedStatement.setString(2, soyad);
            preparedStatement.setString(3, telefon);
            preparedStatement.setString(4, kullaniciAdii);
            preparedStatement.setString(5, sifree);
            preparedStatement.setString(6, favori1);
            preparedStatement.setString(7, favori2);
            preparedStatement.setString(8, favori3);
            preparedStatement.setString(9, id);
            preparedStatement.execute();
            baglantiKapat();
            return true;
        } catch (SQLException throwables) {
            baglantiKapat();
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean uyeligiSil(String id) {
        baglantiAc();
        sqlSorgu = "DELETE KitApp.dbo.uyeler WHERE id=?";
        try {
            preparedStatement = baglanti.prepareStatement(sqlSorgu);
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            baglantiKapat();
            return true;
        } catch (SQLException throwables) {
            baglantiKapat();
            throwables.printStackTrace();
            return false;
        }
    }

    public ArrayList<Uye> uyeleriListele(String telefon) {
        ArrayList<Uye> uyeList = new ArrayList<>();
        Uye uye = new Uye();
        try {
            baglantiAc();
            statement = baglanti.createStatement();
            sqlSorgu = "SELECT * FROM KitApp.dbo.uyeler WHERE uyeTelefon LIKE '" + telefon + "%'";
            resultSet = statement.executeQuery(sqlSorgu);
            while (resultSet.next()) {
                uye.setId(resultSet.getString("id"));
                uye.setAd(resultSet.getString("uyeAdi"));
                uye.setSoyad(resultSet.getString("uyeSoyadi"));
                uye.setTelefon(resultSet.getString("uyeTelefon"));
                uye.setKullaniciAdi(resultSet.getString("kullaniciAdi"));
                uye.setFavori1(resultSet.getString("favori1"));
                uye.setFavori2(resultSet.getString("favori2"));
                uye.setFavori3(resultSet.getString("favori3"));
                uyeList.add(uye);
                uye = new Uye();
            }
            baglantiKapat();
            return uyeList;
        } catch (SQLException throwables) {
            baglantiKapat();
            throwables.printStackTrace();
            return null;
        }
    }

    //yönetici işlemleri
    public boolean yoneticiGirisi(String kullaniciAdii, String sifree) {
        baglantiAc();
        try {
            sqlSorgu = "SELECT * FROM KitApp.dbo.yonetici WHERE kullaniciAdi=? and sifre=?";
            preparedStatement = baglanti.prepareStatement(sqlSorgu);
            preparedStatement.setString(1, kullaniciAdii);
            preparedStatement.setString(2, sifree);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                baglantiKapat();
                return true;
            } else {
                baglantiKapat();
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            baglantiKapat();
            return false;
        }
    }
}

