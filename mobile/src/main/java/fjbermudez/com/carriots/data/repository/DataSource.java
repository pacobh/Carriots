package fjbermudez.com.carriots.data.repository;

import fjbermudez.com.carriots.data.repository.remote.ErrorBase;
import fjbermudez.com.carriots.data.request.GetTemperatureRequest;
import fjbermudez.com.carriots.data.request.SentTemperatureRequest;
import fjbermudez.com.carriots.data.response.GetTermostatoResponse;
import fjbermudez.com.carriots.data.response.SetTermostatoResponse;

public interface DataSource {

    interface SentTemperatureCallback {
        void onSetTemperatureSuccess(SetTermostatoResponse setTermostatoResponse);
        void onSetTemperatureFailure(ErrorBase errorBase);
    }
        interface GetTemperatureCallback {
        void onGetTemperatureSuccess(GetTermostatoResponse getTermostatoResponse);
        void onGetTemperatureFailure(ErrorBase errorBase);
    }

    void setTemperature(SentTemperatureRequest sentTemperatureRequest, SentTemperatureCallback temperatureSentCallback);

    void getTemperature(GetTemperatureRequest getTemperatureRequest, GetTemperatureCallback getTemperatureCallback);



}
