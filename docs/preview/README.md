# Kala, your income and expense managment app

Do you need quick access to your monthly expenses and income? Do you desire to record both cash and digital money? Do you want to store the information in a simple and efficient way? Kala is the answer for all of them.

Kala is a simple monthly income and expense management application. If you want to start managing your expenses, then Kala is perfect for you. You can enter the data along with a few features so that it automatically generates a report for you, which you can access whenever you want.

## :vhs: Prototype Preview

<p align="center">
  <img src="/docs/preview/Prototype_Preview_Kala.gif" alt="Prototype_Preview_Kala">
</p>


To see in detail the prototype, you can [click here!](/docs/preview/Prototype_Kala.pdf)

***

<p align="center">
<strong>This project is currently in progress...</strong>
</p>

## :scroll:Table of Contents

1.  [Requirements Analysis](#bookmark_tabs-requirements-analysis)
    1. [Entities](#house-entities)
    2. [Type of Users](#bust_in_silhouette-type-of-users)
    3. [Functional Requirements](#white_check_mark-functional-requirements)
    4. [Use Case Diagrams](#performing_arts-use-case-diagrams)
    5. [Non Functional Requirements](#dart-non-functional-requirements)
    6. [Class Diagram](#white_large_square-class-diagram)

***

## :bookmark_tabs: Requirements Analysis

This section it is only an overview of the Kala's requirements. To see the complete requirements analysis [click here!](/docs/requirements/versions/Requirements_Analysis_Kala_v1.pdf)

### :house: Entities

| Entities       |
| :------------: |
| User           |
| Month          |
| Money Exchange |

### :bust_in_silhouette: Type of Users

* **Unregistered User** &rarr; This type of user only can create an account and change the language of the application.
* **Registered User** &rarr; This type of user can use all the functionality of the software without any limitation.

### :white_check_mark: Functional Requirements

| Requirements                                                | Unregistered User  | Registered User    |
| :---------------------------------------------------------: | :----------------: | :----------------: |
| Create Account                                              | :heavy_check_mark: |                    |
| Delete Account                                              |                    | :heavy_check_mark: |
| Log in                                                      |                    | :heavy_check_mark: |
| Recover password                                            |                    | :heavy_check_mark: |
| Log out                                                     |                    | :heavy_check_mark: |
| Enter money exchange                                        |                    | :heavy_check_mark: |
| Define type of the money exchange                           |                    | :heavy_check_mark: |
| Define scope of the money exchange                          |                    | :heavy_check_mark: |
| Read record of money exchange                               |                    | :heavy_check_mark: |
| Edit introduced money exchange                              |                    | :heavy_check_mark: |
| Delete introduced money exchange                            |                    | :heavy_check_mark: |
| Read summary of the introduced money exchange               |                    | :heavy_check_mark: |
| Read mensual report                                         |                    | :heavy_check_mark: |
| Change the currency                                         |                    | :heavy_check_mark: |
| Access to Help page                                         | :heavy_check_mark: | :heavy_check_mark: |
| Send verification email                                     | :heavy_check_mark: | :heavy_check_mark: |
| Change language of the application                          | :heavy_check_mark: | :heavy_check_mark: |
| Show error login pop-up                                     |                    | :heavy_check_mark: |
| Access to Home Page                                         |                    | :heavy_check_mark: |
| Access to Configuration Page                                |                    | :heavy_check_mark: |
| Edit Account                                                |                    | :heavy_check_mark: |
| Show Terms and Conditions                                   |                    | :heavy_check_mark: |
| Show Data Protection clause                                 |                    | :heavy_check_mark: |
| Define value of the exchange                                |                    | :heavy_check_mark: |
| Define description of money exchange                        |                    | :heavy_check_mark: |

### :performing_arts: Use Case Diagrams

<p align="center">
  <img src="/docs/requirements/diagrams/Access_System.png" alt="Access system">
  <br>
  <small>Use Case Diagram 1. Access system</small>
</p>

<p align="center">
  <img src="/docs/requirements/diagrams/Home_Page.png" alt="Home page">
  <br>
  <small>Use Case Diagram 2. Home page</small>
</p>

<p align="center">
  <img src="/docs/requirements/diagrams/Configuration_Page.png" alt="Configuration page">
  <br>
  <small>Use Case Diagram 3. Configuration page</small>
</p>

<p align="center">
  <img src="/docs/requirements/diagrams/CRUD_Money_Exchange.png" alt="CRUD of money exchange">
  <br>
  <small>Use Case Diagram 4. CRUD of money exchange</small>
</p>


### :dart: Non Functional Requirements

| Non Functional Requirements                            |
| :----------------------------------------------------: |
| Oriented to smarth phones                              |
| The app must run in Andorid 5 or upper                 |
| The software must be implemented with Kotlin           |
| The application need to connect with a cloud data base |
| Languages Required                                     |
| GUI must be minimalist and user-friendly               |
| Needs an-email system                                  |
| Protection and Security for Registered User Data       |
| Use of Jetpack Compose                                 |

###  :white_large_square: Class Diagram

<p align="center">
  <img src="/docs/requirements/diagrams/Main.png" alt="Main Diagram">
  <br>
  <small>Class Diagram 1. Main</small>
</p>
