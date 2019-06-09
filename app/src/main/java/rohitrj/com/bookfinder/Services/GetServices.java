package rohitrj.com.bookfinder.Services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rohitrj.com.bookfinder.BookData;

public interface GetServices {

    @GET()
    Call<BookData> getBooks(@Query("q") String q );
}
