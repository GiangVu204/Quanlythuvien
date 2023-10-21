package giangvhph33056.fpoly.quanlythuvien.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import giangvhph33056.fpoly.quanlythuvien.Database.DbHelper;
import giangvhph33056.fpoly.quanlythuvien.Model.ThuThu;

public class ThuThuDao {

    DbHelper dbHelper;

    public ThuThuDao(Context context){
        dbHelper = new DbHelper(context);
    }

    //Đăng nhập

    public boolean checkLogin(String MaTT, String MatKhau){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM ThuThu WHERE MaTT = ? AND MatKhau = ?", new String[]{MaTT, MatKhau});
            if (cursor.getCount() != 0){
                return true;
            }else {
                return false;
            }
    }

    public boolean updateMK(String username, String oldPass, String newPass){
        SQLiteDatabase db  = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ThuThu WHERE MaTT = ? AND MatKhau = ?", new String[]{username, oldPass});
        if (cursor.getCount() > 0){
            ContentValues values = new ContentValues();
            values.put("MatKhau", newPass);
            long check = db.update("ThuThu", values, "MaTT = ?", new String[]{username});
            if (check == -1){
                return false;
            }else {
                return true;
            }
        }
        return false;
    }

    public long insert(ThuThu obj) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maTT",obj.getMaTT());
        contentValues.put("hoTen", obj.getHoTen());
        contentValues.put("matKhau",obj.getMatKhau());

        return db.insert("ThuThu",null,contentValues);
    }
}
