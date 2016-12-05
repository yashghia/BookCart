/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookCart.Models;
import java.util.*;
/**
 *
 * @author yash_
 */
public class BookCart {
 
    private ArrayList<CartItem> books;
    
    public BookCart()
    {
        books = new ArrayList<CartItem>();
    }
    public ArrayList<CartItem> getItems() {
        return books;
    }

    public int getCount() {
        return books.size();
    }
 public void addItem(CartItem item) {
        int code = item.getBook().getBookId();
        int quantity = item.getQuantity();
        for (int i = 0; i < books.size(); i++) {
            CartItem cartItem = books.get(i);
            if (cartItem.getBook().getBookId() == code) {
                cartItem.setQuantity(quantity);
                return;
            }
        }
        books.add(item);
    }

    public void removeItem(CartItem item) {
        int code = item.getBook().getBookId();
        for (int i = 0; i < books.size(); i++) {
            CartItem cartItem = books.get(i);
            if (cartItem.getBook().getBookId()==code) {
                books.remove(i);
                return;
            }
        }
    }
}