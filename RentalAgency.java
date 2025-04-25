import java.util.*;

class Vehicle {
    private double c, s, cap;
    private String vt;

    public Vehicle(double c, double s, double cap, String vt) {
        this.c = c;
        this.s = s;
        this.cap = cap;
        this.vt = vt;
    }

    public boolean ok(double cg, double d, double t) {
        return cg <= cap && time(d) <= t;
    }

    public double opCost(double d) {
        return c * d;
    }

    public double insCost(double cg, double d) {
        return 0.05 * d + 0.01 * cg;
    }

    public double total(double cg, double d) {
        return opCost(d) + insCost(cg, d);
    }

    public double time(double d) {
        return d / s;
    }

    public String info(double cg, double d) {
        return String.format(
            "Type: %s\nOp Cost: $%.2f\nIns Cost: $%.2f\nTotal: $%.2f\nTime: %.2f hrs\n%s",
            vt, opCost(d), insCost(cg, d), total(cg, d), time(d), extra()
        );
    }

    public String extra() {
        return "";
    }

    public double getC() { return c; }
    public double getS() { return s; }
    public double getCap() { return cap; }
    public String getVt() { return vt; }
}

class Car extends Vehicle {
    public Car(double c, double s, String vt) {
        super(c, s, 0.5, vt);
    }
    
    @Override
    public String extra() {
        return String.format("Speed: %.0f mph", getS());
    }
}

class Truck extends Vehicle {
    public Truck(double c, double s, double cap, String vt) {
        super(c, s, cap, vt);
    }
    
    @Override
    public double insCost(double cg, double d) {
        return 0.01 * cg * d;
    }
    
    @Override
    public String extra() {
        return String.format("Cap: %.1f yd³", getCap());
    }
}

// Cars
class Ferrari extends Car {
    public Ferrari() { super(1.0, 200, "Ferrari"); }
    
    @Override
    public double insCost(double cg, double d) {
        return getS() * d / 1000;
    }
}

class Chevy extends Car {
    public Chevy() { super(0.25, 100, "Chevy"); }
    
    @Override
    public double insCost(double cg, double d) {
        return 0.05 * d;
    }
}

class VW extends Car {
    public VW() { super(0.10, 75, "VW"); }
    
    @Override
    public double insCost(double cg, double d) {
        return 0.10 * opCost(d);
    }
}

// Trucks
class Pickup extends Truck {
    public Pickup() { super(0.12, 55, 4, "Pickup"); }
}

class Dump extends Truck {
    public Dump() { super(0.80, 55, 10, "Dump"); }
}

class Semi extends Truck {
    public Semi() { super(1.5, 55, 30, "Semi"); }
}

// Agency
class RentalAgencyHelper {
    private Vehicle[] vehicles = {
        new Ferrari(), new Chevy(), new VW(),
        new Pickup(), new Dump(), new Semi()
    };

    public List<Vehicle> okList(double cg, double d, double t) {
        List<Vehicle> list = new ArrayList<>();
        for (Vehicle v : vehicles) {
            if (v.ok(cg, d, t)) {
                list.add(v);
            }
        }
        return list;
    }

    public void show(double cg, double d, double t) {
        List<Vehicle> list = okList(cg, d, t);
        if (list.isEmpty()) {
            System.out.println("Not valid");
            return;
        }

        System.out.println("\nValid:");
        for (Vehicle v : list) {
            System.out.println(v.info(cg, d));
            System.out.println();
        }
    }
}

public class RentalAgency {
    public static void main(String[] args) {
        RentalAgencyHelper agency = new RentalAgencyHelper();
        Scanner sc = new Scanner(System.in);
        System.out.println("Rental Agency");

        while (true) {
            try {
                System.out.print("\nCargo (yd³): ");
                double cg = sc.nextDouble();
                System.out.print("Distance (mi): ");
                double d = sc.nextDouble();
                System.out.print("Time (hr): ");
                double t = sc.nextDouble();

                agency.show(cg, d, t);

                System.out.print("Again? (y/n) ");
                String response = sc.next();
                if (!response.toLowerCase().startsWith("y")) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("runtime gg");
                sc.nextLine();
            }
        }

        System.out.println("Bye");
        sc.close();
    }
}
