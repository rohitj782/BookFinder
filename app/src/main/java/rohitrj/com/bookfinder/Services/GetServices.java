package rohitrj.com.bookfinder.Services;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rohitrj.com.bookfinder.BookData;

public interface GetServices {

    //using multiple query parameter so queryMap is used.
    @GET
    Call<BookData> getBooks(@QueryMap HashMap<String,String> hashMap);
}
