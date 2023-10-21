package giangvhph33056.fpoly.quanlythuvien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;

import giangvhph33056.fpoly.quanlythuvien.fragment.Fragment_add_user;
import giangvhph33056.fpoly.quanlythuvien.fragment.Fragment_doanh_thu;
import giangvhph33056.fpoly.quanlythuvien.fragment.Fragment_doi_mk;
import giangvhph33056.fpoly.quanlythuvien.fragment.Fragment_loai_sach;
import giangvhph33056.fpoly.quanlythuvien.fragment.Fragment_phieu_muon;
import giangvhph33056.fpoly.quanlythuvien.fragment.Fragment_sach;
import giangvhph33056.fpoly.quanlythuvien.fragment.Fragment_thanh_vien;
import giangvhph33056.fpoly.quanlythuvien.fragment.Fragment_top;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        frameLayout = findViewById(R.id.frameLayout);
        navigationView = findViewById(R.id.navigationView);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menuQLPM){
                    Fragment_phieu_muon frgPM = new Fragment_phieu_muon();
                    relaceFrg(frgPM);
                    toolbar.setTitle("Quản lý phiếu mượn");
                } else if (item.getItemId() == R.id.menuQLLS) {
                    Fragment_loai_sach frgLS = new Fragment_loai_sach();
                    relaceFrg(frgLS);
                    toolbar.setTitle("Quản lý loại sách");
                } else if (item.getItemId() == R.id.menuQLS) {
                    Fragment_sach frgS = new Fragment_sach();
                    relaceFrg(frgS);
                    toolbar.setTitle("Quản lý sách");
                } else if (item.getItemId() == R.id.menuQLTV) {
                    Fragment_thanh_vien frgTV = new Fragment_thanh_vien();
                    relaceFrg(frgTV);
                    toolbar.setTitle("Quản lý thành viên");
                }else if (item.getItemId() == R.id.menuTop){
                    Fragment_top frgT = new Fragment_top();
                    relaceFrg(frgT);
                    toolbar.setTitle("Top 10 sách mượn nhiều nhất");
                }else if (item.getItemId() == R.id.menuDT){
                    Fragment_doanh_thu frgDT = new Fragment_doanh_thu();
                    relaceFrg(frgDT);
                    toolbar.setTitle("Quản lý doanh thu");
                } else if (item.getItemId() == R.id.menuTND) {
                    Fragment_add_user frgTND = new Fragment_add_user();
                    relaceFrg(frgTND);
                    toolbar.setTitle("Thêm người dùng");
                } else if (item.getItemId() == R.id.menuDMK) {
                    Fragment_doi_mk frgDMK = new Fragment_doi_mk();
                    relaceFrg(frgDMK);
                    toolbar.setTitle("Đổi mật khẩu");
                } else if (item.getItemId() == R.id.menuDX) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Đăng Xuất");
                    builder.setMessage("Bạn chắc chắn muốn đăng xuất chứ!");
                    builder.setPositiveButton("Đăng xuất", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(MainActivity.this, Login_screen.class));
                            finish();
                        }
                    });
                    builder.setNegativeButton("Hủy", null);
                    builder.create().show();
                }
                drawerLayout.closeDrawer(navigationView);
                return false;
            }
        });
    }
    public void relaceFrg(Fragment frg){
        FragmentManager fg = getSupportFragmentManager();
        fg.beginTransaction().replace(R.id.frameLayout,frg).commit();
    }
}