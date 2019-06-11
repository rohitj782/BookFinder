package rohitrj.com.bookfinder.Services;

import org.json.JSONObject;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rohitrj.com.bookfinder.Models.BookData;

public interface GetServices {

    //using multiple query parameter so queryMap is used.
    //get the result
    @GET("volumes")
    Call<BookData> getBooks(@QueryMap HashMap<String,String> hashMap);
}
