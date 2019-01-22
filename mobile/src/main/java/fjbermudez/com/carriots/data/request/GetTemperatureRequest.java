package fjbermudez.com.carriots.data.request;

public class GetTemperatureRequest {
    String device = "";
    String sort = "created_at";

    public GetTemperatureRequest(){

    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }


}
