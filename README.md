# car-builder

CarBuilder is a Java program that allows a user to select from one of four car models,
customize with various options, input personal information, and view a detailed
breakdown of the cost to purchase the vehicle as built.

## Details

CarBuilder was created for a programming assignment at Broward College. In addition
to satisfying the requirements of the assignment, additional functionality was 
added to validate input, handle errors, and improve stability.

### CarBuilder Class

This class uses JavaFX to enable the user to interact with the program using a
graphical interface. It creates an instance of the MyBuild class and the Customer class
in order to store and/or modify the data relating to the current user and his/her build.

### MyBuild Class

This class describes an object to contain all of the necessary data pertaining to the
user's car build.

### Customer Class

This class describes an object to contain all of the necessary data pertaining to the 
user building the car.

### Negative Trade Exception Class

This class provides a custom error message for negative values entered into the 
trade-in value field of the CarBuilder program.

## Prerequisites

JavaFX

## Built With

* [Intellij IDEA](https://www.jetbrains.com/idea/)

## Author

Daniel Garon

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
