package simtechnospace.tech.jadhavdairy.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.pnikosis.materialishprogress.ProgressWheel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import simtechnospace.tech.jadhavdairy.R;
import simtechnospace.tech.jadhavdairy.pojo_class.URL;

public class RegistrationActivity extends AppCompatActivity {
    android.support.design.widget.TextInputLayout mTextInputLayoutUserName, mCheckBoXTextLayout, mTextInputLayoutUnit, mTextInputLayoutUserPassword,mTextInputLayoutUserEmail, mTextInputLayoutUserAddress, mTextInputLayoutUserMobileNo, mTextInputLayoutMilkType, mTextInputLayoutRequirement, mTextInputSocietyName, mTextInputWingName, mTextInputFlatNo;
    android.support.design.widget.TextInputEditText mEdtUserName, mEdtUserPassword,mEdtUserEmail, mEdtUserAddress, mEdtUserRequirement, mEdtUserMobileNo, mEdtSocietyName, mEdtWingName, mEdtFlatNo;
    Button mBtnSignup;
    String mUserName, mPassword,mUserAddress,mUserEmail,mUserMobileNo, mUnit, mSocietyName, mWingName, mFlatNo, mMilkType;
    Double mUserRequirement;
    Vibrator mVibrator;
    CheckBox checkbox_terms;
    TextView terms_link;

    ArrayAdapter<String> SpinerAdapter, mMilkTypeArrayAdapter;

    TextView mTextViewNewUserRegistration;
    ProgressWheel mProgressWheelPreview;
    ProgressDialog progressDialog;
    String[] spinnerMilkTyepArray;

