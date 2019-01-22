package fjbermudez.com.carriots;

import android.content.Context;
import android.support.annotation.NonNull;

import fjbermudez.com.carriots.data.repository.DataProvider;
import fjbermudez.com.carriots.data.repository.remote.RemoteDataSource;
import fjbermudez.com.carriots.domain.threading.UseCaseHandler;
import fjbermudez.com.carriots.domain.usecases.GetTemperatureUseCase;
import fjbermudez.com.carriots.domain.usecases.SetTemperatureUseCase;

public class Injection {

    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }

    public static DataProvider provideDataSource(@NonNull Context context) {
        return DataProvider.getInstance(RemoteDataSource.getInstance());
    }

    public static GetTemperatureUseCase provideGetTemperatureUseCase(Context context) {
        return new GetTemperatureUseCase(Injection.provideDataSource(context));
    }

    public static SetTemperatureUseCase provideSetTempreatureUseCase(Context context) {
        return new SetTemperatureUseCase(Injection.provideDataSource(context));
    }
}
