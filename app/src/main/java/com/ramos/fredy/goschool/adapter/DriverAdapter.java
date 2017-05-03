package com.ramos.fredy.goschool.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ramos.fredy.goschool.R;
import com.ramos.fredy.goschool.models.Driver;

import java.util.List;

import butterknife.ButterKnife;


public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.ViewHolder> {

    private List<Driver> lstDriver;
    private OnDriverClickListener listener;

    public DriverAdapter(List<Driver> lstDriver, OnDriverClickListener listener) {
        this.lstDriver = lstDriver;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_row_driver, parent, false);

        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Driver driver = lstDriver.get(position);

    }

    @Override
    public int getItemCount() {
        return lstDriver.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnDriverClickListener listener;

        public ViewHolder(View itemView, OnDriverClickListener listener) {
            super(itemView);
            this.listener = listener;
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {

            int pos = getAdapterPosition();
            Driver driver = lstDriver.get(pos);
            listener.onSelectedDriver(driver);

        }
    }

    public interface OnDriverClickListener {
        void onSelectedDriver(Driver driver);
    }

}
