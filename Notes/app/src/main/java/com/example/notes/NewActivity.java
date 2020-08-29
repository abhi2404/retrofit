package com.example.notes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewActivity extends AppCompatActivity {
    EditText edit_1; EditText edit_2;
    Button Add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.items);

        edit_1=(EditText)findViewById(R.id.title);
        edit_2=(EditText)findViewById(R.id.body);
        Add=(Button) findViewById(R.id.add);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t=edit_1.getText().toString();
                String b=edit_2.getText().toString();
                if(b.length()>0&&t.length()>0) {
                    Bundle bundle = new Bundle();
                    bundle.putString("title", t);
                    bundle.putString("body", b);
                    Intent i = new Intent(NewActivity.this, MainActivity.class);
                    i.putExtras(bundle);
                    startActivity(i);
                }
                else{
                    Toast toast=Toast.makeText(getApplicationContext(),"Enter Required Field",Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });
    }
}
