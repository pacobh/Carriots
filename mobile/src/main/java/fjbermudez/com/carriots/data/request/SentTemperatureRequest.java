package fjbermudez.com.carriots.data.request;


import fjbermudez.com.carriots.data.Data;

public class SentTemperatureRequest {

    String protocol ="v2";
    String checksum = "";
    String device ="S7@pacobh.pacobh";
    String at = "now";
    Data data;

    public SentTemperatureRequest(){

    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
