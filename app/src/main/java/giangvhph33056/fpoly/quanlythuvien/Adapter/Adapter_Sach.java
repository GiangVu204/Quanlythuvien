package giangvhph33056.fpoly.quanlythuvien.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashMap;

import giangvhph33056.fpoly.quanlythuvien.Dao.SachDao;
import giangvhph33056.fpoly.quanlythuvien.Model.Sach;
import giangvhph33056.fpoly.quanlythuvien.R;

public class Adapter_Sach extends RecyclerView.Adapter<Adapter_Sach.ViewHolder> {

    private Context context;
    private ArrayList<Sach> list;
    private ArrayList<HashMap<String, Object>> listHM;
    private SachDao SDao;

    public Adapter_Sach(Context context, ArrayList<Sach> list, ArrayList<HashMap<String, Object>> listHM, SachDao SDao) {
        this.context = context;
        this.list = list;
        this.listHM = listHM;
        this.SDao = SDao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sach,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtMaSach.setText(String.valueOf(list.get(position).getMaSach()));
        holder.txtTenSach.setText(list.get(position).getTenSach());
        holder.txtGiaThue.setText(String.valueOf(list.get(position).getGiaThue()));
        holder.txtLoaiSach.setText(String.valueOf(list.get(position).getMaLoai()));
        holder.txtTenLS.setText(list.get(position).getTenLoai());
        holder.txtNamXb.setText(String.valueOf(list.get(position).getNamxb()));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dialogUpdate(list.get(holder.getAdapterPosition()));
                return true;
            }
        });


        holder.Sach_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.drawable.warning_35);
                builder.setTitle("Delete");
                builder.setMessage("Bạn có chắc muốn xóa Sách '" + list.get(position).getMaSach() + "' này chứ!");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int check  = SDao.delete(list.get(holder.getAdapterPosition()).getMaSach());
                        switch (check){
                            case 1:
                                loadData();
                                Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                                break;
                            case 0:
                                loadData();
                                Toast.makeText(context, "Xóa thất bại!!!", Toast.LENGTH_SHORT).show();
                                break;
                            case -1:
                                Toast.makeText(context, "Không xóa được sách này vì đang tồn tại trong phiếu mượn", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                    }
                });
                builder.setNegativeButton("Hủy", null);
                builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaSach, txtTenSach, txtGiaThue, txtLoaiSach, txtTenLS, txtNamXb;
        ImageView Sach_Delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaSach = itemView.findViewById(R.id.MaSach);
            txtTenSach = itemView.findViewById(R.id.TenS);
            txtGiaThue = itemView.findViewById(R.id.Sach_GiaThue);
            txtLoaiSach = itemView.findViewById(R.id.Sach_LS);
            txtTenLS = itemView.findViewById(R.id.Sach_TenLS);
            txtNamXb = itemView.findViewById(R.id.Sach_NamXb);
            Sach_Delete = itemView.findViewById(R.id.S_Delete);


        }
    }

    private void dialogUpdate(Sach sach){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_sach, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextInputLayout in_TenSach = view.findViewById(R.id.in_updateTenS);
        TextInputLayout in_GiaThue = view.findViewById(R.id.in_updateGiaThue);
        TextInputLayout in_NamXb = view.findViewById(R.id.in_updateNamXb);
        TextInputEditText ed_TenSach = view.findViewById(R.id.ed_updateTenS);
        TextInputEditText ed_GiaThue = view.findViewById(R.id.ed_updateGiaThue);
        TextInputEditText ed_NamXb = view.findViewById(R.id.ed_updateNamXb);
        Spinner spnSach = view.findViewById(R.id.spnSach);
        Button add = view.findViewById(R.id.S_update);
        Button cancel = view.findViewById(R.id.S_Cancel);

        ed_TenSach.setText(sach.getTenSach());
        ed_GiaThue.setText(String.valueOf(sach.getGiaThue()));

        ed_TenSach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0){
                    in_TenSach.setError("Vui lòng không để trống tên sách");
                }else {
                    in_TenSach.setError(null);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ed_GiaThue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0){
                    in_GiaThue.setError("Vui lòng không để trống giá thuê");
                }else {
                    in_GiaThue.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ed_NamXb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0){
                    in_NamXb.setError("Vui lòng không để trống năm xuất bản");
                }else {
                    in_NamXb.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                context,
                listHM,
                (android.R.layout.simple_list_item_1),
                new String[]{"TenLoai"},
                new int[]{android.R.id.text1}
        );
        spnSach.setAdapter(simpleAdapter);
        int index = 0;
        int position = -1;

        for(HashMap<String, Object> item : listHM){
            if((int) item.get("MaLoai") == sach.getMaLoai()){
                position = index;
            }
            index ++;
        }
        spnSach.setSelection(position);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tensach = ed_TenSach.getText().toString();
                String checktien = ed_GiaThue.getText().toString();
                String namxb = ed_NamXb.getText().toString();
                HashMap<String, Object> hs = (HashMap<String, Object>) spnSach.getSelectedItem();
                int maloai =(int) hs.get("MaLoai");

                if (tensach.isEmpty() || checktien.isEmpty() || namxb.isEmpty()){
                    if (tensach.equals("")){
                        in_TenSach.setError("Vui lòng không để trống tên sách");
                    }else {
                        in_TenSach.setError(null);
                    }
                    if (checktien.equals("")){
                        in_GiaThue.setError("Vui lòng không để trống giá thuê");
                    }else {
                        in_GiaThue.setError(null);
                    }
                    if (namxb.equals("")){
                        in_NamXb.setError("Vui lòng không để trống giá thuê");
                    }else {
                        in_NamXb.setError(null);
                    }
                }else {
                    int tien = Integer.parseInt(checktien);
                    int namXb = Integer.parseInt(namxb);
                    boolean check = SDao.update(sach.getMaSach(), tensach, tien, maloai, namXb);
                    if (check){
                        loadData();
                        Toast.makeText(context, "Cập nhật sách thành công!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else {
                        Toast.makeText(context, "Cập nhật thất bại!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_TenSach.setText("");
                ed_GiaThue.setText("");
                ed_NamXb.setText("");
            }
        });

    }
    private void loadData(){
        list.clear();
        list = SDao.getDSSach();
        notifyDataSetChanged();
    }
}
