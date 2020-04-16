# Biblioteca

## Dependencies
- Junit5
- Jdk version 1.8

## Release 1
A CLI that responds based on parameters given.

### How to Run CLI
1. Use jar file
`java -jar biblioteca.jar <args>`

2. Run with Parameters
Under Run, click on `Edit Configuration`


### CLI Usage
| Command | Alias | Description |
|---|---|---|
| `--help` | `-h` | Welcome and Help Page |
| ` --menu` | `-m` | Shows list of menu options |
| ` --list-books` | `-l` | Shows list of available books |
| ` --checkout-book <bookTitle>` | `-cb` | Checkout a book |
| ` --return-book <bookTitle>` | `-rb` | Return a book |
| ` --quit` | `-q` | Quit application |


## Release 2
A console application run in the terminal that responds given user input. The program will show a menu, a list of available books and outstanding loans and display actions accordingly. Press q or quit at any point to exit.

### How to Run
- Run `BibliotecaApp.main()`

#### How to Terminate
- Type `q` or `quit`