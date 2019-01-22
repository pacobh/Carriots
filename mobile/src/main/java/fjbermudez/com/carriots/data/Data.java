package fjbermudez.com.carriots.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {


    @SerializedName("tempConsigna")
    @Expose
    double tempConsigna;

    @SerializedName("tempRoom")
    @Expose
    double tempRoom;

    @SerializedName("boilerOn")
    @Expose
    boolean boilerOn;

    public double getTempConsigna() {
        return tempConsigna;
    }

    public void setTempConsigna(double tempConsigna) {
        this.tempConsigna = tempConsigna;
    }

    public double getTempRoom() {
        return tempRoom;
    }

    public void setTempRoom(double tempRoom) {
        this.tempRoom = tempRoom;
    }

    public boolean isBoilerOn() {
        return boilerOn;
    }

    public void setBoilerOn(boolean boilerOn) {
        this.boilerOn = boilerOn;
    }
}
