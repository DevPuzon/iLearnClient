package com.softwaresolution.ilearnclient;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public  class SocketSignal extends AsyncTask<String, String, String> implements
        OnListener {

        public String message = "server in";
        public String getSignal = "server in";
        private static String kq = "";
        String tag = "Login form";

        static String dstAddress;
        static int dstPort;
        static PrintWriter out1;
        public Socket socket = null;

        public static OnListener listener;
        public static boolean flag = true;
        public  void addListener(OnListener listener) {
            this.listener = listener;
        }

        public static Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (flag) {
                    kq += msg.obj.toString() + "\r\n";
                }
            }
        };
        public SocketSignal(String addr, int port) {
            dstAddress = addr;
            dstPort = port;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);

        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub

            try {
                socket = new Socket(dstAddress, dstPort);
                out1 = new PrintWriter(socket.getOutputStream(), true);
                out1.print("Hello server!");
                out1.flush();

                BufferedReader in1 = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));

                do {
                    try {
                        if (!in1.ready()) {
                            if (message != null) {
//                                LoginForm.handler.obtainMessage(0, 0, -1,
//                                         message).sendToTarget();
                                getSignal = message;
                                message = "";
                            }
                        }
                        int num = in1.read();
                        message += Character.toString((char) num);
                    } catch (Exception classNot) {
                    }

                } while (!message.equals("bye"));

                try {
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    socket.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                if (socket.isClosed()) {
                    flag = false;
                }
            } catch (Exception e) {
                Log.d(tag,e.getMessage());
            }

            super.onPostExecute(result);
        }


        @Override
        public void listener(String text) {
            // TODO Auto-generated method stub
            sendMessage(text);
        }

        public static void sendMessage(String msg) {
            try {
                out1.print(msg);
                out1.flush();
//                if (!msg.equals("bye"))
//                    MainActivity.handler.obtainMessage(0, 0, -1, "Me: " + msg)
//                            .sendToTarget();
//                else
//                    MainActivity.handler.obtainMessage(0, 0, -1,
//                            "Ngắt kết nối!").sendToTarget();
            } catch (Exception ioException) {
                ioException.printStackTrace();
            }
        }
}
