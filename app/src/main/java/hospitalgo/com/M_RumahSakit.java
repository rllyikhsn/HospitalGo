package hospitalgo.com;

public class M_RumahSakit {

    private Integer id_rs;
    private String nama_rs;
    private Integer spesialis_jantung;
    private Integer spesialis_kehamilan;
    private Integer spesialis_stroke;
    private Integer spesialis_paruparu;
    private Integer spesialis_asma;
    private Integer jumlah_kasur_umum;
    private Integer jumlah_kasur_vip;
    private Integer kasur_umum_tersedia;
    private Integer kasur_vip_tersedia;
    private String website;
    private String latitude;
    private String longitude;

    public M_RumahSakit(){

    }

    public M_RumahSakit(Integer id_rs, String nama_rs, Integer spesialis_jantung, Integer spesialis_kehamilan, Integer spesialis_stroke, Integer spesialis_paruparu, Integer spesialis_asma, Integer jumlah_kasur_umum, Integer jumlah_kasur_vip, Integer kasur_umum_tersedia, Integer kasur_vip_tersedia, String website, String latitude, String longitude) {
        this.id_rs = id_rs;
        this.nama_rs = nama_rs;
        this.spesialis_jantung = spesialis_jantung;
        this.spesialis_kehamilan = spesialis_kehamilan;
        this.spesialis_stroke = spesialis_stroke;
        this.spesialis_paruparu = spesialis_paruparu;
        this.spesialis_asma = spesialis_asma;
        this.jumlah_kasur_umum = jumlah_kasur_umum;
        this.jumlah_kasur_vip = jumlah_kasur_vip;
        this.kasur_umum_tersedia = kasur_umum_tersedia;
        this.kasur_vip_tersedia = kasur_vip_tersedia;
        this.website = website;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getId_rs() {
        return id_rs;
    }

    public void setId_rs(Integer id_rs) {
        this.id_rs = id_rs;
    }

    public String getNama_rs() {
        return nama_rs;
    }

    public void setNama_rs(String nama_rs) {
        this.nama_rs = nama_rs;
    }

    public Integer getSpesialis_jantung() {
        return spesialis_jantung;
    }

    public void setSpesialis_jantung(Integer spesialis_jantung) {
        this.spesialis_jantung = spesialis_jantung;
    }

    public Integer getSpesialis_kehamilan() {
        return spesialis_kehamilan;
    }

    public void setSpesialis_kehamilan(Integer spesialis_kehamilan) {
        this.spesialis_kehamilan = spesialis_kehamilan;
    }

    public Integer getSpesialis_stroke() {
        return spesialis_stroke;
    }

    public void setSpesialis_stroke(Integer spesialis_stroke) {
        this.spesialis_stroke = spesialis_stroke;
    }

    public Integer getSpesialis_paruparu() {
        return spesialis_paruparu;
    }

    public void setSpesialis_paruparu(Integer spesialis_paruparu) {
        this.spesialis_paruparu = spesialis_paruparu;
    }

    public Integer getSpesialis_asma() {
        return spesialis_asma;
    }

    public void setSpesialis_asma(Integer spesialis_asma) {
        this.spesialis_asma = spesialis_asma;
    }

    public Integer getJumlah_kasur_umum() {
        return jumlah_kasur_umum;
    }

    public void setJumlah_kasur_umum(Integer jumlah_kasur_umum) {
        this.jumlah_kasur_umum = jumlah_kasur_umum;
    }

    public Integer getJumlah_kasur_vip() {
        return jumlah_kasur_vip;
    }

    public void setJumlah_kasur_vip(Integer jumlah_kasur_vip) {
        this.jumlah_kasur_vip = jumlah_kasur_vip;
    }

    public Integer getKasur_umum_tersedia() {
        return kasur_umum_tersedia;
    }

    public void setKasur_umum_tersedia(Integer kasur_umum_tersedia) {
        this.kasur_umum_tersedia = kasur_umum_tersedia;
    }

    public Integer getKasur_vip_tersedia() {
        return kasur_vip_tersedia;
    }

    public void setKasur_vip_tersedia(Integer kasur_vip_tersedia) {
        this.kasur_vip_tersedia = kasur_vip_tersedia;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
