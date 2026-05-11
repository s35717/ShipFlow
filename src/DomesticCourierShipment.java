public class DomesticCourierShipment extends ShipmentOrder {

    private final double  packageWeightKg;
    private final boolean weekendDelivery;

    /**
     * Constructor matches Main:
     *   new DomesticCourierShipment(orderNumber, customerName, distanceKm,
     *                               baseFee, insured, packageWeightKg, weekendDelivery)
     */
    public DomesticCourierShipment(String  orderNumber,
                                   String  customerName,
                                   double  distanceKm,
                                   double  baseFee,
                                   boolean insured,
                                   double  packageWeightKg,
                                   boolean weekendDelivery) {
        super(orderNumber, customerName, distanceKm, baseFee, insured);
        this.packageWeightKg = packageWeightKg;
        this.weekendDelivery = weekendDelivery;
    }

    // ── Shipment type ─────────────────────────────────────────────────────────
    @Override
    public String getShipmentType() {
        return "Domestic courier";
    }

    // ── Base price ────────────────────────────────────────────────────────────
    @Override
    protected double calculateBasePrice() {
        return baseFee + distanceKm * 1.20;
    }

    // ── Additional fee ────────────────────────────────────────────────────────
    @Override
    protected double calculateAdditionalFee() {
        double fee = packageWeightKg * 4.00;
        if (weekendDelivery) {
            fee += 25.00;
        }
        return fee;
    }

    // ── Business discount: 5% off for distances >= 300 km ────────────────────
    @Override
    protected double applyBusinessDiscount(double price) {
        if (distanceKm >= 300) {
            return price * 0.95;
        }
        return price;
    }

    // ── Getters ───────────────────────────────────────────────────────────────
    public double  getPackageWeightKg() { return packageWeightKg; }
    public boolean isWeekendDelivery()  { return weekendDelivery; }
}
