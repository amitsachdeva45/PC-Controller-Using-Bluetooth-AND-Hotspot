package com.lightthefuture.minor_ii;
import java.io.IOException;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;

public class menuoptions1 extends ListActivity {

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        IconifiedTextListAdapter item = new IconifiedTextListAdapter(this);

        item.addItem(new IconifiedText("PowerPoint", ResourcesCompat.getDrawable(getResources(), R.drawable.powerpoint_icon, null)));
        item.addItem(new IconifiedText("Windows Media Player", ResourcesCompat.getDrawable(getResources(), R.drawable.wmp_icon, null)));
        item.addItem(new IconifiedText("Mouse Emulator", ResourcesCompat.getDrawable(getResources(), R.drawable.mouse_icon, null)));
        item.addItem(new IconifiedText("Exit", ResourcesCompat.getDrawable(getResources(), R.drawable.exit1, null)));
        setListAdapter(item);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Log.d("",position+"");
        if(position == 0){
            try{
                ClientThread.bw.write("Powerpoint" + "\n");
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
            Intent intent = new Intent(v.getContext(), Powerpoint.class);
            startActivityForResult(intent, 0);
        }
        else if(position == 1){
            try{
                ClientThread.bw.write("Music" + "\n");
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
            Intent intent = new Intent(v.getContext(), WMP.class);
            startActivityForResult(intent, 0);
        }else if(position == 2){
            try{
                ClientThread.bw.write("Mouse" + "\n");
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
            Intent intent = new Intent(v.getContext(), Mouse.class);
            startActivityForResult(intent, 0);
        }
        else if(position == 3){
            try{
                ClientThread.bw.write("Exit" + "\n");
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
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            startActivityForResult(intent, 0);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(this, MainActivity.class);
            startActivityForResult(intent, 0);
        }
        return super.onKeyDown(keyCode, event);
    }

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