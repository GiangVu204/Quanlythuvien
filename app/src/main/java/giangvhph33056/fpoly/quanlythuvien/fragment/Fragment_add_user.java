package giangvhph33056.fpoly.quanlythuvien.fragment;

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
import giangvhph33056.fpoly.quanlythuvien.Model.ThuThu;
import giangvhph33056.fpoly.quanlythuvien.R;

public class Fragment_add_user extends Fragment {

    TextInputLayout in_MaTT, in_HoTenTT, in_PassTT;
    TextInputEditText ed_txtMaTT, ed_txtHoTenTT, ed_txtPassTT;
    Button btn_CreateTT, btn_CancelCreateTT;

    ThuThuDao ttDao;

    public Fragment_add_user() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);
        in_MaTT = view.findViewById(R.id.in_MaTT);
        ed_txtMaTT = view.findViewById(R.id.ed_txtMaTT);
        in_HoTenTT = view.findViewById(R.id.in_HoTenTT);
        ed_txtHoTenTT = view.findViewById(R.id.ed_txtHoTenTT);
        in_PassTT = view.findViewById(R.id.in_PassTT);
        ed_txtPassTT = view.findViewById(R.id.ed_txtPassTT);
        btn_CreateTT = view.findViewById(R.id.btn_CreateTT);
        btn_CancelCreateTT = view.findViewById(R.id.btn_CancelCreateTT);

        ttDao = new ThuThuDao(getContext());

        btn_CancelCreateTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cancel();
            }
        });

        btn_CreateTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String MaTT = ed_txtMaTT.getText().toString();
                String Hoten = ed_txtHoTenTT.getText().toString();
                String Pass = ed_txtPassTT.getText().toString();
                if (MaTT.isEmpty() || Hoten.isEmpty() || Pass.isEmpty()) {
                    Toast.makeText(view.getContext(), "Mời bạn nhập lại!!!", Toast.LENGTH_SHORT).show();
//                    return;
                } else {
                    ThuThuDao thuThuDao = new ThuThuDao(getContext());
                    thuThuDao.insert(new ThuThu(MaTT, Hoten, Pass));
                    Toast.makeText(view.getContext(), "Thêm người dùng thành công!", Toast.LENGTH_SHORT).show();
                    ed_txtMaTT.setText("");
                    ed_txtHoTenTT.setText("");
                    ed_txtPassTT.setText("");
                }
            }
        });


        return view;
    }


    private void Cancel(){
        ed_txtMaTT.setText("");
        ed_txtHoTenTT.setText("");
        ed_txtPassTT.setText("");
    }
}