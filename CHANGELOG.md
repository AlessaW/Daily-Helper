# Changelog
All notable changes to this project will be documented in this file.

## [released]

## [1.0.0] - 2022/07/15

### Changed
- finished everything up for release

### Removed
- Esspresso Tests


## [0.2.0] - 2022/07/14

### Added
- Readme to navigate the project
- MenuActivity as launcher activity so you can start the app and choose the function you want to use
- DbTests for the Taskmanager

### Changed
- changed the textfields of category, duration and priority in EditTask and AddTask to drop down menus, for it to be easier to use

### Fixed
- the toString method of the Tasks so you can see everything of a task when you call it

### Deprecated
- Esspresso Tests for the Taskmanager, because they just wont work :(


## [0.1.3] - 2022/06/30

### Added
- advanced Scheduler methods so the user can do more with the scheduler
- a working factory for the Scheduler
- Unit Tests for the Scheduler and Esspresso Tests for the Taskmanager
- logging for Taskmanager

### Changed
- the launcher activity to TaskmanagerActivity
- merged the Scheduler and Taskmanager together

### Fixed
- scheduleTask, so the Task List of Taskmanager can be used in it
- method setCompareLength which threw a outOfBoundExepetion 

### Removed
- Task and Adapter classes in Scheduler, because it was not needed
- The not used MainActivity and its fragments


## [0.1.2] - 2022/06/16

### Added
- Basic GUI for the Scheduler so the user can finally see something
- Duration time screen
- The database is now async so that the GUI is still working while the data is saved
- Delete button in Taskmanager works now


## [0.1.1] - 2022/06/12

### Added
- AdvancedScheduler, IScheduler, SchedulerFactory, SimpleScheduler, SchedulerTests classes as the starting point for the programming
- ScheduleTasks in SimpleScheduler
- The TaskList to SchedulerActivity

### Fixed
- Xml File was missing
- A small mistake with the Priorities in ScheduleTasks


## [0.1.0] - 2022/06/09

### Added
- EditTask now works by clicking on the items which is more intuitive
- Cancel button for AddTask and EditTask so the user can cancel out of the editor without breaking anything
- AddTask works now
- DeleteTaskButton in EditTask can now be seen but doesnt work yet

### Changed
- Cleaned the GUI to make the Design more crisp and easier to understand

### Removed
- EditTaskButton in MainActivity because it now works by clicking on the task


## [0.0.3] - 2022/05/30

### Added
- Database in which we can save our Tasks in
- TaskManagerActivity and Fragments with basic layout as a ugly but usable way to interact with our app
- RecyclerView and RecyclerViewAdapter which show the saved tasks
- Connected Task to database
- CI/CD which provides a better workflow with git

### Changed
- The package structure with new and improved packages because our old structure wasnt up to the standards

### Removed
- The Tasklist class because it turned out to ne useless for our project


## [0.0.2] - 2022/05/15

### Added
- TaskManagerController, Task, TaskCategory and Tasklist classes with basic attributes and methods
- Some getter and setter for Task class which provide a controlled access to the attributes of that class

### Changed
- UML diagramm for Taskmanager to netter represent our current project structure

### Fixed
- Changed the Integer declarations to int which wouldnt work otherwise


## [0.0.1] - 2022/04/23

### Added
- Basic project structure to have directories in which we can write our code and save the diagramms and user stories
- User story for the decision matrix to get a better understanding of what we have to do
- Use case and UML diagramms of the Taskmanager, Scheduler and Decisionmatrix for the first ideas on how to get started with coding 
- Changelog
