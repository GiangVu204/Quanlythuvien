package giangvhph33056.fpoly.quanlythuvien.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import giangvhph33056.fpoly.quanlythuvien.Dao.ThuThuDao;
import giangvhph33056.fpoly.quanlythuvien.Login_screen;
import giangvhph33056.fpoly.quanlythuvien.R;

public class Fragment_doi_mk extends Fragment {

    TextInputLayout in_CurrentPassword, in_NewPass, in_Repass;
    TextInputEditText ed_txtCurrentPassword, ed_txtNewPass, ed_txtRepass;
    Button btnSaveMK, btnCancelSaveMK;
    private Context context ;

    public Fragment_doi_mk() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doi_mk, container, false);
        in_CurrentPassword = view.findViewById(R.id.in_CurrentPassword);
        in_NewPass = view.findViewById(R.id.in_NewPass);
        in_Repass = view.findViewById(R.id.in_Repass);
        ed_txtCurrentPassword = view.findViewById(R.id.ed_txtCurrentPassword);
        ed_txtNewPass = view.findViewById(R.id.ed_txtNewPass);
        ed_txtRepass = view.findViewById(R.id.ed_txtRepass);
        btnSaveMK = view.findViewById(R.id.btn_SaveMK);
        btnCancelSaveMK = view.findViewById(R.id.btn_CancelSaveMK);

        btnCancelSaveMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_txtCurrentPassword.setText("");
                ed_txtNewPass.setText("");
                ed_txtRepass.setText("");
            }
        });

        btnSaveMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoiMK();
            }
        });
        return view;
    }

    public void DoiMK(){
        String OldPass = ed_txtCurrentPassword.getText().toString();
        String newPass = ed_txtNewPass.getText().toString();
        String rePass = ed_txtRepass.getText().toString();
        if (newPass.equals(rePass)){
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("User_File", getContext().MODE_PRIVATE);
            String matt = sharedPreferences.getString("Username","");
            String mk = sharedPreferences.getString("Password","");

            //Cập nhật lại
            ThuThuDao ttDao = new ThuThuDao(getContext());
            boolean check = ttDao.updateMK(matt, OldPass, newPass);
            if (OldPass.equals(mk)){
                if (check){
                    Toast.makeText(getContext(), "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), Login_screen.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(), "Dổi mật khẩu thất bại!!!", Toast.LENGTH_SHORT).show();
                }
            }else {
                in_CurrentPassword.setError("Mật khẩu hiện tại hong đúng");
            }
        }else {
            in_NewPass.setError("Mật khẩu không khớp");
            in_Repass.setError("Mật khẩu không khớp");
        }
    }
}