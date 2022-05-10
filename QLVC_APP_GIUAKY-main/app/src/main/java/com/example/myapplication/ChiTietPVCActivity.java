package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ChiTietPVCActivity extends AppCompatActivity {

    DBHelper DBhelper;
    ImageView btnReturn;
    int select_position_PVC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_pvc);
        ImageButton imgbtn_scanPdf = findViewById(R.id.imgbtn_scanPdf);
        // GET THONG TIN VT
        ImageButton imgbtn_addctvc = findViewById(R.id.imgbtn_addctvc);
        ListView listView = findViewById(R.id.lvCTVC);
        btnReturn = findViewById(R.id.btnReturn);

        ArrayList<ChiTietPVC> ArrCTVC = new ArrayList<>();
        DBhelper = new DBHelper(this, "qlvc.sqlite", null, 1);
        Cursor dt = DBhelper.GetData("select * from chitietPVC");
        while (dt.moveToNext()) {
            Log.d("SelectCTPVC", dt.getString(0) + " " + dt.getString(2));
            ChiTietPVC CTVC = new ChiTietPVC(dt.getString(0), dt.getInt(1), dt.getInt(2), dt.getInt(3));
            ArrCTVC.add(CTVC);
        }
        CustomAdapter_CTVC adapter = new CustomAdapter_CTVC(ArrCTVC);
        listView.setAdapter(adapter);

        //Thêm CT Phiếu vận chuyển
        imgbtn_addctvc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietPVCActivity.this, ThemCTVCActivity.class);
                startActivity(intent);
            }
        });

        //In CT Phiếu vận chuyển
        imgbtn_scanPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLogScanner();
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChiTietPVCActivity.this, MainActivity.class));
            }
        });
    }
    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_danhsach, menu);
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.memu_home:
                Intent intent =new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Custom Dialog Scanner
    private void DiaLogScanner(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.scan_pvc_dialog);

        Spinner spinner = dialog.findViewById(R.id.spinMaPVC);
        Button btnIn = dialog.findViewById(R.id.btnIn);
        ArrayList<PhieuVanChuyen> dsPVC= new ArrayList<PhieuVanChuyen>();
        Cursor dt= DBhelper.GetData("select * from PVC");
        while (dt.moveToNext()){
            PhieuVanChuyen ct=new PhieuVanChuyen(dt.getString(0), dt.getString(1),dt.getString(2),dt.getString(3));
            dsPVC.add(ct);
        }
        ArrayAdapter<PhieuVanChuyen> spinnerAdapterPVC = new ArrayAdapter<PhieuVanChuyen>(this, R.layout.support_simple_spinner_dropdown_item, dsPVC);
        spinnerAdapterPVC.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapterPVC);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                select_position_PVC = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "onNothingSelected", Toast.LENGTH_SHORT).show();
            }
        });

        btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChiTietPVCActivity.this, InChiTietPhieuActivity.class);
                intent.putExtra("inMaPVC", dsPVC.get(select_position_PVC).getMaPVC());
                startActivity(intent);
            }
        });
        dialog.show();
    }
}