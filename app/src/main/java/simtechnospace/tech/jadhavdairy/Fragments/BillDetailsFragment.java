package simtechnospace.tech.jadhavdairy.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import simtechnospace.tech.jadhavdairy.Activity.HomeActivity;
import simtechnospace.tech.jadhavdairy.Adapters.BillDetailsAdapter;
import simtechnospace.tech.jadhavdairy.Dialogs.CustomAlertDialog;
import simtechnospace.tech.jadhavdairy.R;
import simtechnospace.tech.jadhavdairy.pojo_class.BillDetails;
import simtechnospace.tech.jadhavdairy.pojo_class.URL;
import simtechnospace.tech.jadhavdairy.pojo_class.UserCredentialsAfterLogin;
import simtechnospace.tech.jadhavdairy.pojo_class.UserDetails;
import android.net.NetworkInfo;

import static android.app.Activity.RESULT_OK;

public class BillDetailsFragment extends Fragment {

    TextView mEditUserNameinBills, mEditUserBillAddress, mEditBillingDate, mEdtPerLitCharges;
    RecyclerView mRecyclerViewBillDetails;
    BillDetailsAdapter mBillDetailsAdapter;
    ArrayList<BillDetails> mBillDetailsArrayList;

    Button mBtnProceedToPay, mBtnProoceedToPayBheem;

    CardView cardView;

    LinearLayoutManager mLinearLayoutManager;

    EditText mEdtTotalPricePay;

    Fragment h;


    final int UPI_PAYMENT = 0;

