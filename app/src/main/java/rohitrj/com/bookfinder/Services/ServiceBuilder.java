package rohitrj.com.bookfinder.Services;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {

    //the server URL
    static final String URL = "https://www.googleapis.com/books/v1/";

    static final HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);


    static OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
            .callTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(logging);

    static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build());

    static Retrofit retrofit = builder.build();

    public static Object buildService(Class<GetServices> sericeType) {
        return retrofit.create(sericeType);
    }

}
