#Hassen
Feature: create project:

Description: Creating project with name,description, start and end date.
Actor: User

Scenario: User is a project leader and creates a project successfully
	Given there is a user with the initials "han", and the name "Hassen Ali Nasrallah" who is a project leader
	And the project leader creates a project with title "software", with the description "this a description", a start date "08-05-2023", and an end date "07-06-2023"
	Then the project with name "software" is created, and has project ID "23011"

      
Scenario: User is a project leader and creates a project with an invalid start date
	Given there is a user with the initials "han", and the name "Hassen Ali Nasrallah" who is a project leader
	And the project leader creates a project with title "softwareNotExist", with the description "this a description", a start date "05-05-2023" before the date it is created, and an end date "07-06-2023"
	Then the project with name "softwareNotExist" is not created.


Scenario: User is a project leader and creates a project with an end date before a start date
	Given there is a user with the initials "han", and the name "Hassen Ali Nasrallah" who is a project leader
	And the project leader creates a project with title "softwareNotExist", with the description "this a description", a start date "08-06-2023", and an end date "07-06-2023" before a start date.
	Then the project with name "softwareNotExist" is not created.

Scenario: User is a project leader and creates a project without a name
	Given there is a user with the initials "han", and the name "Hassen Ali Nasrallah" who is a project leader
	And the project leader creates a project with no title "", with the description "this a description", a start date "08-05-2023", and an end date "08-06-2023".
	Then the project with name "" is not created.

Scenario: User is a project leader and creates a project without a description
	Given there is a user with the initials "han", and the name "Hassen Ali Nasrallah" who is a project leader
	And the project leader creates a project with title "softwareNotExist", with no description "", a start date "08-05-2023", and an end date "08-06-2023".
	Then the project with name "softwareNotExist" is not created.
