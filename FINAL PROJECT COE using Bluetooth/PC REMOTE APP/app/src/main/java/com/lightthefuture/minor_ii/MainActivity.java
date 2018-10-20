package com.lightthefuture.minor_ii;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    Button b1,b2,b3,b4,b5;
    ListView lv;
    private BluetoothAdapter mAdaptor;
    private Set<BluetoothDevice> pairedDevices;
    private Set<BluetoothDevice> discoveredDevices;
    //public Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // b1=(Button)findViewById(R.id.button3);
        b2=(Button)findViewById(R.id.button6);
        b3=(Button)findViewById(R.id.button7);
        b4=(Button)findViewById(R.id.button5);
        b5=(Button)findViewById(R.id.useremote);
        lv = (ListView)findViewById(R.id.listView);
        mAdaptor = BluetoothAdapter.getDefaultAdapter();
        if (mAdaptor == null) {
            // Device does not support Bluetooth
            Toast.makeText(getApplicationContext(), "Bluetooth Not Supported", Toast.LENGTH_LONG).show();
        }
        else
        {
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!mAdaptor.isEnabled()) {
                            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                            startActivityForResult(turnOn, 0);
                            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                            //discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
                            //startActivityForResult(discoverableIntent, 0);
                            Toast.makeText(getApplicationContext(),"Turning on", Toast.LENGTH_LONG).show();
                            String MAC="5C:AC:4C:D3:EC:95";
//                            BluetoothDevice bluetoothDevice = mAdaptor.getRemoteDevice(MAC);
                            // Initiate a connection request in a separate thread
//                            ConnectThread t = new ConnectThread(bluetoothDevice);
 //                           t.start();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Already on", Toast.LENGTH_LONG).show();
                        }

                    }
                });
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mAdaptor.isEnabled()) {
                        mAdaptor.disable();
                        Toast.makeText(getApplicationContext(),"Turned off", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Already off", Toast.LENGTH_LONG).show();
                    }

                }
            });
            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mAdaptor.isEnabled()) {
                        Intent intent = new Intent(MainActivity.this, DeviceList.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(),"Turn On Bluetooth First", Toast.LENGTH_LONG).show();
                    }
                }

            });
            b5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!mAdaptor.isEnabled()) {
                        Toast.makeText(getApplicationContext(),"Please turn on Bluetooth First", Toast.LENGTH_LONG).show();
                    }
                    else {
                        ClientThread a1=new ClientThread();
                        new Thread(a1).start();

//                        Socket socket = a1.getSocket();
//                        BufferedWriter out=a1.getBufferedWriter();
//                        if(socket != null && out != null){
//                            ((SocketConnection)getApplication()).setSocket(socket);
//                            ((SocketConnection)getApplication()).setPrintWriter(out);
//                        }
//
                            Intent intent = new Intent(MainActivity.this,menuoptions.class);
                            startActivity(intent);
//
//
                    }

                }


            });
        }

//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,mousePad.class);
//                startActivity(intent);
//
//            }
//        });

    }
    public Socket setConnection(String IP, String Port) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Socket socket = null;
        try {
            socket= new Socket(IP, Integer.parseInt(Port));
        } catch (NumberFormatException e) {
            showError(builder,"Verifica el puerto");
        } catch (UnknownHostException e) {
            showError(builder,"Error al conectar con el servidor");
        } catch (IOException e) {
            showError(builder,"Error al conectar con el servidor");
        }
        return socket;
    }

    public PrintWriter setPrintWriter(Socket socket){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        PrintWriter out = null;
        try {
            out = new PrintWriter(socket.getOutputStream(),true);
        } catch (IOException e) {
            showError(builder,"Verifica el puerto");
        }
        return out;
    }

    public void showError(AlertDialog.Builder builder, String Mensaje){
        builder.setTitle("Error");
        builder.setMessage(Mensaje);
        builder.setPositiveButton("OK",null);
        builder.create();
        builder.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

 class ClientThread extends AppCompatActivity implements Runnable{
    public static Socket socket=null;
    public static BufferedWriter bw;
     public static int mainflag=0;
     public static String music[];
     public static String ppt[];
    // public static BufferedReader br;
     Boolean flag=false;
//     ClientThread(){
//         music=new String[10];
//         this.music[0] = "asasa";
//         this.music[1] = "fdfdfdfd";
//     }
    @Override
    public void run() {
        try
        {
            String host = "192.168.43.32";
            int port = 60064;
            InetAddress address = InetAddress.getByName(host);
            socket = new Socket(address, port);
            //Send the message to the server
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);
            String number = "Connected to Client";
            String sendMessage = number + "\n";
            bw.write("6");
            bw.flush();
            System.out.println("Message sent to the server : " + sendMessage);
            System.out.println("Setsocket" + socket);
            System.out.println("Setprintwriter" + bw);
//            if(socket != null){
////                SocketConnection s1=new SocketConnection();
////                s1.setSocket(socket);
////                s1.setPrintWriter(bw);
//                flag=true;
//                mainflag=1;
//                System.out.println("Setsocket" + socket);
//                System.out.println("Setprintwriter" + bw);
//
//            }

            //Get the return message from the server
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            System.out.println("Inputstream"+isr);
            BufferedReader  br = new BufferedReader(isr);
            System.out.println("buferstream"+br);
            String message = br.readLine();
            //music=new String[1000];
            music=message.split(",");
            for(int i=0;i<music.length;i++)
            {
                music[i]=Integer.toString(i+1)+": "+music[i];
                System.out.println("song no.:"+ " " + music[i]);
            }
            System.out.println("Message received music from the server : " + message);
            //br.close();
            message=br.readLine();
           // ppt=new String[1000];
            ppt=message.split(",");
            for(int i=0;i<ppt.length;i++)
            {
                ppt[i]=Integer.toString(i+1)+": "+ppt[i];
                System.out.println("song no.:"+ i+1 + " " + ppt[i]);
            }
            System.out.println("PPT:" + message);
        }
        catch (Exception exception)
        {
            System.out.println("not connected");
            flag=false;
           // Toast.makeText(MainActivity.this,"cannnt connnect", Toast.LENGTH_LONG).show();
            exception.printStackTrace();
        }

//        finally
//        {
//            //Closing the socket
//            try
//            {
//                socket.close();
//            }
//            catch(Exception e)
//            {
//                e.printStackTrace();
//            }
//        }
    }
    public Socket getSocket()
    {
        return socket;
    }
    public BufferedWriter getBufferedWriter()
    {
        return bw;
    }
}
