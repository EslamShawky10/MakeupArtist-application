package com.eslamshawky.hp.makeupartist.RegisterFirebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eslamshawky.hp.makeupartist.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity implements View.OnClickListener {
Button button;
EditText edusername,edpassword;
private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        Ui();
}

    private void Ui() {
        button=findViewById(R.id.register);
        edusername=findViewById(R.id.edit_username);
        edpassword=findViewById(R.id.edit_password);
        button.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    public void RegisterEmail(String email,String password){

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();

                    Toast.makeText(Register.this,user.getIdToken(true).toString() , Toast.LENGTH_SHORT).show();
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("register", "createUserWithEmail:success");
                    //updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("register", "createUserWithEmail:failure", task.getException());
                    Toast.makeText(Register.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    //updateUI(null);
                }

                // ...
            }
        });
    }
    @Override
    public void onClick(View v) {
switch (v.getId())
{
    case R.id.register:
        RegisterEmail(edusername.getText().toString(),edpassword.getText().toString());

}    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }
}