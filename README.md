# Notatnik

Aplikacja notatnika pozwala na dodawanie, edytowanie i usuwanie notatek. Została wykonana przy użyciu framework'ów Spring Boot 2 i Hibernate oraz silnika szablonów Thymeleaf.

## Pakiet _model_:
**Klasa _Note.java_:**  
> Jest to klasa modelowa JPA, przystosowana do obsługi przez Hibernate. Posiada pola id, title, author, message oraz date. Musi ona posiadać adnotację _@Entity_, co jest jednym z warunków modelu JPA. Posiada również adnotację _@Table(name)_, która odpowiada za nadanie odpowiedniej nazwy tabeli w bazie danych. Pole id posiada adnotację _@Id_, co jest kolejnym z warunków modelu JPA, oraz adnotację _@GeneratedValue_, gdzie strategia ustawiona jest na IDENTITY, co oznacza, że pole id będzie automatycznie inkrementowane. Pole message posiada adnotację _@Column(columnDefinition)_, która odpowiada za zmianę typu tego pola na text w bazie danych.  
> Klasa posiada również dwa konstruktory, jeden parametrowy z pominięciem id oraz bezparametrowy. Dalej mamy gettery i settery do każdego z pól, czyli kolejne dwa warunki modelu JPA oraz metodę toString do poprawnego wyświetlania danych.

**Klasa _NoteDto.java_:**
> Jest to klasa odpowiedzialna za transfer danych pomiędzy widokiem a naszym kontrolerem. Od klasy modelowej naszej encji różni się tym, że posiada dodatkowo pole answer, w którym wysyłamy odpowiedź na pytanie weryfikujące. Po poprawnej weryfikacji, obiekt ten jest konwertowany na klase modelową JPA z wszystkimi koniecznymi polami.

**Klasa _AuthorDto.java_:**
> Klasa która odpowiedzialna jest za przesyłanie informacji o autorze notatki w momencie jej usuwania. Zawiera jedno pole author typu tekstowego.

## Pakiet _repository_:
**Interfejs _NoteRepo.java_:**
> Interfejs musi posiadać adnotację _@Repository_ oraz rozszerzać interfejs _JpaRepository_, w którym wskazujemy nasz model, którym repozytorium będzie zarządzało oraz typ pola id w tym modelu. Repozytorium pozwala nam na przemapowanie tabeli z bazy danych na listę i zarządzanie nią. Dostarcza nam również wiele metod, takich jak wyświetlanie danych, dodawanie, usuwanie itd.

## Pakiet _service_:
**Klasa _NoteService.java_:**
> Klasa posiadająca adnotację @Service, która odpowiedzialna jest za przetwarzanie logiki biznesowej naszej aplikacji, komunikację z warstwą bazy danych oraz z warstwą kontorlera przechwytującą żądania serwera.
> - Na samym początku mamy wstrzyknięte rezporytorium oraz konstruktor z adnotacją _@Autowired_.
> - Metoda _getAllNotes()_, która wykorzystując metodę dostarczoną przez repozytorium zwraca nam listę wszystkich notatek w bazie danych, jeżeli jakieś się tam znajdują, albo zwraca nam pustą listę, jeżeli baza danych jest pusta.
> - Metoda _getNoteById()_, która wykorzystując metodę z repozytorium zwraca nam notatkę na podstawie przekazanego id, lub zwracam nam null w przypadku, gdy nie znajdzie notatki.
> - Metoda _createOrUpdateNote()_, korzystając z metod z repozytorium dodaje lub edytuje notatkę. W momencie kiedy odwołamy się do meody przekazując id, przechodzi ona do fragmentu, który odpowiada za edcyję notatki. W przypadku, gdy odwołamy się do metody bez przekazania id, uruchamiany jest fragment odpowiedzialny za dodawnie notatki. Dodatkowo metoda sprawdza, czy została podana poprawna odpowiedź na pytanie zabezpieczające przy dodawniu lub edycji.
> - Metoda _deleteNoteById()_, korzystając z metody z repozytorium usuwa wskazaną notatkę. Sprawdza również, czy został podany właściwy autor notatki przy potwierdzeniu usnięcia.

