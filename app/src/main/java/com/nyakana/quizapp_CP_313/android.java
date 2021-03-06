package com.nyakana.quizapp_CP_313;
import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class android extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Button navToggler;
    Button Next_btn;
    LinearLayout linearLayout;
    LinearLayout linearLayout1;
    TextView txtQuestions;
    TextView txtnumberIndicator;
    Dialog result;
    private int count = 0;
    private int position = 0;
    private List<questionsModelClass> list;
    private int score = 0;
    private long pressedTime;
    Button back_button;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); ///Eneter into fullscreen mode
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android);

        drawerLayout = findViewById(R.id.drawer_layout);
        navToggler = findViewById(R.id.action_menu_presenter);
        linearLayout = findViewById(R.id.main_content);
        linearLayout1 = findViewById(R.id.options_layout);
        txtQuestions = findViewById(R.id.question_view);
        txtnumberIndicator = findViewById(R.id.no_of_questions_view);
        Next_btn = findViewById(R.id.next_btn);
        back_button=findViewById(R.id.back_btn);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back=new Intent(getApplicationContext(),MenuHomeScreenActivity.class);
                if (pressedTime + 2000 > System.currentTimeMillis()) {
                    startActivity(back);
                    finish();
                } else {
                    Toast.makeText(getBaseContext(), "Sure you want to quit? Press back again to exit", Toast.LENGTH_SHORT).show();
                }
                pressedTime = System.currentTimeMillis();
            }
        });
        final MediaPlayer level_lose = MediaPlayer.create(this, R.raw.level_lose);///Play sound when user loses level
        final MediaPlayer level_won = MediaPlayer.create(this, R.raw.applause_wav);///Play sound when user wins level
        result = new Dialog(this, R.style.AnimateDialog);
        // PHP QUESTIONS GOES HERE
        list = new ArrayList<>();
        list.add(new questionsModelClass("Which are the  screen densities in Android?" + "", "  low density", "medium density", " extra high density", "all of the above", "all of the above"));
        list.add(new questionsModelClass("If you need your interface to work across different processes, you can create an interface for the service with a ________????", "Binder", "Messenger", "AIDL", " b or c", " b or c" ));
        list.add(new questionsModelClass("How many ways to start services?", " started", " bound", "a & b", "messenger", "a & b"));
        list.add(new questionsModelClass(" Parent class of Service?", " Object", " Context", "ContextWrapper", "ContextThemeWrapper", "ContextWrapper"));
        list.add(new questionsModelClass(" What are the indirect Direct subclasses of Activity?", "launcherActivity", "preferenceActivity", "tabActivity", "all the above", "all the above"));
        list.add(new questionsModelClass(" Which are the screen sizes in Android?", "small", "normal", " large", "all the above", "all the above"));
        list.add(new questionsModelClass(" What are the indirect Direct subclasses of Activity?", "launcherActivity", "preferenceActivity", "tabActivity", "all the above", "all the above"));
        list.add(new questionsModelClass(" When contentProvider would be activated?", " using Intent", "using SQLite", "using ContentResolver", "  none of the above", "using ContentResolver"));
        list.add(new questionsModelClass(" Which component is not activated by an Intent?", " activity", " services", "  contentProvider", "broadcastReceiver", "  contentProvider"));
        list.add(new questionsModelClass(" What are the indirect Direct subclasses of Activity?", "launcherActivity", "preferenceActivity", "tabActivity", "all the above", "all the above"));


        for (int i = 0; i < 4; i++) {
            linearLayout1.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAns((Button) v);
                }
            });
        }
        txtnumberIndicator.setText(position + 1 + "/" + list.size());
        playAnim(txtQuestions, 0, list.get(position).getQuestions());

        Next_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v) {
                Next_btn.setEnabled(false);
                Next_btn.setAlpha(0.7f);
                enableOptions(true);
                position++;
                if (position == list.size()) {
                    //Score Activities
                    if (score <= 2) {
                        Button try_again;
                        result.setContentView(R.layout.fail_20_layout);
                        try_again = result.findViewById(R.id.try_again_btn);
                        level_lose.start();
                        try_again.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent BG = new Intent(getApplicationContext(), questions_now.class); //If User get 20% let him or her play again
                                startActivity(BG);
                            }
                        });
                        Objects.requireNonNull(result.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        result.show();
                    } else if (score <= 4) {
                        Button try_again;
                        result.setContentView(R.layout.middle_50_layout);
                        try_again = result.findViewById(R.id.try_again_btn);
                        level_lose.start();
                        try_again.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent BG = new Intent(getApplicationContext(), questions_now.class); ///If User get 50% let him or her play again
                                startActivity(BG);
                            }
                        });
                        Objects.requireNonNull(result.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        result.show();
                    } else if (score <= 9) {
                        Button try_again;
                        result.setContentView(R.layout.atleast_70_layout);
                        try_again = result.findViewById(R.id.try_again_btn);
                        level_won.start();
                        try_again.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent BG = new Intent(getApplicationContext(),MenuHomeScreenActivity.class); ///If User get 70% let him to next level
                                startActivity(BG);
                            }
                        });
                        Objects.requireNonNull(result.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        result.show();
                    } else if (score == 10) {
                        Button try_again;
                        result.setContentView(R.layout.passed_100_layout);
                        try_again = result.findViewById(R.id.try_again_btn);
                        level_won.start();
                        try_again.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent BG = new Intent(getApplicationContext(), MenuHomeScreenActivity.class); ///If User get 100% promote him or her to next level
                                startActivity(BG);
                            }
                        });
                        Objects.requireNonNull(result.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        result.show();
                    }
                    return;
                }
                count = 0;
                playAnim(txtQuestions, 0, list.get(position).getQuestions());
            }
        });
    }
    //ANIMATING SCREEN
    private void playAnim(final View view, final int value, final String data) {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (value == 0 && count < 4) {
                    String option = "";
                    if (count == 0) {
                        option = list.get(position).getOptionA();
                    } else if (count == 1) {
                        option = list.get(position).getOptionB();
                    } else if (count == 2) {
                        option = list.get(position).getOptionC();
                    } else if (count == 3) {
                        option = list.get(position).getOptionD();
                    }
                    playAnim(linearLayout1.getChildAt(count), 0, option);
                    count++;
                }
            }
            @SuppressLint("SetTextI18n")
            @Override
            public void onAnimationEnd(Animator animation) {

                if (value == 0) {

                    try {
                        ((TextView) view).setText(data);
                        txtnumberIndicator.setText(position + 1 + "/" + list.size());
                    } catch (ClassCastException ex) {
                        ((Button) view).setText(data);
                    }
                    view.setTag(data);
                    playAnim(view, 1, data);
                }
            }
            @Override
            public void onAnimationCancel(Animator animation) {

            }
            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
    private void checkAns(Button selectedOptions) {
        enableOptions(false);
        Next_btn.setEnabled(true);
        Next_btn.setAlpha(1);
        if (selectedOptions.getText().toString().equals(list.get(position).getCorrectAnswer())) {
            //correct Answer
            score++;
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.ding);
            selectedOptions.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#14E39A")));
            mp.start();
        } else {
            //wrong Answer
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.wrong_buzzer);
            selectedOptions.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF2B55")));
            Button correctOption = linearLayout1.findViewWithTag(list.get(position).getCorrectAnswer());
            correctOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#14E39A")));

            mp.start();
        }
    }
    private void enableOptions(boolean enable) {
        for (int i = 0; i < 4; i++) {
            linearLayout1.getChildAt(i).setEnabled(enable);
            if (enable) {
                linearLayout1.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#2133A0")));
            }
        }
    }

    @Override
    public void onBackPressed(){

        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Sure you want to quit? Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }

}

