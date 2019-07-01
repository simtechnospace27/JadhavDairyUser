package simtechnospace.tech.jadhavdairy.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import simtechnospace.tech.jadhavdairy.R;
import simtechnospace.tech.jadhavdairy.pojo_class.UserCredentialsAfterLogin;

public class NotApprovedActivity extends AppCompatActivity {

    UserCredentialsAfterLogin userCredentialsAfterLogin;

    String mUserAddress, mRequirements, mUnit, mDeliveryBoy, mApprovalStatus, mCustomerName, mMobileNo, mEmailId, mUserJoinDate;

    TextView mTextViewUserRequirements, mTextViewApprovalSTatusMsg, mTextViewDeliveryBoyName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notapproved);

        userCredentialsAfterLogin = new UserCredentialsAfterLogin(this);

        mTextViewUserRequirements = findViewById(R.id.txtUserRequirementsNA);
        mTextViewApprovalSTatusMsg = findViewById(R.id.txtUserApprovalStatusNA);
        mTextViewDeliveryBoyName =  findViewById(R.id.txtUserAssignedDeliveryBoyNA);

    }
}