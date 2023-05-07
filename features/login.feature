Feature: login

Background: The planning tool has a list of developers
Given that the planning tool has a list of registered developers:
|isP|name|initials|
|true| "hassen ali nasrallah" | "han" |
|true| "saeed mo ali" | "sma" |
|false| "jon larus" | "jl" |
|false| "abas ali" | "abas" |

Scenario: existing user attempts to login
	Given the initials "abas" is registred with the name "abas ali"
	When  "abas" clicks login after typing in his initials
	Then the startpage is displayed

	Scenario: a non existing "hann" attempts to login
	Given "hann" is not registred
	When "hann" clicks login after typing in his initials
	Then the startpage is not displayed
	
	
	