# Restaurant Management System

This repository contains a course project for the Operating System course. The project is a Restaurant Management System that utilizes scheduling algorithms such as FIFO (First-In-First-Out), LRU (Least Recently Used), and Second-Chance. The system efficiently manages table allocation to customers based on these scheduling algorithms. Additionally, it communicates with a personal server to receive messages from the restaurant manager and handle customer reentry at the end of the business day.

## Project Description

The Restaurant Management System aims to efficiently assign tables to customers based on their arrival and departure times. Each customer is assigned a unique number and is seated at a specific table. When a new customer arrives, the system should select a table from an existing customer and allocate it to the new customer, effectively managing the table allocation process. The system also receives notifications from the restaurant manager through a personal server and handles customer reentry at the end of the business day.

## Scheduling Algorithms

The system incorporates the following scheduling algorithms:

- **FIFO (First-In-First-Out):** This algorithm assigns the table to the new customer based on the order in which the customers arrived. The table that was occupied by the first customer who arrived will be allocated to the new customer.

- **LRU (Least Recently Used):** This algorithm assigns the table to the new customer based on the least recently used table. The table that was least recently occupied will be allocated to the new customer.

- **Second-Chance:** This algorithm is a variation of the FIFO algorithm. It considers whether the customer at a table has been "used" or not. If a table has been used (occupied) previously, it gives the customer a "second chance" before reallocating the table to the new customer.

## Communication with Personal Server

The system establishes a socket connection with the personal server owned by the restaurant manager. The server program (restaurant manager) waits for the client (your application) to connect to it. Once the connection is established successfully, the server sends the number of tables in the restaurant (n) and, at random intervals between 1 millisecond and 2 seconds, sends the number of the customer who wants to order. The system receives these messages and assigns customers to their respective tables using the scheduling algorithms. The system lists the customers currently seated at each table for each algorithm.

At the end of the business day, the server sends the message "0" to indicate the end of the working day. Upon receiving this message, the system prints the page fault for all three algorithms in the following format on the console:

LRU: \<page_fault>, FIFO: \<page_fault>, Second-Chance: \<page_fault>


## Contributing

Contributions to this project are welcome. If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE). You are free to modify and use the code for personal and commercial purposes.
