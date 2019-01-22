package fjbermudez.com.carriots.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import fjbermudez.com.carriots.data.Data;

class Result {

    @SerializedName("device")
    @Expose
    String device;

    @SerializedName("_id")
    @Expose
    String _id;

    @SerializedName("data")
    @Expose
    Data data;

    @SerializedName("created_at")
    @Expose
    long created_at;

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }
}
