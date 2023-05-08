//Saeed
Feature: Create activity inside a existing project
Actor: Project leader

Scenario: Only a project leader can create an activity
Given a user with initials "abas" is registred with the name "abas ali" and is a project leader
And a "project" exist with the start date "09-05-2023" and a end date "22-05-2023"
When creating an activity with the name "activity"
And an estimated time "10" hours
Then a project leader can create an acivity 

Scenario: The estimated time for an activity should be above 0
Given a user with initials "abas" is registred with the name "abas ali" and is a project leader
When creating an acitvity with the name "name" and a time 0
Then the activity can not be created
