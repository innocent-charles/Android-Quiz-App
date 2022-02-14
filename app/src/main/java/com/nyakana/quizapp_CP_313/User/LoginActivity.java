package com.nyakana.quizapp_CP_313.User;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.nyakana.quizapp_CP_313.MenuHomeScreenActivity;
import com.nyakana.quizapp_CP_313.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    TextView txtWelcome,txtSignUpToContinue, txtNewUser;
    TextInputLayout textInputLayout_Email, textInputLayout_Password;
    Button Login_btn;
    Dialog dialog;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_me);

        Login_btn = findViewById(R.id.go_btn);
        txtNewUser = findViewById(R.id.signup_tv);
        txtWelcome = findViewById(R.id.welc_txt);
        txtSignUpToContinue = findViewById(R.id.signin_to_continue_txt);
        textInputLayout_Email = findViewById(R.id.email);
        textInputLayout_Password = findViewById(R.id.password);
        firebaseAuth = FirebaseAuth.getInstance();


        txtNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signUpActivity = new Intent(getApplicationContext(), SignUpActivity.class);
                //open sign up activity
                startActivity(signUpActivity);

            }

        });


        //delay in ms
        int DELAY = 500;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (firebaseAuth.getCurrentUser() != null && firebaseAuth.getCurrentUser().isEmailVerified()) {
                    Intent loginActivity = new Intent(getApplicationContext(), MenuHomeScreenActivity.class);//Take me back to login screen when sign up is successfull
                    startActivity(loginActivity);
                }
            }
        }, DELAY);


        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validate Login Info
                if (!ValidateEmail() | !validatePassword()) {
                    return;
                }


                final String email = Objects.requireNonNull(textInputLayout_Email.getEditText()).getText().toString();
                final String password = Objects.requireNonNull(textInputLayout_Password.getEditText()).getText().toString();

                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                             if(firebaseAuth.getCurrentUser().isEmailVerified()) {
                                Toast.makeText(LoginActivity.this, "Welcome To Test Your IQ", Toast.LENGTH_LONG).show();
                                Intent mainIntent = new Intent(LoginActivity.this, MenuHomeScreenActivity.class);
                                startActivity(mainIntent);


                            } else if (!firebaseAuth.getCurrentUser().isEmailVerified()) {
                                Toast.makeText(LoginActivity.this, "Error: Email is not Verified!", Toast.LENGTH_LONG).show();
                            }


                        }
                    }
                });
            }

        });

        dialog = new Dialog(this);
    }

    private Boolean ValidateEmail() {
        String val = Objects.requireNonNull(textInputLayout_Email.getEditText()).getText().toString();
        if (val.isEmpty()) {
            textInputLayout_Email.setError("Field cannot be empty");
            return false;
        } else {
            textInputLayout_Email.setError(null);
            textInputLayout_Email.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = Objects.requireNonNull(textInputLayout_Password.getEditText()).getText().toString();
        if (val.isEmpty()) {
            textInputLayout_Password.setError("Field cannot be empty");
            return false;
        } else {
            textInputLayout_Password.setError(null);
            textInputLayout_Password.setErrorEnabled(false);
            return true;
        }
    }
}



