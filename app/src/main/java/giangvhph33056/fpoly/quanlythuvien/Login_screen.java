package giangvhph33056.fpoly.quanlythuvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import giangvhph33056.fpoly.quanlythuvien.Dao.ThuThuDao;

public class Login_screen extends AppCompatActivity {
    TextInputLayout in_User, in_Pass;
    TextInputEditText ed_txtUser, ed_txtPass;
    CheckBox chkSave;
    Button btnLogin, btnCancel;

    ThuThuDao ttDao = new ThuThuDao(this);
    private long backPressed;
    private Toast mToast;


    @Override
    public void onBackPressed() {
        if (backPressed + 2000 > System.currentTimeMillis()) {
            mToast.cancel();
            super.onBackPressed();
            return;
        }else {
            mToast = Toast.makeText(Login_screen.this, "Nhấn 1 lần nữa để thoát", Toast.LENGTH_SHORT);
            mToast.show();
        }
        backPressed = System.currentTimeMillis();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        in_User = findViewById(R.id.in_User);
        ed_txtUser = findViewById(R.id.ed_txtUser);
        in_Pass = findViewById(R.id.in_Pass);
        ed_txtPass = findViewById(R.id.ed_txtPass);
        chkSave = findViewById(R.id.chkSave);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);

        SharedPreferences preferences = getSharedPreferences("User_File", MODE_PRIVATE);
        ed_txtUser.setText(preferences.getString("Username", ""));
        ed_txtPass.setText(preferences.getString("Password", ""));
        chkSave.setChecked(preferences.getBoolean("Remember", false));

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cancel();
            }
        });
    }


    public void Login() {
        String user = ed_txtUser.getText().toString();
        String pass = ed_txtPass.getText().toString();
        if (user.isEmpty() || pass.isEmpty()) {
            if (user.equals("")) {
                in_User.setError("Không được để trống tên đăng nhập");
            } else {
                in_User.setError(null);
            }
            if (pass.equals("")){
                in_Pass.setError("Không được để trống password");
            }else {
                in_Pass.setError(null);
            }
        } else {
            if (ttDao.checkLogin(user, pass)) {
                Toast.makeText(this, "Login thành công", Toast.LENGTH_SHORT).show();
                rememberUser(user, pass, chkSave.isChecked());
                Intent intent = new Intent(Login_screen.this, MainActivity.class);
                intent.putExtra("MaTT", user);
                startActivity(intent);
                finish();
            } else {
                in_User.setError("Tên đăng nhập hoặc mật khẩu không đúng");
                in_Pass.setError("Tên đăng nhập hoặc mật khẩu không đúng");
            }
        }
    }


    private void rememberUser(String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("User_File", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status) {
            //xóa trắng dữ liệu trước đó
            edit.clear();
        } else {
            //lưu dữ liệu
            edit.putString("Username",u);
            edit.putString("Password",p);
            edit.putBoolean("Remember",status);
        }
        //lưu lại toàn bộ
        edit.commit();
    }

    public void Cancel() {
        in_User.setError("");
        in_Pass.setError("");
        ed_txtUser.setText("");
        ed_txtPass.setText("");

    }
}