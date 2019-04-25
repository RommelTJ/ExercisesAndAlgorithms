package com.rommelrico.designpatterns.visitor.interfaces;

import com.rommelrico.designpatterns.visitor.models.*;

public interface Visitor {
    double visitor(Shirt shirt);
    double visitor(TShirt tshirt);
    double visitor(Jacket jacket);
}
