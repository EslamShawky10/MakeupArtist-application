package com.eslamshawky.hp.makeupartist.Register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.eslamshawky.hp.makeupartist.R;
public class RegisterActivity extends AppCompatActivity implements RegisterInterfaces.RegisterView, View.OnClickListener {
    Button button;
    Register_Presenter register_presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);
        button = findViewById(R.id.register);
        button.setOnClickListener(this);

        register_presenter = new Register_Presenter(RegisterActivity.this, this);
    }

    @Override
    public void Succes(String s) {
        Toast.makeText(this, "" + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void error(String s) {
        Toast.makeText(this, "" + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                register_presenter.Register("username@yahoo.com", "pas4588525s");
                break;

        }
    }
}
