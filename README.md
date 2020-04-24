# Biblioteca

## Dependencies
- Junit5
- Jdk version 1.8

## Release 1
A CLI that responds based on parameters given. The CLI creates a txt file that serializes the Library class.

### How to Run CLI
1. Use jar file in root of folder
`java -jar biblioteca.jar <args>`

2. Run Configurations
Under Run, click on `Edit Configurations` and choose scenario to run in


### CLI Usage
| Command | Example | Description |
|---|---|---|
| `-h` | `-h` | Welcome and Help Page |
|  `-m` | `-m` | Shows list of menu options |
|  `-m <menuOptNumber>` | `-m 1` | Shows Menu Item 1: list of available books |
| `-cb <bookTitle>` | `-cb "TDD for Dummies"` | Checkout a book |
| ` -rb <bookTitle>` | `-rb "TDD for Dummies"` | Return a book |
| `-q` | `-q` | Quit application |


## Release 2
A console application run in the terminal that responds given user input. Only logged in users can proceed. The program will show a menu, a list of available books, movies, user information. Upon clicking into the list of books and movies, user can choose to checkout a resource or view outstanding loans and display actions accordingly. 
 
 ### For Authentication
- LibraryID: `123-456789`
- Password: `password`


### How to quit application
Press q or quit at any point to exit.
