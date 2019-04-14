package com.rommelrico.designpatterns.adapter.interfaces;

import com.rommelrico.designpatterns.adapter.models.*;

public interface SocketAdapter {

    Volt get120Volts();
    Volt get12Volts();
    Volt get3Volts();
    Volt get1Volt();

}
