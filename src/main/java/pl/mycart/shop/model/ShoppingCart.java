package pl.mycart.shop.model;

import pl.mycart.shop.util.Util;

import java.io.Serializable;
import java.util.*;

public class ShoppingCart implements Serializable {
    private static final long serialVersionUID = -4461143736086272397L;

    private Map<Long, ShoppingCartItem> items = new HashMap<Long, ShoppingCartItem>();

    public ShoppingCart() {
    }

    public List<ShoppingCartItem> getItems() {
        List<ShoppingCartItem> list = new ArrayList<ShoppingCartItem>(items.values());
        Collections.sort(list);
        return list;
    }

    public void addItem(Product product) {
        long productId = product.getId();
        ShoppingCartItem item = items.get(productId);
        if (item != null) {
            item.incrementQuantity();
        } else {
            items.put(productId, new ShoppingCartItem(product, 1));
        }
    }

    public int getItemCount() {
        int count = 0;
        for (ShoppingCartItem item : items.values()) {
            count += item.getQuantity();
        }
        return count;
    }

    public String getTotalPriceInDollars() {
        int total = 0;
        for (ShoppingCartItem item : items.values()) {
            total += item.getTotalPriceInCents();
        }
        return Util.getPriceInDollars(total);
    }

    public void clear() {
        items.clear();
    }
}
