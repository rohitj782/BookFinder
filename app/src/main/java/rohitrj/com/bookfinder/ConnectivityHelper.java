package rohitrj.com.bookfinder;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectivityHelper {

    public static String getBooks(String name) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJSONString = null;
        String TAG = "ConnectivityHelper";

        final String BASEURL = "https://www.googleapis.com/books/v1/volumes?";
        final String QUERY = "q";
        final String MAX_RES = "maxResults";
        final String PRINT_TYPE = "printType";


        try {
            Uri requestURI = Uri.parse(BASEURL).buildUpon()
                    .appendQueryParameter(QUERY, name)
                    .appendQueryParameter(MAX_RES, "20")
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .build();

            final URL url = new URL(requestURI.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            StringBuilder builder = new StringBuilder();   //to hold the result.

            String line; //to read input line by line.

            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }

            if (builder.length() == 0) {
                // Stream was empty. No point in parsing.
                return null;
            }

            bookJSONString = builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Log.i(TAG, bookJSONString);
        return bookJSONString;
    }
}
