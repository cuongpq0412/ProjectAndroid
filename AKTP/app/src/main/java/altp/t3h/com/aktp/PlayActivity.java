package altp.t3h.com.aktp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Random;

import altp.t3h.com.aktp.databinding.ActivityPlayBinding;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {
    private MediaPlayer mBg;
    private Question question;
    private ActivityPlayBinding binding;
    private DatabaseManager databaseManager;
    private int trueCase;
    private boolean chooseQues = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_play);
        databaseManager = new DatabaseManager(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (chooseQues==false){
                    Intent intent =  new Intent();
                    intent.setClass(PlayActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },15000);
        inits();
    }

    private void inits() {
        Intent intent = getIntent();
        int level = intent.getIntExtra("LEVEL", 1);
        if (level <= 5) {
//            mBg = MediaPlayer.create(this, R.r)
        }
//        question.setScore(intent.getIntExtra("score",1));
        question = databaseManager.getQuestion(level);
        binding.tvLevel.setText(question.getLevel() + "");
        binding.tvQuestion.setText(question.getQuestion());
        binding.tvA.setText(question.getcA());
        binding.tvB.setText(question.getcB());
        binding.tvC.setText(question.getcC());
        binding.tvD.setText(question.getcD());
        trueCase = question.getTrueCase();

        binding.tvA.setOnClickListener(this);
        binding.tvB.setOnClickListener(this);
        binding.tvC.setOnClickListener(this);
        binding.tvD.setOnClickListener(this);
        binding.ivStop.setOnClickListener(this);
        binding.ivPercent50.setOnClickListener(this);
        binding.ivRestart.setOnClickListener(this);
    }

    private void setBackgroundView() {
        if (trueCase == 1) {

            binding.tvA.setBackgroundResource(R.drawable.anim_true);
            AnimationDrawable an = (AnimationDrawable) binding.tvA.getBackground();
            an.start();
        } else if (trueCase == 2) {

            binding.tvB.setBackgroundResource(R.drawable.anim_true);
            AnimationDrawable an = (AnimationDrawable) binding.tvB.getBackground();
            an.start();
        } else if (trueCase == 3) {

            binding.tvC.setBackgroundResource(R.drawable.anim_true);
            AnimationDrawable an = (AnimationDrawable) binding.tvC.getBackground();
            an.start();
        } else if (trueCase == 4) {

            binding.tvD.setBackgroundResource(R.drawable.anim_true);
            AnimationDrawable an = (AnimationDrawable) binding.tvD.getBackground();
            an.start();
        }
    }
    private void setMusicBgLose() {
        if (trueCase == 1) {
            MediaPlayer m = MediaPlayer.create(PlayActivity.this,R.raw.lose_a);
            m.start();

        } else if (trueCase == 2) {
            MediaPlayer n = MediaPlayer.create(PlayActivity.this,R.raw.lose_b);
            n.start();

        } else if (trueCase == 3) {
            MediaPlayer n = MediaPlayer.create(PlayActivity.this,R.raw.lose_c);
            n.start();

        } else if (trueCase == 4) {

            MediaPlayer n = MediaPlayer.create(PlayActivity.this,R.raw.lose_d);
            n.start();
        }
    }
    @Override
    public void onClick(View view) {
        chooseQues=true;
        MediaPlayer m = MediaPlayer.create(PlayActivity.this,R.raw.touch_sound);
        m.start();
        switch (view.getId()) {
            case R.id.tv_a:
                binding.tvA.setBackgroundResource(R.drawable.anim_select);
                AnimationDrawable an = (AnimationDrawable) binding.tvA.getBackground();
                an.start();
                MediaPlayer n = MediaPlayer.create(PlayActivity.this,R.raw.ans_a);
                n.start();

                if (trueCase == 1) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
//                            binding.tvA.setBackgroundResource(R.drawable.anim_true);
//                            AnimationDrawable an2 = (AnimationDrawable) binding.tvA.getBackground();
//                            an2.start();
                            setBackgroundView();
                            MediaPlayer m = MediaPlayer.create(PlayActivity.this,R.raw.true_a);
                            m.start();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent();
                                    intent.putExtra("kq", 1);
                                    setResult(1, intent);
                                    finish();

                                }
                            }, 3000);
                        }
                    }, 3000);

                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            binding.tvA.setBackgroundResource(R.drawable.anim_fail);
                            AnimationDrawable an3 = (AnimationDrawable) binding.tvA.getBackground();
                            an3.start();
                            setMusicBgLose();
                            setBackgroundView();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent();
                                    intent.putExtra("kq", 2);
                                    setResult(1, intent);
                                    finish();

                                }
                            }, 3000);
                        }
                    }, 3000);

                }
                break;
            case R.id.tv_b:
                binding.tvB.setBackgroundResource(R.drawable.anim_select);
                AnimationDrawable a2n = (AnimationDrawable) binding.tvB.getBackground();
                a2n.start();
                MediaPlayer n2 = MediaPlayer.create(PlayActivity.this,R.raw.ans_b);
                n2.start();

                if (trueCase == 2) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
