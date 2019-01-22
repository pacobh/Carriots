package fjbermudez.com.carriots.app;

import org.w3c.dom.CharacterData;

import fjbermudez.com.carriots.data.Data;
import fjbermudez.com.carriots.data.request.GetTemperatureRequest;
import fjbermudez.com.carriots.data.request.SentTemperatureRequest;
import fjbermudez.com.carriots.domain.threading.UseCase;
import fjbermudez.com.carriots.domain.threading.UseCaseHandler;
import fjbermudez.com.carriots.domain.usecases.GetTemperatureUseCase;
import fjbermudez.com.carriots.domain.usecases.SetTemperatureUseCase;

public class TemperaturePresenterImp implements TemperaturePresenter {

    private GetTemperatureUseCase getTemperatureUseCase;
    private SetTemperatureUseCase setTemperatureUseCase;
    private UseCaseHandler mUseCaseHandler;
    private TemperatureView mView;
    private final double MODIFY_TEMP = 0.5;
    private double tempConsigna;
    private double tempConsignaChanged;

    public TemperaturePresenterImp(GetTemperatureUseCase getTemperatureUseCase,
                                   SetTemperatureUseCase setTemperatureUseCase,
                                   TemperatureView mView,
                                   UseCaseHandler mUseCaseHandler) {
        this.getTemperatureUseCase = getTemperatureUseCase;
        this.setTemperatureUseCase = setTemperatureUseCase;
        this.mView = mView;
        this.mUseCaseHandler = mUseCaseHandler;
    }


    @Override
    public void changeTemperature(String tempConsigna) {

        SentTemperatureRequest sentTemperatureRequest = new SentTemperatureRequest();
        Data data = new Data();
        data.setBoilerOn(true);
        data.setTempConsigna(NumberUtils.getDoubleAmountFromString(tempConsigna));
        data.setTempRoom(22);
        mUseCaseHandler.execute(setTemperatureUseCase, new SetTemperatureUseCase.RequestValues(sentTemperatureRequest), new UseCase.UseCaseCallback<SetTemperatureUseCase.ResponseValue>() {
            @Override
            public void onSuccess(SetTemperatureUseCase.ResponseValue response) {
                if(response != null){
//                    mView.updateView(response.getSetTermostatoResponse().getTempConsigna(),
//                            response.getSetTermostatoResponse().getTempRoom(), response.getSetTermostatoResponse().isBoilerON());
                }
            }

            @Override
            public void onError(String error) {
                mView.showError(error);
            }
        });

    }

    @Override
    public void getTemperature() {
        GetTemperatureRequest getTemperatureRequest = new GetTemperatureRequest();

        mUseCaseHandler.execute(getTemperatureUseCase, new GetTemperatureUseCase.RequestValues(getTemperatureRequest), new UseCase.UseCaseCallback<GetTemperatureUseCase.ResponseValue>() {
            @Override
            public void onSuccess(GetTemperatureUseCase.ResponseValue response) {
                if(response != null){
//                    mView.updateView(response.getTermostatoResponse().getTempConsigna(),
//                            response.getTempRoom(),
//                            response.getTermostatoResponse().isBoilerON());
                }
            }

            @Override
            public void onError(String error) {
                mView.showError(error);
            }
        });

    }

    @Override
    public void reduceTemperature(String temperature) {
        double oldTemperature = tempConsigna;
        double newTemperature = NumberUtils.getDoubleAmountFromString(temperature) - MODIFY_TEMP;

        if (Double.compare(newTemperature, oldTemperature) != 0) {
            mView.showSendButton();
        } else {
            mView.hideSendButton();
        }

        mView.changeTempConsigna(String.valueOf(newTemperature));

    }

    @Override
    public void increaseTemperature(String temperature) {

        double oldTemperature = tempConsigna;
        double newTemperature = NumberUtils.getDoubleAmountFromString(temperature) + MODIFY_TEMP;

        if (Double.compare(newTemperature, oldTemperature) != 0) {
            mView.showSendButton();
        } else {
            mView.hideSendButton();
        }

        mView.changeTempConsigna(String.valueOf(newTemperature));
    }

    @Override
    public void validateTemperature(String s) {
        mView.showSendButton();
    }
}
