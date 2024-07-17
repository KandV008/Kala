## :scroll:Table of Contents

1.  [Design](#triangular_ruler-design)
    1. [Architecture](#house_with_garden-architecture)
        1. [UI Layer](#eyes-ui-layer)
        1. [Model Layer](#collision-model-layer)
        1. [FireBase Layer](#fire-firebase-layer)
    1. [Navigation](#boat-navigation-map)

***

## :triangular_ruler: Design
This section it is only an overview of the Kala's design.

###  :house_with_garden: Architecture

Kala is a mobile application with a layered architecture, being exactly 3 layers: ui, model and FireBase.

<p align="center">
  <img src="/docs/architecture/diagrams/layered_architecture.svg" alt="Layered Architecture Diagram">
  <br>
  <small>Class Diagram 1. Layered Architecture</small>
</p>

#### :eyes: UI Layer

The ui package contains all the visual elements of the application. These are the components, the themes and the screens.

<p align="center">
  <img src="/docs/architecture/diagrams/ui_package.svg" alt="ui package Diagram">
  <br>
  <small>Class Diagram 2. ui package</small>
</p>

#### :collision: Model Layer

The model package contains all the business logic. These are the services, entities and storage.

<p align="center">
  <img src="/docs/architecture/diagrams/model_package.svg" alt="model package Diagram">
  <br>
  <small>Class Diagram 3. model package</small>
</p>

#### :fire: Firebase Layer

The application's cloud storage and authentication system has been provided by [Google's Firebase service](https://firebase.google.com/?hl=es-419). 

It has been used for the [FireStore database](https://firebase.google.com/docs/firestore?hl=es-419), which is a NoSQL database.

###  :boat: Navigation Map

The navigation has been divided into two parts, what a registered user can visit and what a non-registered user can visit.

The complete navigation of the application is as follows:

<p align="center">
  <img src="/docs/architecture/diagrams/Navigation_Map.png" alt="Navigation Map Diagram">
  <br>
  <small>Activity Diagram 1. Navigation Map</small>
</p>

Different aspects of accessibility and usability can be accessed on all screens. Specific navigation is shown here:

<p align="center">
  <img src="/docs/architecture/diagrams/Utilities_Map.png" alt="Utilities Map Diagram">
  <br>
  <small>Acitivity Diagram 2. Utilities Map</small>
</p>