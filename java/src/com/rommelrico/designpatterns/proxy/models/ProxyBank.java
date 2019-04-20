package com.rommelrico.designpatterns.proxy.models;

import com.rommelrico.designpatterns.proxy.interfaces.*;

import java.util.*;

public class ProxyBank implements Bank {

    private RealBank realBank = new RealBank();
    private static List<String> bannedClients;

    static {
        bannedClients = new ArrayList<>();
        bannedClients.add("hackerman");
    }

    @Override
    public void withdrawMoney(String clientName) throws Exception {
        if (bannedClients.contains(clientName.toLowerCase())) throw new Exception("Access denied, bitch!");
        realBank.withdrawMoney(clientName);
    }

}
