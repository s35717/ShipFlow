public class PickupPointShipment extends ShipmentOrder {

    private final String  lockerSize;   // "S", "M", or "L"
    private final boolean fragile;

    /**
     * Constructor matches Main:
     *   new PickupPointShipment(orderNumber, customerName, distanceKm,
     *                           baseFee, insured, lockerSize, fragile)
     */
    public PickupPointShipment(String  orderNumber,
                               String  customerName,
                               double  distanceKm,
                               double  baseFee,
                               boolean insured,
                               String  lockerSize,
                               boolean fragile) {
        super(orderNumber, customerName, distanceKm, baseFee, insured);
        this.lockerSize = lockerSize;
        this.fragile    = fragile;
    }

    // ── Validation hook: locker size must be S / M / L ────────────────────────
    @Override
    protected void validateSpecificRules() {
        if (!"S".equals(lockerSize) && !"M".equals(lockerSize) && !"L".equals(lockerSize)) {
            throw new IllegalArgumentException(
                    "Invalid locker size '" + lockerSize + "'. Allowed values: S, M, L.");
        }
    }

    // ── Shipment type ─────────────────────────────────────────────────────────
    @Override
    public String getShipmentType() {
        return "Pickup point";
    }

    // ── Base price ────────────────────────────────────────────────────────────
    @Override
    protected double calculateBasePrice() {
        return baseFee + distanceKm * 0.75;
    }

    // ── Additional fee ────────────────────────────────────────────────────────
    @Override
    protected double calculateAdditionalFee() {
        double fee = switch (lockerSize) {
            case "S" ->  5.00;
            case "M" -> 10.00;
            case "L" -> 18.00;
            default  ->  0.00;   // unreachable after validateSpecificRules
        };
        if (fragile) {
            fee += 12.00;
        }
        return fee;
    }

    // ── Getters ───────────────────────────────────────────────────────────────
    public String  getLockerSize() { return lockerSize; }
    public boolean isFragile()     { return fragile; }
}
