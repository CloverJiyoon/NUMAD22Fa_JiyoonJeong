package edu.northeastern.numad22fa_jiyoonjeong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ClickyActivity extends AppCompatActivity implements View.OnClickListener {

    TextView press;
    Button btn_0 , btn_1 , btn_2 , btn_3 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky);

        press = (TextView) findViewById(R.id.pressed);
        press.setOnClickListener(this);

        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);

//        btn_0 = (Button)findViewById(R.id.button);
//        btn_0.setOnClickListener(this);

    }

    public void onClick (View view)
    {
        int theId = view.getId();
        if (theId == R.id.button) {
            press.setText("Pressed: A");
        }
        else if (theId == R.id.button2) {
            press.setText("Pressed: B");
        }
        else if (theId == R.id.button3) {
            press.setText("Pressed: C");
        }
        else if (theId == R.id.button4) {
            press.setText("Pressed: D");
        }
        else if (theId == R.id.button5) {
            press.setText("Pressed: E");
        }
        else if (theId == R.id.button6) {
            press.setText("Pressed: F");
        }
    }
}