package fjbermudez.com.carriots.data.response;

import fjbermudez.com.carriots.data.repository.remote.ErrorBase;

public class GetTemperatureResponseError implements ErrorBase {

    String error;

    public GetTemperatureResponseError(String error) {
        this.error = error;
    }

    @Override
    public String ErrorDescription() {
        return error;
    }
}
