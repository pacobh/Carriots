package fjbermudez.com.carriots.data.repository;

import fjbermudez.com.carriots.data.request.GetTemperatureRequest;
import fjbermudez.com.carriots.data.request.SentTemperatureRequest;

public class DataProvider implements DataSource{

    private static DataProvider INSTANCE = null;
    private final DataSource remoteDataSource ;

    //region repository_methods

    public DataProvider(DataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    /**
     * Devuelve la instancia de la clase, creandola si es necesario.
     * @param remoteDataSource el servicio web.
     * @return la instancia actual
     */
    public static synchronized DataProvider getInstance(DataSource remoteDataSource){

        if (INSTANCE == null){
            INSTANCE = new DataProvider(remoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void setTemperature(SentTemperatureRequest sentTemperatureRequest, SentTemperatureCallback temperatureSentCallback) {
        remoteDataSource.setTemperature(sentTemperatureRequest,temperatureSentCallback);
    }

    @Override
    public void getTemperature(GetTemperatureRequest getTemperatureRequest, GetTemperatureCallback getTemperatureCallback) {
        remoteDataSource.getTemperature(getTemperatureRequest,getTemperatureCallback);
    }
}
