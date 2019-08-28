package simtechnospace.tech.jadhavdairy.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

import java.util.HashMap;
import java.util.Map;

import simtechnospace.tech.jadhavdairy.Activity.MainActivity;
import simtechnospace.tech.jadhavdairy.Activity.RegistrationActivity;
import simtechnospace.tech.jadhavdairy.R;
import simtechnospace.tech.jadhavdairy.pojo_class.URL;
import simtechnospace.tech.jadhavdairy.pojo_class.UserDetails;

public class ProfileFragment extends Fragment {


    android.support.design.widget.TextInputLayout mTextInputLayoutUserName,mTextInputLayoutOldPassword,mTextInputLayoutNewPassword, mTextInputLayoutUnit,mTextInputLayoutUserEmail, mTextInputLayoutUserAddress, mTextInputLayoutUserMobileNo,mTextInputLayoutRequirement, mTextInputLayoutMilkType, mTextInputSocietyName, mTextInputWingName, mTextInputFlatNo, mTextInputLayoutAddnMilkType;
    android.support.design.widget.TextInputEditText mEdtUserName,mEdtUserEmail, mEdtUserAddress, mEdtUserRequirement, mEdtUserMobileNo,mEdtUserOldPassword,mEdtUserNewPassword,mEdtAddnRequirement, mEdtSocietyName, mEdtWingName, mEdtFlatNo;;
    Button mBtnSave;
    String mUnitChange, mSocietyName, mWingName, mFlatNo, mMilkType, mUnitChandgeAddn, mMilkTypeAddn;
    ArrayAdapter<String> SpinerAdapter, mMilkTypeArrayAdapter;
    String[] spinnerMilkTyepArray;

    private Spinner spinner1, mMIlkTypeSpinner,spinner2,mMilkTypeAddnSpinner;
    int pos,pos1;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        pos = 0;pos1 = 0;

        mTextInputLayoutUserEmail =view.findViewById(R.id.txtUserNameRegistration);
        mTextInputLayoutUserName = view.findViewById(R.id.txtUserName);
        mTextInputLayoutUserAddress = view.findViewById(R.id.txtUserAddress);
        mTextInputLayoutUserMobileNo = view.findViewById(R.id.txtMobileNo);
        mTextInputLayoutRequirement = view.findViewById(R.id.txtRequirement);
        mTextInputLayoutUnit = view.findViewById(R.id.txtUnit);
        mTextInputLayoutOldPassword = view.findViewById(R.id.txtUserOldPassword);
        mTextInputLayoutNewPassword = view.findViewById(R.id.txtUserNewPassword);
        mTextInputLayoutAddnMilkType = view.findViewById(R.id.txtAddnUnit);

        mEdtUserEmail = view.findViewById(R.id.edit_tUserNameRegistration);

        String registrationUrl1 = URL.url_milk_type;

        mTextInputLayoutMilkType = view.findViewById(R.id.textInputLayoutMilkType);



        mEdtSocietyName = view.findViewById(R.id.edtUserSocietyName);
        mEdtWingName = view.findViewById(R.id.edtUserWingName);
        mEdtFlatNo = view.findViewById(R.id.edtFlatNo);

        mTextInputFlatNo = view.findViewById(R.id.textInputLayoutFlatNo);
        mTextInputWingName = view.findViewById(R.id.textInputLayoutWingName);
        mTextInputSocietyName = view.findViewById(R.id.textInputLayoutSocietyName);





        mEdtUserName = view.findViewById(R.id.edit_tUserName);
        mEdtUserAddress = view.findViewById(R.id.edit_tUserAddress);
        mEdtUserMobileNo = view.findViewById(R.id.edit_tUserMobileNo);
        mEdtUserRequirement = view.findViewById(R.id.edit_tUserRequirement);
        mEdtUserOldPassword = view.findViewById(R.id.edit_tUserOldPassword);
        mEdtUserNewPassword = view.findViewById(R.id.edit_tUserNewPassword);
        mEdtAddnRequirement = view.findViewById(R.id.edit_tUserAddnRequirement);


        mEdtSocietyName.setText(UserDetails.getmSocietyName());
        mEdtWingName.setText(UserDetails.getmWingName());
        mEdtFlatNo.setText(UserDetails.getmFlatNo());
        mEdtAddnRequirement.setText(UserDetails.getmAddnRequirement());


        spinner1 = (Spinner)view.findViewById(R.id.spinner2);
        mMIlkTypeSpinner = view.findViewById(R.id.spinnerMilkType);

