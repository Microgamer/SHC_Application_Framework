package net.kleditzsch.Edimax.SmartPug;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Edimax SmartPlug SP-2101
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class SP2101 extends SP1101 {

    public class EnergyData {

        private LocalDateTime lastSwitchTime;

        private double nowCurrent;

        private double nowPower;

        private double dayEnergy;

        private double weekEnergy;

        private double monthEnergy;

        public EnergyData() {
        }

        public EnergyData(LocalDateTime lastSwitchTime, double nowCurrent, double nowPower, double dayEnergy, double weekEnergy, double monthEnergy) {
            this.lastSwitchTime = lastSwitchTime;
            this.nowCurrent = nowCurrent;
            this.nowPower = nowPower;
            this.dayEnergy = dayEnergy;
            this.weekEnergy = weekEnergy;
            this.monthEnergy = monthEnergy;
        }

        public LocalDateTime getLastSwitchTime() {
            return lastSwitchTime;
        }

        public void setLastSwitchTime(LocalDateTime lastSwitchTime) {
            this.lastSwitchTime = lastSwitchTime;
        }

        public double getNowCurrent() {
            return nowCurrent;
        }

        public void setNowCurrent(double nowCurrent) {
            this.nowCurrent = nowCurrent;
        }

        public double getNowPower() {
            return nowPower;
        }

        public void setNowPower(double nowPower) {
            this.nowPower = nowPower;
        }

        public double getDayEnergy() {
            return dayEnergy;
        }

        public void setDayEnergy(double dayEnergy) {
            this.dayEnergy = dayEnergy;
        }

        public double getWeekEnergy() {
            return weekEnergy;
        }

        public void setWeekEnergy(double weekEnergy) {
            this.weekEnergy = weekEnergy;
        }

        public double getMonthEnergy() {
            return monthEnergy;
        }

        public void setMonthEnergy(double monthEnergy) {
            this.monthEnergy = monthEnergy;
        }
    }

    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    /**
     * @param ip IP Adresse
     */
    public SP2101(String ip) {

        super(ip);
    }

    /**
     * @param ip IP Adresse
     * @param password Passwort
     */
    public SP2101(String ip, String password) {

        super(ip, password);
    }

    /**
     * @param ip IP Adresse
     * @param user Benutzername
     * @param password Passwort
     */
    public SP2101(String ip, String user, String password) {

        super(ip, user, password);
    }

    /**
     * gibt die Messdaten der Steckdose zurück
     *
     * @return Energiedaten
     * @throws IOException
     */
    public EnergyData getEnergyData() throws IOException {

        String request = "<?xml version=\"1.0\" encoding=\"UTF8\"?>" +
                "               <SMARTPLUG id=\"edimax\">" +
                "                   <CMD id=\"get\"> " +
                "                       <NOW_POWER></NOW_POWER> " +
                "                   </CMD> " +
                "               </SMARTPLUG>";

        String response = sendHttpCommand(request);
        Document doc = null;
        try {
            doc = new SAXBuilder().build(new StringReader(response));
            Element root = doc.getRootElement();
            Element cmd = root.getChild("CMD");
            Element data = cmd.getChild("NOW_POWER");

            EnergyData energyData = new EnergyData();

            String lastToggleTime = data.getChildText("Device.System.Power.LastToggleTime");
            LocalDateTime lastSwitchTime = LocalDateTime.parse(lastToggleTime, formatter);
            energyData.setLastSwitchTime(lastSwitchTime);
            energyData.setNowCurrent(Double.parseDouble(data.getChildText("Device.System.Power.NowCurrent")));
            energyData.setNowPower(Double.parseDouble(data.getChildText("Device.System.Power.NowPower")));
            energyData.setDayEnergy(Double.parseDouble(data.getChildText("Device.System.Power.NowEnergy.Day")));
            energyData.setWeekEnergy(Double.parseDouble(data.getChildText("Device.System.Power.NowEnergy.Week")));
            energyData.setMonthEnergy(Double.parseDouble(data.getChildText("Device.System.Power.NowEnergy.Month")));

            return energyData;
        } catch (JDOMException e) {}
        return null;
    }
}
