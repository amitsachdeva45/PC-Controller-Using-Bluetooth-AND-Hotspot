package com.lightthefuture.minor_ii;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class WMP extends Activity {

    EditText ed1;
    String s1;
    ListView listView;
    ListViewPptAdapter adapter;
    ArrayList<String> strings = new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_wmp);
        AddData();
        listView = (ListView) findViewById(R.id.listView44);
        adapter = new ListViewPptAdapter(this, strings);
        listView.setAdapter(adapter);
        ed1=(EditText)findViewById(R.id.editText44);
        Button b1=(Button)findViewById(R.id.playmusic);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1 = ed1.getText().toString();
                System.out.println("EditText:" + s1);
                try {
                    ClientThread.bw.write("0" + " " + "3" + " " + s1);
                    ClientThread.bw.flush();
                } catch (Exception exception) {
                    exception.printStackTrace();
                } finally {
                    //Closing the socket
                    try {

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }


        });
        System.out.println("edittext:" + s1);
    }

    public void pressNext(View v){

        try
        {
            ClientThread.bw.write("3" + " " + "3" + " " + "1000");
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
                // socket.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    public void AddData(){
        for(int i=0; i<ClientThread.music.length; i++)
            strings.add(ClientThread.music[i]);

    }

    public void pressBack(View v){
        try
        {
            ClientThread.bw.write("4" + " " + "3" + " " + "1000");
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
                // socket.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

    }

    public void pressPlay(View v){

        try
        {
            ClientThread.bw.write("1" + " " + "3" + " " + "1000");
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
                // socket.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

    }

    public void pressStop(View v){
        try
        {
            ClientThread.bw.write("5" + " " + "3" + " " + "1000");
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
                // socket.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

    }

    public void pressPause(View v){
        try
        {
            ClientThread.bw.write("2" + " " + "3" + " " + "1000");
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
                // socket.close();
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
            try
            {
                ClientThread.bw.write("0" + " " + "6" + " " + "1000");
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
                    // socket.close();
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