import java.util.*;

enum VehicleType {
    BIKE, CAR, SPORTS_CAR, TRUCK, BUS
}

class Vehicle {
    private String registrationNumber;
    private String color;
    private VehicleType type;

    public Vehicle(String registrationNumber, String color, VehicleType type) {
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.type = type;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getColor() {
        return color;
    }

    public VehicleType getType() {
        return type;
    }
}

class ParkingLot {
    private int totalCapacity;
    private int floors;
    private Map<Integer, Map<VehicleType, List<Vehicle>>> parkingSpaces;
    private Map<Integer, Map<Vehicle, Integer>> parkingTokens;

    public ParkingLot(int totalCapacity, int floors) {
        this.totalCapacity = totalCapacity;
        this.floors = floors;
        this.parkingSpaces = new HashMap<>();
        this.parkingTokens = new HashMap<>();
        for (int i = 1; i <= floors; i++) {
            parkingSpaces.put(i, new HashMap<>());
            parkingTokens.put(i, new HashMap<>());
        }
    }

    public void addVehicle(Vehicle vehicle, int floor) {
        if (!parkingSpaces.containsKey(floor)) {
            throw new IllegalArgumentException("Invalid floor");
        }
        Map<VehicleType, List<Vehicle>> floorMap = parkingSpaces.get(floor);
        Map<Vehicle, Integer> tokenMap = parkingTokens.get(floor);
        if (!floorMap.containsKey(vehicle.getType())) {
            floorMap.put(vehicle.getType(), new ArrayList<>());
        }
        List<Vehicle> vehicles = floorMap.get(vehicle.getType());
        if (vehicles.size() >= totalCapacity / floors) {
            throw new IllegalStateException("Floor " + floor + " is full for vehicle type " + vehicle.getType());
        }
        vehicles.add(vehicle);
        tokenMap.put(vehicle, tokenMap.size() + 1);
    }

    public void removeVehicle(int token, int floor) {
        if (!parkingSpaces.containsKey(floor)) {
            throw new IllegalArgumentException("Invalid floor");
        }
        Map<VehicleType, List<Vehicle>> floorMap = parkingSpaces.get(floor);
        Map<Vehicle, Integer> tokenMap = parkingTokens.get(floor);
        for (List<Vehicle> vehicles : floorMap.values()) {
            for (Vehicle vehicle : vehicles) {
                if (tokenMap.get(vehicle) == token) {
                    vehicles.remove(vehicle);
                    tokenMap.remove(vehicle);
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Invalid token");
    }

    public boolean isFloorFull(int floor) {
        if (!parkingSpaces.containsKey(floor)) {
            throw new IllegalArgumentException("Invalid floor");
        }
        Map<VehicleType, List<Vehicle>> floorMap = parkingSpaces.get(floor);
        int totalVehicleCount = floorMap.values().stream().mapToInt(List::size).sum();
        return totalVehicleCount >= totalCapacity / floors;
    }

    public int checkAvailability(int floor, VehicleType type) {
        if (!parkingSpaces.containsKey(floor)) {
            throw new IllegalArgumentException("Invalid floor");
        }
        Map<VehicleType, List<Vehicle>> floorMap = parkingSpaces.get(floor);
        if (!floorMap.containsKey(type)) {
            return totalCapacity / floors;
        }
        int capacity = totalCapacity / floors;
        List<Vehicle> vehicles = floorMap.get(type);
        return Math.max(0, capacity - vehicles.size());
    }

    public void displayParkingStatus() {
        System.out.println("Parking Lot Status:");
        for (int i = 1; i <= floors; i++) {
            System.out.println("Floor " + i + ":");
            Map<VehicleType, List<Vehicle>> floorMap = parkingSpaces.get(i);
            for (Map.Entry<VehicleType, List<Vehicle>> entry : floorMap.entrySet()) {
                VehicleType type = entry.getKey();
                List<Vehicle> vehicles = entry.getValue();
                System.out.println("  " + type + ": " + vehicles.size() + " vehicles parked");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter total capacity of parking lot: ");
        int totalCapacity = scanner.nextInt();
        System.out.print("Enter number of floors: ");
        int floors = scanner.nextInt();

        ParkingLot parkingLot = new ParkingLot(totalCapacity, floors);

        while (true) {
            System.out.println("1. Add Vehicle\n2. Remove Vehicle\n3. Display Parking Status\n4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter registration number: ");
                    String regNumber = scanner.next();
                    System.out.print("Enter color: ");
                    String color = scanner.next();
                    System.out.print("Enter vehicle type (BIKE, CAR, SPORTS_CAR, TRUCK, BUS): ");
                    VehicleType type = VehicleType.valueOf(scanner.next().toUpperCase());
                    System.out.print("Enter floor number: ");
                    int floor = scanner.nextInt();
                    try {
                        parkingLot.addVehicle(new Vehicle(regNumber, color, type), floor);
                        System.out.println("Vehicle parked successfully.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Enter token number: ");
                    int token = scanner.nextInt();
                    System.out.print("Enter floor number: ");
                    int floorNum = scanner.nextInt();
                    try {
                        parkingLot.removeVehicle(token, floorNum);
                        System.out.println("Vehicle removed successfully.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    parkingLot.displayParkingStatus();
                    break;
                case 4:
                    System.out.println("Exiting program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
