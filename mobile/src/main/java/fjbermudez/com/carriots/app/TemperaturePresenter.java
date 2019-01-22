package fjbermudez.com.carriots.app;

public interface TemperaturePresenter {

    void changeTemperature(String tempConsigna);

    void getTemperature();

    void reduceTemperature(String temperature);

    void increaseTemperature(String temperature);

    void validateTemperature(String s);
}
