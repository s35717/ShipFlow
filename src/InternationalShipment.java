public class InternationalShipment extends ShipmentOrder {

    private final String  destinationCountry;
    private final boolean customsDocumentsRequired;
    private final boolean expressDelivery;

    /**
     * Constructor matches Main:
     *   new InternationalShipment(orderNumber, customerName, distanceKm,
     *                             baseFee, insured,
     *                             destinationCountry, customsDocumentsRequired, expressDelivery)
     */
    public InternationalShipment(String  orderNumber,
                                 String  customerName,
                                 double  distanceKm,
                                 double  baseFee,
                                 boolean insured,
                                 String  destinationCountry,
                                 boolean customsDocumentsRequired,
                                 boolean expressDelivery) {
        super(orderNumber, customerName, distanceKm, baseFee, insured);
        this.destinationCountry        = destinationCountry;
        this.customsDocumentsRequired  = customsDocumentsRequired;
        this.expressDelivery           = expressDelivery;
    }

    // ── Validation hook: destination country must not be empty ───────────────
    @Override
    protected void validateSpecificRules() {
        if (destinationCountry == null || destinationCountry.isBlank()) {
            throw new IllegalArgumentException("Destination country cannot be empty.");
        }
    }

    // ── Shipment type ─────────────────────────────────────────────────────────
    @Override
    public String getShipmentType() {
        return "International";
    }

    // ── Base price ────────────────────────────────────────────────────────────
    @Override
    protected double calculateBasePrice() {
        return baseFee + distanceKm * 2.10;
    }

    // ── Additional fee ────────────────────────────────────────────────────────
    @Override
    protected double calculateAdditionalFee() {
        double fee = 0.0;
        if (customsDocumentsRequired) {
            fee += 45.00;
        }
        if (expressDelivery) {
            fee += 80.00;
        }
        return fee;
    }

    // ── Business discount: 3% off for non-express orders > 1000 km ───────────
    @Override
    protected double applyBusinessDiscount(double price) {
        if (!expressDelivery && distanceKm > 1000) {
            return price * 0.97;
        }
        return price;
    }

    // ── Getters ───────────────────────────────────────────────────────────────
    public String  getDestinationCountry()       { return destinationCountry; }
    public boolean isCustomsDocumentsRequired()  { return customsDocumentsRequired; }
    public boolean isExpressDelivery()           { return expressDelivery; }
}
