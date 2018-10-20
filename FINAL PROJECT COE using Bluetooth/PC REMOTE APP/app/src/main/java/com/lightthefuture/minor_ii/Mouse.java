package com.lightthefuture.minor_ii;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class Mouse extends Activity implements View.OnTouchListener {

    private View Area;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.content_mouse);
        Area = (View)findViewById(R.id.Area);
        Area.setOnTouchListener(this);
    }

    public void clickRight(View v){

        try{
            ClientThread.bw.write("0" + " " + "0" + " " + "2" + " " + "3" + "\n");
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

    public void clickLeft(View v){

        try{
            ClientThread.bw.write("0" + " " + "0" + " " + "2" + " " + "2" + "\n");
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
                ClientThread.bw.write("0" + " " + "0" + " " + "5" + " " + "0" + "\n");
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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int Height = Area.getMeasuredHeight();
        int Width = Area.getMeasuredWidth();
        System.out.println("height:" + Height +"width:" + Width);
        int x = (int) event.getX();
        int y = (int) event.getY();
        System.out.println("X:" + x +"Y:" + y);

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

        try{
            ClientThread.bw.write(x + " " + y + " " + "2" + " " + "1" + "\n");
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
        return true;
    }
}
