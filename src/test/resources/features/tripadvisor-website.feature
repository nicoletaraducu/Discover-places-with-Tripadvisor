Feature: Find places to eat and top-rated tourist attractions

    Scenario: I plan to visit a city and I want to create a list of places to eat and top-rated tourist attractions
        Given Open the Tripadvisor website
        When Entering the city where you go "London"
        And Searching restaurants in the city
        Then Creating a list with top five restaurants
        When Searching top attractions in the city
        Then Add to the list the first five places




