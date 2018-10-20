package com.lightthefuture.minor_ii;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;

/**
 * Created by Deepanshu on 5/6/2016.
 */
public class menuoptions extends AppCompatActivity {

    Button b1,b2,b3,b4,b5;

    public void onCreate(Bundle savedInstanceState) {
        //super.onCreate(icicle);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_menuoptions);
//        IconifiedTextListAdapter item = new IconifiedTextListAdapter(this);
//
//        item.addItem(new IconifiedText("PowerPoint", ResourcesCompat.getDrawable(getResources(), R.drawable.powerpoint_icon, null)));
//        item.addItem(new IconifiedText("Windows Media Player", ResourcesCompat.getDrawable(getResources(), R.drawable.wmp_icon, null)));
//        item.addItem(new IconifiedText("Mouse Emulator", ResourcesCompat.getDrawable(getResources(), R.drawable.mouse_icon, null)));
//        item.addItem(new IconifiedText("Exit", ResourcesCompat.getDrawable(getResources(), R.drawable.exit, null)));
//        setListAdapter(item);
        b1 = (Button) findViewById(R.id.button8);
        b2 = (Button) findViewById(R.id.button9);
        b3 = (Button) findViewById(R.id.button10);
        b4 = (Button) findViewById(R.id.button11);
        b5= (Button)findViewById(R.id.button12);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    ClientThread.bw.write("2" + "\n");//Mouse
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

                Intent intent = new Intent(menuoptions.this, Mouse.class);
                startActivity(intent);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    ClientThread.bw.write("1" + "\n");//PPT
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

                Intent intent = new Intent(menuoptions.this, Powerpoint.class);
                startActivity(intent);

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    ClientThread.bw.write("3" + "\n");//Music
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

                Intent intent = new Intent(menuoptions.this, WMP.class);
                startActivity(intent);

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    ClientThread.bw.write("4" + "\n");//Exit
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

                Intent intent = new Intent(menuoptions.this, MainActivity.class);
                startActivity(intent);

            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    ClientThread.bw.write("5" + "\n");//Keyboard
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

                Intent intent = new Intent(menuoptions.this, keyboard.class);
                startActivity(intent);

            }
        });
    }
//
//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//        Log.d("", position + "");
//        if(position == 0){
//            sendCommand("PowerPoint");
//            Intent intent = new Intent(v.getContext(), Powerpoint.class);
//            startActivityForResult(intent, 0);
//        }
//        else if(position == 1){
//            sendCommand("WMP");
//            Intent intent = new Intent(v.getContext(), WMP.class);
//            startActivityForResult(intent, 0);
//        }else if(position == 2){
//            sendCommand("Mouse");
//            Intent intent = new Intent(v.getContext(), Mouse.class);
//            startActivityForResult(intent, 0);
//        }
//        else if(position == 3){
//            closeConnection();
//            finish();
//        }
//    }
//
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            try{
                ClientThread.bw.write("5" + "\n");//Exit
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
//
//    private void sendCommand(String Command){
//        ((SocketConnection)getApplication()).getPrintWriter().println(Command);
//        ((SocketConnection)getApplication()).getPrintWriter().flush();
//    }
//
//    private void closeConnection(){
//        ((SocketConnection)getApplication()).getPrintWriter().println("Exit");
//        ((SocketConnection)getApplication()).getPrintWriter().flush();
//        ((SocketConnection)getApplication()).getPrintWriter().close();
//        try {
//            ((SocketConnection)getApplication()).getSocket().close();
//        } catch (IOException e) {
//        }
//    }
    }