        spinner2 = (Spinner)view.findViewById(R.id.spinnerAddn2);
        mMilkTypeAddnSpinner = view.findViewById(R.id.spinnerAddnMilkType);


        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());


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

                            mMilkTypeArrayAdapter = new ArrayAdapter<String>(getActivity(),
                                    android.R.layout.simple_spinner_dropdown_item, spinnerMilkTyepArray);
                            mMIlkTypeSpinner.setAdapter(mMilkTypeArrayAdapter);
                            mMilkTypeAddnSpinner.setAdapter(mMilkTypeArrayAdapter);
                            mMilkTypeArrayAdapter.notifyDataSetChanged();




                            if (UserDetails.getmMilkType().equalsIgnoreCase(name))
                            {
                                pos = m+1;
                            }

                            if (m == (ja.length() -1))
                            {
                                mMIlkTypeSpinner.setSelection(pos);
                            }


                            if (UserDetails.getmAddnMilkType().equalsIgnoreCase(name))
                            {
                                pos1 = m+1;
                            }

                            if (m == (ja.length() -1))
                            {
                                mMilkTypeAddnSpinner.setSelection(pos1);
                            }
                        }



                    }
                    else{
                        Toast.makeText(getActivity(), response.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), "Please Check Credentials", Toast.LENGTH_SHORT).show();


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



        mBtnSave = view.findViewById(R.id.btnSave);

        String[] arrayItems = {"Select Unit","Ltr","ml"};

        SpinerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayItems);
        spinner1.setAdapter(SpinerAdapter);

        spinner2.setAdapter(SpinerAdapter);

        //System.out.println(UserDetails.getCustomerName());

        mEdtUserAddress.setText(UserDetails.getUserAddress());
        mEdtUserEmail.setText(UserDetails.getEmailId());

        mEdtUserEmail.setEnabled(false);

        mEdtUserMobileNo.setText(UserDetails.getMobileNo());
        mEdtUserName.setText(UserDetails.getCustomerName());
        mEdtUserRequirement.setText(UserDetails.getRequirements());
        mEdtAddnRequirement.setText(UserDetails.getmAddnRequirement());
        //spin.setPrompt(UserDetails.getUnit());

        if(UserDetails.getmUnit().equalsIgnoreCase("Ltr")) {
            spinner1.setSelection(1);
            mUnitChange = spinner1.getSelectedItem().toString();
        }
        else if(UserDetails.getmUnit().equalsIgnoreCase("ml")) {
            spinner1.setSelection(2);
            mUnitChange = spinner1.getSelectedItem().toString();
        }
        else{
            spinner1.setSelection(0);
            mUnitChange = spinner1.getSelectedItem().toString();
        }




        if(UserDetails.getmAddnUnit().equalsIgnoreCase("Ltr")) {
            spinner2.setSelection(1);
            mUnitChandgeAddn = spinner2.getSelectedItem().toString();
        }
        else if(UserDetails.getmUnit().equalsIgnoreCase("ml")) {
            spinner2.setSelection(2);
            mUnitChandgeAddn = spinner2.getSelectedItem().toString();
        }
        else{
            spinner2.setSelection(0);
            mUnitChandgeAddn = spinner2.getSelectedItem().toString();
        }

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UpdateUrl = URL.mUpdateUrl;

                final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                mUnitChange = spinner1.getSelectedItem().toString();
                mUnitChandgeAddn = spinner2.getSelectedItem().toString();

                mFlatNo = mEdtFlatNo.getText().toString();
                mSocietyName = mEdtSocietyName.getText().toString();
                mWingName = mEdtWingName.getText().toString();
                mMilkType = mMIlkTypeSpinner.getSelectedItem().toString();
                mMilkTypeAddn = mMilkTypeAddnSpinner.getSelectedItem().toString();


                if (!mEdtAddnRequirement.getText().toString().isEmpty() && mUnitChandgeAddn.equalsIgnoreCase("Select Unit")) {

                    mTextInputLayoutAddnMilkType.setErrorEnabled(true);
                    mTextInputLayoutAddnMilkType.setError("Please Select Unit");

                } else {


                    JSONObject params = new JSONObject();
                    try {

                        String userName = mEdtUserName.getText().toString();

                        params.put("userName", userName);
                        params.put("mobile", mEdtUserMobileNo.getText().toString());
                        params.put("address", mEdtUserAddress.getText().toString());
                        params.put("requirement", mEdtUserRequirement.getText().toString());
                        params.put("unit", mUnitChange);
                        params.put("email", mEdtUserEmail.getText().toString());
                        params.put("oldpassword", mEdtUserOldPassword.getText().toString());
                        params.put("newpassword", mEdtUserNewPassword.getText().toString());
                        params.put("socname", mSocietyName);
                        params.put("flat", mFlatNo);
                        params.put("wing", mWingName);
                        params.put("milktype", mMilkType);
                        params.put("additionalMilkType", mMilkTypeAddn);
                        params.put("additionalRequirement", mEdtAddnRequirement.getText().toString());
                        params.put("additionalUnit", mUnitChandgeAddn);

                        System.out.println(params);


                    } catch (JSONException e) {
                        e.printStackTrace();

                    }


                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, UpdateUrl, params, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {


                            System.out.println(response.toString());

                            try {
                                if (response.getInt("status") == 1) {

                                    Toast.makeText(getActivity(), response.getString("msg"), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity(), response.getString("msg"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(getActivity(), "Please Check Email or Old Password", Toast.LENGTH_SHORT).show();


                        }
                    }) {
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
        });
        return view;
    };

}
