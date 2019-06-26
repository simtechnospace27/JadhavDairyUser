package simtechnospace.tech.jadhavdairy.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import simtechnospace.tech.jadhavdairy.R;

public class CustomAlertDialog extends Dialog{

    public Context c;
    TextInputEditText mEditDate, mEditUserCardRequirement, mEditDeliveryStatus;
    Button mBtnOK;

    int mShowType;

    String mDate,mRequirements,mDeliveryStatus;

    public CustomAlertDialog(Context a, String date, String requirements, String deliveryStatus) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.mDate = date;
        this.mRequirements = requirements;
        this.mDeliveryStatus = deliveryStatus;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.delivery_dialog_box);


        mEditDate = findViewById(R.id.edit_tDate);
        mEditDeliveryStatus = findViewById(R.id.edit_tDeliveryStatus);
        mEditUserCardRequirement = findViewById(R.id.edit_tUserCardRequirement);

        mEditDate.setText(mDate);
        mEditUserCardRequirement.setText(mRequirements);
        mEditDeliveryStatus.setText(mDeliveryStatus);



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
        }
        else{
            mEditDeliveryStatus.setEnabled(false);
            mEditDate.setEnabled(false);
            mEditUserCardRequirement.setEnabled(false);
        }




        mBtnOK = findViewById(R.id.btnOK);

        mBtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                dismiss();


            }
        });




    }



}