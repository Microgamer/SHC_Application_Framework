package net.kleditzsch.shcCore.ClientData.Device;

import net.kleditzsch.shcCore.ClientData.AbstractResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Geräteliste
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class DeviceResponse extends AbstractResponse {

    protected List<DeviceData> deviceDataList = new ArrayList<>();

    public List<DeviceData> getDeviceDataList() {
        return deviceDataList;
    }
}
