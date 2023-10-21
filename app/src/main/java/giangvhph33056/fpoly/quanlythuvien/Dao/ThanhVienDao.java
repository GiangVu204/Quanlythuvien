package giangvhph33056.fpoly.quanlythuvien.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import giangvhph33056.fpoly.quanlythuvien.Database.DbHelper;
import giangvhph33056.fpoly.quanlythuvien.Model.ThanhVien;

public class ThanhVienDao {
    DbHelper dbHelper;

    public ThanhVienDao(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<ThanhVien> getDSThanhVien(){
        ArrayList<ThanhVien> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ThanhVien",null);
        if (cursor.getCount() != 0 ){
            cursor.moveToFirst();
            do {
                list.add(new ThanhVien(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }


    public boolean insert(String hoten, String namsinh){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("HoTen", hoten);
        values.put("NamSinh", namsinh);
        long check = db.insert("ThanhVien",null,values);
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }


    public boolean update(int matv, String hoten, String namsinh){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("HoTen",hoten);
        values.put("NamSinh",namsinh);
        long check = db.update("ThanhVien",values,"MaTV = ?",new String[]{String.valueOf(matv)});
        if (check == -1){
            return  false;
        }else {
            return true;
        }
    }


    //int : Xóa thành côn; int 0:Xóa thất bại; int -1:Tìm thấy thành viên có phiếu mượn
    public int delete(int matv){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PhieuMuon WHERE MaTV = ?", new String[]{String.valueOf(matv)});
        if (cursor.getCount() != 0){
            return -1;
        }
        long check = db.delete("ThanhVien", "MaTV = ?", new String[]{String.valueOf(matv)});
        if (check == -1){
            return 0;
        }else {
            return 1;
        }
    }
}
