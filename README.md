[README.md](https://github.com/user-attachments/files/27595326/README.md)# ShipFlow — Shipping Order System

Java console application implementing the **Template Method** design pattern for a logistics company that handles three types of shipments.

## Project Description

ShipFlow processes shipment orders using a common algorithm defined once in an abstract class. Each shipment type calculates its price differently, but the processing flow is always the same:

1. Validate common fields
2. Validate type-specific rules
3. Calculate base price
4. Calculate additional fee
5. Apply insurance (7% surcharge if insured)
6. Apply business discount (type-specific)
7. Print result

## Class List

| Class / Interface | Type | Description |
|---|---|---|
| `SummaryPrintable` | Interface | Defines `buildSummaryLine()` — every shipment can return a readable summary |
| `ShipmentOrder` | Abstract class | Stores common fields, implements `processOrder()` as a `final` Template Method |
| `DomesticCourierShipment` | Concrete class | Domestic courier delivery — price based on distance × 1.20, weight surcharge, 5% discount for 300+ km |
| `PickupPointShipment` | Concrete class | Pickup point / locker delivery — price based on distance × 0.75, size fee (S/M/L), fragile surcharge |
| `InternationalShipment` | Concrete class | International delivery — price based on distance × 2.10, customs and express surcharges, 3% discount for non-express 1000+ km |
| `Main` | Main class | Creates a `ShipmentOrder[]` array with 6 orders and processes each one |

## Design Patterns Used

- **Template Method** — `processOrder()` in `ShipmentOrder` defines the algorithm skeleton; subclasses implement only the variable steps
- **Hook methods** — `validateSpecificRules()` and `applyBusinessDiscount()` have default implementations that subclasses can optionally override

## How to Run

```bash
cd src
javac *.java
java Main
```

## Example Output

```
Processing: DOM-100    | Anna Kowalska             | Domestic courier     | insured: yes   | price: 227.91 PLN
SUMMARY | DOM-100 | Anna Kowalska | Domestic courier | 227.91 PLN

Processing: DOM-101    | Piotr Nowak               | Domestic courier     | insured: no    | price: 593.75 PLN
SUMMARY | DOM-101 | Piotr Nowak | Domestic courier | 593.75 PLN
...
```

## Project Structure

```
ShipFlow/
├── README.md
└── src/
    ├── SummaryPrintable.java
    ├── ShipmentOrder.java
    ├── DomesticCourierShipment.java
    ├── PickupPointShipment.java
    ├── InternationalShipment.java
    └── Main.java
```
