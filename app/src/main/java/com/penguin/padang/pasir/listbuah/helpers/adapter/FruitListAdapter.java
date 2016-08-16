package com.penguin.padang.pasir.listbuah.helpers.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.penguin.padang.pasir.listbuah.R;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by jowy on 8/11/16.
 */
public class FruitListAdapter extends BaseAdapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;

    private ArrayList<String> mData = new ArrayList<>();
    private TreeSet<Integer> sectionHeader = new TreeSet<>();

    private LayoutInflater mInflater;

    public FruitListAdapter(Context context) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(final String item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void addSectionHeaderItem(final String item) {
        mData.add(item);
        sectionHeader.add(mData.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return sectionHeader.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;

        int rowType = getItemViewType(i);

        try {
            if (view == null) {
                holder = new ViewHolder();
                switch (rowType) {
                    case TYPE_ITEM:
                        view = mInflater.inflate(R.layout.snippet_item1, null);
                        holder.textView = (TextView) view.findViewById(R.id.text);
                        break;
                    case TYPE_SEPARATOR:
                        view = mInflater.inflate(R.layout.snippet_item2, null);
                        holder.textView = (TextView) view.findViewById(R.id.textSeparator);
                        break;
                }
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            holder.textView.setText(mData.get(i));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    public static class ViewHolder {
        public TextView textView;
    }
}
