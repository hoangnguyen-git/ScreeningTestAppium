@createNewClient
Feature: Create a new client

  Scenario: Create a new client
    When Login on Web page
    And Get the QR code
    And Open app and enter activation code
    And User goes to the contact page and invite a new client
    And Enter all information of new client and click on invite button
      | Code Name | Phone        | First Name | Last Name | Email   |
      | contact   | +84963282927 | fncontact  | lncontact | contact |
    Then Invite new contact is successful with correct information
