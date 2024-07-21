import java.util.*;

class Laptop {
    private String brand;
    private int ram; // ОЗУ в ГБ
    private int hdd; // Объем ЖД в ГБ
    private String os; // Операционная система
    private String color; // Цвет

    public Laptop(String brand, int ram, int hdd, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public int getRam() {
        return ram;
    }

    public int getHdd() {
        return hdd;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "brand='" + brand + '\'' +
                ", ram=" + ram +
                ", hdd=" + hdd +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        Set<Laptop> laptops = createLaptops();
        filterLaptops(laptops);
    }

    private static Set<Laptop> createLaptops() {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Dell", 16, 512, "Windows", "Black"));
        laptops.add(new Laptop("HP", 8, 256, "Windows", "Silver"));
        laptops.add(new Laptop("Apple", 16, 512, "macOS", "Gray"));
        laptops.add(new Laptop("Asus", 8, 1024, "Windows", "White"));
        laptops.add(new Laptop("Lenovo", 32, 1024, "Windows", "Black"));
        return laptops;
    }

    private static void filterLaptops(Set<Laptop> laptops) {
        try (Scanner scanner = new Scanner(System.in)) {
            Map<Integer, String> criteria = new HashMap<>();
            criteria.put(1, "RAM");
            criteria.put(2, "HDD");
            criteria.put(3, "OS");
            criteria.put(4, "Color");

            System.out.println("Введите цифру, соответствующую необходимому критерию:");
            for (Map.Entry<Integer, String> entry : criteria.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }

            int criteriaKey = scanner.nextInt();
            scanner.nextLine(); // consume newline
            String criteriaValue = criteria.get(criteriaKey);

            Map<String, Object> filterCriteria = new HashMap<>();
            switch (criteriaValue) {
                case "RAM":
                    System.out.println("Введите минимальное значение ОЗУ (в ГБ): ");
                    int minRam = scanner.nextInt();
                    filterCriteria.put("RAM", minRam);
                    break;
                case "HDD":
                    System.out.println("Введите минимальное значение объема ЖД (в ГБ): ");
                    int minHdd = scanner.nextInt();
                    filterCriteria.put("HDD", minHdd);
                    break;
                case "OS":
                    System.out.println("Введите операционную систему: ");
                    String os = scanner.nextLine();
                    filterCriteria.put("OS", os);
                    break;
                case "Color":
                    System.out.println("Введите цвет: ");
                    String color = scanner.nextLine();
                    filterCriteria.put("Color", color);
                    break;
                default:
                    System.out.println("Некорректный ввод");
                    return;
            }

            Set<Laptop> filteredLaptops = new HashSet<>();
            for (Laptop laptop : laptops) {
                boolean matches = true;
                for (Map.Entry<String, Object> entry : filterCriteria.entrySet()) {
                    switch (entry.getKey()) {
                        case "RAM":
                            if (laptop.getRam() < (int) entry.getValue()) {
                                matches = false;
                            }
                            break;
                        case "HDD":
                            if (laptop.getHdd() < (int) entry.getValue()) {
                                matches = false;
                            }
                            break;
                        case "OS":
                            if (!laptop.getOs().equalsIgnoreCase((String) entry.getValue())) {
                                matches = false;
                            }
                            break;
                        case "Color":
                            if (!laptop.getColor().equalsIgnoreCase((String) entry.getValue())) {
                                matches = false;
                            }
                            break;
                    }
                }
                if (matches) {
                    filteredLaptops.add(laptop);
                }
            }

            System.out.println("Ноутбуки, соответствующие критериям:");
            for (Laptop laptop : filteredLaptops) {
                System.out.println(laptop);
            }
        }
    }
}
