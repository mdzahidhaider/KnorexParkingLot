Java Car Parking System
A flexible and configurable car parking system implemented in Java, designed to accommodate various types of vehicles and support customizable cost strategies.

Project Structure
CostStrategy

Interface defining the contract for calculating parking costs.
FlatCostStrategy

Implementation of the CostStrategy interface, representing a flat cost structure.
Vehicle

Class representing a vehicle with attributes such as type, registration number, and color.
VehicleSpace

Class representing a parking space with details like space number, availability, and associated vehicle.
Floor

Class representing a parking floor with attributes like floor number and a list of vehicle spaces.
ParkingLot

Main class representing the entire parking lot, managing floors and vehicle spaces. It supports functionalities like adding vehicles, removing vehicles, and checking availability.
MainClass

A demonstration class showcasing the usage of the ParkingLot and its methods.
Cost Structure
The project includes a simple flat cost structure for bikes at ₹10 per hour.

Usage
Init:

Initializes the parking lot with the given number of floors and vehicle spaces per floor for each vehicle type.
addVehicle:

Adds a vehicle of the specified type with the given registration number and color to the parking lot.
removeVehicle:

Removes the vehicle with the specified registration number from the parking lot.
checkAvailability:

Checks the availability of vehicle spaces on the specified floor for the given vehicle type.
Getting Started
Clone the repository and run the MainClass to see the parking system in action. Feel free to modify the code, add additional functionalities, or enhance the design to meet specific requirements.
