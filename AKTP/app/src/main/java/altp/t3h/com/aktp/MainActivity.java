package altp.t3h.com.aktp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import altp.t3h.com.aktp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityMainBinding binding;
    private boolean isDestroy = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       ActivityMainBinding binding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        binding.btnPlay.setOnClickListener(this);
        MediaPlayer m = MediaPlayer.create(MainActivity.this,R.raw.bgmusic);
        m.start();

    }

//    private void inits(){
//        Animation ani = AnimationUtils.loadAnimation(this,
//                R.anim.rotate_animation);
//        binding.ivRotate.setAnimation(ani);
//        binding.ivRotate.startAnimation(ani);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (isDestroy){
//                    return;
//                }
//                Intent intent = new Intent();
//                intent.setClass(MainActivity.this, PlayActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        }, 3000);
//    }

    @Override
    protected void onDestroy() {
        isDestroy = true;
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        MediaPlayer m = MediaPlayer.create(MainActivity.this,R.raw.touch_sound);
        m.start();
        int level = 1;
        Intent intent = new Intent();
        intent.setClass(this,MenuActivity.class);
        intent.putExtra("LEVEL",level);
        startActivity(intent);
        finish();

    }


}
