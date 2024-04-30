package com.example.gigver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gigver.R;

import java.util.List;

import models.Post;

public class CustomListViewAdapter extends BaseAdapter {
    Context context;
    List<Post> posts;
    LayoutInflater inflater;
    public CustomListViewAdapter(Context ctx, List<Post> posts){
        this.context = ctx;
        this.posts = posts;

        inflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inflater.inflate(R.layout.activity_custom_list_view, parent, false);

        TextView view = (TextView) convertView.findViewById(R.id.textViewAgent);

        Post currentItem = (Post) getItem(position);

        view.setText(currentItem.GetSubject());
        return convertView;
    }
}
