package simtechnospace.tech.jadhavdairy;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    ImageView mImageViewLoginLogo;
    android.support.design.widget.TextInputLayout mTextInputLayoutUserName,mTextInputLayoutUserPassword;
    android.support.design.widget.TextInputEditText mEdtUserName,mEdtUserPassword;
    Button mBtnLogin;
    String mUserName, mPassword;
    TextView mTextViewNewUserRegistration;
    Vibrator mVibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mImageViewLoginLogo = findViewById(R.id.imageViewLoginLogo);

        mTextInputLayoutUserName = findViewById(R.id.textInputLayoutUserName);
        mTextInputLayoutUserPassword = findViewById(R.id.textInputLayoutUserPassword);

        mEdtUserName = findViewById(R.id.edtUserName);
        mEdtUserPassword = findViewById(R.id.edtUserPassword);

        mBtnLogin = findViewById(R.id.btnLogin);

        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        mTextViewNewUserRegistration = findViewById(R.id.textRegisterNewUser);


        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mUserName = mEdtUserName.getText().toString();
                mPassword = mEdtUserPassword.getText().toString();
            }
        });


    }
}
