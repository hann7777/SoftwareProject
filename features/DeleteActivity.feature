//Abas
Feature: Delete Activity:

Description: Delete an existing Activity
Actor: User

Background: Two Activity are registered in the library:
Given that project exist in the library:
	|name    | sDate         | eDate         |Description|
	|"firstP" | 09-05-2023 | 11-05-2023 |"first description" |
Given that Activity exist in the Project:
	|name    | estimatedTime |   
	|"first" | 10.5          | 
	|"second"| 20.5          | 

Scenario: a project leader tries to delete an Activity
	Given the initials "abas" is registred with the name "abas ali" and is a project leader 
	When "abas" clicks delete Activity
	Then the selected Activity is deleted
	
Scenario: a developer tries to delete an Activity
	Given the initials "han" is registred with the name "hassen ali nasrallah" and is not a project leader
	When  "han" clicks delete Activity 
	Then the Activity is not deleted