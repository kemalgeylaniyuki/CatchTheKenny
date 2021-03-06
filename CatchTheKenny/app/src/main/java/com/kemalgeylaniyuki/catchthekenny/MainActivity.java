package com.kemalgeylaniyuki.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timeText;
    TextView scoreText;
    int score;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeText = findViewById(R.id.timeText);
        scoreText = findViewById(R.id.scoreText);

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);

        imageArray = new ImageView[] {imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9};

        hideImages();

        score = 0;

        new CountDownTimer(10000, 1000){  // uygulama açılınca 10 dan geriye doğru say

            @Override
            public void onTick(long l) {
                timeText.setText("Time : " + l/1000);
            }

            @Override
            public void onFinish() { // geri sayma bitince yapılacaklar
                timeText.setText("Time off :( "); // time off yazdır.
                handler.removeCallbacks(runnable); // runnable yi durdur
                // kenny tekrar sakla hepsini
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE); // hepsini görünmez yapmak
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart ? ");
                alert.setMessage("Are you sure to restart game ? ");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { // YES e tıklayınca ne olacak ?
                        // Restart
                        Intent intent = getIntent(); // güncel aktiviteyi bitir
                        finish();
                        startActivity(intent ); // aynı aktiviteyi baştan başlat
                    }
                });

                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { // No tıklayınca ne olacak ?
                        Toast.makeText(MainActivity.this, "GAME OVER :/ ", Toast.LENGTH_SHORT).show(); // Game over toast yazdır
                    }
                });

                alert.show();

            }
        }.start();

    }

    public void increaseScore (View view){ // resme tıklayınca ne olsun? metodu.

        score++; // her tıklamada score ı bie arttır
        scoreText.setText("SCORE :  " + score); // score text e yazdır
    }

    public void hideImages(){ // dizideki elemanları görünmez yapmak için metod oluşturduk

        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {

                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE); // hepsini görünmez yapmak
                }
                Random random = new Random(); // Random objesi oluşturduk
                int i = random.nextInt(9); // 0 ile 8 arası rastgele sayı oluştur
                imageArray[i].setVisibility(View.VISIBLE); // dizideki random elemanı belirlenen periyotta görünür yap

                handler.postDelayed(this, 500); // periyot 0.5 sn

            }
        };

        handler.post(runnable);

    }



}