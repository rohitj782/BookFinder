package rohitrj.com.bookfinder.Activiies;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rohitrj.com.bookfinder.Adapters.BookAdapter;
import rohitrj.com.bookfinder.Models.BookData;
import rohitrj.com.bookfinder.Models.Items;
import rohitrj.com.bookfinder.Models.VolumeInfo;
import rohitrj.com.bookfinder.R;
import rohitrj.com.bookfinder.Services.GetServices;
import rohitrj.com.bookfinder.Services.ServiceBuilder;

public class MainActivity extends AppCompatActivity {

    private Button buttonFind,buttonRawJson;
    private EditText editTextBook;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonFind=findViewById(R.id.buttonFind);
        editTextBook=findViewById(R.id.editTextBook);
        buttonRawJson=findViewById(R.id.buttonRawJson);
        recyclerView=findViewById(R.id.recyclerView);
        progressBar=findViewById(R.id.progressBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editTextBook.getText().toString().isEmpty()){
                    editTextBook.setError("Enter book name");
                    editTextBook.requestFocus();
                    return;
                }

                String book=editTextBook.getText().toString();

                //To close keyboard.
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                if (inputManager != null ) {
                    inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }

                //To check network connectivity.
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = null;
                if (connMgr != null) {
                    networkInfo = connMgr.getActiveNetworkInfo();
                }

                if (networkInfo != null && networkInfo.isConnected()) {
                    progressBar.setVisibility(View.VISIBLE);

                    // call the network via retrofit

                    GetServices services= (GetServices) ServiceBuilder.buildService(GetServices.class);
                    HashMap <String, String> hashMap = new HashMap<>();
                    hashMap.put("q",book);
                    hashMap.put("maxResults","40");
                    hashMap.put("printType","books");

                    final Call<BookData> request =services.getBooks(hashMap);


                    request.enqueue(new Callback<BookData>() {
                        @Override
                        public void onResponse(Call<BookData> call, Response<BookData> response) {
                            if(response.isSuccessful()){

                                progressBar.setVisibility(View.INVISIBLE);
                                Log.i("TAG",response.body().getTotalItems()+"");

                                List<Items> items= response.body().getItems();

                                BookAdapter bookAdapter=new BookAdapter(items);
                                recyclerView.setAdapter(bookAdapter);

                            }else {
                                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            }

                        }

                        @Override
                        public void onFailure(Call<BookData> call, Throwable t) {

                            Toast.makeText(MainActivity.this, "Error "+t, Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    });



                }
            }
        });

    }
}
