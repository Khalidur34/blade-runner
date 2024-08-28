# Blade Runner Carriage Control Program

Welcome to the **Blade Runner Carriage Control Program** project repository. 

## Project: Blader Runner 2024

The project aims to design, prototype, construct, and refine a section of an integrated mass transit system. Each team is responsible for developing a component of a collective machine, which will operate within an interconnected system to transport BladeRunners—suspended monorails—along a narrow, blade-like rail. This system will incorporate a messaging network to manage individual runners and an autonomous control mechanism to prevent collisions, coordinate movements between stops, and facilitate passenger boarding and disembarkation.

## Control Carriage Program (CCP)
Program responsible for executing commands from the Master Control Program, managing carriage operations, and ensuring real-time communication over TCP/IP.

### Key Features
- **Communication:** JSON commands are exchanged with the Master Control Program (MCP). 
- **Motion:** Execute FORWARD, BACKWARD, STOP, GO commands.
- **Failure System:** Includes safety protocals to maintain basic operations during communication failures or emergenies.
- **Modular Architecture:** The program is designed with a modular approach, allowing for easy updates to components such as motor control, LED indicators, and sensor interfaces.
- **Performance & Reliability:** Optimized to meet strict requirements for communication latency, power management, and system uptime.

## Repository Structure

- **/src**: Contains the source code for the CCP, written in C++ for Arduino.
- **/docs**: Documentation including scoping documents, design artifacts, and testing protocols.
- **/design**: Conceptual Design, Preliminary Design, Final Design.
- **/tests**: Test cases and testing scripts for validating system performance and functionality.

Thank you for checking out the BladeRunner Control System project!