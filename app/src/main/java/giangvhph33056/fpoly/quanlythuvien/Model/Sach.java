package giangvhph33056.fpoly.quanlythuvien.Model;

public class Sach {

    private int MaSach;
    private String TenSach;
    private int GiaThue;

    private int MaLoai;
    private String TenLoai;
    private int SoLanMuon;

    private int namxb;

    public Sach() {
    }

    public Sach(int maSach, String tenSach, int giaThue, int maLoai, String tenLoai, int namxb) {
        MaSach = maSach;
        TenSach = tenSach;
        GiaThue = giaThue;
        MaLoai = maLoai;
        TenLoai = tenLoai;
        this.namxb = namxb;
    }

    public Sach(int maSach, String tenSach, int namxb) {
        MaSach = maSach;
        TenSach = tenSach;
        this.namxb = namxb;
    }

    public int getMaSach() {
        return MaSach;
    }

    public void setMaSach(int maSach) {
        MaSach = maSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public int getGiaThue() {
        return GiaThue;
    }

    public void setGiaThue(int giaThue) {
        GiaThue = giaThue;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }

    public int getSoLanMuon() {
        return SoLanMuon;
    }

    public void setSoLanMuon(int soLanMuon) {
        SoLanMuon = soLanMuon;
    }

    public int getNamxb() {
        return namxb;
    }

    public void setNamxb(int namxb) {
        this.namxb = namxb;
    }
}
