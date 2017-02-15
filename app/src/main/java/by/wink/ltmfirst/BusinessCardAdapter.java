package by.wink.ltmfirst;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by amine on 15/02/17.
 */

public class BusinessCardAdapter extends RecyclerView.Adapter<BusinessCardAdapter.BusinessCardVH> {

    private ArrayList<BusinessCard> dataSet = new ArrayList<>();

    public void addBusinessCard(BusinessCard bc){

        dataSet.add(0,bc);
        notifyItemInserted(0);
    }

    public void setDataSet (ArrayList<BusinessCard> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    @Override
    public BusinessCardAdapter.BusinessCardVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_business_card,parent,false);
        return new BusinessCardVH(view);
    }

    @Override
    public void onBindViewHolder(BusinessCardAdapter.BusinessCardVH holder, int position) {

        BusinessCard businessCard = dataSet.get(position);
        holder.nameTv.setText(businessCard.getName());
        holder.emailTv.setText(businessCard.getEmail());
        holder.phoneNumberTv.setText(businessCard.getPhoneNumber());
        holder.courseTv.setText(businessCard.getCourse());
        holder.addressTv.setText(businessCard.getAddress());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    public class BusinessCardVH extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nameTv, phoneNumberTv,emailTv, courseTv, addressTv;
        Button infoBtn;

        public BusinessCardVH(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.business_card_name);
            phoneNumberTv = (TextView) itemView.findViewById(R.id.business_card_phone_number);
            emailTv = (TextView)itemView.findViewById(R.id.business_card_email);
            courseTv = (TextView)itemView.findViewById(R.id.business_card_course);
            addressTv = (TextView)itemView.findViewById(R.id.business_card_address);
            infoBtn = (Button) itemView.findViewById(R.id.business_card_info_btn);
            infoBtn.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            MainActivity.showSnackBar(dataSet.get(getAdapterPosition()).getName());

        }
    }
}
