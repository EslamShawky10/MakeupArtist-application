package com.eslamshawky.hp.makeupartist.Register;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.eslamshawky.hp.makeupartist.RegisterFirebase.Register;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register_Presenter implements RegisterInterfaces.RegisterPresenter {
    private FirebaseAuth mAuth;
    Activity activity;
    RegisterInterfaces.RegisterView registerView;

    public Register_Presenter(Activity activity, RegisterInterfaces.RegisterView registerView) {
        this.activity = activity;
        this.registerView = registerView;
    }

    @Override
    public void Register(String email, String password) {
      mAuth=FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //updateUI(user);
                            FirebaseUser user=mAuth.getCurrentUser();
                        registerView.Succes(user.getEmail());
                        } else {
registerView.error("create Faild");
                            // If sign in fails, display a message to the user.
                          // Log.w("register", "createUserWithEmail:failure", task.getException());
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }


    @Override
    public void selectImage() {

    }

    @Override
    public void encoding() {

    }
}
