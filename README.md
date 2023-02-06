# scheduling-algorithms
## A restaurant management system.
In this project, we plan to write a restaurant management system. This restaurant is a very popular restaurant and sometimes the tables may be full. In this case, we need to kick out one of the customers and a new customer enters the restaurant. 
we want to find out which customer to kick out of the restaurant.
We are hesitating between several algorithms to solve this problem: FIFO, LRU, Second-chance.
Finally, in this project, we want to find out which algorithm is most useful for the restaurant.

## Description of the problem 
At the beginning, when each customer enters the restaurant, he is given a specific number. When another customer enters, according to the algorithm, one of the customers is kicked out and the new customer sits insteas of him.

If a customer who was thrown out pervioudly, wants to order again, he can enter the restaurant again, and so on and there is no contraindication. Also, customers can be inside the restaurant and order a few more times.

We are informed by the restaurant's manager, the names of customers who currently want to order, every 1ms to 2s. The restaurant manager sends his notifications through the personal server that he bought and installed. In this way, you should receive the messages of the restaurant manager from his server and report the number of kicked-out customers from the restaurant throughout the working day. When the manager of the restaurant sends the message "0", it means it is the end of the working day.

## Project Implementation

The connection between the restaurant manager program and your program is in the form of a socket.

In summary, the socket connection between two programs is such that the server program (restaurant manager) waits until
Client (our application) will connect to it. When the connection is established correctly, Server shows  "Client connected".

By seeing this message in the server console, you can understand that the initial connection is correctly established. After that, the server first sends the value "n" or the number of tables in the restaurant. Then during times between 1 millisecond and 2 seconds, a random function will send you the number of the customer who wants to order.

Right after receiving these messages, we should place the customer at his table. In other words, we should run all three algorithms after receiving each message from the server and list the people who are currently at each table.

In the end, the server sends us the message "0" ("None") and we immediately after receiving this message
We will print number of the page fault for all three algorithms on our console.
