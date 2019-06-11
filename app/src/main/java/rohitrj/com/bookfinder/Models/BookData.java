package rohitrj.com.bookfinder.Models;

import java.util.ArrayList;
import java.util.List;

public class BookData {

    public BookData() { }

    String kind;
    int totalItems;
    List <Items> items =new ArrayList<>();

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
}
