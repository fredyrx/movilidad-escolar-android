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
import com.ramos.fredy.goschool.models.Dependent;
import com.ramos.fredy.goschool.models.School;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sistemas on 03/05/2017.
 */


public class DependentAdapter extends RecyclerView.Adapter<DependentAdapter.ViewHolder> {

    private List<Dependent> lstDependent;
    private onDependentClickListener listener;
    private Context context;

    public DependentAdapter(List<Dependent> lstDependent, onDependentClickListener listener, Context context) {
        this.lstDependent = lstDependent;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_row_dependent, parent, false);

        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Dependent dependent = lstDependent.get(position);

        Glide.with(context)
                .load(dependent.getPhotoUri())
                .centerCrop()
                .crossFade()
                .fallback(R.mipmap.ic_bus_launcher)
                .into(holder.imgLogo);

        holder.txvName.setText(dependent.getName());
        holder.txvAddress.setText(dependent.getHomeAddress());

    }

    @Override
    public int getItemCount() {
        return lstDependent.size();
    }

    public void replaceData(List<Dependent> lstDependent) {
        this.lstDependent = lstDependent;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.img_dependent_photo)
        ImageView imgLogo;
        @BindView(R.id.txv_dependent_name)
        TextView txvName;
        @BindView(R.id.txv_dependent_address)  TextView txvAddress;

        onDependentClickListener listener;

        public ViewHolder(View itemView, onDependentClickListener  listener) {
            super(itemView);
            this.listener = listener;

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int pos = getAdapterPosition();
            Dependent dependent = lstDependent.get(pos);

            listener.onSelectedDepent(dependent);
        }
    }

    public interface onDependentClickListener {

        void onSelectedDepent(Dependent dependent);

    }

}