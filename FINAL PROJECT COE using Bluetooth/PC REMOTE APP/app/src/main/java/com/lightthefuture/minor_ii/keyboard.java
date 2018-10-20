package com.lightthefuture.minor_ii;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class keyboard extends AppCompatActivity {

    Button b1,b2;
    EditText ed2;
    String s1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);
        b1=(Button)findViewById(R.id.append);
        b2=(Button)findViewById(R.id.submit);
        ed2=(EditText)findViewById(R.id.editText2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1 = ed2.getText().toString();
                System.out.println("EditText:" + s1);
                try {
                    ClientThread.bw.write(s1 + "!" + "500");
                    ClientThread.bw.flush();
                    ed2.setText("");
                    Toast.makeText(getApplicationContext(), "Text Written", Toast.LENGTH_LONG).show();
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
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("EditText:" + s1);
                try {
                    ClientThread.bw.write("1000" + "!" + "500");
                    ClientThread.bw.flush();
                    Toast.makeText(getApplicationContext(),"File Submitted and saved in PC", Toast.LENGTH_LONG).show();
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
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            try
            {
                ClientThread.bw.write("" + "!" + "5");
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
