package com.lightthefuture.minor_ii;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Powerpoint extends Activity {

    String s1;
    EditText ed1;
    List<String> li;
    ListView listView;
    ListViewPptAdapter adapter;
    ArrayList<String> strings = new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_powerpoint);
        AddData();
        listView = (ListView) findViewById(R.id.listView2);
        adapter = new ListViewPptAdapter(this, strings);
        listView.setAdapter(adapter);

        ed1=(EditText)findViewById(R.id.editText);
        Button b1=(Button)findViewById(R.id.showppt);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1=ed1.getText().toString();
                System.out.println("EditText:" + s1);
                try{
                    ClientThread.bw.write("0" + " " + "1" + " " +  s1);
                    ClientThread.bw.flush();
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }
                finally
                {
                    //Closing the socket
                    try
                    {

                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }

            }


        });
        System.out.println("edittext:"+s1);
//        ClientThread1 a1=new ClientThread1();
//        new Thread(a1).start();
    }

    public void AddData(){
        for(int i=0; i<ClientThread.ppt.length; i++)
            strings.add(ClientThread.ppt[i]);

    }

    public void enterPPTNo(View v){
        try{
            ClientThread.bw.write("0" + " " + "1" + " " +  s1);
            ClientThread.bw.flush();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            //Closing the socket
            try
            {

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    public void pressNext(View v){
        try{
            ClientThread.bw.write("1" + " " + "1" + " " + "1000");
            ClientThread.bw.flush();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            //Closing the socket
            try
            {

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void pressBack(View v){

        try{
            ClientThread.bw.write("2" + " " + "1" + " " + "1000");
                    ClientThread.bw.flush();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            //Closing the socket
            try
            {

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void pressStart(View v){

        try{
            ClientThread.bw.write("4" + " "+ "1" + "\n");
            ClientThread.bw.flush();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            //Closing the socket
            try
            {

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void pressStop(View v){
        try{
            ClientThread.bw.write("3" + " " + "1" + " " +  "1000");
            ClientThread.bw.flush();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            //Closing the socket
            try
            {

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            try{
                ClientThread.bw.write("0" + " " + "5"+ " "+"1000");
                ClientThread.bw.flush();
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
            finally
            {
                //Closing the socket
                try
                {

                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}

//class ClientThread1 extends AppCompatActivity implements Runnable{
//    public static BufferedReader br;
//    Boolean flag=false;
//    @Override
//    public void run() {
//        System.out.println("Entered thread");
//        try
//        {//Get the return message from the server
//            System.out.println("Entered try");
//            InputStream is = ClientThread.socket.getInputStream();
//            InputStreamReader isr = new InputStreamReader(is);
//            br = new BufferedReader(isr);
//            String message = br.readLine();
//            System.out.println("Exit try");
//            System.out.println("Message received from the server : " +message);
//        }
//        catch (Exception exception)
//        {
//            System.out.println("not connected");
//            flag=false;
//            // Toast.makeText(MainActivity.this,"cannnt connnect", Toast.LENGTH_LONG).show();
//            exception.printStackTrace();
//        }
//
////        finally
////        {
////            //Closing the socket
////            try
////            {
////                socket.close();
////            }
////            catch(Exception e)
////            {
////                e.printStackTrace();
////            }
////        }
//    }
//}