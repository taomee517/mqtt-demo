package org.demo.mqtt.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class IpAddrUtil {

    public static InetAddress getIpAddress() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface current = interfaces.nextElement();
            if (!current.isUp()
                    || current.isLoopback()
                    || current.isVirtual()
                    || current.getDisplayName().contains("Virtual")){
                continue;
            }
            Enumeration<InetAddress> addresses = current.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress addr = addresses.nextElement();
                if (addr.isLoopbackAddress()) {
                    continue;
                }
                //去掉还回和虚拟地址
                if (addr.isSiteLocalAddress()) {
                    return addr;
                }
            }
        }

        throw new SocketException("Can't get our ip address, interfaces are: " + interfaces);
    }
}
