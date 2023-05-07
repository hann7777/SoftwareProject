Feature: Delete Project:

Description: Delete an existing project
Actor: User

Background: Two project are registered in the library
Given that projects exist in the library:
	|name    | sDate         | eDate         |Description|
	|"first" | 09-05-2023 | 11-05-2023 |"first description" |
	|"second"| 09-05-2023 | 12-05-2023 |"second description"|

Scenario: a project leader tries to delete a project
	Given the initials "abas" is registred with the name "abas ali" and is a project leader
	When "abas" clicks delete 
	Then the selected project is deleted
	
Scenario: a developer tries to delete a project
	Given the initials "han" is registred with the name "hassen ali nasrallah" and is not a project leader
	When  "han" clicks delete 
	Then the and the selected project is not deleted