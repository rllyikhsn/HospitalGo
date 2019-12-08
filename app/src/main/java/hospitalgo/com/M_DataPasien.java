package hospitalgo.com;

public class M_DataPasien {
    String nama_pasien;
    String jenis_penyakit;
    String jenis_kelamin;
    String keterangan_tambahan;
    String lokasi_pasien;

    public M_DataPasien(){

    }

    public M_DataPasien(String nama_pasien, String jenis_penyakit, String jenis_kelamin, String keterangan_tambahan, String lokasi_pasien) {
        this.nama_pasien = nama_pasien;
        this.jenis_penyakit = jenis_penyakit;
        this.jenis_kelamin = jenis_kelamin;
        this.keterangan_tambahan = keterangan_tambahan;
        this.lokasi_pasien = lokasi_pasien;
    }

    public String getNama_pasien() {
        return nama_pasien;
    }

    public void setNama_pasien(String nama_pasien) {
        this.nama_pasien = nama_pasien;
    }

    public String getJenis_penyakit() {
        return jenis_penyakit;
    }

    public void setJenis_penyakit(String jenis_penyakit) {
        this.jenis_penyakit = jenis_penyakit;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getKeterangan_tambahan() {
        return keterangan_tambahan;
    }

    public void setKeterangan_tambahan(String keterangan_tambahan) {
        this.keterangan_tambahan = keterangan_tambahan;
    }

    public String getLokasi_pasien() {
        return lokasi_pasien;
    }

    public void setLokasi_pasien(String lokasi_pasien) {
        this.lokasi_pasien = lokasi_pasien;
    }
}
