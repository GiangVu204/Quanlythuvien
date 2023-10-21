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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import giangvhph33056.fpoly.quanlythuvien.Dao.ThanhVienDao;
import giangvhph33056.fpoly.quanlythuvien.Model.ThanhVien;
import giangvhph33056.fpoly.quanlythuvien.R;

public class Adapter_ThanhVien extends RecyclerView.Adapter<Adapter_ThanhVien.ViewHolder> {

    private Context context;
    private ArrayList<ThanhVien> list;
    ThanhVienDao tvDao;

    public Adapter_ThanhVien(Context context, ArrayList<ThanhVien> list) {
        this.context = context;
        this.list = list;
        tvDao = new ThanhVienDao(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thanhvien, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.MaTV.setText(String.valueOf(list.get(position).getMaTV()));
        holder.TenTV.setText(list.get(position).getHoTen());
        holder.NSTV.setText(list.get(position).getNamSinh());
        ThanhVien tv = list.get(position);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dialogUpdateTV(tv);
                return true;
            }
        });

        holder.TV_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.drawable.warning_35);
                builder.setTitle("Delete");
                builder.setMessage("Bạn có chắc muốn xóa thành viên '" + list.get(position).getHoTen() + "' không");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int check = tvDao.delete(list.get(holder.getAdapterPosition()).getMaTV());
                        switch (check) {
                            case 1:
                                loadData();
                                Toast.makeText(context, "Xóa thành viên thành công!", Toast.LENGTH_SHORT).show();
                                break;
                            case 0:
                                Toast.makeText(context, "Xóa thành viên thất bại!!!", Toast.LENGTH_SHORT).show();
                                break;
                            case -1:
                                Toast.makeText(context, "Thành viên đang tồn tại phiếu mượn, hiện tại không thể xóa", Toast.LENGTH_SHORT).show();
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView MaTV, TenTV, NSTV;
        ImageView TV_Delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            MaTV = itemView.findViewById(R.id.MaTV);
            TenTV = itemView.findViewById(R.id.TenTV);
            NSTV = itemView.findViewById(R.id.NSTV);
            TV_Delete = itemView.findViewById(R.id.TV_Delete);

        }
    }

        @SuppressLint("MissingInflatedId")
        private void dialogUpdateTV(ThanhVien tv) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            View view = inflater.inflate(R.layout.update_tv, null);
            builder.setView(view);
            Dialog dialog = builder.create();
            dialog.show();

            TextInputLayout in_updateMaTV = view.findViewById(R.id.in_updateMaTV);
            TextInputEditText ed_updateMaTV = view.findViewById(R.id.ed_updateMaTV);
            TextInputLayout in_updateTenTV = view.findViewById(R.id.in_updateTenTV);
            TextInputEditText ed_updateTenTV = view.findViewById(R.id.ed_updateTenTV);
            TextInputLayout in_updateNamSinh = view.findViewById(R.id.in_updateNamSinh);
            TextInputEditText ed_updateNamSinh = view.findViewById(R.id.ed_updateNamSinh);
            Button TV_Update = view.findViewById(R.id.TV_Update);
            Button TV_Cancel = view.findViewById(R.id.TV_Cancel);

            ed_updateMaTV.setText(String.valueOf(tv.getMaTV()));
            ed_updateTenTV.setText(tv.getHoTen());
            ed_updateNamSinh.setText(tv.getNamSinh());

            ed_updateTenTV.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() == 0) {
                        in_updateTenTV.setError("Vui lòng không để trống tên thành viên");
                    } else {
                        in_updateTenTV.setError(null);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            ed_updateNamSinh.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() == 0) {
                        in_updateNamSinh.setError("Vui lòng không để trống năm sinh");
                    } else {
                        in_updateNamSinh.setError(null);
                    }

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            TV_Update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String hoten = ed_updateTenTV.getText().toString();
                    String namsinh = ed_updateNamSinh.getText().toString();
                    int id = tv.getMaTV();

                    boolean check = tvDao.update(id, hoten, namsinh);
                    if (hoten.isEmpty() || namsinh.isEmpty()) {
                        if (hoten.equals("")) {
                            in_updateTenTV.setError("Vui lòng nhập đầy đủ họ tên");
                        } else {
                            in_updateTenTV.setError(null);
                        }
                        if (namsinh.equals("")) {
                            in_updateNamSinh.setError("Vui lòng nhập đầy đủ năm sinh");
                        } else {
                            in_updateNamSinh.setError(null);
                        }
                    } else {
                        if (check) {
                            loadData();
                            Toast.makeText(context, "Cập nhật thành viên thành công!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "Cập nhật thành viên thất bại!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

            TV_Cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ed_updateTenTV.setText("");
                    ed_updateNamSinh.setText("");
                }
            });
        }

    public void loadData() {
        list.clear();
        list = tvDao.getDSThanhVien();
        notifyDataSetChanged();
    }
}