## Pakiet _controller_:
**Klasa _NoteController.java_:**
> Klasa posiadająca adnotację _@Controller_ i odpowiadająca za implementację metod z _NoteService_ jako metod webowych oraz za połączenie z widokiem. Posiada również adnotację _@RequestMapping("/")_, która określa początkową ścieżkę adresu URL dla wszystkich metod webowych znajdujących się w danej klasie.
> - Na początku mamy wstrzyknięty _NoteService_ oraz konstruktor z adnotacją _@Autowired_.
> - Metoda _getAllNotes()_ korzystając z metody z _NoteService_ o takiej samej nazwie tworzy listę wszystkich notatek, dodaje do modelu atrybut _notes_, który jest wykorzystywany w widoku oraz zwraca widok _list-notes.html_, który jest stroną główną.
> - Metoda _editNoteById()_ odpowiedzialna jest za dodawanie lub edycję notatki. W przypadku edycji do metody przekazywane jest id, na podstawie którego w bazie daych jest szukana notatka z takim id, a następnie znaleziona encja konwertowana jest do obiektu _NoteDto_, dodawana do modelu widoku i zwracany jest widok _add-edit-note.html_. Gdy do metody nie jest przekazywane id, to tworzy ona pusty obiekt _NoteDto_ i zwraca widok _add-edit-note.html_.
> - Metoda _deleteNote()_ dodaje do modelu atrybuty _id_ oraz _authorDto_ i zwraca widok _delete-note.html_, który odpowiada za potwierdzenie usunięcia notatki.
> - Metoda _deleteNoteById()_ usuwa notatkę i przekierowuje na stronę główną, jeżeli został podany właściwy autor notatki, lub pozostaje na stronie potwierdzenia usunięcia i wyświetla informację o błędnej odpowiedzi.
> - Metoda _createOrUpdateNote()_ dodaje lub edytuje notatkę i przekierowuje na stronę główną, jeżeli została podana prawidłowa odpowiedź na pytanie zabezpieczające, lub pozostaje na stronie dodawania i wyświetla informację o błędnej odpowiedzi.

## Folder _resources_:
**_application.properties_:**
> W _application.properties_ konfigurujemy połączenie z bazą danych, wskazując URL bazy danych, nazwę użytkownika, hasło oraz sterownik. Dodatkowo wykorzystujemy zależność, która tworzy za nas schemat bazy danych, w tym przypadku jest to _update_, które aktualizuje dane w bazie danych, za każdym razem jak coś się zmieni.

## Folder _templates_:
**_add-edit-note.html_:**
> Widok posiadający formularz z polami tytuł, autor, treść, data oraz pytanie zabezpieczające. Posiada przycisk dodający/edytujący notatkę oraz przycisk powrotu do strony głównej. Formularz operuje na obiekcie stworzonym w metodzie _editNoteById()_ w klasie _NoteController_, natomiast korzysta z metody _createOrUpdateNote()_, która działa na endpoincie _/createNote_.

**_delete-note.html_:**
> Widok posiadający formularz z polem autor oraz przycisk do potwierdzenia usunięcia notatki. Posiada również przycisk powrotu do strony głównej. Formularz operuje na obiekcie _authorDto_ stworzonym w metodzie _deleteNote()_ w klasie _NoteController_, natomiast korzysta z metody _deleteNoteById()_, która działa na endpoincie _/delete/{id}_.

**_list-notes.html_:**
> Widok odpowiadający za stronę główną, który wyświetla wszystkie notatki. Korzysta z atrybutu _notes_, stworzonym w metodzie _getAllNotes()_ w _NoteController_. Przy każdej notatce są przyciski _Edit_ oraz _Delete_, które przekierowują pod odpowiednie endpointy odpowiedzialne za edycję bądź usuwanie notatki. Na samej górze mamy przycisk _Add note_, który przekierowuje do endpointu odpowiedzialnego za dodawanie notatki.
