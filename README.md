# BladeRunner Control System - T4 C-2

Welcome to the **BladeRunner Control System** project repository. This project is part of the Integrated Mass Transit System initiative, focusing on the development of the Carriage Control Program (CCP) for suspended monorail carriages, known as BladeRunners.

## Project Overview

The BladeRunner Control System is designed to manage the movement, safety, and communication of monorail carriages within a narrow rail-blade network. This repository contains the code, documentation, and design artifacts that support the development and testing of the CCP.

### Key Features
- **Carriage Control Program (CCP):** Responsible for executing commands from the Master Control Program (MCP), managing carriage operations (speed, direction, stop), and ensuring real-time communication over TCP/IP.
- **Modular Architecture:** The CCP is designed with a modular approach, allowing for easy updates to components such as motor control, LED indicators, and sensor interfaces.
- **Limp Mode Functionality:** Includes safety protocols to maintain basic operations during communication failures or emergencies.
- **Performance & Reliability:** Optimized to meet strict requirements for communication latency, power management, and system uptime.

## Repository Structure

- **/src**: Contains the source code for the CCP, written in C++ for Arduino.
- **/docs**: Documentation including scoping documents, design artifacts, and testing protocols.
- **/design**: UML diagrams and preliminary design documents.
- **/tests**: Test cases and testing scripts for validating system performance and functionality.

Thank you for checking out the BladeRunner Control System project!