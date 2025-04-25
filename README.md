# CSA-hw
Chapter 9 Rental Agency

```mermaid
---
title: Rental Agency Design
---
classDiagram
    direction TB
    
    class RentalAgency {
        +main(String[] args)
    }
    
    class RentalAgencyHelper {
        -Vehicle[] vehicles
        +List~Vehicle~ okList(double cg, double d, double t)
        +show(double cg, double d, double t)
    }
    
    class Vehicle {
        -double c
        -double s
        -double cap
        -String vt
        +Vehicle(c, s, cap, vt)
        +ok(cg, d, t)
        +opCost(d)
        +insCost(cg, d)
        +total(cg, d)
        +time(d)
        +info(cg, d)
        +extra()
        +getters()
    }
    
    class Car {
        +Car(c, s, vt)
        +extra()
    }
    
    class Truck {
        +Truck(c, s, cap, vt)
        +insCost(cg, d)
        +extra()
    }
    
    %% Car Classes
    class Ferrari {
        +Ferrari()
        +insCost(cg, d)
    }
    
    class Chevy {
        +Chevy()
        +insCost(cg, d)
    }
    
    class VW {
        +VW()
        +insCost(cg, d)
    }
    
    %% Truck Classes
    class Pickup {
        +Pickup()
    }
    
    class Dump {
        +Dump()
    }
    
    class Semi {
        +Semi()
    }
    
    RentalAgency --> RentalAgencyHelper
    RentalAgencyHelper o-- Vehicle
    
    Vehicle <|-- Car
    Vehicle <|-- Truck
    
    Car <|-- Ferrari
    Car <|-- Chevy
    Car <|-- VW
    
    Truck <|-- Pickup
    Truck <|-- Dump
    Truck <|-- Semi
