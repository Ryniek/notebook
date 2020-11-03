# Noatnik

Aplikacja notatnika pozwala na dodawanie, edytowanie i usuwanie notatek. Została wykonana przy użyciu framework'a Spring Boot 2, Hibernate oraz silnika szablonów Thymeleaf.

## Folder model:
**Klasa Note:**
Jest to klasa modelowa JPA, przystosowana do obsługi przez Hibernate. Posiada pola id, title, author, message oraz date. Musi ona posiadać adnotację @Entity, co jest jednym z warunków modelu JPA. Posiada również adnotację @Table(name), która odpowiada za nadanie odpowiedniej nazwy tabeli w bazie danych. Pole id posiada adnotację @Id, co jest kolejnym z warunków modelu JPA, oraz adnotację @GeneratedValue, gdzie strategia ustawiona jest na IDENTITY, co oznacza, że pole id będzie automatycznie inkrementowane. Pole message posiada adnotację @Column(columnDefinition), która odpowiada za zmianę typu tego pola na text w bazie danych.
Klasa posiada również dwa konstruktory, jeden parametrowy z pominięciem id oraz bezparametrowy. Dalej mamy gettery i settery do każdego z pół oraz metodę toString do poprawnego wyświetlania danych.
