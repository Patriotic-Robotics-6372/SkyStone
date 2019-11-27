## Ideas

* Create classes for different mechanisms instead of the robot as a whole; for example, make a Base, Intake, and Lift class insead of just PRRobot class. 
This way, we can pick and choose which classes to include in a new program based on our needs.
If we need to test base, we only need to call the Base class. Calling PRRobot on the other hand is redundant. 

* For the method robotStatus(), we can use arrays and have the telemetry information be squeezed in more efficiently / laid out more clearly. 
