package net.kleditzsch.Edimax.SmartPug;

import java.time.LocalDateTime;

/**
 * Edimax SmartPlug SP-1101
 */
public class SP2101 extends SP1101 {

    private class EnergyData {

        private LocalDateTime lastSwitchTime;

        private float nowCurrent;

        private float nowPower;

        private float dayEnergy;

        private float weekEnergy;

        private float monthEnergy;

        public EnergyData(String lastSwitch, float nowCurrent, float nowPower, float dayEnergy, float weekEnergy, float monthEnergy) {


        }
    }

    public SP2101(String ip) {

        super(ip);
    }

    public SP2101(String ip, String password) {

        super(ip, password);
    }

    public SP2101(String ip, String user, String password) {

        super(ip, user, password);
    }

    public EnergyData getEnergyData() {

        return null;
    }
}
