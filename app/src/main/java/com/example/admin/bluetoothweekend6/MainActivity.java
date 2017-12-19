package com.example.admin.bluetoothweekend6;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import android.widget.Toast;


import com.example.blelibrary.BLEDiscoveryClass;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 1;
    private static final String TAG = "MainActivity";



    private BluetoothAdapter mBluetoothAdapter= BluetoothAdapter.getDefaultAdapter();
    private IntentFilter intentFilter;
    private RecyclerView recyclerView;
    List<BluetoothClass> bluetoothClassList;
    private RecylerViewAdapter recylerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bluetoothSupportCheck();
        recyclerView = findViewById(R.id.rcDeviceList);

        bluetoothClassList= new ArrayList<>();
        recylerViewAdapter = new RecylerViewAdapter(bluetoothClassList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


// bluetooth check
    public void bluetoothSupportCheck(){

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
            finish();
        }
    }



//button click
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void scanBluetooth(View view) {

        Log.d(TAG, "scanBluetooth: ");
         mBluetoothAdapter.startDiscovery();
//        bluetoothClassList.add(new BluetoothClass("Great"));
//        bluetoothClassList.add(new BluetoothClass("Great"));
//        bluetoothClassList.add(new BluetoothClass("Great"));
        intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(broadcastReceiver, intentFilter);
        recyclerView.setAdapter(recylerViewAdapter);


        Log.d(TAG, "scanBluetooth: "+intentFilter.getAction(0));
        Log.d(TAG, "scanBluetooth: "+broadcastReceiver.getResultData());
//        BLEDiscoveryClass bleDiscoveryClass = new BLEDiscoveryClass();
//        bleDiscoveryClass.scanLeDevice(true);
    }
// broadcastrecevier for bluetooth devices
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive:outer ");
String action = intent.getAction();

if(BluetoothDevice.ACTION_FOUND.equals(action)){
    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
    String deviceName = device.getName();
    bluetoothClassList.add(new BluetoothClass(deviceName));
}
        }
    };
}
