package com.manager.kafka.utils;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * <p>Description:  Ip工具 </p>
 * <p>Company: 古尘工作室</p>
 * <p>Date：2023-07-20 17:30:42 </p>
 *
 * @author <a href="mailto:is_fire_subscribe@hotmail.com">古尘</a>
 * @version V1.0
 */
public class IpUtils {

    private static final List<String> FILTER = List.of("awdl", "bridge", "p2p", "llw", "ap");

    public static String getIpV4() throws SocketException {
        List<String> vpnv4 = new ArrayList<>();
        List<String> vpnv6 = new ArrayList<>();
        List<String> ipV4 = new ArrayList<>();
        List<String> ipV6 = new ArrayList<>();
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface ni = interfaces.nextElement();
            long count = FILTER.stream().filter(nit -> ni.getName().contains(nit)).count();
            if (count >= 1) {
                continue;
            }
            Enumeration<InetAddress> addresses = ni.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress ip = addresses.nextElement();
                boolean isVpn = ni.getName().contains("utun");
                if (ip instanceof Inet4Address v4) {
                    if (isVpn) {
                        vpnv4.add(v4.getHostAddress());
                    } else {
                        ipV4.add(v4.getHostAddress());
                    }
                }
                if (ip instanceof Inet6Address v6) {
                    String v6Address = v6.getHostAddress().replaceFirst("%.*", "");
                    if (isVpn) {
                        vpnv6.add(v6Address);
                    } else {
                        ipV6.add(v6Address);
                    }
                }

            }
        }
        return ipV4.get(0);
    }

    public static void main(String[] args) throws SocketException {
        getIpV4();
    }
}
