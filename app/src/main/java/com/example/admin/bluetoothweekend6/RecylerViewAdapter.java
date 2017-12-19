package com.example.admin.bluetoothweekend6;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  Admin on 12/18/2017.
 */

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.ViewHolder> {
    List<BluetoothClass> bluetoothClassList= new ArrayList<>();
    @Override
    public RecylerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(com.example.blelibrary.R.layout.bluetooth_device_list,null,false);
        return new ViewHolder(view);
    }

    public RecylerViewAdapter(List<BluetoothClass> bluetoothClassList) {
        this.bluetoothClassList = bluetoothClassList;
    }

    @Override
    public void onBindViewHolder(RecylerViewAdapter.ViewHolder holder, int position) {
        if (bluetoothClassList!=null) {
            holder.tvDeviceName.setText(bluetoothClassList.get(position).getName());
        }

    }

    @Override
    public int getItemCount() {
        return bluetoothClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvDeviceName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDeviceName = itemView.findViewById(com.example.blelibrary.R.id.tvDeviceName);
        }
    }
}
