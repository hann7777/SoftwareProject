Feature: create project:

Description: adds a project deadline, name, activities to a project. And the project leader can add a member to the list, if none then an employee can do it, the employee can register how much time spent on an activity.
Actor: User

Scenario: User is a project leader and creates a project successfully
	Given there is a user with the initials "han", and the name "Hassen Ali Nasrallah" who is a project leader
#	And the project leader creates a project with title "software", with the description "this a description", a start date "08-05-2023", and an end date "07-06-2023"
#	Then the project with name "software" is created, and has project ID "2023001"

      
#Scenario: User is a project leader and creates a project with an invalid start date
#	Given there is a user with the initials "han", and the name "Hassen Ali Nasrallah" who is a project leader
#	And the project leader creates a project with title "software", with the description "this a description", a start date "<LocalDate>", and an end date "<LocalDate>".
#	Then the project with name "software" is not created.
#	And errormessage "invalid start date";
#	    Examples:
      #| LocalDate    |
      #| "05.05.2023" |
      #| "07.06.2023" |
#
#Scenario: User is a project leader and creates a project with an end date before a start date
#	Given there is a user with the initials "han", and the name "Hassen Ali Nasrallah" who is a project leader
#	And the project leader creates a project with title "software", with the description "this a description", a start date "<LocalDate>", and an end date "<LocalDate>".
#	Then the project with name "software" is not created.
#	And errormessage "invalid end date";
#	    Examples:
      #| LocalDate    |
      #| "08.06.2023" |
      #| "07.06.2023" |
      #
#Scenario: User is a project leader and creates a project without a name
#	Given there is a user with the initials "han", and the name "Hassen Ali Nasrallah" who is a project leader true
#	And the project leader creates a project with no title "", with the description "this a description", a start date "<LocalDate>", and an end date "<LocalDate>".
#	Then the project with name "software" is not created.
#	And errormessage "invalid name";
#	    Examples:
      #| LocalDate    |
      #| "05.05.2023" |
      #| "07.06.2023" |
      #
#Scenario: User is a project leader and creates a project without a description
#	Given there is a user with the initials "han", and the name "Hassen Ali Nasrallah" who is a project leader
#	And the project leader creates a project with title "software", with no description "", a start date "<LocalDate>", and an end date "<LocalDate>".
#	Then the project with name "software" is not created.
#	And errormessage "invalid description";
#	    Examples:
      #| LocalDate    |
      #| "05.05.2023" |
      #| "07.06.2023" |
      #
