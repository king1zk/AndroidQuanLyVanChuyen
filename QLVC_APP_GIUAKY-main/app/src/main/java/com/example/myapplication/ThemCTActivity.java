package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ThemCTActivity extends AppCompatActivity {

    DBHelper DBhelper;
    EditText edtTenCT, edtDiaChi, edtMaCT;
    Button btnTaoCT;
    ImageView btnReturn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_ct);
        create();
        setEvent();

    }


    private void setEvent() {

        btnTaoCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean flagValid = true;
                if (edtMaCT.getText().toString().trim().isEmpty()) {
                    edtMaCT.setError("Mã công trình không được trống");
                    flagValid = false;
                }
                if (edtTenCT.getText().toString().trim().isEmpty()) {
                    edtTenCT.setError("Tên công trình không được trống");
                    flagValid = false;
                }
                if (edtDiaChi.getText().toString().trim().isEmpty()) {
                    edtDiaChi.setError("Địa chỉ không được trống");
                    flagValid = false;
                }
                try {
                    if (flagValid) {
                        Log.d("addd", "CT: " + edtTenCT.getText().toString().trim());

                        DBhelper = new DBHelper(ThemCTActivity.this, "qlvc.sqlite", null, 1);
                        DBhelper.InsertCT(edtMaCT.getText().toString(), edtTenCT.getText().toString(), edtDiaChi.getText().toString());
                        Toast.makeText(ThemCTActivity.this, "thêm công trình thành công!", Toast.LENGTH_LONG).show();
                        //thêm xong thì về lại danh sách
                        Intent intent = new Intent(ThemCTActivity.this, CongTrinhActivity.class);
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    Toast.makeText(ThemCTActivity.this, "không thành công!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
        btnReturn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThemCTActivity.this, MainActivity.class));
            }
        });
    }

    private void create() {
        edtMaCT = findViewById(R.id.edtMaCT);
        edtTenCT = findViewById(R.id.edtTenCT);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        btnTaoCT = findViewById(R.id.btnTaoCT);
        btnReturn5 = findViewById(R.id.btnReturn5);
    }
    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back, menu);
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.memu_home:
                Intent intent =new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case  R.id.memu_back:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}