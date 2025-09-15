package shopcart.service;

import shopcart.model.Product;
import shopcart.model.CartItem;
import java.math.BigDecimal;
import java.util.*;

public class ShoppingCartService {

    private final Map<Integer, CartItem> items = new LinkedHashMap<>();

    public void addProduct(Product product, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be > 0");
        CartItem existing = items.get(product.getId());
        if (existing == null) {
            items.put(product.getId(), new CartItem(product, quantity));
        } else {
            existing.setQuantity(existing.getQuantity() + quantity);
        }
    }

    public void updateQuantity(int productId, int newQuantity) {
        CartItem existing = items.get(productId);
        if (existing == null) throw new NoSuchElementException("Product not in cart: " + productId);
        if (newQuantity <= 0) {
            items.remove(productId);
        } else {
            existing.setQuantity(newQuantity);
        }
    }

    public void removeProduct(int productId) {
        if (items.remove(productId) == null) {
            throw new NoSuchElementException("Product not in cart: " + productId);
        }
    }

    public BigDecimal getTotal() {
        return items.values()
                    .stream()
                    .map(CartItem::getSubtotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<CartItem> getItems() {
        return List.copyOf(items.values());
    }

    public void clear() { items.clear(); }
}
