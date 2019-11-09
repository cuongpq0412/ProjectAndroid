package altp.t3h.com.aktp;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import altp.t3h.com.aktp.databinding.ActivityListscoreBinding;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private  boolean isDestroy =false;
    private ActivityListscoreBinding binding;
    private  int curren_level =1;
    private RelativeLayout relx1,relx2,relx3,relx4,relxLast,relxCurrent;
    private String game_score;
    private TextView tScore;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_listscore);
//        relx.findViewById(R.id.rl_s01);

        ActivityListscoreBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_listscore);
//        binding.rlS01.setBackgroundColor(Color.RED);
        tScore = findViewById(R.id.tv_numscore);
        tScore.setText("0");
        relx4 = findViewById(R.id.rl_s15);
        relx3 = findViewById(R.id.rl_s11);
        inits(relx3,relx4,5000);
        relx2 = findViewById(R.id.rl_s06);
        inits(relx2,relx3,5500);
        relx1 = findViewById(R.id.rl_s01);
        inits(relx1,relx2,6000);

        binding.btnContinute.setOnClickListener(this);
        MediaPlayer m = MediaPlayer.create(MenuActivity.this,R.raw.reday);
        m.start();
    }

    private void inits(final RelativeLayout relx1, final RelativeLayout r2, int s) {
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        if (isDestroy){
                            return;
                        }
                        relx1.setBackgroundResource(R.color.xanhDaTroi);
                        r2.setBackgroundResource(R.color.trongSuot);

                    }
                }, s
        );
    }

    @Override
    protected void onDestroy() {
        isDestroy = true;
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        MediaPlayer m = MediaPlayer.create(MenuActivity.this,R.raw.touch_sound);
        MediaPlayer m1 = MediaPlayer.create(MenuActivity.this,R.raw.gofind);
        m.start();
        m1.start();
        int score = 200000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                inits(relx4,relx1,1000);
                MediaPlayer m2 = MediaPlayer.create(MenuActivity.this,R.raw.ques1);
                m2.start();
            }
        },4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(MenuActivity.this,PlayActivity.class);
                intent.putExtra("LEVEL",curren_level);
                startActivityForResult(intent, 1);
            }
        },6000);

    }
    private void showMusicBg(){
        if (curren_level==1){
            MediaPlayer n2 = MediaPlayer.create(MenuActivity.this,R.raw.ques1);
            n2.start();
        }else if(curren_level==2){
            MediaPlayer n2 = MediaPlayer.create(MenuActivity.this,R.raw.ques2);
            n2.start();

        }else if(curren_level==3){
            MediaPlayer n2 = MediaPlayer.create(MenuActivity.this,R.raw.ques3);
            n2.start();

        }else if(curren_level==4){
            MediaPlayer n2 = MediaPlayer.create(MenuActivity.this,R.raw.ques4);
            n2.start();

        }else if(curren_level==5){
            MediaPlayer n2 = MediaPlayer.create(MenuActivity.this,R.raw.ques5);
            n2.start();

        }else if(curren_level==6){
            MediaPlayer n2 = MediaPlayer.create(MenuActivity.this,R.raw.ques6);
            n2.start();

        }else if(curren_level==7){
            MediaPlayer n2 = MediaPlayer.create(MenuActivity.this,R.raw.ques7);
            n2.start();

        }else if(curren_level==8){
            MediaPlayer n2 = MediaPlayer.create(MenuActivity.this,R.raw.ques8);
            n2.start();

        }else if(curren_level==9){
            MediaPlayer n2 = MediaPlayer.create(MenuActivity.this,R.raw.ques9);
            n2.start();

        }else if(curren_level==10){
            MediaPlayer n2 = MediaPlayer.create(MenuActivity.this,R.raw.ques10);
            n2.start();

        }else if(curren_level==11){
            MediaPlayer n2 = MediaPlayer.create(MenuActivity.this,R.raw.ques11);
            n2.start();

        }else if(curren_level==12){
            MediaPlayer n2 = MediaPlayer.create(MenuActivity.this,R.raw.ques12);
            n2.start();

        }else if(curren_level==13){
            MediaPlayer n2 = MediaPlayer.create(MenuActivity.this,R.raw.ques13);
            n2.start();

        }else if(curren_level==14){
            MediaPlayer n2 = MediaPlayer.create(MenuActivity.this,R.raw.ques14);
            n2.start();

        }else if(curren_level==15){
            MediaPlayer n2 = MediaPlayer.create(MenuActivity.this,R.raw.ques15);
            n2.start();
        }else if(curren_level==16){
            game_score="150,000,000";

        }
    }
    private void setScore(int curren_level){
        if (curren_level==1){
            game_score="0";
        }else if(curren_level==2){
            game_score="200,000";

        }else if(curren_level==3){
            game_score="400,000";

        }else if(curren_level==4){
            game_score="600,000";

        }else if(curren_level==5){
            game_score="1,000,000";

        }else if(curren_level==6){
            game_score="2,000,000";

        }else if(curren_level==7){
            game_score="5,000,000";

        }else if(curren_level==8){
            game_score="6,000,000";

        }else if(curren_level==9){
            game_score="10,000,000";

        }else if(curren_level==10){
            game_score="14,000,000";

        }else if(curren_level==11){
            game_score="22,000,000";

        }else if(curren_level==12){
            game_score="30,000,000";

        }else if(curren_level==13){
            game_score="40,000,000";

        }else if(curren_level==14){
            game_score="60,000,000";

        }else if(curren_level==15){
            game_score="85,000,000";
        }else if(curren_level==16){
            game_score="150,000,000";

        }
        tScore.setText("" + game_score);
    }

    private void setBgView(){
        if (curren_level==1){
           relxLast = findViewById(R.id.rl_s01);
           relxCurrent = findViewById(R.id.rl_s15);
            inits(relxCurrent,relxLast,2000);
        }else if(curren_level==2){
            relxLast = findViewById(R.id.rl_s15);
            relxCurrent = findViewById(R.id.rl_s14);
            inits(relxCurrent,relxLast,2000);

        }else if(curren_level==3){
            relxLast = findViewById(R.id.rl_s14);
            relxCurrent = findViewById(R.id.rl_s13);
            inits(relxCurrent,relxLast,2000);

        }else if(curren_level==4){
            relxLast = findViewById(R.id.rl_s13);
            relxCurrent = findViewById(R.id.rl_s12);
            inits(relxCurrent,relxLast,2000);

        }else if(curren_level==5){
            relxLast = findViewById(R.id.rl_s12);
            relxCurrent = findViewById(R.id.rl_s11);
            inits(relxCurrent,relxLast,2000);

        }else if(curren_level==6){
            relxLast = findViewById(R.id.rl_s11);
            relxCurrent = findViewById(R.id.rl_s10);
            inits(relxCurrent,relxLast,2000);

        }else if(curren_level==7){
            relxLast = findViewById(R.id.rl_s10);
            relxCurrent = findViewById(R.id.rl_s09);
            inits(relxCurrent,relxLast,2000);

        }else if(curren_level==8){
            relxLast = findViewById(R.id.rl_s09);
            relxCurrent = findViewById(R.id.rl_s08);
            inits(relxCurrent,relxLast,2000);

        }else if(curren_level==9){
            relxLast = findViewById(R.id.rl_s08);
            relxCurrent = findViewById(R.id.rl_s07);
            inits(relxCurrent,relxLast,2000);

        }else if(curren_level==10){
            relxLast = findViewById(R.id.rl_s07);
            relxCurrent = findViewById(R.id.rl_s06);
            inits(relxCurrent,relxLast,2000);

        }else if(curren_level==11){
            relxLast = findViewById(R.id.rl_s06);
            relxCurrent = findViewById(R.id.rl_s05);
            inits(relxCurrent,relxLast,2000);

        }else if(curren_level==12){
            relxLast = findViewById(R.id.rl_s05);
            relxCurrent = findViewById(R.id.rl_s04);
            inits(relxCurrent,relxLast,2000);

        }else if(curren_level==13){
            relxLast = findViewById(R.id.rl_s04);
            relxCurrent = findViewById(R.id.rl_s03);
            inits(relxCurrent,relxLast,2000);

        }else if(curren_level==14){
            relxLast = findViewById(R.id.rl_s03);
            relxCurrent = findViewById(R.id.rl_s02);
            inits(relxCurrent,relxLast,2000);

        }else if(curren_level==15){
            relxLast = findViewById(R.id.rl_s02);
            relxCurrent = findViewById(R.id.rl_s01);
            inits(relxCurrent,relxLast,2000);
        }else if(curren_level==16){
            game_score="150,000,000";

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int request = data.getIntExtra("kq",1);
//        relx3 = findViewById(R.id.rl_s14);
//        inits(relx3,relx4,3000);

        if (request == 1){
            curren_level = curren_level+1;
            setScore(curren_level);
            setBgView();
            showMusicBg();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent();
                    intent.setClass(MenuActivity.this,PlayActivity.class);
                    intent.putExtra("LEVEL",curren_level);
                    startActivityForResult(intent, 1);
                }
            },4000);
        }else{
            Intent intent = new Intent();
            intent.setClass(MenuActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
