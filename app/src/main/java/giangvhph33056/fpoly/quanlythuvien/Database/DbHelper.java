package giangvhph33056.fpoly.quanlythuvien.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    static final String dbName = "QuanLySach";
    static final int dbVersion = 5;

    public DbHelper(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Bảng thủ thư
        String Thuthu = "CREATE TABLE ThuThu("+
                "MaTT text primary key," +
                "HoTen text not null," +
                "MatKhau text not null," +
                "Loaitaikhoan text not null)";
        sqLiteDatabase.execSQL(Thuthu);

        //Bảng Thành Viên
        String ThanhVien = "CREATE TABLE ThanhVien(" +
                "MaTV integer primary key autoincrement," +
                "HoTen text not null," +
                "NamSinh text not null)";
        sqLiteDatabase.execSQL(ThanhVien);

        // Bảng loại sách
        String LoaiSach = "CREATE TABLE LoaiSach(" +
                "MaLoai integer primary key autoincrement," +
                "HoTen text not null)";
        sqLiteDatabase.execSQL(LoaiSach);

        // Bảng Sách
        String Sach = "CREATE TABLE Sach(" +
                "MaSach integer primary key autoincrement," +
                "TenSach text not null," +
                "GiaThue integer not null," +
                "Maloai integer references LoaiSach(MaLoai)," +
                "namxb integer not null)";
        sqLiteDatabase.execSQL(Sach);

        // Bảng phiếu mượn
        String PhieuMuon = "CREATE TABLE PhieuMuon(" +
                "MaPM integer primary key autoincrement," +
                "MaTT text references ThuThu(MaTT)," +
                "MaTV integer references ThanhVien(MaTV)," +
                "MaSach integer references Sach(MaSach)," +
                "TienThue integer not null," +
                "TraSach integer not null," +
                "Ngay text not null)";
        sqLiteDatabase.execSQL(PhieuMuon);


        //
        sqLiteDatabase.execSQL("INSERT INTO LoaiSach VALUES(1,'Lập trình cơ bản'), (2,'Lập trình nâng cao'), (3, 'Hướng dẫn lập trình')");
        sqLiteDatabase.execSQL("INSERT INTO Sach VALUES(1, 'Javascript', 2000, 1, 2004), (2, 'Android 2', 3000, 3, 2005), (3, 'C++', 2500, 2, 2009)");
        sqLiteDatabase.execSQL("INSERT INTO ThuThu VALUES('Admin01', 'Vũ Hoàng Giang', '123456', 'ThuThu'), ('Admin02', 'Giang Vũ', '123456','ThuThu01')");
        sqLiteDatabase.execSQL("INSERT INTO ThanhVien VALUES(1, 'Thành Viên 01', '2004'), (2, 'Thành Viên 02', '2003'), (3, 'Thành Viên 03', '2002')");

        //
        sqLiteDatabase.execSQL("INSERT INTO PhieuMuon VALUES (1, 'Admin01',1, 3, 3000, 1, '1/10/2023'), (2, 'Admin02', 2, 1, 2000, 2, '30/9/2023'), (3, 'Admin02', 2, 1, 2500, 2, '10/8/2023')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ThuThu");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ThanhVien");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LoaiSach");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Sach");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PhieuMuon");
            onCreate(sqLiteDatabase);
        }

    }
}
