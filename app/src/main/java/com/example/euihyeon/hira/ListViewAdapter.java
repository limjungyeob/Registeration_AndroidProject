package com.example.euihyeon.hira;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>();

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        /* get a reference to 'list VIew item' */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        /* get a location reference for the internal view */
        TextView tvAppraisal = (TextView) convertView.findViewById(R.id.tvAppraisal);
        TextView tvHsptName = (TextView) convertView.findViewById(R.id.tvHsptName);
        TextView tvAddr = (TextView) convertView.findViewById(R.id.tvAddr);
        TextView tvGrade = (TextView) convertView.findViewById(R.id.tvGrade);

        /* get a location reference for a set of data */
        ListViewItem listViewItem = listViewItemList.get(position);

        /* reflect data */
        tvAppraisal.setText(listViewItem.getAppraisal());
        tvHsptName.setText(listViewItem.getHsptName());
        tvAddr.setText(listViewItem.getAddr());
        tvGrade.setText(listViewItem.getGrade());

        return convertView;
    }

    /* add Custom Item*/
    public void addItem(String appraisal, String hsptName, String addr, String grade) {
        ListViewItem item = new ListViewItem();

        item.setAppraisal(appraisal);
        item.setHsptName(hsptName);
        item.setAddr(addr);
        item.setGrade(grade);

        listViewItemList.add(item);
    }
}
