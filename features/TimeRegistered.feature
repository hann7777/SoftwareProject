#Jon
Feature: Time Registered:

Description: A developer on a project activity can type in how much time he has spent on that activity
Actor: User

Background: A developer want to register time:
Given that this project already exist in the library:
	|name    | sDate         | eDate         |Description|
	|"firstP" | 09-05-2023 | 11-05-2023 |"first description"|
Given that one developer is registered on the project:
	|name    | estimatedTime |   
	|"first" | 10.5          | 
	
Scenario: the developer registers a valid time, and the remaining time updates
 	Given the initials "han" is registred with the name "hassen ali" and is a developer 
	When "han" clicks add time 
  Then he registers 10.0 hours to the activity
  Then the remainingtime updates to 0.5
	
Scenario: the developer registers an invalid time, and the remaining time does not update
 	Given the initials "han" is registred with the name "hassen ali" and is a developer 
	When "han" clicks add time 
	Then he registers more time than estimated 12.0 hours to the activity
	Then the remainingtime stays at 10.5;
	
