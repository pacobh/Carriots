package fjbermudez.com.carriots.data.response;

import fjbermudez.com.carriots.data.repository.remote.ErrorBase;

public class SetTemperatureResponseError implements ErrorBase {

    String error;

    public SetTemperatureResponseError(String error) {
        this.error = error;
    }

    @Override
    public String ErrorDescription() {
        return error;
    }
}