    UserCredentialsAfterLogin userCredentialsAfterLogin;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.activity_billdisplay, container, false);
        userCredentialsAfterLogin = new UserCredentialsAfterLogin(getActivity());

        mEditUserNameinBills = view.findViewById(R.id.edit_tUserNameinBills);
        mEditUserBillAddress = view.findViewById(R.id.edit_tUserBillAddress);
        mEditBillingDate = view.findViewById(R.id.edit_tBillingDate);
        mRecyclerViewBillDetails = view.findViewById(R.id.BillPayment);
        mEdtPerLitCharges = view.findViewById(R.id.edit_tPerLitCharges);
        cardView = view.findViewById(R.id.cardBillDetails);

        mBtnProceedToPay = view.findViewById(R.id.btnPay);
        mBtnProceedToPay.setVisibility(View.INVISIBLE);


        mBtnProoceedToPayBheem = view.findViewById(R.id.btnPay1);
        mBtnProoceedToPayBheem.setVisibility(View.INVISIBLE);

        mEdtTotalPricePay = view.findViewById(R.id.edtTotalPricePay);

        mBillDetailsArrayList = new ArrayList<>();

        mBillDetailsAdapter = new BillDetailsAdapter(mBillDetailsArrayList);


        mLinearLayoutManager = new LinearLayoutManager(getActivity());

        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerViewBillDetails.setLayoutManager(mLinearLayoutManager);
        mRecyclerViewBillDetails.setHasFixedSize(true);


        //we can now set adapter to recyclerView;
        mRecyclerViewBillDetails.setAdapter( mBillDetailsAdapter );


        mBillDetailsAdapter.notifyDataSetChanged();




        JSONObject params = new JSONObject();
        try {

            params.put("email", userCredentialsAfterLogin.getEmail());

        } catch (JSONException e) {
            e.printStackTrace();

        }







        String getBillDetailsUrl = URL.mGetBillDetailsUrl;


        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, getBillDetailsUrl, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                try {
                    int status = response.getInt("status");

                    if (status == 1)
                    {


                        String perLitCharge = response.getString("perLitCharge");
                        String billingDate = response.getString("billingDate");
                        String customerName = response.getString("customerName");
                        String address = response.getString("address");



                        mEditBillingDate.setText(billingDate+"");
                        mEdtPerLitCharges.setText("Rs. "+Double.parseDouble(perLitCharge)+" /-");
                        mEditUserNameinBills.setText(customerName);
                        mEditUserBillAddress.setText(address);

                        mBtnProceedToPay.setVisibility(View.VISIBLE);
                        mBtnProoceedToPayBheem.setVisibility(View.VISIBLE);

                        JSONArray jsonArray = response.getJSONArray("requirements");
                        Double totalCost = 0.0;

                        for (int i=0; i< jsonArray.length(); i++)
                        {
                            JSONObject js = jsonArray.getJSONObject(i);

                            String req = js.getString("requirements");
                            String unit = js.getString("unit");
                            String daycount = js.getString("daycount");
                            String fromdate = js.getString("fromdate");
                            String todate = js.getString("todate");

                            if (daycount.equalsIgnoreCase("Pending Bill"))
                            {
                                totalCost = totalCost + Double.parseDouble(js.getString("totalCost"));
                                BillDetails billDetails = new BillDetails(req, unit, daycount, fromdate, todate, js.getString("totalCost"));

                                mBillDetailsArrayList.add(billDetails);

                            }
                            else {


                                Double requir = Double.parseDouble(req);
                                Double days = Double.parseDouble(daycount);
                                Double price = Double.parseDouble(perLitCharge);

                                totalCost = totalCost + (requir * days * price);
                                BillDetails billDetails = new BillDetails(req, unit, daycount, fromdate, todate, perLitCharge);

                                mBillDetailsArrayList.add(billDetails);

                            }



                            mBillDetailsAdapter.notifyDataSetChanged();

                        }

                        BillDetails billDetails = new BillDetails("", "","Total","","",totalCost+"");

                        mEdtTotalPricePay.setText(totalCost+"");

                        mBillDetailsArrayList.add(billDetails);

                        mBillDetailsAdapter.notifyDataSetChanged();

                    }
                    else{

                        cardView.setVisibility(View.GONE);

                        String perLitCharge = response.getString("perLitCharge");
                        String billingDate = response.getString("billingDate");
                        String customerName = response.getString("customerName");
                        String address = response.getString("address");

                        String msg = response.getString("msg");



                        mEditBillingDate.setText(msg);
                        mEdtPerLitCharges.setText("Rs. - "+Double.parseDouble(perLitCharge)+" /-");
                        mEditUserNameinBills.setText(customerName);
                        mEditUserBillAddress.setText(address);



                    }


                } catch (JSONException e) {
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





        mBtnProceedToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //   String amount = mEdtTotalPricePay.getText().toString();
               String amount = "2";
                String note = "Bill Payment";
                String name = "Imran Gulab Mulani";
                String upiId = "9881533090@upi";

                payUsingUpi(amount, upiId, name, note);

            }
        });



        mBtnProoceedToPayBheem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String amount = mEdtTotalPricePay.getText().toString();
                String amount = "2";
                String note = "Bill Payment";
                String name = "Imran Gulab Mulani";
                String upiId = "9881533090@upi";

                payUsingUpiBheem(amount, upiId, name, note);

            }
        });



        return view;
    }



    void payUsingUpi(String amount, String upiId, String name, String note) {

        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                .build();


        //Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
        //upiPayIntent.setData(uri);

        // will always show a dialog to user to choose an app
       // Intent chooser = Intent.createChooser(upiPayIntent, "Pay with ");

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        intent.setPackage("com.google.android.apps.nbu.paisa.user");

        if(isPackageInstalled("com.google.android.apps.nbu.paisa.user", getActivity().getPackageManager()))
        {
            startActivityForResult(intent, UPI_PAYMENT);
        }
        else{
            Toast.makeText(getActivity(), "UPI App is Not Installed, Please instal first", Toast.LENGTH_SHORT).show();
        }

        // check if intent resolves
//        if(null != chooser.resolveActivity(getActivity().getPackageManager())) {
//            //System.out.println(getActivity().getPackageManager());
//            startActivityForResult(chooser, UPI_PAYMENT);
//        } else {
//            Toast.makeText(getActivity(),"No UPI app found, please install one to continue",Toast.LENGTH_SHORT).show();
//        }


    }





    void payUsingUpiBheem(String amount, String upiId, String name, String note) {

        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                .build();


        //Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
        //upiPayIntent.setData(uri);

        // will always show a dialog to user to choose an app
        // Intent chooser = Intent.createChooser(upiPayIntent, "Pay with ");

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        intent.setPackage("in.org.npci.upiapp");


        if(isPackageInstalled("in.org.npci.upiapp", getActivity().getPackageManager()))
        {
            startActivityForResult(intent, UPI_PAYMENT);
        }
        else{
            Toast.makeText(getActivity(), "UPI App is Not Installed, Please instal first", Toast.LENGTH_SHORT).show();
        }


        // check if intent resolves
//        if(null != chooser.resolveActivity(getActivity().getPackageManager())) {
//            //System.out.println(getActivity().getPackageManager());
//            startActivityForResult(chooser, UPI_PAYMENT);
//        } else {
//            Toast.makeText(getActivity(),"No UPI app found, please install one to continue",Toast.LENGTH_SHORT).show();
//        }


    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                    if (data != null) {
                        String trxt = data.getStringExtra("response");
                        Log.d("UPI", "onActivityResult: " + trxt);
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add(trxt);
                        upiPaymentDataOperation(dataList);
                    } else {
                        Log.d("UPI", "onActivityResult: " + "Return data is null");
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("nothing");
                        upiPaymentDataOperation(dataList);
                    }
                } else {
                    Log.d("UPI", "onActivityResult: " + "Return data is null"); //when user simply back without payment
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    upiPaymentDataOperation(dataList);
                }
                break;
        }
    }



    private void upiPaymentDataOperation(ArrayList<String> data) {
        if (isConnectionAvailable(getActivity())) {
            String str = data.get(0);
            Log.d("UPIPAY", "upiPaymentDataOperation: "+str);
            String paymentCancel = "";
            if(str == null) str = "discard";
            String status = "";
            String approvalRefNo = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                System.out.println(equalStr[0]+" = size = "+equalStr.length);
                if(equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    }
                    else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRefNo = equalStr[1];
                    }

                    System.out.println("tid = "+approvalRefNo);
                }
                else {
                    paymentCancel = "Payment cancelled by user.";
                }
            }

            if (status.equals("success")) {
                //Code to handle successful transaction here.
                Toast.makeText(getActivity(), "Transaction successful.", Toast.LENGTH_SHORT).show();
                Log.d("UPI", "responseStr: "+approvalRefNo);


                String saveBillPaymentStatusUrl = URL.mGetBillPaidDataSaveUrl;


                JSONObject params = new JSONObject();
                try {

                    params.put("email", userCredentialsAfterLogin.getEmail());
                    params.put("bill_date", mEditBillingDate.getText().toString());
                    params.put("amount_paid", mEdtTotalPricePay.getText().toString());
                    params.put("transactionId", approvalRefNo);

                    System.out.println(params.toString());


                } catch (JSONException e) {
                    e.printStackTrace();

                }





                final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

                h = this;

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, saveBillPaymentStatusUrl, params, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {


                        System.out.println(response.toString());

                        try {
                            if (response.getInt("status") == 1) {
                                Toast.makeText(getActivity(), response.getString("msg"), Toast.LENGTH_SHORT).show();


                             //   FragmentManager fragmentManager = getFragmentManager();

//                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                                    fragmentManager.beginTransaction().detach(h).commitNow();
//                                    fragmentManager.beginTransaction().attach(h).commitNow();
//                                } else {
//                                    fragmentManager.beginTransaction().detach(h).attach(h).commit();
//                                }
//

                                Intent intent = new Intent(getActivity(), HomeActivity.class);
                                startActivity(intent);
                                getActivity().finish();

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




            }
            else if("Payment cancelled by user.".equals(paymentCancel)) {
                Toast.makeText(getActivity(), "Payment cancelled by user.", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getActivity(), "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
        }
    }


    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }



    private boolean isPackageInstalled(String packageName, PackageManager packageManager) {

        boolean found = true;

        try {

            packageManager.getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {

            found = false;
        }

        return found;
    }


}
