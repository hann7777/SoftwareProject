//Saeed
Feature: Edit Activity:

Description: Edit an Activity by the name and estimated time
Actor: User

Background: We want to edit one Activity by the project leader:
Given that one project exist in the library:
	|name    | sDate         | eDate         |Description|
	|"firstP" | 09-05-2023 | 11-05-2023 |"first description" |
Given that one Activity exist in the Project:
	|name    | estimatedTime |   
	|"first" | 10.5          | 
	
Scenario: a project leader tries to edit an Activity
	Given the initials "abas" is registred with the name "abas ali" and is a project leader 
	When "abas" clicks edit Activity
	Then he edit the name of the Activity from "first" to "newfirst"
	Then he can edit the estimatedTime from 10.5 to 11.5
	

	
