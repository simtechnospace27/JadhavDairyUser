package simtechnospace.tech.jadhavdairy.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import simtechnospace.tech.jadhavdairy.R;
import simtechnospace.tech.jadhavdairy.pojo_class.UserDetails;

public class ProfileFragment extends Fragment {


    android.support.design.widget.TextInputLayout mTextInputLayoutUserName, mTextInputLayoutUnit,mTextInputLayoutUserEmail, mTextInputLayoutUserAddress, mTextInputLayoutUserMobileNo,mTextInputLayoutRequirement;
    android.support.design.widget.TextInputEditText mEdtUserName,mEdtUserEmail, mEdtUserAddress, mEdtUserRequirement, mEdtUserMobileNo;
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

        mEdtUserEmail = view.findViewById(R.id.edit_tUserNameRegistration);
        mEdtUserName = view.findViewById(R.id.edit_tUserName);
        mEdtUserAddress = view.findViewById(R.id.edit_tUserAddress);
        mEdtUserMobileNo = view.findViewById(R.id.edit_tUserMobileNo);
        mEdtUserRequirement = view.findViewById(R.id.edit_tUserRequirement);

        Spinner spin = (Spinner)view.findViewById(R.id.spinner2);

        ArrayAdapter<String> SpinerAdapter;
        String[] arrayItems = {"Select Unit","Ltr","ml"};

        SpinerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayItems);
        spin.setAdapter(SpinerAdapter);


        //System.out.println(UserDetails.getCustomerName());

        mEdtUserAddress.setText(UserDetails.getUserAddress());
        mEdtUserEmail.setText(UserDetails.getEmailId());
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


        return view;
    };

}
