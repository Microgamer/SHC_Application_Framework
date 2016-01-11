package net.kleditzsch.Test;

import net.kleditzsch.Edimax.SmartPug.SP1101;

import java.io.IOException;

/**
 * Created by oliver on 27.12.15.
 */
public class Test {

    public static void main(String[] args) throws IOException {

        //FritzBoxSmarthome fritzBoxSmarthome = new FritzBoxSmarthome("fritz.box", "oliver", "1988oliver");
        //fritzBoxSmarthome.listDevices();

        SP1101 sp1101 = new SP1101("192.168.115.185");
        sp1101.switchOn();
    }
}
