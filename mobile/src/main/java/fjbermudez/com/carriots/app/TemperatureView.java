package fjbermudez.com.carriots.app;

public interface TemperatureView {
    
    void updateView(double tempConsigna, int tempRoomInteger,int tempRoomDecimal, boolean boilerOn);

    void showError(String error);

    void hideSendButton();

    void showSendButton();

    void changeTempConsigna(String newTemperature);
}
