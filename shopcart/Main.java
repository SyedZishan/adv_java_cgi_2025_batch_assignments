package shopcart;

import shopcart.model.Product;
import shopcart.service.ShoppingCartService;
import java.math.BigDecimal;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ShoppingCartService cart = new ShoppingCartService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Shopping Cart Menu ===");
            System.out.println("1. Add product");
            System.out.println("2. Update quantity");
            System.out.println("3. Remove product");
            System.out.println("4. View cart");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        System.out.print("Enter product id: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter product name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter product price: ");
                        BigDecimal price = new BigDecimal(scanner.nextLine());
                        System.out.print("Enter quantity: ");
                        int qty = Integer.parseInt(scanner.nextLine());
                        cart.addProduct(new Product(id, name, price), qty);
                        System.out.println("Product added.");
                        break;
                    case "2":
                        System.out.print("Enter product id to update: ");
                        int updateId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter new quantity: ");
                        int newQty = Integer.parseInt(scanner.nextLine());
                        cart.updateQuantity(updateId, newQty);
                        System.out.println("Quantity updated.");
                        break;
                    case "3":
                        System.out.print("Enter product id to remove: ");
                        int removeId = Integer.parseInt(scanner.nextLine());
                        cart.removeProduct(removeId);
                        System.out.println("Product removed.");
                        break;
                    case "4":
                        printCart(cart);
                        break;
                    case "5":
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void printCart(ShoppingCartService cart) {
        System.out.println("Cart items:");
        if (cart.getItems().isEmpty()) {
            System.out.println("  (empty)");
        } else {
            cart.getItems().forEach(item ->
                    System.out.println("  " + item.getProduct().getName() +
                            " x " + item.getQuantity() +
                            " = " + item.getSubtotal())
            );
        }
        System.out.println("Total: " + cart.getTotal());
    }
}

