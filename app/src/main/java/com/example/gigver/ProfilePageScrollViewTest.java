package com.example.gigver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfilePageScrollViewTest extends BaseAdapter {

    Context context;
    String agentsValo[];
    int valoImages[];
    LayoutInflater inflater;

    public ProfilePageScrollViewTest(Context ctx, String[] valorantAgents, int[] valoAgentsImages){
        this.context = ctx;
        this.agentsValo = valorantAgents;
        this.valoImages = valoAgentsImages;
        inflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return agentsValo.length;
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
        ImageView img = (ImageView) convertView.findViewById(R.id.imageIcon);
        view.setText(agentsValo[position]);
        img.setImageResource(valoImages[position]);
        return convertView;
    }
}
