package com.example.myapplication.Adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.UtilImages.util;
import com.example.myapplication.model.DataHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class mainAdapter extends  RecyclerView.Adapter<mainAdapter.HomeViewHolder> {
    ArrayList<DataHolder> list;
    Context con;
    static int val=-1;
    public mainAdapter(Context con, ArrayList<DataHolder> list){
        super();
        this.list=list;
        this.con=con;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_singleviewholder,parent,false);
        return new HomeViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        DataHolder dataHolder=list.get(position);
        holder.Borders.setText(dataHolder.borders.toString());
        holder.capital.setText(dataHolder.capital.toString());
        holder.country.setText(dataHolder.name.toString());
        holder.Population.setText(dataHolder.population.toString());
        holder.region.setText(dataHolder.region.toString());
        holder.subRegion.setText(dataHolder.subregion.toString());
        holder.Languages.setText(dataHolder.languages.toString());
       // Picasso.get().load(dataHolder.flag).error(R.drawable.not_availa).into(holder.img);

            util.fetchSvg(con, dataHolder.flag.toString(), holder.img);
           val= position;
           System.out.println("value"+position);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {
        public View view;
        TextView country;
        TextView capital;
        ImageView img;
        TextView Population;
        TextView region;
        TextView subRegion;
        TextView Borders;
        TextView Languages;
        HomeViewHolder(View view) {

            super(view);
            this.view = view;
            System.out.println(view==null);
            country=view.findViewById(R.id.txtCountryName);
             capital=view.findViewById(R.id.txtCapitalName);
             img=view.findViewById(R.id.imgRes);
            Population=view.findViewById(R.id.txtPopulation);
             region=view.findViewById(R.id.txtRegionName);
             subRegion=view.findViewById(R.id.txtSubRegionName);
             Borders=view.findViewById(R.id.txtBordersName);
             Languages=view.findViewById(R.id.txtLanguages);
        }


    }

}
