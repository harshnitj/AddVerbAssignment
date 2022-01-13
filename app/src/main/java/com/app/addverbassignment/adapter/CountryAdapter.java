package com.app.addverbassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.addverbassignment.R;
import com.app.addverbassignment.modal.CountryModal;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Map;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    private ArrayList<CountryModal> countryList;
    private Context context;

    public CountryAdapter(ArrayList<CountryModal> countryList, Context context) {
        this.countryList = countryList;
        this.context = context;
    }


    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        CountryModal countryModal = countryList.get(position);
        String borders = "";
        if (countryModal.borders != null)
            for (String border : countryModal.borders)
                borders = border + "  " + borders;
        String languages="";
        if(countryModal.languages!=null)
        {
            Map<String,String> lang=countryModal.languages;
            for(Map.Entry<String, String> entry: lang.entrySet()) {
                languages=languages+"  "+entry.getValue();
            }
        }

        holder.countryName.setText(countryModal.name.common);
        holder.countryRegion.setText(countryModal.region);
        holder.countrySubRegion.setText(countryModal.subregion);
        holder.countryPopulation.setText(countryModal.population + "");
        holder.countryBorders.setText(borders);
        holder.countryLanguages.setText(languages);
        Glide.with(context)
                .load(countryModal.flags.png)
                .centerCrop()
                .into(holder.countryImg);

    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {
        TextView countryName, countryRegion, countrySubRegion, countryPopulation, countryBorders,countryLanguages;
        ImageView countryImg;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            countryName = (TextView) itemView.findViewById(R.id.countyName);
            countryRegion = (TextView) itemView.findViewById(R.id.countyRegion);
            countrySubRegion = (TextView) itemView.findViewById(R.id.countySubRegion);
            countryPopulation = (TextView) itemView.findViewById(R.id.countryPopulation);
            countryBorders = (TextView) itemView.findViewById(R.id.countryBorders);
            countryLanguages = (TextView) itemView.findViewById(R.id.countryLanguages);
            countryImg = (ImageView) itemView.findViewById(R.id.countyImg);
        }
    }
}