//                            binding.tvB.setBackgroundResource(R.drawable.anim_true);
//                            AnimationDrawable an2 = (AnimationDrawable) binding.tvB.getBackground(); n
//                            an2.start();
                            MediaPlayer m = MediaPlayer.create(PlayActivity.this,R.raw.true_b2);
                            m.start();
                            setBackgroundView();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent();
                                    intent.putExtra("kq", 1);
                                    setResult(1, intent);
                                    finish();
                                }
                            }, 3000);
                        }
                    }, 3000);

                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            binding.tvB.setBackgroundResource(R.drawable.anim_fail);
                            AnimationDrawable an3 = (AnimationDrawable) binding.tvB.getBackground();
                            an3.start();
                            setMusicBgLose();
                            setBackgroundView();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent();
                                    intent.putExtra("kq", 2);
                                    setResult(1, intent);
                                    finish();

                                }
                            }, 3000);
                        }
                    }, 3000);

                }
                break;
            case R.id.tv_c:
                binding.tvC.setBackgroundResource(R.drawable.anim_select);
                AnimationDrawable a3n = (AnimationDrawable) binding.tvC.getBackground();
                a3n.start();
                MediaPlayer n3 = MediaPlayer.create(PlayActivity.this,R.raw.ans_c);
                n3.start();

                if (trueCase == 3) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
//                            binding.tvC.setBackgroundResource(R.drawable.anim_true);
//                            AnimationDrawable an2 = (AnimationDrawable) binding.tvC.getBackground();
//                            an2.start();
                            setBackgroundView();
                            MediaPlayer m = MediaPlayer.create(PlayActivity.this,R.raw.true_c2);
                            m.start();
                            Intent intent = new Intent();
                            intent.putExtra("kq", 1);
                            setResult(1, intent);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();

                                }
                            }, 3000);
                        }
                    }, 3000);

                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            binding.tvC.setBackgroundResource(R.drawable.anim_fail);
                            AnimationDrawable an3 = (AnimationDrawable) binding.tvC.getBackground();
                            an3.start();
                            setMusicBgLose();
                            setBackgroundView();
                            Intent intent = new Intent();
                            intent.putExtra("kq", 2);
                            setResult(1, intent);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();

                                }
                            }, 3000);
                        }
                    }, 3000);

                }
                break;
            case R.id.tv_d:
                binding.tvD.setBackgroundResource(R.drawable.anim_select);
                AnimationDrawable a4n = (AnimationDrawable) binding.tvD.getBackground();
                a4n.start();
                MediaPlayer n4 = MediaPlayer.create(PlayActivity.this,R.raw.ans_d);
                n4.start();

                if (trueCase == 4) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
//                            binding.tvD.setBackgroundResource(R.drawable.anim_true);
//                            AnimationDrawable an2 = (AnimationDrawable) binding.tvD.getBackground();
//                            an2.start();
                            setBackgroundView();
                            MediaPlayer m = MediaPlayer.create(PlayActivity.this,R.raw.true_d2);
                            m.start();
                            Intent intent = new Intent();
                            intent.putExtra("kq", 1);
                            setResult(1, intent);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();

                                }
                            }, 3000);
                        }
                    }, 3000);

                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            binding.tvD.setBackgroundResource(R.drawable.anim_fail);
                            AnimationDrawable an3 = (AnimationDrawable) binding.tvD.getBackground();
                            an3.start();
                            setMusicBgLose();
                            setBackgroundView();
                            Intent intent = new Intent();
                            intent.putExtra("kq", 2);
                            setResult(1, intent);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            }, 3000);
                        }
                    }, 3000);

                }
                break;
            case R.id.iv_stop:
                Intent intent = new Intent();
                intent.setClass(PlayActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case  R.id.iv_percent50:
                MediaPlayer m2 = MediaPlayer.create(PlayActivity.this,R.raw.sound5050);
                m2.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Random rd = new Random();
                        int caseH1= rd.nextInt(4) +1,caseH2 = rd.nextInt(4) +1;
                        while(caseH1==trueCase){
                            caseH1= rd.nextInt(4) +1;
                        }
                        while(caseH2==trueCase){
                            caseH2 = rd.nextInt(4) +1;
                        }
                        if (caseH1 == 1) {
                            binding.tvA.setBackgroundResource(R.drawable.anim_fail);
                        }else if (caseH1==2){
                            binding.tvB.setBackgroundResource(R.drawable.anim_fail);
                        }else if (caseH1==3){
                            binding.tvC.setBackgroundResource(R.drawable.anim_fail);
                        }else if (caseH1==4){
                            binding.tvD.setBackgroundResource(R.drawable.anim_fail);
                        }
                        if (caseH2 == 1) {
                            binding.tvA.setBackgroundResource(R.drawable.anim_fail);
                        }else if (caseH2==2){
                            binding.tvB.setBackgroundResource(R.drawable.anim_fail);
                        }else if (caseH2==3){
                            binding.tvC.setBackgroundResource(R.drawable.anim_fail);
                        }else if (caseH2==4){
                            binding.tvD.setBackgroundResource(R.drawable.anim_fail);
                        }

                    }
                },3000);


                break;
            case R.id.iv_restart:

                Intent intent1 = new Intent();
                intent1.setClass(PlayActivity.this,MenuActivity.class);
                startActivity(intent1);
                finish();
                break;
        }
    }
}
