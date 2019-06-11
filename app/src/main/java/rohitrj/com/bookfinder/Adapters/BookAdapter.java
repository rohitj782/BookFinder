package rohitrj.com.bookfinder.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rohitrj.com.bookfinder.Models.BookData;
import rohitrj.com.bookfinder.Models.Items;
import rohitrj.com.bookfinder.Models.VolumeInfo;
import rohitrj.com.bookfinder.R;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    ArrayList arrayList;

    public BookAdapter(List<Items> arrayList) {
        this.arrayList = (ArrayList) arrayList;
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

        myViewHolder.seeDetails((Items) arrayList.get(i));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void seeDetails(Items items) {

            VolumeInfo volumeInfo= items.getVolumeInfo();

            TextView title, author, summary,date;

            title = itemView.findViewById(R.id.textViewBookName);
            author = itemView.findViewById(R.id.textViewAuthor);
            summary = itemView.findViewById(R.id.textViewSummary);
            date=itemView.findViewById(R.id.textViewDate);

            date.setText(volumeInfo.getPublishedDate());
            title.setText(volumeInfo.getTitle());

            String allAuthor = "";
            String authorsArray[]=volumeInfo.getAuthors();

            for(int i=0;i<authorsArray.length;i++) {
                if(i==authorsArray.length-1){
                    allAuthor+=authorsArray[i]+"";
                }else {
                    allAuthor+=authorsArray[i]+",  ";
                }
            }

            if(!allAuthor.contains("null"))
            author.setText(allAuthor);

            summary.setText(volumeInfo.getDescription());

        }
    }
}
