package fjbermudez.com.carriots.domain.usecases;

import fjbermudez.com.carriots.data.repository.DataSource;
import fjbermudez.com.carriots.data.repository.DataSource.SentTemperatureCallback;
import fjbermudez.com.carriots.data.repository.remote.ErrorBase;
import fjbermudez.com.carriots.data.request.SentTemperatureRequest;
import fjbermudez.com.carriots.data.response.SetTermostatoResponse;
import fjbermudez.com.carriots.domain.threading.UseCase;

public class SetTemperatureUseCase extends UseCase<SetTemperatureUseCase.RequestValues, SetTemperatureUseCase.ResponseValue> {

    private final DataSource dataSource;

    public SetTemperatureUseCase(DataSource dataSource) {
        this.dataSource = dataSource;
    }



    @Override
    protected void executeUseCase(SetTemperatureUseCase.RequestValues requestValues) {
        dataSource.setTemperature(requestValues.getSentTemperatureRequest(), new SentTemperatureCallback() {
            @Override
            public void onSetTemperatureSuccess(SetTermostatoResponse setTermostatoResponse) {
                getUseCaseCallback().onSuccess(new SetTemperatureUseCase.ResponseValue(setTermostatoResponse));

            }

            @Override
            public void onSetTemperatureFailure(ErrorBase errorBase) {
                getUseCaseCallback().onError(errorBase.ErrorDescription());
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {

        SentTemperatureRequest sentTemperatureRequest;

        public RequestValues( SentTemperatureRequest sentTemperatureRequest) {
            this.sentTemperatureRequest = sentTemperatureRequest;
        }


        public SentTemperatureRequest getSentTemperatureRequest() {
            return sentTemperatureRequest;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private final SetTermostatoResponse setTermostatoResponse;

        public ResponseValue(SetTermostatoResponse setTermostatoResponse) {
            this.setTermostatoResponse = setTermostatoResponse;
        }

        public SetTermostatoResponse getSetTermostatoResponse() {
            return setTermostatoResponse;
        }
    }
}
