package simtechnospace.tech.jadhavdairy.Dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import simtechnospace.tech.jadhavdairy.R;
import simtechnospace.tech.jadhavdairy.pojo_class.URL;
import simtechnospace.tech.jadhavdairy.pojo_class.UserCredentialsAfterLogin;
import simtechnospace.tech.jadhavdairy.pojo_class.UserDetails;

public class CustomAlertDialog extends Dialog{

    public Context c;
    TextInputEditText mEditDate, mEditUserCardRequirement, mEditDeliveryStatus, edit_tUserCardRequirementAddn;
    Button mBtnOK;
    Spinner mSpinner, mMIlkTypeSpinner, mMilkTypeAddnSpinner, spinnerAddn;

    String mDetailsUrl, mUpdateDetailsUrl;

    TextInputLayout txtUserCardRequirement, txtUnit, txtMilkType;

    int mShowType;

    String mDate,mRequirements,mDeliveryStatus,mEmailId, mUnit;

    UserCredentialsAfterLogin userCredentialsAfterLogin;

    Vibrator mVibrator;

    ArrayAdapter<String> mMilkTypeArrayAdapter;
    String[] spinnerMilkTyepArray;

    CheckBox noMilkCheckBox;
    LinearLayout hideOnNoMilk,hideDateOptions;

    TextView txtFromDate;
    TextInputLayout txtUptoDate;
    TextInputEditText edit_txtUpToDate;
    DatePickerDialog.OnDateSetListener date;
    Calendar myCalendar;


