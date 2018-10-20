package com.lightthefuture.minor_ii;

import android.app.Application;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Deepanshu on 5/6/2016.
 */
public class SocketConnection extends Application {
    private Socket socket;
    private BufferedWriter out;

    public Socket getSocket(){
        return socket;
    }

    public BufferedWriter getPrintWriter(){
        return out;
    }

    public void setSocket(Socket socket){
        this.socket = socket;
    }

    public void setPrintWriter(BufferedWriter out){
        this.out = out;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        socket = null;
        out = null;
    }


}
