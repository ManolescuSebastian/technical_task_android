Application screens
-----
<img src="https://github.com/ManolescuSebastian/technical_task_android/blob/master/screenshots/1.jpg" width="30%"></img>
<img src="https://github.com/ManolescuSebastian/technical_task_android/blob/master/screenshots/2.jpg" width="30%"></img>
<img src="https://github.com/ManolescuSebastian/technical_task_android/blob/master/screenshots/3.jpg" width="30%"></img>

Project was implemented using the <b>MVVM pattern</b> combined with <b>Clean architecture</b>.       

I have created 3 modules each with it's own role.
1. App Module - contains the view logic and the main responsability of this model is to provide visual feedback to the user
2. Domain Module - contains the business logic of the application and acts as a binder between the data module and app module (even if this app is a small app )
3. Data Module - contains the impementation that provides communication between the server and this application.      

If this was a bigger project with different features, I would have created a module for each feature and in that module the main packages would be:
- Presentation
- Domain
- Data

In this configuration we allow the following dependency
APP Module can has access to Domain Module and Data Module content
Domain Module has no access to any of the modules content
Data Module has access only to domain content


<img src="https://uploads.toptal.io/blog/image/127608/toptal-blog-image-1543413671794-80993a19fea97477524763c908b50a7a.png" width="55%"></img>        

The data flow for this application:
Fragment -> ViewModel -> UseCase -> Repository -> Endpoint

Project requirements
-----

# Sliide Android developer challenge 
## Congratulations, you have reached the next stage which is solving a Sliide practical test.
We’d like to you to write simple Android application for managing users.

### Description
When we have reviewed your test, and any accompanying documents you feel necessary, if we like what we see, we’ll invite you to join us for a video conversation during which we’ll ask you to go through your test, explaining any decisions that you made.

### Implementation
For implementation we use https://gorest.co.in/ public API

### Functional requirement
Feel free to use whatever flare you can to show off your skills.

You shouldn't spend more than 1 day on implementation, but if you need more time to show the best quality, feel free to use it. We prefer finished, clean, production ready implementation with unit tests, rather than half done solution.

#### 1 Displaying list of users
- After app is open list of users is displayed (only users from last page of the endpoint)
- Each entry contains name, email address and creation time (relative to now)
- Loading and error state are welcome

#### 2 Adding new user
- After + button is clicked pop up dialog is displayed with name and email entries
- After confirmation and successful user creation (201 response code) item is added to the list

#### 3 Removing existing user
- After item long press pop up dialog is displayed with question “Are you sure you want to remove this user?“
- After OK is clicked and user is removed (204 response code) item is deleted from the list

### Technical requirements
- Application must be developed in Kotlin with minimum Android SDK version of 21
- You are free to use whatever frameworks or tools you see fit
- Application needs to support device rotation
- Design should follow Material design guidelines
- RxJava or Coroutines
- Architecture one of MVP/MVVM/MVI
- Dependency injection with Dagger 2 or Hilt
- Unit tests

### Evaluation Criteria
- You create testable code
- You pay attention to detail
- Code should be production ready

### Deliverables
- The forked version of this repo


