//package com.example.myapplication;
//
//import android.content.Intent;
//import android.database.Cursor;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.util.ArrayList;
//
//import static com.example.myapplication.ext.ConstExt.POSITION;
//
//public class SuaPVCActivity extends AppCompatActivity {
//
//    DBHelper DBhelper;
//    String maPVC;
//    EditText  edtTT;
//    DatePicker datePicker;
//    Spinner spnMaCT;
//    Button btnSuaPVC;
//    ImageView btnReturn4;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sua_pvc);
//        create();
//
//        ArrayList<PhieuVanChuyen> ArrPVC = new ArrayList<>();
//        DBhelper = new DBHelper(this, "qlvc.sqlite", null, 1);
//        Cursor dt = DBhelper.GetData("select * from PVC");
//        while (dt.moveToNext()) {
//            Log.d("SelectPVC", dt.getString(0));
//            PhieuVanChuyen PVC = new PhieuVanChuyen(dt.getString(0),dt.getString(1),dt.getString(2),dt.getString(3));
//            ArrPVC.add(PVC);
//        }
//        CustomAdapter_PVC adapter = new CustomAdapter_PVC(ArrPVC);
//        PhieuVanChuyen ct = (PhieuVanChuyen) adapter.getItem(POSITION);
//        edtTT.setText(ct.getTT());
//        spnMaCT.setText(ct.setMaCT());
//        maPVC = ct.getMaPVC();
//        setEvent();
//
//    }
//
//
//    private void setEvent() {
//
//        btnSuaCT.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Boolean flagValid = true;
//                if (edtTenCT.getText().toString().trim().isEmpty()) {
//                    edtTenCT.setError("Tên công trình không được trống");
//                    flagValid = false;
//                }
//                if (edtDiaChi.getText().toString().trim().isEmpty()) {
//                    edtDiaChi.setError("Địa chỉ không được trống");
//                    flagValid = false;
//                }
//                try {
//                    if (flagValid) {
//                        Log.d("edit", "CT: " + edtTenCT.getText().toString().trim());
//
//                        DBhelper = new DBHelper(SuaPVCActivity.this, "qlvc.sqlite", null, 1);
//                        DBhelper.updateCT(edtTenCT.getText().toString(), edtDiaChi.getText().toString(), maCT);
//                        Toast.makeText(SuaPVCActivity.this, "sửa công trình thành công!", Toast.LENGTH_LONG).show();
//                        //sửa xong thì về lại danh sách
//                        Intent intent = new Intent(SuaPVCActivity.this, CongTrinhActivity.class);
//                        startActivity(intent);
//                    }
//                } catch (Exception e) {
//                    Toast.makeText(SuaPVCActivity.this, "không thành công!", Toast.LENGTH_LONG).show();
//                    e.printStackTrace();
//                }
//            }
//        });
//        btnReturn4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(SuaPVCActivity.this, MainActivity.class));
//            }
//        });
//
//    }
//
//    private void create() {
//        edtTenCT = findViewById(R.id.edtTenCT);
//        edtDiaChi = findViewById(R.id.edtDiaChi);
//        btnSuaCT = findViewById(R.id.btnSuaCT);
//        btnReturn4 = findViewById(R.id.btnReturn4);
//    }
//}