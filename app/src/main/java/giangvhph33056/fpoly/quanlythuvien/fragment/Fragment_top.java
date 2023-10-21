package giangvhph33056.fpoly.quanlythuvien.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import giangvhph33056.fpoly.quanlythuvien.Adapter.Adapter_Top10;
import giangvhph33056.fpoly.quanlythuvien.Dao.ThongKeDao;
import giangvhph33056.fpoly.quanlythuvien.Model.Sach;
import giangvhph33056.fpoly.quanlythuvien.R;

public class Fragment_top extends Fragment {


    public Fragment_top() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, container, false);
        RecyclerView rcv = view.findViewById(R.id.rcv_Top);

        ThongKeDao thongKeDao = new ThongKeDao(getContext());
        ArrayList<Sach> list = thongKeDao.getTop10();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        Adapter_Top10 adapter = new Adapter_Top10(getContext(),list);
        rcv.setAdapter(adapter);

        return view;
    }
}