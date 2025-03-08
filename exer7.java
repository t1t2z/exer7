import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Slice_o_Heaven {
    // Final constants
    private final String DEF_ORDER_ID = "DEF-SOH-099";
    private final String DEF_PIZZA_INGREDIENTS = "Mozzarella Cheese";
    private final double DEF_ORDER_TOTAL = 15.00;
    private static final long BLACKLISTED_NUMBER = 12345678901234L; // 14位黑名单号码

    // Private instance variables
    private String orderID;
    private double orderTotal;
    private String pizzaIngredients;
    private String sides;
    private String drinks;
    private String pizzaSize;
    private boolean extraCheese;

    // Default constructor
    public Slice_o_Heaven() {
        this.orderID = DEF_ORDER_ID;
        this.pizzaIngredients = DEF_PIZZA_INGREDIENTS;
        this.orderTotal = DEF_ORDER_TOTAL;
        this.sides = "Nothing for me";
        this.drinks = "No drinks for me";
        this.pizzaSize = "Medium";
        this.extraCheese = false;
    }

    // Parameterized constructor
    public Slice_o_Heaven(String orderID, String pizzaIngredients, double orderTotal) {
        this.orderID = orderID;
        this.pizzaIngredients = pizzaIngredients;
        this.orderTotal = orderTotal;
        this.sides = "Nothing for me";
        this.drinks = "No drinks for me";
        this.pizzaSize = "Medium";
        this.extraCheese = false;
    }

    // Getters
    public String getOrderID() { return orderID; }
    public double getOrderTotal() { return orderTotal; }
    public String getPizzaIngredients() { return pizzaIngredients; }
    public String getSides() { return sides; }
    public String getDrinks() { return drinks; }
    public String getPizzaSize() { return pizzaSize; }
    public boolean getExtraCheese() { return extraCheese; }

    // Setters
    public void setOrderID(String orderID) { this.orderID = orderID; }
    public void setOrderTotal(double orderTotal) { this.orderTotal = orderTotal; }
    public void setPizzaIngredients(String pizzaIngredients) { this.pizzaIngredients = pizzaIngredients; }
    public void setSides(String sides) { this.sides = sides; }
    public void setDrinks(String drinks) { this.drinks = drinks; }
    public void setPizzaSize(String pizzaSize) { this.pizzaSize = pizzaSize; }
    public void setExtraCheese(boolean extraCheese) { this.extraCheese = extraCheese; }

    // Updated takeOrder method
    public void takeOrder() {
        Scanner scanner = new Scanner(System.in);
        boolean validInput;

        try {
            // 1. Ingredients selection
            int ingChoice1, ingChoice2, ingChoice3;
            String ing1, ing2, ing3;
            do {
                System.out.println("Please pick any three of the following ingredients:\n" +
                        "1. Mushroom\n" +
                        "2. Paprika\n" +
                        "3. Sun-dried tomatoes\n" +
                        "4. Chicken\n" +
                        "5. Pineapple\n" +
                        "Enter any three choices (1,2,3,...) separated by spaces:");
                ingChoice1 = scanner.nextInt();
                ingChoice2 = scanner.nextInt();
                ingChoice3 = scanner.nextInt();

                validInput = (ingChoice1 >= 1 && ingChoice1 <= 5) &&
                            (ingChoice2 >= 1 && ingChoice2 <= 5) &&
                            (ingChoice3 >= 1 && ingChoice3 <= 5);

                if (!validInput) {
                    System.out.println("Invalid choice(s). Please pick only from the given list:");
                }
            } while (!validInput);

            // Convert choices to ingredients using switch-case
            ing1 = switch (ingChoice1) {
                case 1 -> "Mushroom";
                case 2 -> "Paprika";
                case 3 -> "Sun-dried tomatoes";
                case 4 -> "Chicken";
                case 5 -> "Pineapple";
                default -> "";
            };
            ing2 = switch (ingChoice2) {
                case 1 -> "Mushroom";
                case 2 -> "Paprika";
                case 3 -> "Sun-dried tomatoes";
                case 4 -> "Chicken";
                case 5 -> "Pineapple";
                default -> "";
            };
            ing3 = switch (ingChoice3) {
                case 1 -> "Mushroom";
                case 2 -> "Paprika";
                case 3 -> "Sun-dried tomatoes";
                case 4 -> "Chicken";
                case 5 -> "Pineapple";
                default -> "";
            };
            this.pizzaIngredients = ing1 + ", " + ing2 + ", " + ing3;

            // 2. Pizza size selection
            int sizeChoice;
            do {
                System.out.println("What size should your pizza be?\n" +
                        "1. Large\n" +
                        "2. Medium\n" +
                        "3. Small\n" +
                        "Enter only one choice (1,2, or 3):");
                sizeChoice = scanner.nextInt();

                validInput = (sizeChoice >= 1 && sizeChoice <= 3);
                if (!validInput) {
                    System.out.println("Invalid choice. Please pick only from the given list:");
                }
            } while (!validInput);

            this.pizzaSize = switch (sizeChoice) {
                case 1 -> "Large";
                case 2 -> "Medium";
                case 3 -> "Small";
                default -> "Medium";
            };

            // 3. Extra cheese
            String extraCheeseInput;
            do {
                System.out.println("Do you want extra cheese (Y/N):");
                extraCheeseInput = scanner.next().toUpperCase();
                validInput = extraCheeseInput.equals("Y") || extraCheeseInput.equals("N");
                if (!validInput) {
                    System.out.println("Invalid choice. Please enter Y or N:");
                }
            } while (!validInput);
            this.extraCheese = extraCheeseInput.equals("Y");

            // 4. Side dish selection
            int sideDishChoice;
            do {
                System.out.println("Following are the side dish that go well with your pizza:\n" +
                        "1. Calzone\n" +
                        "2. Garlic bread\n" +
                        "3. Chicken puff\n" +
                        "4. Muffin\n" +
                        "5. Nothing for me\n" +
                        "What would you like? Pick one (1,2,3,...):");
                sideDishChoice = scanner.nextInt();

                validInput = (sideDishChoice >= 1 && sideDishChoice <= 5);
                if (!validInput) {
                    System.out.println("Invalid choice. Please pick only from the given list:");
                }
            } while (!validInput);

            this.sides = switch (sideDishChoice) {
                case 1 -> "Calzone";
                case 2 -> "Garlic bread";
                case 3 -> "Chicken puff";
                case 4 -> "Muffin";
                case 5 -> "Nothing for me";
                default -> "Nothing for me";
            };

            // 5. Drink selection
            int drinkChoice;
            do {
                System.out.println("Choose from one of the drinks below. We recommend Coca Cola:\n" +
                        "1. Coca Cola\n" +
                        "2. Cold coffee\n" +
                        "3. Cocoa Drink\n" +
                        "4. No drinks for me\n" +
                        "Enter your choice:");
                drinkChoice = scanner.nextInt();

                validInput = (drinkChoice >= 1 && drinkChoice <= 4);
                if (!validInput) {
                    System.out.println("Invalid choice. Please pick only from the given list:");
                }
            } while (!validInput);

            this.drinks = switch (drinkChoice) {
                case 1 -> "Coca Cola";
                case 2 -> "Cold coffee";
                case 3 -> "Cocoa Drink";
                case 4 -> "No drinks for me";
                default -> "No drinks for me";
            };

            // 6. Discount question
            String wantDiscount;
            do {
                System.out.println("Would you like the chance to pay only half for your order? (Y/N):");
                wantDiscount = scanner.next().toUpperCase();
                validInput = wantDiscount.equals("Y") || wantDiscount.equals("N");
                if (!validInput) {
                    System.out.println("Invalid choice. Please enter Y or N:");
                }
            } while (!validInput);

            if (wantDiscount.equals("Y")) {
                isItYourBirthday();
            } else {
                makeCardPayment();
            }
        } finally {
            scanner.close(); // Close the scanner to prevent resource leak
        }
    }

    // Updated isItYourBirthday method
    public void isItYourBirthday() {
        Scanner scanner = new Scanner(System.in);
        LocalDate today = LocalDate.now();
        LocalDate birthdate;
        boolean validDate;

        try {
            do {
                System.out.println("Enter your birthdate (YYYY-MM-DD):");
                String birthdateStr = scanner.next();
                try {
                    birthdate = LocalDate.parse(birthdateStr, DateTimeFormatter.ISO_LOCAL_DATE);
                    Period age = Period.between(birthdate, today);
                    validDate = age.getYears() >= 5 && age.getYears() <= 120;
                    if (!validDate) {
                        System.out.println("Invalid date. You are either too young or too dead to order.\n" +
                                "Please enter a valid date:");
                    }
                } catch (Exception e) {
                    validDate = false;
                    System.out.println("Invalid date. You are either too young or too dead to order.\n" +
                            "Please enter a valid date:");
                }
            } while (!validDate);

            boolean isBirthday = birthdate.getMonth() == today.getMonth() && 
                               birthdate.getDayOfMonth() == today.getDayOfMonth();
            
            if (isBirthday) {
                System.out.println("Congratulations! You pay only half the price for your order.");
                this.orderTotal /= 2;
            } else {
                System.out.println("Too bad! You do not meet the conditions to get our 50% discount.");
                makeCardPayment();
            }
        } finally {
            scanner.close(); // Close the scanner to prevent resource leak
        }
    }

    // Updated makeCardPayment method
    public void makeCardPayment() {
        Scanner scanner = new Scanner(System.in);
        long cardNumber;
        String expiryDate;
        int cvv;
        LocalDate today = LocalDate.now();
        boolean validDate;

        try {
            // Card number
            System.out.println("Enter your card number:");
            cardNumber = scanner.nextLong();

            // Expiry date
            do {
                System.out.println("Enter the card's expiry date (YYYY-MM):");
                expiryDate = scanner.next();
                try {
                    LocalDate expDate = LocalDate.parse(expiryDate + "-01", DateTimeFormatter.ISO_LOCAL_DATE);
                    validDate = expDate.isAfter(today);
                    if (!validDate) {
                        System.out.println("Invalid expiry date. Please enter a future date:");
                    }
                } catch (Exception e) {
                    validDate = false;
                    System.out.println("Invalid expiry date. Please enter a future date:");
                }
            } while (!validDate);

            // CVV
            System.out.println("Enter the card's CVV number:");
            cvv = scanner.nextInt();

            processCardPayment(cardNumber, expiryDate, cvv);
        } finally {
            scanner.close(); // Close the scanner to prevent resource leak
        }
    }

    // Updated processCardPayment method
    public void processCardPayment(long cardNumber, String expiryDate, int cvv) {
        Scanner scanner = new Scanner(System.in);
        String cardNumberStr;
        boolean validCard;

        try {
            do {
                cardNumberStr = Long.toString(cardNumber);
                validCard = cardNumberStr.length() == 14 && cardNumber != BLACKLISTED_NUMBER;
                if (!validCard) {
                    System.out.println("Invalid card number. Must be 14 digits and not blacklisted.\n" +
                            "Enter your card number:");
                    cardNumber = scanner.nextLong();
                }
            } while (!validCard);

            System.out.println("Card accepted");
            // Removed unused variables firstCardDigit and lastFourDigits
            String cardNumberToDisplay = cardNumberStr.charAt(0) + 
                                       "*".repeat(cardNumberStr.length() - 5) + 
                                       cardNumberStr.substring(cardNumberStr.length() - 4);
            System.out.println("Card Number to Display: " + cardNumberToDisplay);
        } finally {
            scanner.close(); // Close the scanner to prevent resource leak
        }
    }

    // Special of the day method (unchanged)
    public void specialOfTheDay(String pizzaOfTheDay, String sideOfTheDay, String specialPrice) {
        System.out.println("\n--- Special of the Day ---");
        System.out.println("Pizza: " + pizzaOfTheDay);
        System.out.println("Side: " + sideOfTheDay);
        System.out.println("Price: " + specialPrice);
    }

    // toString method replacing printReceipt
    @Override
    public String toString() {
        return "\n--- Slice-o-Heaven Receipt ---\n" +
               "Order ID: " + orderID + "\n" +
               "Pizza Ingredients: " + pizzaIngredients + "\n" +
               "Size: " + pizzaSize + "\n" +
               "Extra Cheese: " + (extraCheese ? "Yes" : "No") + "\n" +
               "Quantity: 1\n" +
               "Sides: " + sides + "\n" +
               "Drinks: " + drinks + "\n" +
               "Total: $" + String.format("%.2f", orderTotal) + "\n" +
               "Thank you for your order!";
    }

    public static void main(String[] args) {
        Slice_o_Heaven order = new Slice_o_Heaven();
        order.takeOrder();
        System.out.println(order); // 使用 toString 方法
    }
}