package rohitrj.com.bookfinder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    ArrayList arrayList;

    public BookAdapter(ArrayList<BookData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.show_books, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.seeDetails((BookData) arrayList.get(i));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void seeDetails(BookData bookData) {
            TextView title, author, summary,date;

            title = itemView.findViewById(R.id.textViewBookName);
            author = itemView.findViewById(R.id.textViewAuthor);
            summary = itemView.findViewById(R.id.textViewSummary);
            date=itemView.findViewById(R.id.textViewDate);

            date.setText(bookData.getDate());
            title.setText(bookData.getName());
            author.setText(bookData.getAuthor());
            summary.setText(bookData.getSummary());

        }
    }
}
