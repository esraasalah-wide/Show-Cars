package com.example.showcars;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CarsAdapter extends BaseAdapter {
    private List<Cars> cars;
    private Context context;

    public CarsAdapter(List<Cars> cars, Context context) {
        this.cars = cars;
        this.context = context;
    }

    @Override
    public int getCount() {
        return cars.size();
    }

    @Override
    public Object getItem(int position) {
        return cars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(150, 400));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 10, 8, 8);
        }
        else
        {
            imageView = (ImageView) convertView;
        }

        Picasso.get().load("https://image.tmdb.org/t/p/w500" + cars.get(position).getImage()).into(imageView);
        return imageView;
    }

}
