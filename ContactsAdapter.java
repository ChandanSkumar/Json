package com.example.hp.jsondemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hp on 5/18/2017.
 */
public class ContactsAdapter extends BaseAdapter {

    private List<Contacts> mList;
    private Context mContext;
    public ContactsAdapter(MainActivity mainActivity, List<Contacts> mlist) {
        this.mList=mlist;
        this.mContext=mainActivity;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView= inflater.inflate(R.layout.column,null,false);

        TextView tv= (TextView) convertView.findViewById(R.id.t1);
        TextView tv0= (TextView) convertView.findViewById(R.id.t2);
        TextView tv1= (TextView) convertView.findViewById(R.id.t3);
        TextView tv2= (TextView) convertView.findViewById(R.id.t4);
        TextView tv3= (TextView) convertView.findViewById(R.id.t5);


        tv.setText(mList.get(position).getId());
        tv0.setText(mList.get(position).getName());
        tv1.setText(mList.get(position).getEmail());
        tv2.setText(mList.get(position).getAddress());
        tv3.setText(mList.get(position).getGender());


        return convertView;
    }


}
