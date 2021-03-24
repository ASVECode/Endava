package com.transportInc;

import com.transportInc.core.Engine;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //Singleton design pattern
        //Ensures that there is only one instance of Engine in existence
        var engine = Engine.getInstance();
        engine.start();
    }
}
