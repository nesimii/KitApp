package com.example.kitapp.yonetici;

public class Yonetici {
    private String id, ad, kullaniciAdi, sifre;

    public Yonetici() {
    }

    public Yonetici(String id, String ad, String kullaniciAdi, String sifre) {
        this.id = id;
        this.ad = ad;
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
}
