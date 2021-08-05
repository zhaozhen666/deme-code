package com.zhao.hash;

import java.util.SortedMap;
import java.util.TreeMap;

public class HashNoVirtual {
    public static void main(String[] args) {
        String[] servers = new String[]{"110.78.23.1", "118.35.43.2", "110.78.23.6", "119.168.10.63"};
        SortedMap<Integer, String> hashServerMap = new TreeMap<Integer, String>();
        for (String server : servers) {
            int serverHash = Math.abs(server.hashCode());
            System.out.println(serverHash);
            hashServerMap.put(serverHash, server);
        }
        String[] clients = new String[]{"163.87.32.87", "178.97.0.12", "132.96.94.7"};
        for (String client : clients) {
            int clientHash = Math.abs(client.hashCode());
            SortedMap<Integer, String> integerStringSortedMap = hashServerMap.tailMap(clientHash);
            System.out.println(integerStringSortedMap);
            if (integerStringSortedMap.isEmpty()) {
                Integer firstKey = hashServerMap.firstKey();
                System.out.println("client..." + client + "      server " + hashServerMap.get(firstKey));

            } else {
                Integer firstKey = integerStringSortedMap.firstKey();
                System.out.println("client..." + client + "      server " + hashServerMap.get(firstKey));
            }
        }

    }
}
