package com.aysusen.catchmario;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageButton button;
    TextView text1;
    TextView text2;
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        button=findViewById(R.id.imageButton3);
        text1=findViewById(R.id.textView);
        text2=findViewById(R.id.textView2);
        score=0;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        Random random = new Random();
                Random random1 = new Random();
                        int x = random.nextInt(1000);
                        int y = random.nextInt(1000);
                button.setX(x);
                button.setY(y);
                int x1 = random1.nextInt(1000);
                int y1 = random1.nextInt(1000);
                button.setX(x1);
                button.setY(y1);
                        score++;
                        text2.setText("Score: " + score);
                    }
        });
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                text1.setText("Time: "+millisUntilFinished/1000);
            }
            @Override
            public void onFinish() {
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Catch The Mario");
                alert.setMessage("Are you sure? ");
                alert.setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       Toast.makeText(MainActivity.this,"Game over!",Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();
                button.setVisibility(View.INVISIBLE);
                text1.setText("Time finished!");
            }
        }.start();
    }
}