    private Spinner spinner1, mMIlkTypeSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);



        String registrationUrl1 = URL.url_milk_type;





        mProgressWheelPreview = findViewById(R.id.progress_wheel);
        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        mTextInputLayoutUserEmail = findViewById(R.id.textInputLayoutUserNameRegistration);
        mTextInputLayoutUserPassword = findViewById(R.id.textInputLayoutUserPasswordRegistration);
        mTextInputLayoutUserName = findViewById(R.id.textInputLayoutUserName);
        mTextInputLayoutUserAddress = findViewById(R.id.textInputLayoutUserAddress);
        mTextInputLayoutUserMobileNo = findViewById(R.id.textInputLayoutMobileNo);
        mTextInputLayoutRequirement = findViewById(R.id.textInputLayoutRequirement);
        mTextInputLayoutUnit = findViewById(R.id.textInputLayoutUnit);
        mTextInputLayoutMilkType = findViewById(R.id.textInputLayoutMilkType);
        mCheckBoXTextLayout = findViewById(R.id.textInputLayoutCheckBox);

        checkbox_terms = findViewById(R.id.checkbox_terms);
        terms_link = findViewById(R.id.terms_link);

        terms_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, TermsAndCondition.class);
                startActivity(intent);
            }
        });



        mEdtSocietyName = findViewById(R.id.edtUserSocietyName);
        mEdtWingName = findViewById(R.id.edtUserWingName);
        mEdtFlatNo = findViewById(R.id.edtFlatNo);

        mTextInputFlatNo = findViewById(R.id.textInputLayoutFlatNo);
        mTextInputWingName = findViewById(R.id.textInputLayoutWingName);
        mTextInputSocietyName = findViewById(R.id.textInputLayoutSocietyName);


        mEdtUserEmail = findViewById(R.id.edtUserNameRegistration);
        mEdtUserPassword = findViewById(R.id.edtUserPasswordRegistration);
        mEdtUserName = findViewById(R.id.edtUserName);
        mEdtUserAddress = findViewById(R.id.edtUserAddress);
        mEdtUserMobileNo = findViewById(R.id.edtUserMobileNo);
        mEdtUserRequirement = findViewById(R.id.edtUserRequirement);


        spinner1 = (Spinner) findViewById(R.id.spinner1);
        mMIlkTypeSpinner = findViewById(R.id.spinnerMilkType);







        String[] arrayItems = {"Select Unit","Ltr/kg","ml/gm"};

        SpinerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, arrayItems);
        spinner1.setAdapter(SpinerAdapter);


        final RequestQueue requestQueue = Volley.newRequestQueue(RegistrationActivity.this);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, registrationUrl1, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {


                System.out.println(response.toString());



                try {
                    if (response.getInt("status") == 1) {

                        JSONArray ja = response.getJSONArray("result");
                        spinnerMilkTyepArray = new String[ja.length()+1];
                        spinnerMilkTyepArray[0] = "Select Milk Type";

                        for (int m =0; m<ja.length(); m++)
                        {
                            JSONObject jo = ja.getJSONObject(m);
                            String name = jo.getString("name");
                            spinnerMilkTyepArray[m+1] = name;

                            mMilkTypeArrayAdapter = new ArrayAdapter<String>(RegistrationActivity.this,
                                    android.R.layout.simple_spinner_dropdown_item, spinnerMilkTyepArray);
                            mMIlkTypeSpinner.setAdapter(mMilkTypeArrayAdapter);
                            mMilkTypeArrayAdapter.notifyDataSetChanged();


                        }

                    }
                    else{
                        Toast.makeText(RegistrationActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(RegistrationActivity.this, "Please Check Credentials", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        requestQueue.add(jsonObjectRequest);





        progressDialog = new ProgressDialog(RegistrationActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);


        mBtnSignup = findViewById(R.id.btnSignUp);

        mBtnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (checkbox_terms.isChecked()) {
                    mCheckBoXTextLayout.setErrorEnabled(false);
                    mUserName = mEdtUserName.getText().toString();
                    mPassword = mEdtUserPassword.getText().toString();
                    mUserAddress = mEdtUserAddress.getText().toString();
                    mFlatNo = mEdtFlatNo.getText().toString();
                    mSocietyName = mEdtSocietyName.getText().toString();
                    mWingName = mEdtWingName.getText().toString();


                    if (!mEdtUserMobileNo.getText().toString().trim().isEmpty()) {
                        mUserMobileNo = mEdtUserMobileNo.getText().toString();
                    } else {
                        mUserMobileNo = "0";
                    }


                    mUserEmail = mEdtUserEmail.getText().toString();
                    if (!mEdtUserRequirement.getText().toString().isEmpty()) {
                        mUserRequirement = Double.parseDouble(mEdtUserRequirement.getText().toString());
                    } else {
                        mUserRequirement = 0.0;
                    }
                    mUnit = spinner1.getSelectedItem().toString();

                    mMilkType = mMIlkTypeSpinner.getSelectedItem().toString();

                    submitForm(mUserEmail, mPassword, mUserName, mUserAddress, mUserMobileNo, mUserRequirement, mUnit, mFlatNo, mSocietyName, mWingName, mMilkType);
                }
                else{
                    mCheckBoXTextLayout.setErrorEnabled(true);
                    mCheckBoXTextLayout.setError("Please Agree Terms And Condition First");
                }
            }

        });


    }


    private boolean checkFirstName(String userName) {

        userName = userName.trim();

        if(userName.trim().isEmpty() || (userName.length() <= 1)){
            mTextInputLayoutUserName.setErrorEnabled( true );
            mTextInputLayoutUserName.setError( "Please Enter Valid Name" );
            return false;
        }
        mTextInputLayoutUserName.setErrorEnabled( false );
        return true;
    }


    private boolean checkSocietyName(String userName) {

        userName = userName.trim();

        if(userName.trim().isEmpty() || (userName.length() <= 1)){
            mTextInputSocietyName.setErrorEnabled( true );
            mTextInputSocietyName.setError( "Please Enter Valid Society Name" );
            return false;
        }
        mTextInputSocietyName.setErrorEnabled( false );
        return true;
    }


    private boolean checkFlatNo(String userName) {

        userName = userName.trim();

        if(userName.trim().isEmpty()){
            mTextInputFlatNo.setErrorEnabled( true );
            mTextInputFlatNo.setError( "Please Enter Valid Flat No" );
            return false;
        }
        mTextInputFlatNo.setErrorEnabled( false );
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

    private boolean checkRequirement(double value)
    {
        if(value <= 0)
        {
            mTextInputLayoutRequirement.setErrorEnabled(true);
            mTextInputLayoutRequirement.setError("Please Enter Valid Requirement");
            return false;
        }
        mTextInputLayoutRequirement.setErrorEnabled(false);
        return true;
    }

    private boolean checkUnit(String unit)
    {
        if(unit.trim().equalsIgnoreCase("Select Unit"))
        {
            mTextInputLayoutUnit.setErrorEnabled(true);
            mTextInputLayoutUnit.setError("Please Select Unit First");
            return false;
        }
        mTextInputLayoutUnit.setErrorEnabled(false);
        return true;
    }

    private boolean checkMilkType(String milkType)
    {
        if(milkType.trim().equalsIgnoreCase("Select Milk Type"))
        {
            mTextInputLayoutMilkType.setErrorEnabled(true);
            mTextInputLayoutMilkType.setError("Please Select Milk Type First");
            return false;
        }
        mTextInputLayoutMilkType.setErrorEnabled(false);
        return true;
    }


    private boolean checkMobile(String mobileNo) {
        if (mobileNo.length() == 10) {
            mTextInputLayoutUserMobileNo.setErrorEnabled(false);
            return true;
        } else if (mobileNo.isEmpty() || mobileNo.length() < 10 || mobileNo.length() > 10) {
            mTextInputLayoutUserMobileNo.setErrorEnabled(true);
            mTextInputLayoutUserMobileNo.setError("Please Enter Valid Mobile No");
            return false;
        }
        return true;
    }


    public void submitForm(String Email,String password,String username,String Address, String mobileNo, double Requirement, String unit, String flatNo, String mSocietyName, String wingName, String mMilkType) {

        if (!checkFirstName(username)) {
            mVibrator.vibrate(1000);
            mEdtUserName.requestFocus();
            return;

        } else if (!checkEmail(Email)) {
            mVibrator.vibrate(1000);
            mEdtUserEmail.requestFocus();
            return;
        } else if (!checkPassword(password)) {
            mVibrator.vibrate(1000);
            mEdtUserPassword.requestFocus();
            return;

        }
        else if (!checkSocietyName(mSocietyName)) {
            mVibrator.vibrate(1000);
            mEdtSocietyName.requestFocus();
            return;

        }
        else if (!checkFlatNo(flatNo)) {
            mVibrator.vibrate(1000);
            mEdtFlatNo.requestFocus();
            return;

        }
        else if (!checkAddress(Address)) {
            mVibrator.vibrate(1000);
            mEdtUserAddress.requestFocus();
            return;

        } else if (!checkMobile(mobileNo)) {
            mVibrator.vibrate(1000);
            mEdtUserMobileNo.requestFocus();
            return;

        }else if (!checkMilkType(mMilkType)) {
            mVibrator.vibrate(1000);
            mMIlkTypeSpinner.requestFocus();
            return;

        }
        else if (!checkRequirement(Requirement)) {
            mVibrator.vibrate(1000);
            mEdtUserRequirement.requestFocus();
            return;

        }else if (!checkUnit(mUnit)) {
            mVibrator.vibrate(1000);
            spinner1.requestFocus();
            return;

        }
        else {
            mTextInputLayoutUserName.setErrorEnabled(false);
            mTextInputLayoutUserPassword.setErrorEnabled(false);
            mTextInputLayoutUserAddress.setErrorEnabled(false);
            mTextInputLayoutUserEmail.setErrorEnabled(false);
            mTextInputLayoutUserMobileNo.setErrorEnabled(false);
            mTextInputLayoutRequirement.setErrorEnabled(false);
            mTextInputLayoutUnit.setErrorEnabled(false);
            mTextInputSocietyName.setErrorEnabled(false);
            mTextInputFlatNo.setErrorEnabled(false);
            mTextInputLayoutMilkType.setErrorEnabled(false);

            progressDialog.show();
            JSONObject params = new JSONObject();
            try {

                params.put("name", username);
                params.put("mblno", mobileNo);
                params.put("address", Address);
                params.put("requirement",Requirement);
                params.put("unit", mUnit);
                params.put("email", Email);
                params.put("paasword", password);
                params.put("socname", mSocietyName);
                params.put("flat", flatNo);
                params.put("wing", wingName);
                params.put("milktype", mMilkType);


            } catch (JSONException e) {
                e.printStackTrace();

            }

            System.out.println(params.toString());



           String registrationUrl = URL.Url_Registration;


            final RequestQueue requestQueue = Volley.newRequestQueue(RegistrationActivity.this);


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, registrationUrl, params, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {


                    System.out.println(response.toString());

                    try {
                        if (response.getInt("status") == 1) {

                            Toast.makeText(RegistrationActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                            progressDialog.dismiss();
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();

                        }
                        else{
                            Toast.makeText(RegistrationActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(RegistrationActivity.this, "Please Check Credentials", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            })
            {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json");
                    return headers;
                }
            };

            requestQueue.add(jsonObjectRequest);




        }
    }



}
