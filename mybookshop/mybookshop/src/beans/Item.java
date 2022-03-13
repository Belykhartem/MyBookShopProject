package beans;

/**
 * Created by IntelliJ IDEA.
 * User: Alex
 * Date: 28.07.2004
 * Time: 10:14:26
 * To change this template use Options | File Templates.
 */

// This is an item in an inventory or shopping cart (same thing)
// with four properties: sku, (stock keeping unit) name, price,
// and amount. Items are nearly immutable to eliminate
// multithreading concerns.

public class Item implements java.io.Serializable {
    private final int sku; // stock keeping unit
    private final String name;
    private final String author;
    private final float price;
    private final int size;

    private float amount;

    public Item(int sku, String name, String author, float price, int size, float amount) {
        this.sku    = sku;
        this.name   = name;
        this.author = author;
        this.price  = price;
        this.size = size;
        this.amount = amount;
    }

    public int    getSku()    { return sku;    }
    public String getName()   { return name;   }
    public String getAuthor()   { return author;   }
    public float  getPrice()  { return price;  }
    public int getSize()   { return size;   }

    public synchronized float getAmount() {
        return amount;
    }
    public synchronized void setAmount(float amount) {
        this.amount = amount;
    }
}
