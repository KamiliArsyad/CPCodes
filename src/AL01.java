import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidParameterException;
import java.util.*;

/**
 * Since we can only submit one class. I have to pack everything
 * into one class. There's also an issue of performance which, for most coding assessment,
 * is quite easy as I don't have to worry about the design of the code. I will try to
 * balance the two in this class.
 */
public class AL01 {
    static class OrderBook {
        /**
         * Rational: Since every time we process an order we have to scan in log(n) times the entire
         * order book anyways, we can just occasionally sort the order book. Quicksort for the possible
         * size of the array is much faster than maintaining a sorted treemap assuming it fits into
         * a single cache line.
         */
        private List<LimitOrder> buyOrders;
        private List<LimitOrder> sellOrders;

        // Management variables
        private int previousSellSize;
        private int previousBuySize;
        private final float MAX_SIZE = 1.5f;
        private final float PROBABILITYOFREBUILD = 0.2f;

        public OrderBook() {
            buyOrders = new ArrayList<>();
            sellOrders = new ArrayList<>();
            previousBuySize = 100;
            previousSellSize = 100;
        }

        public void processMarketOrder(MarketOrder marketOrder) throws InvalidParameterException {
            managePerformance(marketOrder.isBuyOrder());
            int sum = 0;
            if (marketOrder.isBuyOrder()) {
                // Buy order
                // Sort the order book
                sellOrders.sort(LimitOrder::compareTo);
                for (LimitOrder sellOrder : sellOrders) {
                    int quantitySold = marketOrder.buyFrom(sellOrder);
                    sum += quantitySold * sellOrder.getPrice();

                    if (!marketOrder.is_available()) {
                        break;
                    }
                }
            } else {
                // Sell order
                buyOrders.sort(Comparator.reverseOrder());
                for (LimitOrder buyOrder : buyOrders) {
                    int quantityBought = marketOrder.sellTo(buyOrder);
                    sum += quantityBought * buyOrder.getPrice();
                    if (!marketOrder.is_available()) {
                        break;
                    }
                }
            }

            System.out.println(sum);
        }

        public void processLimitOrder(LimitOrder limitOrder) throws InvalidParameterException {
            managePerformance(limitOrder.isBuyOrder());
            int sum = 0;
            if (limitOrder.isBuyOrder()) {
                // Buy order
                // Sort the order book where low price is first
                sellOrders.sort(LimitOrder::compareTo);
                for (LimitOrder sellOrder : sellOrders) {
                    if (limitOrder.getPrice() < sellOrder.getPrice()) {
                        continue;
                    }
                    int quantitySold = limitOrder.buyFrom(sellOrder);
                    sum += quantitySold * sellOrder.getPrice();
                    if (!limitOrder.is_available()) {
                        break;
                    }
                }

                // If the order is still available, add it to the order book
                if (limitOrder.is_available()) {
                    buyOrders.add(limitOrder);
                }
            } else {
                // Sell order
                // Sort the order book where high price is first
                buyOrders.sort(Comparator.reverseOrder());
                for (LimitOrder buyOrder : buyOrders) {
                    if (limitOrder.getPrice() > buyOrder.getPrice()) {
                        continue;
                    }
                    int quantityBought = limitOrder.sellTo(buyOrder);
                    sum += quantityBought * buyOrder.getPrice();
                    if (!limitOrder.is_available()) {
                        break;
                    }
                }

                if (limitOrder.is_available()) {
                    sellOrders.add(limitOrder);
                }
            }

            System.out.println(sum);
        }

        /**
         * Sets the quantity of the order to 0.
         */
        public void processCancelOrder(String orderID) {
            for (LimitOrder buyOrder : buyOrders) {
                if (buyOrder.sameID(orderID)) {
                    buyOrder.setUnavailable();
                    return;
                }
            }

            for (LimitOrder sellOrder : sellOrders) {
                if (sellOrder.sameID(orderID)) {
                    sellOrder.setUnavailable();
                    return;
                }
            }
        }

        // PERFORMANCE MANAGEMENT FUNCTIONS ========================================

        /**
         * Rebuild the order book if the size is too big.
         * @param buyOrder
         */
        private void managePerformance(boolean buyOrder) {
            if (buyOrder && buyOrders.size() > MAX_SIZE * previousBuySize && Math.random() < PROBABILITYOFREBUILD) {
                rebuildOrderBook(true);
            } else if (!buyOrder && sellOrders.size() > MAX_SIZE * previousSellSize && Math.random() < PROBABILITYOFREBUILD) {
                rebuildOrderBook(false);
            }
        }

        /**
         * Eliminates the unavailable orders and rebuilds the order book.
         * @param buyOrder
         */
        private void rebuildOrderBook(boolean buyOrder) {
            List<LimitOrder> orders = new ArrayList<>();
            if (buyOrder) {
                for (LimitOrder order : buyOrders) {
                    if (order.is_available()) {
                        orders.add(order);
                    }
                }

                previousBuySize = orders.size();
                this.buyOrders = orders;
            } else {
                for (LimitOrder order : sellOrders) {
                    if (order.is_available()) {
                        orders.add(order);
                    }
                }

                previousSellSize = orders.size();
                this.sellOrders = orders;
            }
        }

        // ========================================================================

