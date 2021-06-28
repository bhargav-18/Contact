package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity implements View.OnClickListener{

    EditText etName, etPhone, etWebsite, etAddress;
    ImageView ivHappy, ivAvg, ivSad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etWebsite = findViewById(R.id.etWebsite);
        etAddress = findViewById(R.id.etAddress);
        ivHappy = findViewById(R.id.ivHappy);
        ivAvg = findViewById(R.id.ivAvg);
        ivSad = findViewById(R.id.ivSad);

        ivHappy.setOnClickListener(this);
        ivAvg.setOnClickListener(this);
        ivSad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(etName.getText().toString().isEmpty() || etPhone.getText().toString().isEmpty() ||
                etWebsite.getText().toString().isEmpty() || etAddress.getText().toString().isEmpty())
        {
            Toast.makeText(Activity2.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
        }
        else
        {
            String name, phone, website, address;
            name = etName.getText().toString().trim();
            phone = etPhone.getText().toString().trim();
            website = etWebsite.getText().toString().trim();
            address = etAddress.getText().toString().trim();

            Intent intent = new Intent();
            intent.putExtra("name", name);
            intent.putExtra("phone", phone);
            intent.putExtra("website", website);
            intent.putExtra("address", address);

            if(v.getId() == R.id.ivHappy)
            {
                intent.putExtra("emoji", "happy");
            }
            else if(v.getId() == R.id.ivAvg)
            {
                intent.putExtra("emoji", "average");
            }
            else
            {
                intent.putExtra("emoji", "sad");
            }

            setResult(RESULT_OK, intent);
            Activity2.this.finish();
        }
    }
}