package com.lightthefuture.minor_ii;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import com.lightthefuture.minor_ii.MainActivity;
import android.view.View.OnTouchListener;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class mousePad extends Activity implements OnTouchListener {
    public ConnectThread t;
    public ConnectedThread t1;

    private View Area;
    public BluetoothAdapter mAdaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouse_pad);
        Area = (View)findViewById(R.id.Area);
        String MAC="5C:AC:4C:D3:EC:95";
        mAdaptor = BluetoothAdapter.getDefaultAdapter();
        BluetoothDevice bluetoothDevice = mAdaptor.getRemoteDevice(MAC);
        // Initiate a connection request in a separate thread
        t = new ConnectThread(bluetoothDevice);
        t.start();
        System.out.println("Sending Data");
        BluetoothSocket mmSocket1=t.getMmSocket();

//        t1 = new ConnectedThread(mmSocket1);
//        t1.start();
        Area.setOnTouchListener(this);

    }
    public boolean onTouch(View v, MotionEvent event) {
        int Height = Area.getMeasuredHeight();
        int Width = Area.getMeasuredWidth();

        int x = (int) event.getX();
        int y = (int) event.getY();

        if(x < 0){
            x = 0;
        }
        else if (x > Width){
            x = Width;
        }
        if(y < 0){
            y = 0;
        }
        else if(y>Height){
            y = Height;
        }
        System.out.println(x+""+y);
        //((SocketConnection)getApplication()).getPrintWriter().write(x+" "+y+"\n");
        //((SocketConnection)getApplication()).getPrintWriter().flush();

        //t1.write(a);
        return true;
    }

}
class ConnectThread extends Thread {
    public final UUID uuid = UUID.fromString("e0cbf06c-cd8b-4647-bb8a-263b43f0f974");
    private BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;
    public ConnectThread(BluetoothDevice device) {
        // Use a temporary object that is later assigned to mmSocket,
        // because mmSocket is final
        BluetoothSocket tmp = null;
        mmDevice = device;
        // Get a BluetoothSocket to connect with the given BluetoothDevice
        try {
            // MY_UUID is the app's UUID string, also used by the server code
            tmp = mmDevice.createRfcommSocketToServiceRecord(uuid);
        } catch (IOException e) { }
        mmSocket = tmp;
    }
    public BluetoothSocket getMmSocket()
    {
        return mmSocket;
    }
    public void run() {
        BluetoothAdapter mAdaptor;
        // Cancel discovery because it will slow down the connection
        mAdaptor = BluetoothAdapter.getDefaultAdapter();
        mAdaptor.cancelDiscovery();
        try {
            // Connect the device through the socket. This will block
            // until it succeeds or throws an exception
            if(!mmSocket.isConnected()) {
                System.out.println("Connecting");
                mmSocket.connect();
                System.out.println("Connected");
            }

        } catch (IOException connectException)
        {
            // Unable to connect; close the socket and get out
/*            try {
                mmSocket.close();
            } catch (IOException closeException) { }*/
            try {
                Log.e("", "trying fallback...");

                mmSocket =(BluetoothSocket) mmDevice.getClass().getMethod("createRfcommSocket", new Class[] {int.class}).invoke(mmDevice,1);
                mmSocket.connect();

                Log.e("","Connected");
            }catch (IOException closeException) { } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            return;
        }
        catch(Exception e){
            e.printStackTrace();
            try {
                Log.e("", "trying fallback...exception");

                mmSocket =(BluetoothSocket) mmDevice.getClass().getMethod("createRfcommSocket", new Class[] {int.class}).invoke(mmDevice,1);
                mmSocket.connect();

                Log.e("","Connected");
            }catch (IOException closeException) { } catch (InvocationTargetException e1) {
                e1.printStackTrace();
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            } catch (IllegalAccessException e3) {
                e3.printStackTrace();
            }
        }

        // Do work to manage the connection (in a separate thread)
/*        System.out.println("Connected Sending Data");
        ConnectedThread t1 = new ConnectedThread(mmSocket);
        t1.start();*/
    }

    /** Will cancel an in-progress connection, and close the socket */
    public void cancel() {
/*        try {
            mmSocket.close();
        } catch (IOException e) { }
*/    }
}
class ConnectedThread extends Thread {
    private final BluetoothSocket mmSocket;
    private final InputStream mmInStream;
    private final OutputStream mmOutStream;

    public ConnectedThread(BluetoothSocket socket) {
        mmSocket = socket;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;

        // Get the input and output streams, using temp objects because
        // member streams are final
        try {
            tmpIn = socket.getInputStream();
            tmpOut = socket.getOutputStream();
        } catch (IOException e) { }

        mmInStream = tmpIn;
        mmOutStream = tmpOut;
    }

    public void run() {
        System.out.println("maa ka bhosa");
        byte[] buffer = new byte[1024];  // buffer store for the stream
        int bytes; // bytes returned from read()

        // Keep listening to the InputStream until an exception occurs
        while (true) {
            try {
                // Read from the InputStream
                bytes = mmInStream.read(buffer);
                // Send the obtained bytes to the UI activity
                //Handler mHandler;
                //mHandler.obtainMessage("ABCD", bytes, -1, buffer)
                //       .sendToTarget();
            } catch (IOException e) {
                break;
            }
        }
    }

    /* Call this from the main activity to send data to the remote device */
    public void write(byte[] bytes) {
        try {
            mmOutStream.write(bytes);
        } catch (IOException e) { }
    }

    /* Call this from the main activity to shutdown the connection */
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) { }
    }
}
