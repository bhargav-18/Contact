package com.example.contact;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView ivEmoji, ivCall, ivBrowse, ivMap;
    Button btnCreate;

    final int ACTIVITY2 = 2;
    String name = "", phone = "", website = "", map = "", emoji = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivEmoji = findViewById(R.id.ivEmoji);
        ivCall = findViewById(R.id.ivCall);
        ivBrowse = findViewById(R.id.ivBrowse);
        ivMap = findViewById(R.id.ivMap);
        btnCreate = findViewById(R.id.btnCreate);

        ivEmoji.setVisibility(View.GONE);
        ivCall.setVisibility(View.GONE);
        ivBrowse.setVisibility(View.GONE);
        ivMap.setVisibility(View.GONE);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.contact.Activity2.class);
                startActivityForResult(intent, ACTIVITY2);
            }
        });

        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                startActivity(intent);
            }
        });

        ivBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + website));
                startActivity(intent);
            }
        });

        ivMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + map));
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ACTIVITY2)
        {
            if(resultCode == RESULT_OK)
            {
                ivEmoji.setVisibility(View.VISIBLE);
                ivCall.setVisibility(View.VISIBLE);
                ivBrowse.setVisibility(View.VISIBLE);
                ivMap.setVisibility(View.VISIBLE);

                name = data.getStringExtra("name");
                phone = data.getStringExtra("phone");
                website = data.getStringExtra("website");
                map = data.getStringExtra("address");
                emoji = data.getStringExtra("emoji");

                if(emoji.equals("happy"))
                {
                    ivEmoji.setImageResource(R.drawable.happy);
                }
                else if(emoji.equals("average"))
                {
                    ivEmoji.setImageResource(R.drawable.average);
                }
                else
                {
                    ivEmoji.setImageResource(R.drawable.sad);
                }
            }
            if(resultCode == RESULT_CANCELED)
            {
                Toast.makeText(this, "No data received", Toast.LENGTH_SHORT).show();
            }
        }
    }
}