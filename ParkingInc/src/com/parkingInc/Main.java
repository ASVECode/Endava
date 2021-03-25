package com.parkingInc;

import com.parkingInc.core.Engine;

public class Main {

    public static void main(String[] args) {
	Engine engine = Engine.getInstanceHolder();
	engine.start();
    }
}
