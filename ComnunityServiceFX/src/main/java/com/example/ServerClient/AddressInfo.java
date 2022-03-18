package com.example.ServerClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class AddressInfo {
    public static InetAddress IP;

    static{
        try {
            IP=InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
