package fjbermudez.com.carriots.data.repository.remote;

import fjbermudez.com.carriots.data.Data;
import fjbermudez.com.carriots.data.response.GetTermostatoResponse;
import fjbermudez.com.carriots.data.response.SetTermostatoResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("streams")
    Call<GetTermostatoResponse> getTemperature(@Query("sort") String order);

    @POST("streams")
    @FormUrlEncoded
    Call<SetTermostatoResponse> setTemperature(@Field("protocol")String protocol,
                                               @Field("checksum")String checksum,
                                               @Field("device")String device,
                                               @Field("at")String at,
                                               @Field("data")Data data);
}