    public CustomAlertDialog(Context a, String date, String emailid) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.mDate = date;
        this.mEmailId = emailid;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.delivery_dialog_box);

        noMilkCheckBox = findViewById(R.id.noMilkCheckBox);
        hideOnNoMilk = findViewById(R.id.hideOnNoMilk);
        hideDateOptions = findViewById(R.id.hideDateOptions);

        txtFromDate = findViewById(R.id.txtFromDate);
        txtUptoDate = findViewById(R.id.txtUptoDate);
        edit_txtUpToDate = findViewById(R.id.edit_txtUpToDate);


        txtUserCardRequirement = findViewById(R.id.txtUserCardRequirement);
        txtUnit = findViewById(R.id.txtUnit);
        txtMilkType = findViewById(R.id.txtMilkType);
        edit_tUserCardRequirementAddn = findViewById(R.id.edit_tUserCardRequirementAddn);

        mMIlkTypeSpinner = findViewById(R.id.spinnerMilkType);
        spinnerAddn = findViewById(R.id.spinnerAddn);
        mMilkTypeAddnSpinner = findViewById(R.id.spinnerMilkTypeAddn);

        mDetailsUrl = URL.mDatewiseDetaislUrl;
        mUpdateDetailsUrl = URL.mUpdateDailyDetails;

        mEditDate = findViewById(R.id.edit_tDate);
        mEditDeliveryStatus = findViewById(R.id.edit_tDeliveryStatus);
        mEditUserCardRequirement = findViewById(R.id.edit_tUserCardRequirement);
        mSpinner = findViewById(R.id.spinner2);

        mEditDate.setText(mDate);
        txtFromDate.setText(txtFromDate.getText().toString()+" "+mDate);
        hideDateOptions.setVisibility(View.GONE);
        //mEditUserCardRequirement.setText(mRequirements);
        //mEditDeliveryStatus.setText(mDeliveryStatus);

        ArrayAdapter<String> SpinerAdapter;
        String[] arrayItems = {"Select Unit","Ltr","ml"};

        SpinerAdapter = new ArrayAdapter<String>(c, android.R.layout.simple_spinner_dropdown_item, arrayItems);
        mSpinner.setAdapter(SpinerAdapter);
        spinnerAddn.setAdapter(SpinerAdapter);


        mVibrator = (Vibrator) c.getSystemService(Context.VIBRATOR_SERVICE);



         myCalendar = Calendar.getInstance();

         date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        edit_txtUpToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(c, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });






        final RequestQueue requestQueue1 = Volley.newRequestQueue(c);
        String registrationUrl1 = URL.url_milk_type;

        noMilkCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    hideOnNoMilk.setVisibility(View.GONE);
                    hideDateOptions.setVisibility(View.VISIBLE);
                }
                else{
                    hideOnNoMilk.setVisibility(View.VISIBLE);
                    hideDateOptions.setVisibility(View.GONE);
                }
            }
        });


        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST, registrationUrl1, null, new Response.Listener<JSONObject>() {


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

                            mMilkTypeArrayAdapter = new ArrayAdapter<String>(c,
                                    android.R.layout.simple_spinner_dropdown_item, spinnerMilkTyepArray);
                            mMIlkTypeSpinner.setAdapter(mMilkTypeArrayAdapter);
                            mMilkTypeAddnSpinner.setAdapter(mMilkTypeArrayAdapter);
                            mMilkTypeArrayAdapter.notifyDataSetChanged();


                        }



                    }
                    else{
                        Toast.makeText(c, response.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(c, "Please Check Credentials", Toast.LENGTH_SHORT).show();


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

        requestQueue1.add(jsonObjectRequest1);





        try {

            Date today = new Date();

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String strDate= formatter.format(today);
            Date d = formatter.parse(strDate);
            long milliseconds = d.getTime();


            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = formatter2.parse(mDate);
            long minMilliSeconds = d1.getTime();

            System.out.println(milliseconds+"=>"+minMilliSeconds);
            System.out.println(strDate+"=>"+mDate);

            if(milliseconds < minMilliSeconds)
            {
                mShowType = 1;
            }
            else
            {
                mShowType = 0;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (mShowType == 1)
        {
            mEditDate.setEnabled(false);
            mEditDeliveryStatus.setEnabled(false);
            mEditUserCardRequirement.setText("");
            mSpinner.setSelection(0);
        }
        else{
            mEditDeliveryStatus.setEnabled(false);
            mEditDate.setEnabled(false);
            mEditUserCardRequirement.setEnabled(false);
            mSpinner.setEnabled(false);
        }


        userCredentialsAfterLogin = new UserCredentialsAfterLogin(c);


        JSONObject params = new JSONObject();
        try {

            params.put("email", userCredentialsAfterLogin.getEmail());
            params.put("date", mDate);


        } catch (JSONException e) {
            e.printStackTrace();

        }



        final RequestQueue requestQueue = Volley.newRequestQueue(c);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, mDetailsUrl, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                System.out.println(response.toString());

                try {
                    if (response.getInt("status") == 1) {



                        mRequirements = response.getString("requirements");
                        mUnit = response.getString("unit");
                        String dst = response.getString("dstatus");

                        mEditDeliveryStatus.setText(dst);

                        if (mShowType == 1)
                        {
                            mEditDate.setEnabled(false);
                            mEditDeliveryStatus.setEnabled(false);
                        }
                        else{
                            mEditUserCardRequirement.setText(mRequirements);

                            if (mUnit.equalsIgnoreCase("ml"))
                            {
                                mSpinner.setSelection(2);
                            }
                            else if(mUnit.equalsIgnoreCase("Ltr"))
                            {
                                mSpinner.setSelection(1);
                            }
                            else{
                                mSpinner.setSelection(0);
                            }
                        }




                    }
                    else{
                        //Toast.makeText(c, response.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(c, "Please Check Internet Connection", Toast.LENGTH_SHORT).show();


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







        mBtnOK = findViewById(R.id.btnOK);

        mBtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String mRequirementsUpdate = mEditUserCardRequirement.getText().toString();
                String mUnitUpdate = mSpinner.getSelectedItem().toString();
                String mDateUpdate = mDate;
                String milkType= mMIlkTypeSpinner.getSelectedItem().toString();

                String addReq = edit_tUserCardRequirementAddn.getText().toString();
                String addUnit = spinnerAddn.getSelectedItem().toString();
                String addMilkType = mMilkTypeAddnSpinner.getSelectedItem().toString();

             if (mUnitUpdate.equalsIgnoreCase("Select Unit"))
             {
                 txtUnit.setErrorEnabled(true);
                 txtUnit.setError("Please Select Unit First");
             }
             else if(milkType.equalsIgnoreCase("Select Milk Type"))
             {
                 txtMilkType.setErrorEnabled(true);
                 txtMilkType.setError("Please Select Milk Type");
             }
             else {
                 txtUnit.setErrorEnabled(false);
                 txtMilkType.setErrorEnabled(false);
                 submitForm(mRequirementsUpdate, mUnitUpdate, milkType, mShowType, mDateUpdate, addReq, addUnit, addMilkType);
             }

            }
        });




    }


    private boolean checkUnit(String unit)
    {
        System.out.println(unit);
        if(unit.trim().equalsIgnoreCase("Select Unit"))
        {
            txtUnit.setErrorEnabled(true);
            txtUnit.setError("Please Select Unit First");
            mVibrator.vibrate(1000);
            return false;
        }
        txtUnit.setErrorEnabled(false);
        return true;
    }

    private boolean checkRequirement(String requirements) {
        if(requirements.trim().equalsIgnoreCase(""))
        {
            txtUserCardRequirement.setErrorEnabled(true);
            txtUserCardRequirement.setError("Please Enter Requirement");
            return false;
        }
        txtUserCardRequirement.setErrorEnabled(false);
        return true;
    }



    public void submitForm(String requirements,String unit, String milkType, int showType, String date, String addReq, String addUnit, String addMilkType)
    {

        if (!checkRequirement(requirements)) {
            mVibrator.vibrate(1000);
            mEditUserCardRequirement.requestFocus();
            return;

        }else if (!checkUnit(mUnit)) {
            mVibrator.vibrate(1000);
            mSpinner.requestFocus();
            return;

        }
        else {
            txtUserCardRequirement.setErrorEnabled(false);
            txtUnit.setErrorEnabled(false);




            if (showType == 1)
            {

                JSONObject params = new JSONObject();
                try {

                    params.put("email", userCredentialsAfterLogin.getEmail());
                    params.put("date", date);
                    params.put("requirement",requirements );
                    params.put("unit", unit);
                    params.put("milktype", milkType);
                    params.put("addReq", addReq);
                    params.put("addUnit", addUnit);
                    params.put("addMilkType", addMilkType);

                    System.out.println(params);


                } catch (JSONException e) {
                    e.printStackTrace();

                }




                final RequestQueue requestQueue1 = Volley.newRequestQueue(c);


                JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST, mUpdateDetailsUrl, params, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {


                        System.out.println(response.toString());

                        try {
                            if (response.getInt("status") == 1) {
                                Toast.makeText(c, response.getString("msg"), Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(c, response.getString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(c, "Please Check Internet Connection", Toast.LENGTH_SHORT).show();


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

                requestQueue1.add(jsonObjectRequest1);

                dismiss();

            }
            else{
                dismiss();
            }






        }





    }


    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edit_txtUpToDate.setText(sdf.format(myCalendar.getTime()));

    }



    }