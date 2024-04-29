package com.example.gigver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListViewAdapter extends BaseAdapter {

    Context context;
    String agents[];
    int valoImages[];
    LayoutInflater inflater;
    public CustomListViewAdapter(Context ctx, String[] valoAgents, int [] images){
        this.context = ctx;
        this.agents = valoAgents;
        this.valoImages = images;
        inflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return agents.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_custom_list_view, null);
        TextView view = (TextView) convertView.findViewById(R.id.textViewAgent);
        ImageView imageVal = (ImageView) convertView.findViewById(R.id.imageIcon);
        view.setText(agents[position]);
        imageVal.setImageResource(valoImages[position]);
        return convertView;
    }
}
