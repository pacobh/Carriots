package fjbermudez.com.carriots.domain.usecases;

import fjbermudez.com.carriots.data.repository.DataSource;
import fjbermudez.com.carriots.data.repository.remote.ErrorBase;
import fjbermudez.com.carriots.data.request.GetTemperatureRequest;
import fjbermudez.com.carriots.data.response.GetTermostatoResponse;
import fjbermudez.com.carriots.domain.threading.UseCase;

public class GetTemperatureUseCase extends UseCase<GetTemperatureUseCase.RequestValues, GetTemperatureUseCase.ResponseValue> {

    private final DataSource dataSource;

    public GetTemperatureUseCase(DataSource dataSource) {
        this.dataSource = dataSource;
        }


      @Override
      protected void executeUseCase(GetTemperatureUseCase.RequestValues requestValues) {
        dataSource.getTemperature(requestValues.getGetTemperatureRequest(),new DataSource.GetTemperatureCallback() {
            @Override
            public void onGetTemperatureSuccess(GetTermostatoResponse getTermostatoResponse) {
                getUseCaseCallback().onSuccess(new GetTemperatureUseCase.ResponseValue(getTermostatoResponse));
            }

            @Override
            public void onGetTemperatureFailure(ErrorBase errorBase) {
                getUseCaseCallback().onError(errorBase.ErrorDescription());

            }
        });
        }

    public static final class RequestValues implements UseCase.RequestValues {

    GetTemperatureRequest getTemperatureRequest;

    public RequestValues(GetTemperatureRequest getTemperatureRequest) {
        this.getTemperatureRequest = getTemperatureRequest;
    }

        public GetTemperatureRequest getGetTemperatureRequest() {
            return getTemperatureRequest;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private final GetTermostatoResponse termostatoResponse;

        public ResponseValue( GetTermostatoResponse termostatoResponse) {
            this.termostatoResponse = termostatoResponse;
        }

        public GetTermostatoResponse getTermostatoResponse() {
            return termostatoResponse;
        }
    }


}
