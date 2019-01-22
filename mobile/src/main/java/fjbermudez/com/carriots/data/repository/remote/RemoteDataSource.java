package fjbermudez.com.carriots.data.repository.remote;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import fjbermudez.com.carriots.BuildConfig;
import fjbermudez.com.carriots.R;
import fjbermudez.com.carriots.app.AppVass;
import fjbermudez.com.carriots.data.repository.DataSource;
import fjbermudez.com.carriots.data.request.GetTemperatureRequest;
import fjbermudez.com.carriots.data.request.SentTemperatureRequest;
import fjbermudez.com.carriots.data.response.GetTemperatureResponseError;
import fjbermudez.com.carriots.data.response.GetTermostatoResponse;
import fjbermudez.com.carriots.data.response.SetTemperatureResponseError;
import fjbermudez.com.carriots.data.response.SetTermostatoResponse;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource implements DataSource {


    private static RemoteDataSource INSTANCE;
    ApiService apiServices = null;

    public RemoteDataSource() {

        OkHttpClient httpClient = configureOkHttpClient();
        Gson gson = new Gson();
        if ( BuildConfig.DEBUG ){
            gson = new GsonBuilder().setLenient().create();
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiServices = retrofit.create(ApiService.class);
    }

    public static RemoteDataSource getInstance(){
        if (INSTANCE == null){
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }

    @NonNull
    private OkHttpClient configureOkHttpClient() {

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();


        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder builder = request.newBuilder().header("Content-Type", "application/json").
                        header("User-Agent","S7@pacobh.pacobh").
                        header("carriots.apikey","c429553e4d460dae01d71a514e9ec796ed50f9159ad173499e50cb4ae78879c1");
                builder.cacheControl( CacheControl.FORCE_NETWORK );


                return chain.proceed(builder.build());
            }
        });

        //Network checking
        httpClientBuilder.addInterceptor(
                new ConnectivityInterceptor(
                        (ConnectivityManager) AppVass.getAppVassContext().getSystemService(Context.CONNECTIVITY_SERVICE),
                        AppVass.getAppVassContext().getString(R.string.connectivity_no_network_error_description)
                )
        );


        //setting timeouts
        httpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(45, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(60, TimeUnit.SECONDS);

        if(BuildConfig.DEBUG){
            httpClientBuilder.addInterceptor(createLogInterceptor());
        }



        return httpClientBuilder.build();
    }

    private HttpLoggingInterceptor createLogInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Override
    public void setTemperature(SentTemperatureRequest sentTemperatureRequest, final SentTemperatureCallback temperatureSentCallback) {
        Call<SetTermostatoResponse> setTermostatoResponseCall = apiServices.setTemperature(sentTemperatureRequest.getProtocol(),
                sentTemperatureRequest.getChecksum(),sentTemperatureRequest.getDevice(),
                String.valueOf(Calendar.getInstance().getTimeInMillis()),
                sentTemperatureRequest.getData());
        try {
            setTermostatoResponseCall.enqueue(new Callback<SetTermostatoResponse>() {
                @Override
                public void onResponse(Call<SetTermostatoResponse> call, Response<SetTermostatoResponse> response) {
                    if (response.isSuccessful() && response.body()!=null){
                        temperatureSentCallback.onSetTemperatureSuccess(response.body());
                    }else if (response.message()!=null){
                        temperatureSentCallback.onSetTemperatureFailure(new SetTemperatureResponseError(response.message()));
                    }
                }

                @Override
                public void onFailure(Call<SetTermostatoResponse> call, Throwable t) {
                    temperatureSentCallback.onSetTemperatureFailure(new SetTemperatureResponseError(t.getMessage()));
                }
            });
        }catch (Exception e){
            temperatureSentCallback.onSetTemperatureFailure(new SetTemperatureResponseError(e.getMessage()));
        }
    }

    @Override
    public void getTemperature(GetTemperatureRequest getTemperatureRequest, final GetTemperatureCallback getTemperatureCallback) {
        Call<GetTermostatoResponse> getTermostatoResponseCall = apiServices.getTemperature(getTemperatureRequest.getSort());
        try {
            getTermostatoResponseCall.enqueue(new Callback<GetTermostatoResponse>() {
                @Override
                public void onResponse(Call<GetTermostatoResponse> call, Response<GetTermostatoResponse> response) {
                    if (response.isSuccessful() && response.body()!=null){
                        getTemperatureCallback.onGetTemperatureSuccess(response.body());
                    }else if (response.message()!=null){
                        getTemperatureCallback.onGetTemperatureFailure(new GetTemperatureResponseError(response.message()));
                    }
                }

                @Override
                public void onFailure(Call<GetTermostatoResponse> call, Throwable t) {
                    getTemperatureCallback.onGetTemperatureFailure(new GetTemperatureResponseError(t.getMessage()));
                }
            });
        }catch (Exception e){
            getTemperatureCallback.onGetTemperatureFailure(new GetTemperatureResponseError(e.getMessage()));
        }
    }
}
