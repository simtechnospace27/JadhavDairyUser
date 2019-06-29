package simtechnospace.tech.jadhavdairy.Adapters;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import simtechnospace.tech.jadhavdairy.R;
import simtechnospace.tech.jadhavdairy.pojo_class.BillDetails;

public class BillDetailsAdapter  extends RecyclerView.Adapter<BillDetailsAdapter.MyViewHolder> {

    private ArrayList<BillDetails> mBillDetailsArrayList; // this data structure carries our title and description

    int mPosition;


    public BillDetailsAdapter(ArrayList<BillDetails> billDetailsArrayList) {
        this.mBillDetailsArrayList = billDetailsArrayList;
    }

    @Override
    public BillDetailsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {


        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_activity_billdisplay, parent, false);
        final MyViewHolder mViewHolder = new MyViewHolder(view);


        // inflate your custom row layout here
        return mViewHolder;

    }

    @Override
    public void onBindViewHolder(final BillDetailsAdapter.MyViewHolder holder, int position) {

        mPosition = position;

        if ((position+1) < mBillDetailsArrayList.size()) {
            holder.mTextSrNoDisplay.setText(position + 1 + "");
            holder.mRequirementDisplay.setText(mBillDetailsArrayList.get(position).getRequirements() + " " + mBillDetailsArrayList.get(position).getUnit());
            holder.mDaysDisplay.setText(mBillDetailsArrayList.get(position).getDayCount());

            double actualCost;
            if (mBillDetailsArrayList.get(position).getDayCount().equalsIgnoreCase("Pending Bill")) {
                actualCost = Double.parseDouble(mBillDetailsArrayList.get(position).getPerLiterCost());
            }
            else {
                double requirementInNumber = Double.parseDouble(mBillDetailsArrayList.get(position).getRequirements());
                double daysCountInNumber = Double.parseDouble(mBillDetailsArrayList.get(position).getDayCount());
                double perLiterCost = Double.parseDouble(mBillDetailsArrayList.get(position).getPerLiterCost());

                 actualCost = requirementInNumber * daysCountInNumber * perLiterCost;
            }
            holder.mTotalBillDisplay.setText(actualCost + "");

        }
        else{
            holder.mTextSrNoDisplay.setText("");
            holder.mRequirementDisplay.setText(mBillDetailsArrayList.get(position).getRequirements() + " " + mBillDetailsArrayList.get(position).getUnit());
            holder.mDaysDisplay.setText(mBillDetailsArrayList.get(position).getDayCount());
            holder.mTotalBillDisplay.setText(mBillDetailsArrayList.get(position).getPerLiterCost());


        }

    }


    @Override
    public int getItemCount() {
        return mBillDetailsArrayList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        Context context;
        TextView mTextSrNoDisplay, mRequirementDisplay, mDaysDisplay, mTotalBillDisplay;


        MyViewHolder(View view) {
            super(view);


            context = view.getContext();
            mTextSrNoDisplay = view.findViewById(R.id.srnoDisplay);
            mRequirementDisplay = view.findViewById(R.id.requirementDisplay);
            mDaysDisplay = view.findViewById(R.id.daysDisplay);
            mTotalBillDisplay = view.findViewById(R.id.edtTotalBillDisplay);


        }
    }
}
