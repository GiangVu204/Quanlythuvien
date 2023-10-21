package giangvhph33056.fpoly.quanlythuvien.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import giangvhph33056.fpoly.quanlythuvien.Adapter.Adapter_ThanhVien;
import giangvhph33056.fpoly.quanlythuvien.Dao.ThanhVienDao;
import giangvhph33056.fpoly.quanlythuvien.Model.ThanhVien;
import giangvhph33056.fpoly.quanlythuvien.R;

public class Fragment_thanh_vien extends Fragment {

    FloatingActionButton fltAll;
    RecyclerView rcv;
    ThanhVienDao dao;
    ArrayList<ThanhVien> list;


    public Fragment_thanh_vien() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        rcv = view.findViewById(R.id.rcv_TV);
        fltAll = view.findViewById(R.id.add_TV);
        dao = new ThanhVienDao(getContext());
        loadData();

        fltAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAddTV();
            }
        });
        return view;
    }

    private void loadData(){
        list = dao.getDSThanhVien();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        Adapter_ThanhVien adapter = new Adapter_ThanhVien(getContext(),list);
        adapter.notifyDataSetChanged();
        rcv.setAdapter(adapter);
    }

    @SuppressLint("MissingInflatedId")
    private void dialogAddTV(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.add_thanhvien,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextInputEditText ed_txtTenTV = view.findViewById(R.id.ed_addTenTV);
        TextInputEditText ed_txtNamSinh = view.findViewById(R.id.ed_addNamSinh);
        TextInputLayout in_txtTenTV = view.findViewById(R.id.in_addTenTV);
        TextInputLayout in_txtNamSinh = view.findViewById(R.id.in_addNamSinh);
        Button btn_add = view.findViewById(R.id.TV_add);
        Button btn_cancel = view.findViewById(R.id.TV_Cancel);

        ed_txtTenTV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    in_txtTenTV.setError("Vui lòng nhập tên thành viên");
                }else{
                    in_txtTenTV.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ed_txtNamSinh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    in_txtNamSinh.setError("Vui lòng không để trống năm sinh");
                }else{
                    in_txtNamSinh.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoten = ed_txtTenTV.getText().toString();
                String namsinh = ed_txtNamSinh.getText().toString();
//                boolean check = dao.insert(hoten,namsinh);

                if(hoten.isEmpty() || namsinh.isEmpty()){
                    if (hoten.equals("")){
                        in_txtTenTV.setError("Họ tên đang trống vui lòng nhập vào!");
                    }else {
                        in_txtTenTV.setError(null);
                    }
                    if (namsinh.equals("")){
                        in_txtNamSinh.setError("Năm sinh đang trống vui lòng nhập vào!");
                    }else {
                        in_txtNamSinh.setError(null);
                    }
                    if (hoten.trim().equals("")){
                        Toast.makeText(getContext(), "Họ tên đang trống", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (namsinh.trim().equals("")){
                        Toast.makeText(getContext(), "Năm sinh đang trống", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    try {
                        Integer.parseInt(ed_txtNamSinh.getText().toString().trim());
                    }catch (NumberFormatException e){
                        Toast.makeText(getContext(), "Năm sinh phải là số", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if (dao.insert(hoten, namsinh)){
                    list.clear();
                    loadData();
                    dialog.dismiss();
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }

//                if(hoten.isEmpty() || namsinh.isEmpty()){
//                    if(hoten.equals("")){
//                        in_txtTenTV.setError("Vui lòng nhập đầy đủ tên thành viên");
//                    }else{
//                        in_txtTenTV.setError(null);
//                    }
//
//                    if(namsinh.equals("")){
//                        in_txtNamSinh.setError("Vui lòng nhập đầy đủ năm sinh thành viên");
//                    }else{
//                        in_txtNamSinh.setError(null);
//                    }
//                }else{
//                    if(check){
//                        loadData();
//                        Toast.makeText(getContext(), "Thêm Thành Viên Thành Công", Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
//                    }else{
//                        Toast.makeText(getContext(), "Thêm Thành Viên Không Thành Công", Toast.LENGTH_SHORT).show();
//                    }
//                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_txtTenTV.setText("");
                ed_txtNamSinh.setText("");
            }
        });
    }
}