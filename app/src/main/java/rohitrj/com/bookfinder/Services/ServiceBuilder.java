package rohitrj.com.bookfinder.Services;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {

    //the server URL
    final String URL="https://www.googleapis.com/books/v1/volumes";

    OkHttpClient.Builder okHttpClient= new OkHttpClient.Builder();

    Retrofit retrofit= new Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build()).build();

    public Object buildService(Class<Object> sericeType){
        return retrofit.create(sericeType);
    }
    
}
