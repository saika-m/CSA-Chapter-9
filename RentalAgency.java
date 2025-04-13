import java.util.*;

class V {
    private double c, s, cap;
    private String vt;

    public V(double c, double s, double cap, String vt) {
        this.c = c;
        this.s = s;
        this.cap = cap;
        this.vt = vt;
    }

    public boolean ok(double cg, double d, double t) {
        if (cg > cap) {
            return false;
        }
        if (time(d) > t) {
            return false;
        }
        return true;
    }

    public double opCost(double d) {
        return c * d;
    }

    public double insCost(double cg, double d) {
        return 0;
    }

    public double total(double cg, double d) {
        return opCost(d) + insCost(cg, d);
    }

    public double time(double d) {
        return d / s;
    }

    public String info(double cg, double d, double t) {
        return String.format(
            "Type: %s\nOp Cost: $%.2f\nIns Cost: $%.2f\nTotal: $%.2f\nTime: %.2f hrs\n%s",
            vt, opCost(d), insCost(cg, d), total(cg, d), time(d), extra()
        );
    }

    public String extra() {
        if (isCar()) {
            return String.format("Speed: %.0f mph", s);
        } else {
            return String.format("Cap: %.1f yd³", cap);
        }
    }

    public boolean isCar() {
        return cap == 0.5;
    }

    public double getC() { return c; }
    public double getS() { return s; }
    public double getCap() { return cap; }
    public String getVt() { return vt; }
}

// Cars
class Ferrari extends V {
    public Ferrari() { super(1.0, 200, 0.5, "Ferrari"); }
    public double insCost(double cg, double d) {
        return getS() * d / 1000;
    }
}

class Chevy extends V {
    public Chevy() { super(0.25, 100, 0.5, "Chevy"); }
    public double insCost(double cg, double d) {
        return 0.05 * d;
    }
}

class VW extends V {
    public VW() { super(0.10, 75, 0.5, "VW"); }
    public double insCost(double cg, double d) {
        return 0.10 * opCost(d);
    }
}

// Trucks
class Pickup extends V {
    public Pickup() { super(0.12, 55, 4, "Pickup"); }
    public double insCost(double cg, double d) {
        return 0.01 * cg * d;
    }
}

class Dump extends V {
    public Dump() { super(0.80, 55, 10, "Dump"); }
    public double insCost(double cg, double d) {
        return 0.01 * cg * d;
    }
}

class Semi extends V {
    public Semi() { super(1.5, 55, 30, "Semi"); }
    public double insCost(double cg, double d) {
        return 0.01 * cg * d;
    }
}

// Agency
class R {
    private V[] vs = {
        new Ferrari(), new Chevy(), new VW(),
        new Pickup(), new Dump(), new Semi()
    };

    public List<V> okList(double cg, double d, double t) {
        List<V> list = new ArrayList<>();
        for (V v : vs) {
            if (v.ok(cg, d, t)) {
            list.add(v);
            }
        }
        return list;
    }

    public void show(double cg, double d, double t) {
        List<V> list = okList(cg, d, t);
        if (list.isEmpty()) {
            System.out.println("Not valid");
            return;
        }

        System.out.println("\nValid:");
        for (V v : list) {
            System.out.println(v.info(cg, d, t));
            System.out.println();
        }
    }
}

public class RentalAgency {
    public static void main(String[] args) {
        R agency = new R();
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
