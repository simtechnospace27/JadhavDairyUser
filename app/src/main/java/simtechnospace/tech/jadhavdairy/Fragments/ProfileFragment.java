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


    android.support.design.widget.TextInputLayout mTextInputLayoutUserName,mTextInputLayoutOldPassword,mTextInputLayoutNewPassword, mTextInputLayoutUnit,mTextInputLayoutUserEmail, mTextInputLayoutUserAddress, mTextInputLayoutUserMobileNo,mTextInputLayoutRequirement;
    android.support.design.widget.TextInputEditText mEdtUserName,mEdtUserEmail, mEdtUserAddress, mEdtUserRequirement, mEdtUserMobileNo,mEdtUserOldPassword,mEdtUserNewPassword;
    Button mBtnSave;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        mTextInputLayoutUserEmail =view.findViewById(R.id.txtUserNameRegistration);
        mTextInputLayoutUserName = view.findViewById(R.id.txtUserName);
        mTextInputLayoutUserAddress = view.findViewById(R.id.txtUserAddress);
        mTextInputLayoutUserMobileNo = view.findViewById(R.id.txtMobileNo);
        mTextInputLayoutRequirement = view.findViewById(R.id.txtRequirement);
        mTextInputLayoutUnit = view.findViewById(R.id.txtUnit);
        mTextInputLayoutOldPassword = view.findViewById(R.id.txtUserOldPassword);
        mTextInputLayoutNewPassword = view.findViewById(R.id.txtUserNewPassword);

        mEdtUserEmail = view.findViewById(R.id.edit_tUserNameRegistration);



        mEdtUserName = view.findViewById(R.id.edit_tUserName);
        mEdtUserAddress = view.findViewById(R.id.edit_tUserAddress);
        mEdtUserMobileNo = view.findViewById(R.id.edit_tUserMobileNo);
        mEdtUserRequirement = view.findViewById(R.id.edit_tUserRequirement);
        mEdtUserOldPassword = view.findViewById(R.id.edit_tUserOldPassword);
        mEdtUserNewPassword = view.findViewById(R.id.edit_tUserNewPassword);

        final Spinner spin = (Spinner)view.findViewById(R.id.spinner2);
        mBtnSave = view.findViewById(R.id.btnSave);

        ArrayAdapter<String> SpinerAdapter;
        String[] arrayItems = {"Select Unit","Ltr","ml"};

        SpinerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayItems);
        spin.setAdapter(SpinerAdapter);


        //System.out.println(UserDetails.getCustomerName());

        mEdtUserAddress.setText(UserDetails.getUserAddress());
        mEdtUserEmail.setText(UserDetails.getEmailId());

        mEdtUserEmail.setEnabled(false);

        mEdtUserMobileNo.setText(UserDetails.getMobileNo());
        mEdtUserName.setText(UserDetails.getCustomerName());
        mEdtUserRequirement.setText(UserDetails.getRequirements());
        //spin.setPrompt(UserDetails.getUnit());

        if(UserDetails.getUnit().equalsIgnoreCase("Ltr")) {
            spin.setSelection(1);
        }
        else if(UserDetails.getUnit().equalsIgnoreCase("ml")) {
            spin.setSelection(2);
        }
        else{
            spin.setSelection(0);
        }

        final String mUnitChange = spin.getSelectedItem().toString();
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UpdateUrl = URL.mUpdateUrl;

                final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

                JSONObject params = new JSONObject();
                try {

                    String userName = mEdtUserName.getText().toString();

                    System.out.println(userName);

                    params.put("userName",  userName);
                    params.put("mobile", mEdtUserMobileNo.getText().toString());
                    params.put("address", mEdtUserAddress.getText().toString());
                    params.put("requirement",mEdtUserRequirement.getText().toString());
                    params.put("unit", mUnitChange);
                    params.put("email", mEdtUserEmail.getText().toString());
                    params.put("oldpassword", mEdtUserOldPassword.getText().toString());
                    params.put("newpassword",mEdtUserNewPassword.getText().toString());

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

                        Toast.makeText(getActivity(), "Please Check Email or Old Password", Toast.LENGTH_SHORT).show();


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
        });
        return view;
    };

}
