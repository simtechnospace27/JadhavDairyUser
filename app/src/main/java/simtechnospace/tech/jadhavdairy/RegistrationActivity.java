package simtechnospace.tech.jadhavdairy;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.pnikosis.materialishprogress.ProgressWheel;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistrationActivity extends AppCompatActivity {
    android.support.design.widget.TextInputLayout mTextInputLayoutUserName, mTextInputLayoutUserPassword,mTextInputLayoutUserEmail, mTextInputLayoutUserAddress, mTextInputLayoutUserMobileNo,mTextInputLayoutRequirement;
    android.support.design.widget.TextInputEditText mEdtUserName, mEdtUserPassword,mEdtUserEmail, mEdtUserAddress, mEdtUserRequirement, mEdtUserMobileNo;
    Button mBtnSignup;
    String mUserName, mPassword,Url,mUserAddress,mUserEmail,mUserRequirement,mUserMobileNo;
    Vibrator mVibrator;

    TextView mTextViewNewUserRegistration;
    ProgressWheel mProgressWheelPreview;
    ProgressDialog progressDialog;

    private Spinner spinner1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        mProgressWheelPreview = findViewById(R.id.progress_wheel);
        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        mTextInputLayoutUserEmail = findViewById(R.id.textInputLayoutUserNameRegistration);
        mTextInputLayoutUserPassword = findViewById(R.id.textInputLayoutUserPasswordRegistration);
        mTextInputLayoutUserName = findViewById(R.id.textInputLayoutUserName);
        mTextInputLayoutUserAddress = findViewById(R.id.textInputLayoutUserAddress);
        mTextInputLayoutUserMobileNo = findViewById(R.id.textInputLayoutMobileNo);
        mTextInputLayoutRequirement = findViewById(R.id.textInputLayoutRequirement);

        mEdtUserEmail = findViewById(R.id.edtUserNameRegistration);
        mEdtUserPassword = findViewById(R.id.edtUserPasswordRegistration);
        mEdtUserName = findViewById(R.id.edtUserName);
        mEdtUserAddress = findViewById(R.id.edtUserAddress);
        mEdtUserMobileNo = findViewById(R.id.edtUserMobileNo);
        mEdtUserRequirement = findViewById(R.id.edtUserRequirement);




        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemClickListener((AdapterView.OnItemClickListener) this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.quantity_arrays, android.R.layout.simple_spinner_item);
               // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
               // Apply the adapter to the spinner
           spinner1.setAdapter(adapter);



        progressDialog = new ProgressDialog(RegistrationActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);


        mBtnSignup = findViewById(R.id.btnSignUp);

        mBtnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mUserName = mEdtUserName.getText().toString();
                mPassword = mEdtUserPassword.getText().toString();
                mUserAddress = mEdtUserAddress.getText().toString();
                mUserMobileNo = mEdtUserMobileNo.getText().toString();
                mUserEmail = mEdtUserEmail.getText().toString();
                mUserRequirement = mEdtUserRequirement.getText().toString();


                submitForm(mUserEmail,mPassword,mUserName,mUserAddress,mUserMobileNo,mUserRequirement);
            }

        });


    }


    private boolean checkFirstName(String userName) {

        userName = userName.trim();

        if(userName.trim().isEmpty() || (userName.length() <= 1)){
            mTextInputLayoutUserName.setErrorEnabled( true );
            mTextInputLayoutUserName.setError( "Please Enter Valid First Name" );
            return false;
        }
        mTextInputLayoutUserName.setErrorEnabled( false );
        return true;
    }


    private boolean checkEmail(String userName) {

        userName = userName.trim();

        if (userName.trim().isEmpty() || !isValidEmail(userName)){
            mTextInputLayoutUserEmail.setErrorEnabled(true);
            mTextInputLayoutUserEmail.setError("Please Enter Valid Email ID");
            return false;
        }
        mTextInputLayoutUserEmail.setErrorEnabled(false);
        return true;
    }

    private boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean chkPass(String pass)
    {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}";

        return pass.matches(pattern);

    }

    private boolean checkPassword(String password) {
        if (password.trim().isEmpty() || (password.length() < 7) ){
            mTextInputLayoutUserPassword.setErrorEnabled(true);
            mTextInputLayoutUserPassword.setError("Please enter password length greater than 6");
            return false;
        }
        mTextInputLayoutUserPassword.setErrorEnabled(false);
        return true;

    }

    private boolean checkAddress(String address){
        if (address.trim().isEmpty()){
            mTextInputLayoutUserAddress.setErrorEnabled(true);
            mTextInputLayoutUserAddress.setError("Please Enter Valid Address");
            return false;
        }
        mTextInputLayoutUserAddress.setErrorEnabled(false);
        return true;
    }

    private boolean checkMobile(String mobileNo) {
        if (Integer.getInteger(mobileNo) == 10) {
            mTextInputLayoutUserMobileNo.setErrorEnabled(false);
            return true;
        } else if (mobileNo.isEmpty() || Integer.getInteger(mobileNo) < 10 ||Integer.getInteger(mobileNo) > 10) {
            mTextInputLayoutUserMobileNo.setErrorEnabled(true);
            mTextInputLayoutUserMobileNo.setError("Please Enter Valid Mobile No");
            return false;
        }
        return true;
    }


    public void submitForm(String Email,String password,String username,String Address, String mobileNo, String Requirement) {

        if (!checkFirstName(username)) {
            mVibrator.vibrate(1000);
            return;

        } else if (!checkAddress(Address)) {
            mVibrator.vibrate(1000);
            return;

        } else if (!checkMobile(mobileNo)) {
            mVibrator.vibrate(1000);
            return;

        } else if (!checkEmail(Email)) {
            mVibrator.vibrate(1000);
            return;
        } else if (!checkPassword(password)) {
            mVibrator.vibrate(1000);
            return;

        } else if (!checkRequirement(Requirement,unit)) {
            mVibrator.vibrate(1000);
            return;

        } else {
            mTextInputLayoutUserName.setErrorEnabled(false);
            mTextInputLayoutUserPassword.setErrorEnabled(false);
            mTextInputLayoutUserAddress.setErrorEnabled(false);
            mTextInputLayoutUserEmail.setErrorEnabled(false);
            mTextInputLayoutUserMobileNo.setErrorEnabled(false);
            mTextInputLayoutRequirement.setErrorEnabled(false);

            progressDialog.show();
            JSONObject params = new JSONObject();
            try {

                params.put("name", username);
                params.put("mblno", mobileNo);
                params.put("address", Address);
                params.put("requirement",Requirement);
                params.put("email", Email);
                params.put("paasword", password);


            } catch (JSONException e) {
                e.printStackTrace();

            }

            System.out.println(params.toString());





        }
    }

            public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
         parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
