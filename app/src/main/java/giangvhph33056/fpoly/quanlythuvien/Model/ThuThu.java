package giangvhph33056.fpoly.quanlythuvien.Model;

public class ThuThu {

    private String maTT, MatKhau, HoTen;

    public ThuThu() {
    }

    public ThuThu(String maTT, String matKhau, String hoTen) {
        this.maTT = maTT;
        this.MatKhau = matKhau;
        this.HoTen = hoTen;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }
}
