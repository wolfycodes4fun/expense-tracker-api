**Expense Tracking For Noob Devs**  

I'll record whatever bits I might forget in the future for now; proper documentation will be done when I think its ready :)  

* Both DDL annotations (shape the DB tables) and Java validation annotations (validates Java code)  are needed
* While creating a 'repository' we start of by implementing it as an interface, in the case where this will be used in the service (ex - ExpenseRepository) Spring Boot creates a proxy class which implements this interface and uses dependency injection.