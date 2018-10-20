package com.lightthefuture.minor_ii;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class DeviceList extends AppCompatActivity {

    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // Whenever a remote Bluetooth device is found
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Add the name and address to an array adapter to show in a ListView
                adapter.add(bluetoothDevice.getName() + "\n"
                        + bluetoothDevice.getAddress());
            }
        }
    };
    private BluetoothAdapter bluetoothAdapter;
    private ToggleButton toggleButton;
    private ListView listview;
    private ArrayAdapter adapter;
    private static final int ENABLE_BT_REQUEST_CODE = 1;
    private static final int DISCOVERABLE_BT_REQUEST_CODE = 2;
    private static final int DISCOVERABLE_DURATION = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_device_list);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);

        listview = (ListView) findViewById(R.id.listView);

        adapter = new ArrayAdapter
                (this,android.R.layout.simple_list_item_1);
        listview.setAdapter(adapter);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    }

    public void onToggleClicked(View view) {

        //adapter.clear();

        ToggleButton toggleButton = (ToggleButton) view;

        discoverDevices();
        if (toggleButton.isChecked()) { // to turn on bluetooth
            Toast.makeText(getApplicationContext(), "Making your device discoverable to other's" +
                            "\n" + "Scanning for remote Bluetooth devices...",
                    Toast.LENGTH_SHORT).show();
            // To discover remote Bluetooth devices

            // Make local device discoverable by other devices
            //discoverDevices();
            makeDiscoverable();
        } else {
            // Turn off bluetooth
            //adapter.clear();
            //discoverDevices();
            Toast.makeText(getApplicationContext(), "Your device is not discoverable.",
                    Toast.LENGTH_SHORT).show();
        }
    }


//        if (bluetoothAdapter == null) {
//            // Device does not support Bluetooth
//            Toast.makeText(getApplicationContext(), "Oop! Your device does not support Bluetooth",
//                    Toast.LENGTH_SHORT).show();
//            toggleButton.setChecked(false);
//        } else {
//
//
//            if (toggleButton.isChecked()){ // to turn on bluetooth
//                if (!bluetoothAdapter.isEnabled()) {
//                    // A dialog will appear requesting user permission to enable Bluetooth
//                    Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//                    startActivityForResult(enableBluetoothIntent, ENABLE_BT_REQUEST_CODE);
//                } else {
//                    Toast.makeText(getApplicationContext(), "Making your device discoverable to other's" +
//                                    "\n" + "Scanning for remote Bluetooth devices...",
//                            Toast.LENGTH_SHORT).show();
//                    // To discover remote Bluetooth devices
//                    discoverDevices();
//                    // Make local device discoverable by other devices
//                    makeDiscoverable();
//                }
//            } else { // Turn off bluetooth
//                //adapter.clear();
//                discoverDevices();
//                Toast.makeText(getApplicationContext(), "Your device is not discoverable.",
//                        Toast.LENGTH_SHORT).show();
//            }
//        }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == ENABLE_BT_REQUEST_CODE) {

            // Bluetooth successfully enabled!
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Bluetooth is now enabled..!!" +
                                "\n" + "Scanning for Bluetooth devices...",
                        Toast.LENGTH_SHORT).show();

                // Make local device discoverable by other devices
                makeDiscoverable();

                // To discover remote Bluetooth devices
                discoverDevices();

            } else { // RESULT_CANCELED as user refused or failed to enable Bluetooth
                Toast.makeText(getApplicationContext(), "Bluetooth is not enabled.",
                        Toast.LENGTH_SHORT).show();

                // Turn off togglebutton
                toggleButton.setChecked(false);
            }
        } else if (requestCode == DISCOVERABLE_BT_REQUEST_CODE){

            if (resultCode == DISCOVERABLE_DURATION){
                Toast.makeText(getApplicationContext(), "Your device is now discoverable by other devices for " +
                                DISCOVERABLE_DURATION + " seconds",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Fail to enable discoverability on your device.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    protected void discoverDevices(){
        // To scan for remote Bluetooth devices
        if (bluetoothAdapter.startDiscovery()) {
            Toast.makeText(getApplicationContext(), "Discovering other bluetooth devices...",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Discovery failed to start.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    protected void makeDiscoverable(){
        // Make local device discoverable
        Intent discoverableIntent = new
                Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, DISCOVERABLE_DURATION);
        startActivityForResult(discoverableIntent, DISCOVERABLE_BT_REQUEST_CODE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register the BroadcastReceiver for ACTION_FOUND
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        this.registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.unregisterReceiver(broadcastReceiver);
    }

}
