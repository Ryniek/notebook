# Noatnik

Aplikacja notatnika pozwala na dodawanie, edytowanie i usuwanie notatek. Została wykonana przy użyciu framework'a Spring Boot 2, Hibernate oraz silnika szablonów Thymeleaf.

## Folder _model_:
**Klasa _Note_:**  
> Jest to klasa modelowa JPA, przystosowana do obsługi przez Hibernate. Posiada pola id, title, author, message oraz date. Musi ona posiadać adnotację _@Entity_, co jest jednym z warunków modelu JPA. Posiada również adnotację _@Table(name)_, która odpowiada za nadanie odpowiedniej nazwy tabeli w bazie danych. Pole id posiada adnotację _@Id_, co jest kolejnym z warunków modelu JPA, oraz adnotację _@GeneratedValue_, gdzie strategia ustawiona jest na IDENTITY, co oznacza, że pole id będzie automatycznie inkrementowane. Pole message posiada adnotację _@Column(columnDefinition)_, która odpowiada za zmianę typu tego pola na text w bazie danych.  
> Klasa posiada również dwa konstruktory, jeden parametrowy z pominięciem id oraz bezparametrowy. Dalej mamy gettery i settery do każdego z pól, czyli kolejne dwa warunki modelu JPA oraz metodę toString do poprawnego wyświetlania danych.

**Klasa _NoteDto_:**
> AAA

**Klasa _AuthorDto_:**
> AAA

## Folder _controller_:
**Klasa _NoteController_:**
> AAA

## Folder _repository_:
**Interfejs _NoteRepo_:**
> AAA

## Folder _service_:
**Klasa _NoteService_:**
> AAA

## Folder _resources_:
**_application.resources_:**
> AAA

## Folder _templates_:
**_add-edit-note.html_:**
> AAA

**_delete-note.html_:**
> AAA

**_list-notes.html_:**
> AAA
