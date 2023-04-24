Feature: login

Scenario: existing "abas" attempts to login
	Given "abas" is registred
	When  "abas" clicks login after typing in his initials
	Then the startpage is displayed

	Scenario: a non existing "hann" attempts to login
	Given "hann" is not registred
	When "hann" clicks login after typing in his initials
	Then the startpage is not displayed
	
	
	