# Library Management System

A Java-based library management system built while learning Spring Boot fundamentals.

## Features

- **Book Management**: Create and manage books with properties (title, author, pages, price)
- **Magazine Support**: Handle magazines with issue numbers and publication dates
- **Polymorphism**: Uses Readable and Purchasable interfaces for flexible item handling
- **Search Functionality**: Find items by title, author, or other criteria
- **Price Analysis**: Calculate total value, find most/least expensive items
- **Comprehensive Testing**: Full JUnit 5 test coverage with 50+ tests

## Technologies Used

- Java 17
- Maven
- JUnit 5
- Streams API
- Lambda Expressions

## Project Structure
```
src/
├── main/java/org/example/
│   ├── Book.java           - Book class with properties
│   ├── EBook.java          - EBook extending Book
│   ├── Magazine.java       - Magazine class
│   ├── Library.java        - Library management logic
│   ├── Readable.java       - Interface for readable items
│   └── Purchasable.java    - Interface for purchasable items
└── test/java/org/example/
    ├── BookTest.java       - Comprehensive Book tests
    └── LibraryTest.java    - Comprehensive Library tests
```

## Running the Project
```bash
# Clone the repository
git clone https://github.com/YOUR-USERNAME/LibraryManagementSystem.git

# Navigate to project directory
cd LibraryManagementSystem

# Run tests
mvn test

# Compile
mvn compile
```

## What I Learned

- Object-Oriented Programming (classes, inheritance, interfaces, polymorphism)
- Modern Java features (lambdas, streams, Optional, method references)
- Test-Driven Development with JUnit 5
- Maven project structure
- Git version control and GitHub workflow

## Future Enhancements

- Add database persistence
- Implement user authentication
- Create REST API with Spring Boot
- Add web interface

## Author

Built as part of a 10-week Spring Boot learning journey.

---

**Week 1 Complete!** ✅