package simtechnospace.tech.jadhavdairy.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import simtechnospace.tech.jadhavdairy.Activity.HomeActivity;
import simtechnospace.tech.jadhavdairy.Dialogs.CustomAlertDialog;
import simtechnospace.tech.jadhavdairy.R;
import simtechnospace.tech.jadhavdairy.pojo_class.URL;
import simtechnospace.tech.jadhavdairy.pojo_class.UserCredentialsAfterLogin;
import simtechnospace.tech.jadhavdairy.pojo_class.UserDetails;

public class HomeFragment extends Fragment {

    UserCredentialsAfterLogin userCredentialsAfterLogin;

    String mUserAddress, mRequirements, mUnit, mDeliveryBoy, mApprovalStatus, mCustomerName, mMobileNo, mEmailId, mUserJoinDate, mMilkType,mAddnRequirement, mAddnUnit, mAddnMilkType, mSocietyName, mWingName, mFlatNo;

    TextView mTextViewUserRequirements, mTextViewApprovalSTatusMsg, mTextViewDeliveryBoyName;
    CardView mCardViewCalender;
    CalendarView mCalenderviewUserDetails;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.home_fragment, container, false);



        userCredentialsAfterLogin = new UserCredentialsAfterLogin(getActivity());

        mTextViewUserRequirements = view.findViewById(R.id.txtUserRequirements);
        mTextViewApprovalSTatusMsg = view.findViewById(R.id.txtUserApprovalStatus);
        mTextViewDeliveryBoyName = view.findViewById(R.id.txtUserAssignedDeliveryBoy);

        mCardViewCalender = view.findViewById(R.id.calenderCard);

        mCalenderviewUserDetails = view.findViewById(R.id.userDeliveryStatusCalender);


        JSONObject params = new JSONObject();
        try {

            params.put("email", userCredentialsAfterLogin.getEmail());


        } catch (JSONException e) {
            e.printStackTrace();

        }

        System.out.println(params.toString());



        String getUserProfileDetailsUrl = URL.mGetProfileDetailsUrl;


        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, getUserProfileDetailsUrl, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                System.out.println(response.toString());

                try {
                    if (response.getInt("status") == 1) {

                        //Toast.makeText(HomeActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();

                        mUserAddress = response.getString("delivery_address");
                        mRequirements = response.getString("requirements");
                        mUnit = response.getString("unit");
                        mDeliveryBoy = response.getString("assigned_delivery_boy");
                        mApprovalStatus = response.getString("approval_status");
                        mCustomerName = response.getString("cutomer_name");
                        mMobileNo = response.getString("mbl_no");
                        mEmailId = response.getString("email");
                        mUserJoinDate = response.getString("join_date");
                        mSocietyName = response.getString("socname");
                        mWingName = response.getString("wingname");
                        mFlatNo = response.getString("flatno");



                        mMilkType = response.getString("milktype");

                        mAddnMilkType = response.getString("additinalmilktype");
                        mAddnRequirement = response.getString("additionalreq");
                        mAddnUnit = response.getString("additionalunit");

                        UserDetails userDetails = new UserDetails(mUserAddress, mRequirements, mUnit, mDeliveryBoy, mApprovalStatus, mCustomerName, mMobileNo, mEmailId, mUserJoinDate, mMilkType, mAddnRequirement, mAddnUnit, mAddnMilkType, mSocietyName, mWingName, mFlatNo);

                        mTextViewUserRequirements.setText(mRequirements+" "+mUnit);


                        if (mApprovalStatus.equalsIgnoreCase("Not Approved By Admin"))
                        {
                            mTextViewApprovalSTatusMsg.setText(mApprovalStatus + "Please contact admin to approve your account...");
                            mCardViewCalender.setVisibility(View.GONE);

                        }
                        else {
                            mTextViewApprovalSTatusMsg.setText(mApprovalStatus);
                        }

                        if (mDeliveryBoy.equalsIgnoreCase("null"))
                        {
                            mTextViewDeliveryBoyName.setText("Delivery Boy Not Assigned Yet, Need to contact admin...");
                        }
                        else{
                            mTextViewDeliveryBoyName.setText(mDeliveryBoy);
                        }


                        Date today = new Date();
                        Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));

                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        String strDate= formatter.format(tomorrow);


                        Date d = formatter.parse(strDate);
                        long milliseconds = d.getTime();

                        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
                        Date d1 = formatter2.parse(mUserJoinDate);
                        //System.out.println(d1);
                        long minMilliSeconds = d1.getTime();

                        //System.out.println(milliseconds);

                        mCalenderviewUserDetails.setMaxDate(milliseconds);
                        mCalenderviewUserDetails.setMinDate(minMilliSeconds);

                        mCalenderviewUserDetails.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                            @Override
                            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                                String date = year+"-"+(month+1)+"-"+dayOfMonth;

                                CustomAlertDialog customAlertDialog = new CustomAlertDialog(getActivity(),date, mEmailId);
                                customAlertDialog.show();


                            }
                        });


                    }
                    else{
                         Toast.makeText(getActivity(), response.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_SHORT).show();


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



        return view;
    }





    }
