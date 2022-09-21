package edu.northeastern.numad22fa_jiyoonjeong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        }

        public void onClick (View view)
        {
            int theId = view.getId();
            if (theId == R.id.btn1) {
                Toast.makeText(getApplicationContext(), "Name: Jiyoon Jeong\nEmail: wldbs8278@gmail.com", Toast.LENGTH_SHORT).show();
            }
            if (theId == R.id.btn2) {
                Toast.makeText(getApplicationContext(), "San Jose, CA", Toast.LENGTH_SHORT).show();
            }
            if (theId == R.id.btn3) {
                openClicky();
            }
        }

        public void openClicky(){
            Intent intent = new Intent(this, ClickyActivity.class);
            startActivity(intent);
        }

}

//    Button btn1, btn2, btn3;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        btn1 = (Button)findViewById(R.id.btn1);
//        btn2 = (Button)findViewById(R.id.btn2);
//        btn3 = (Button)findViewById(R.id.btn3);
//
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Name: Jiyoon Jeong\nEmail: wldbs8278@gmail.com", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "San Jose, CA", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//    }


