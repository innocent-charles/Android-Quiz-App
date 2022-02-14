package com.nyakana.quizapp_CP_313;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.nyakana.quizapp_CP_313.User.LoginActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
public class MenuHomeScreenActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Button navToggler_btn;
    LinearLayout linearLayout;
    Dialog dialog;
    Button allKnowledge_btn;
    Button android;
    Button finance;
    Button java;
    Button cplus;
    Button python;
    Button linux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuhome);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navToggler_btn = findViewById(R.id.action_menu_presenter);
        linearLayout = findViewById(R.id.main_content);
        allKnowledge_btn = findViewById(R.id.allknowledge_start);
        android=findViewById(R.id.android);
        finance=findViewById(R.id.finance);
        cplus=findViewById(R.id.cplus);
        java=findViewById(R.id.java);
        python=findViewById(R.id.python);
        linux=findViewById(R.id.linux);


        allKnowledge_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AK = new Intent(getApplicationContext(),questions_now.class);
                startActivity(AK);
            }
        });


        android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent android=new Intent(getApplicationContext(),android.class);
                startActivity(android);
            }
        });


        finance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent finance=new Intent(getApplicationContext(),questions_now.class);
                startActivity(finance);
            }
        });


        java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent java=new Intent(getApplicationContext(),questions_now.class);
                startActivity(java);
            }
        });

        python.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent python = new Intent(getApplicationContext(),questions_now.class);
                startActivity(python);
            }
        });

        linux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent linux=new Intent(getApplicationContext(),questions_now.class);
                startActivity(linux);
            }
        });

        cplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cplus=new Intent(getApplicationContext(),questions_now.class);
                startActivity(cplus);
            }
        });

        navigationDrawer();
        dialog = new Dialog(this, R.style.AnimateDialog);

        };

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.home);

        navToggler_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();

    }

    private void animateNavigationDrawer() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.cat_heading));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                final float diffScaledOffset = slideOffset*(1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                linearLayout.setScaleX(offsetScale);
                linearLayout.setScaleY(offsetScale);

                final float xOffset = drawerView.getWidth()*slideOffset;
                final float xOffsetDiff = linearLayout.getWidth()*diffScaledOffset/2;
                final float xTranslation = xOffset - xOffsetDiff;
                linearLayout.setTranslationX(xTranslation);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        });
    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if (menuItem.getItemId() == R.id.user_profile) {
//            Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
//            startActivity(intent);
//            MenuHomeScreenActivity.super.onBackPressed();

        } else if (menuItem.getItemId() == R.id.tutorial) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/")));

        } else if (menuItem.getItemId() == R.id.about)
        {
//        GO to Developer Linkedin
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/innocent-charles-329194214/")));

        } else if (menuItem.getItemId() == R.id.logout) {
            //Logout
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }
        return true;
    }

}
