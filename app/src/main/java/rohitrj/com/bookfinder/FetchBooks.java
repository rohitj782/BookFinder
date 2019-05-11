package rohitrj.com.bookfinder;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class FetchBooks extends AsyncTask<String, Void, String> {

    private WeakReference<RecyclerView> rec;
    private WeakReference<Button> rawJSON;
    private WeakReference<ProgressBar> progressBar;


    FetchBooks(RecyclerView recyclerView, Button buttonJSON, ProgressBar pB) {
        progressBar = new WeakReference<>(pB);
        rawJSON = new WeakReference<>(buttonJSON);
        rec = new WeakReference<>(recyclerView);
    }

    @Override
    protected String doInBackground(String... strings) {
        return ConnectivityHelper.getBooks(strings[0]);
    }

    @Override
    protected void onPostExecute(final String s) {
        super.onPostExecute(s);

        rawJSON.get().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), RawJSON.class);
                intent.putExtra("RES", s);
                view.getContext().startActivity(intent);

            }
        });

        //Parsing JSON inside try catch block.

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            int i = 0;
            String title = null;
            String date=null;
            String summary = null;

            ArrayList arrayList = new ArrayList<BookData>();

            while (i < itemsArray.length()) {
                // Get the current item information.
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                // Try to get the author and title and description from the current item,
                // catch if either field is empty and move on.
                try {

                    summary = volumeInfo.getString("description");
                    title = volumeInfo.getString("title");
                    date=volumeInfo.getString("publishedDate");

                    JSONArray authorArray = volumeInfo.getJSONArray("authors");
                    int j = 0;

                    StringBuilder allAuthors = new StringBuilder();
                    while (j < authorArray.length()) {

                        if (j == authorArray.length() - 1) {
                            allAuthors.append(authorArray.get(j) + "");
                        } else
                            allAuthors.append(authorArray.get(j) + ", ");

                        //Move to next author (if there are more than one author).
                        j++;

                    }

                    BookData bookData = new BookData();

                    bookData.setAuthor(allAuthors.toString());
                    bookData.setName(title);
                    bookData.setSummary(summary);
                    bookData.setDate(date);

                    arrayList.add(bookData);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Move to the next item.
                i++;
            }

            BookAdapter bookAdapter = new BookAdapter(arrayList);
            rec.get().setAdapter(bookAdapter);
            progressBar.get().setVisibility(View.INVISIBLE);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
