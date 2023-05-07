Feature: Edit Project:

Description: Edit a Project by the name, descrtiption,start date, and end date.
Actor: User

Background: We want to edit one Project by the project leader:
Given that one project already exist in the library:
	|name    | sDate         | eDate         |Description|
	|"firstP" | 09-05-2023 | 11-05-2023 |"first description" |
	
Scenario: a project leader tries to edit the name of an existing Project
	Given the initials "abas" is registred with the name "abas ali" and is a project leader 
	When "abas" clicks edit Project
	Then he edits the name of the Activity from "firstP" to "newfirst"
	
Scenario: a project leader tries to edit the descrtiption of an existing Project
	Given the initials "abas" is registred with the name "abas ali" and is a project leader 
	When "abas" clicks edit Project
	Then he edits the name of the descrtiption from "first description" to "new first description"
	
Scenario: a project leader tries to edit the start date of an existing Project
	Given the initials "abas" is registred with the name "abas ali" and is a project leader 
	When "abas" clicks edit Project
	Then he edits the start date from "09-05-2023" to "10-05-2023"
	
Scenario: a project leader tries to edit the start date of an existing Project
	Given the initials "abas" is registred with the name "abas ali" and is a project leader 
	When "abas" clicks edit Project
	Then he edits the end date from "11-05-2023" to "12-05-2023"
	

	