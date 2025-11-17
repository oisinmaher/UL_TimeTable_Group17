CSV Files are Comment Separated Values.
It stores a list of values (in our case the variables of an object) line by line
The values of each object are seperated by commas

For example the Student object:
name = "Oisin", id = 24377112, yearOfStudy = 2
would be stored on its OWN line as
Oisin, 24377112, 2

When reading/writing we will need to convert these values to and from Strings

The point of the csv files is to allow us to "save our progress"
when we are creating our timetable 
(so we don't have to spend up to an hour creating a suitable timetable every time we start our project)
