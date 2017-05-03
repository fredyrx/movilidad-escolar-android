package com.ramos.fredy.goschool.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ramos.fredy.goschool.R;
import com.ramos.fredy.goschool.models.School;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by boticas on 02/05/17.
 */

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.ViewHolder> {

    private List<School> lstSchool;
    private OnSchoolClickListener listener;
    private Context context;

    public SchoolAdapter(List<School> lstSchool, OnSchoolClickListener listener, Context context) {
        this.lstSchool = lstSchool;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_row_school, parent, false);

        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        School school = lstSchool.get(position);

        Glide.with(context)
                .load(school.getUrlLogo())
                .centerCrop()
                .crossFade()
                .fallback(R.mipmap.ic_bus_launcher)
                .into(holder.imgLogo);

        holder.txvName.setText(school.getName());
        holder.txvAddress.setText(school.getAddress());

    }

    @Override
    public int getItemCount() {
        return lstSchool.size();
    }

    public void replaceData(List<School> lstSchool) {
        this.lstSchool = lstSchool;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.img_school_Logo)     ImageView imgLogo;
        @BindView(R.id.txv_school_name)     TextView txvName;
        @BindView(R.id.txv_school_address)  TextView txvAddress;

        OnSchoolClickListener listener;

        public ViewHolder(View itemView, OnSchoolClickListener listener) {
            super(itemView);
            this.listener = listener;

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int pos = getAdapterPosition();
            School school = lstSchool.get(pos);

            listener.onSelectedSchool(school);
        }
    }

    public interface OnSchoolClickListener {

        void onSelectedSchool(School school);

    }

}
