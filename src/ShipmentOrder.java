public abstract class ShipmentOrder implements SummaryPrintable {

    protected final String orderNumber;
    protected final String customerName;
    protected final double distanceKm;
    protected final double baseFee;
    protected final boolean insured;
    protected double lastCalculatedPrice;

    public ShipmentOrder(String orderNumber,
                         String customerName,
                         double distanceKm,
                         double baseFee,
                         boolean insured) {
        this.orderNumber  = orderNumber;
        this.customerName = customerName;
        this.distanceKm   = distanceKm;
        this.baseFee      = baseFee;
        this.insured      = insured;
    }

    // ── Template Method ───────────────────────────────────────────────────────
    public final void processOrder() {
        validateOrder();
        validateSpecificRules();

        double price = calculateBasePrice();
        price += calculateAdditionalFee();
        price  = applyInsurance(price);
        price  = applyBusinessDiscount(price);

        lastCalculatedPrice = price;
        printProcessingResult();
    }

    // ── Common private steps ──────────────────────────────────────────────────
    private void validateOrder() {
        if (orderNumber == null || orderNumber.isBlank()) {
            throw new IllegalArgumentException("Order number cannot be empty.");
        }
        if (distanceKm <= 0) {
            throw new IllegalArgumentException("Distance must be positive.");
        }
    }

    private double applyInsurance(double price) {
        return insured ? price * 1.07 : price;
    }

    private void printProcessingResult() {
        System.out.printf("Processing: %-10s | %-25s | %-20s | insured: %-5s | price: %.2f PLN%n",
                orderNumber, customerName, getShipmentType(),
                insured ? "yes" : "no", lastCalculatedPrice);
    }

    // ── Hook methods (optional override) ─────────────────────────────────────
    protected void validateSpecificRules() {
        // empty default – subclasses may override
    }

    protected double applyBusinessDiscount(double price) {
        return price;   // no discount by default
    }

    // ── Abstract methods ──────────────────────────────────────────────────────
    protected abstract double calculateBasePrice();
    protected abstract double calculateAdditionalFee();
    public    abstract String getShipmentType();

    // ── SummaryPrintable ──────────────────────────────────────────────────────
    @Override
    public String buildSummaryLine() {
        return String.format(
                "SUMMARY | %s | %s | %s | %.2f PLN",
                orderNumber, customerName, getShipmentType(), lastCalculatedPrice);
    }

    // ── Getters ───────────────────────────────────────────────────────────────
    public String getOrderNumber()       { return orderNumber; }
    public String getCustomerName()      { return customerName; }
    public double getDistanceKm()        { return distanceKm; }
    public double getBaseFee()           { return baseFee; }
    public boolean isInsured()           { return insured; }
    public double getLastCalculatedPrice() { return lastCalculatedPrice; }
}
