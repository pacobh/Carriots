package fjbermudez.com.carriots.data.repository.remote;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by franciscojose.bermud on 28/06/2018.
 */

public class ConnectivityInterceptor implements Interceptor {

    private ConnectivityManager cManager;
    private String messageErrorDescription;

    public ConnectivityInterceptor(ConnectivityManager cm, String messageErrorDescription) {
        this.cManager = cm;
        this.messageErrorDescription = messageErrorDescription;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {

        NetworkInfo activeNetwork = cManager.getActiveNetworkInfo();
        boolean isNetworkActive = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (!isNetworkActive) {
            throw new NoConnectivityException();
        }
        else {
            Response response = chain.proceed(chain.request());
            return response;
        }
    }

    public class NoConnectivityException extends IOException {

        @Override
        public String getMessage() {
            return messageErrorDescription;
        }
    }
}

