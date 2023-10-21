package giangvhph33056.fpoly.quanlythuvien.Model;

public class PhieuMuon {
    private int MaPM;
    private String MaTT;
    private int MaTV, MaSach, TienThue, TrangThai;
    private String NgayThue, HoTenTV,TenSach;

    public PhieuMuon() {
    }

    public PhieuMuon(int maTV, int maSach, int tienThue, int trangThai, String ngayThue) {
        MaTV = maTV;
        MaSach = maSach;
        TienThue = tienThue;
        TrangThai = trangThai;
        NgayThue = ngayThue;
    }

    public PhieuMuon(String maTT, int maTV, int maSach, int tienThue, int trangThai, String ngayThue) {
        MaTT = maTT;
        MaTV = maTV;
        MaSach = maSach;
        TienThue = tienThue;
        TrangThai = trangThai;
        NgayThue = ngayThue;
    }

    public PhieuMuon(int maPM, String maTT, int maTV, int maSach, int tienThue, int trangThai, String ngayThue, String hoTenTV, String tenSach) {
        MaPM = maPM;
        MaTT = maTT;
        MaTV = maTV;
        MaSach = maSach;
        TienThue = tienThue;
        TrangThai = trangThai;
        NgayThue = ngayThue;
        HoTenTV = hoTenTV;
        TenSach = tenSach;
    }

    public PhieuMuon(int maPM, String hoTenTV, String tenSach, int tienThue, int trangThai, String ngayThue) {
        MaPM = maPM;
        HoTenTV = hoTenTV;
        TenSach = tenSach;
        TienThue = tienThue;
        TrangThai = trangThai;
        NgayThue = ngayThue;
    }

    public int getMaPM() {
        return MaPM;
    }

    public void setMaPM(int maPM) {
        MaPM = maPM;
    }

    public String getMaTT() {
        return MaTT;
    }

    public void setMaTT(String maTT) {
        MaTT = maTT;
    }

    public int getMaTV() {
        return MaTV;
    }

    public void setMaTV(int maTV) {
        MaTV = maTV;
    }

    public int getMaSach() {
        return MaSach;
    }

    public void setMaSach(int maSach) {
        MaSach = maSach;
    }

    public int getTienThue() {
        return TienThue;
    }

    public void setTienThue(int tienThue) {
        TienThue = tienThue;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }

    public String getNgayThue() {
        return NgayThue;
    }

    public void setNgayThue(String ngayThue) {
        NgayThue = ngayThue;
    }

    public String getHoTenTV() {
        return HoTenTV;
    }

    public void setHoTenTV(String hoTenTV) {
        HoTenTV = hoTenTV;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }
}
