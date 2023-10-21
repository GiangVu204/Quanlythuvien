package giangvhph33056.fpoly.quanlythuvien.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import giangvhph33056.fpoly.quanlythuvien.Model.Sach;
import giangvhph33056.fpoly.quanlythuvien.R;

public class Adapter_Top10 extends RecyclerView.Adapter<Adapter_Top10.ViewHoldel> {

    private Context context;
    private ArrayList<Sach> list;

    public Adapter_Top10(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHoldel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_top10,parent,false);
        return new ViewHoldel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoldel holder, int position) {
        holder.Top_MS.setText(String.valueOf(list.get(position).getMaSach()));
        holder.Top_TS.setText(String.valueOf(list.get(position).getTenSach()));
        holder.Top_SL.setText(String.valueOf(list.get(position).getSoLanMuon()));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoldel extends RecyclerView.ViewHolder{
        TextView Top_MS, Top_TS, Top_SL;

        public ViewHoldel(@NonNull View itemView) {
            super(itemView);
            Top_MS = itemView.findViewById(R.id.Top_MS);
            Top_TS = itemView.findViewById(R.id.Top_TS);
            Top_SL = itemView.findViewById(R.id.Top_SL);
        }
    }
}