        @Override
        public String toString() {
            // Sort both lists
            buyOrders.sort(Comparator.reverseOrder());
            sellOrders.sort(LimitOrder::compareTo);
            StringBuilder sb = new StringBuilder();
            sb.append("B: ");
            for (LimitOrder order : buyOrders) {
                if (order.is_available()) {
                    sb.append(order.toString());
                    sb.append(" ");
                }
            }
            sb.append("\nS: ");
            for (LimitOrder order : sellOrders) {
                if (order.is_available()) {
                    sb.append(order.toString());
                    sb.append(" ");
                }
            }
            return sb.toString();
        }


    }

    static abstract class Order {
        static private int orderCount = 0;
        private String orderID;
        private int quantity;
        /* type 0: buy; type 1: sell */
        private boolean type;

        public Order(String orderID, int quantity, boolean type) {
            this.orderID = orderID;
            this.quantity = quantity;
            this.type = type;
            orderCount++;
        }

        public boolean isBuyOrder() {
            return this.type;
        }

        public boolean is_available() {
            return this.quantity > 0;
        }

        public void setUnavailable() {
            this.quantity = 0;
        }

        /**
         * This function does not handle whether the price is correct. The limit order type
         * has to be a sell order and the order to sell to has to be a buy order. The function also
         * does not handle the order book if the order is completely sold.
         *
         * @param buyOrder The buy order to sell to.
         * @return The quantity of the order that is sold.
         * @throws InvalidParameterException
         */
        public int sellTo(Order buyOrder) throws InvalidParameterException {
            if (!buyOrder.type) {
                throw new InvalidParameterException("buyOrder is not a buy order");
            }

            // Sell as much as possible.
            int quantitySold = Math.min(this.quantity, buyOrder.quantity);
            this.quantity -= quantitySold;
            buyOrder.quantity -= quantitySold;

            return quantitySold;
        }

        /**
         * This function does not handle whether the price is correct. The limit order type
         * has to be a buy order and the order to buy from has to be a sell order. The function also
         * does not handle the order book if the order is completely bought.
         *
         * @param sellOrder The sell order to buy from.
         * @return The quantity of the order that is bought.
         * @throws InvalidParameterException
         */
        public int buyFrom(Order sellOrder) throws InvalidParameterException {
            if (sellOrder.type) {
                throw new InvalidParameterException("sellOrder is not a sell order");
            }

            int quantityBought = Math.min(this.quantity, sellOrder.quantity);
            this.quantity -= quantityBought;
            sellOrder.quantity -= quantityBought;

            return quantityBought;
        }

        public boolean sameID(String orderID) {
            return this.orderID.equals(orderID);
        }

        @Override
        public String toString() {
            // String is formatted as "quantity#orderID"
            return String.format("%d#%s", quantity, orderID);
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Order)) {
                return false;
            }
            Order order = (Order) o;
            return orderID.equals(order.orderID);
        }
    }

    static class LimitOrder extends Order implements Comparable<LimitOrder> {
        private int price;
        private int orderNumber;
        public LimitOrder(String orderID, int quantity, boolean type, int price) {
            super(orderID, quantity, type);
            this.price = price;
            this.orderNumber = Order.orderCount;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public String toString() {
            // String is formatted as "quantity@price#orderID"
            return String.format("%d@%d#%s", super.quantity, price, super.orderID);
        }

        @Override
        public int compareTo(LimitOrder o) {
            if (this.price == o.price)
                if (super.type) {
                    // Buy order
                    return o.orderNumber - this.orderNumber;
                } else {
                    // Sell order
                    return this.orderNumber - o.orderNumber;
                }
            else
                return this.price - o.price;
        }
    }

    static class MarketOrder extends Order {
        public MarketOrder(String orderID, int price, boolean type) {
            super(orderID, price, type);
        }
    }

    public static void main(String[] args) throws IOException {
        // Time
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        OrderBook orderBook = new OrderBook();
        // Keep reading the input until END is encountered.
        String line = br.readLine();
        long startTime = System.currentTimeMillis();
        int count = 0;
        while (!line.equals("END")) {
            /**
             * The input for limit order, market order, and cancel order are, respectively:
             * SUB LO [buy/sell] [orderID] [quantity] [price]
             * SUB MO [buy/sell] [orderID] [quantity]
             * CXL [orderID]
             */

            String[] input = line.split(" ");
            String orderCategory = input[0];
            String orderType = input[1];
            if (orderCategory.equals("SUB") && orderType.equals("LO")) {
                String orderID = input[3];
                boolean buyOrder = input[2].equals("B");
                int quantity = Integer.parseInt(input[4]);
                int price = Integer.parseInt(input[5]);
                LimitOrder order = new LimitOrder(orderID, quantity, buyOrder, price);
                orderBook.processLimitOrder(order);
            } else if (orderCategory.equals("SUB") && orderType.equals("MO")) {
                String orderID = input[3];
                boolean buyOrder = input[2].equals("B");
                int quantity = Integer.parseInt(input[4]);
                MarketOrder order = new MarketOrder(orderID, quantity, buyOrder);
                orderBook.processMarketOrder(order);
            } else if (orderCategory.equals("CXL")) {
                String orderID = input[1];
                orderBook.processCancelOrder(orderID);
            }

            count++;
            line = br.readLine();
        }

        System.out.println(orderBook);
        System.out.println("Time: " + (System.currentTimeMillis() - startTime) + "ms");
        System.out.println("average latency: " + 1000 * (System.currentTimeMillis() - startTime) / count + "us");
    }
}
