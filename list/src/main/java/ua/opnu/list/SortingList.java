package ua.opnu.list;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Comparator;

/*
 * Даний клас є нащадком стандартного класу Application.
 * Клас Application керує роботою застосунку на базі FX.
 * При розробці програми з використанням бібліотеки javaFX необхідно
 * створити власний клас, що наслідує клас Application
 */
public class SortingList extends Application {

    // Колекція об'єктів студентів.
    // Інтерфейс ObservableList подібний до інтерфейсу List, проте
    // надає можливість повідомляти інші компоненти про свої зміни
    private ObservableList<Student> students;

    /*
     * Даний метод виконується під час старту вашого застосунку.
     * Stage - клас, що представляє "сцену". Можна розглядати його як вікно програми.
     * У JavaFX вікно називається "сценою", аналогічно до театральної сцени.
     * Перша "сцена" (головне вікно програми) створюється системою автоматично та передається
     * як вхідний аргумент. Для створення додаткових "сцен"
     * потрібно робити це власноруч
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Назва вікна програми
        primaryStage.setTitle("Список студентів");

        // Наповнюємо колекцію студентів інформацією
        students = populateList();

        // Вертикальний контейнер для компонентів
        final VBox vbox = new VBox();
        // Проміжок між компонентами
        vbox.setSpacing(5);
        // Задаємо внутрішні відступи по 5 пікселів з усіх боків
        vbox.setPadding(new Insets(5));
        vbox.setAlignment(Pos.CENTER);

        // Компонент для відображення списку елементів.
        // При ініціалізації компонента передаємо колекцію студентів (students)
        // Для кожного об'єкта студента викликається метод toString() для відображення
        final ListView<Student> listView = new ListView<>(students);
        // Рекомендовані розміри компонента списку
        listView.setPrefSize(400, 240);

        // Створюємо горизонтальний контейнер з кнопками
        final HBox hbox = setButtons();

        // Додаємо спочатку компонент списку, потім контейнер з кнопками
        vbox.getChildren().addAll(listView, hbox);

        // Клас Scene є контейнером верхнього рівня для всіх компонентів інтерфейсу.
        // Scene містить кнопки, текстові поля, перемикачі та інші елементи.
        // У даному випадку Scene містить вертикальний контейнер
        // з списком студентів вгорі та рядком кнопок внизу
        Scene scene = new Scene(vbox);

        // Встановлюємо Scene для Stage. Можливість легкої заміни сцен
        // дозволяє зручно змінювати вміст вікон програми
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        // Відображаємо вікно на екрані
        primaryStage.show();
    }

    /*
     * Наповнюємо колекцію даними вручну
     */
    private ObservableList<Student> populateList() {
        Student student1 = new Student("Григорій", "Іванов", 75);
        Student student2 = new Student("Сергій", "Петренко", 92);
        Student student3 = new Student("Григорій", "Сергієнко", 61);
        Student student4 = new Student("Максим", "Сковорода", 88);

        // Клас ObservableArrayList подібний до ArrayList,
        // але має можливість повідомляти інші об'єкти про зміни
        return FXCollections.observableArrayList(
                student1, student2, student3, student4);
    }

    /*
     * Створюємо та налаштовуємо кнопки. Місце для вашого коду
     */
    private HBox setButtons() {
        final Button sortByNameButton = new Button("Сортувати за ім'ям");
        final Button sortByLastNameButton = new Button("Сортувати за прізвищем");
        final Button sortByMarkButton = new Button("Сортувати за оцінкою");

        HBox.setHgrow(sortByNameButton, Priority.ALWAYS);
        HBox.setHgrow(sortByLastNameButton, Priority.ALWAYS);
        HBox.setHgrow(sortByMarkButton, Priority.ALWAYS);
        sortByNameButton.setMaxWidth(Double.MAX_VALUE);
        sortByLastNameButton.setMaxWidth(Double.MAX_VALUE);
        sortByMarkButton.setMaxWidth(Double.MAX_VALUE);

        // індивідуальні прапорці для кожної кнопки сортування
        final boolean[] nameOrder = {true};
        final boolean[] lastNameOrder = {true};
        final boolean[] markOrder = {true};

        // Обробка сортування за ім'ям
        sortByNameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                students.sort(new NameSorter(nameOrder[0]));
                nameOrder[0] = !nameOrder[0];
            }
        });

        // Обробка сортування за прізвищем
        sortByLastNameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                students.sort(new LastNameSorter(lastNameOrder[0]));
                lastNameOrder[0] = !lastNameOrder[0];
            }
        });

        // Обробка сортування за середнім балом
        sortByMarkButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                students.sort(new MarkSorter(markOrder[0]));
                markOrder[0] = !markOrder[0];
            }
        });

        HBox hb = new HBox();
        hb.setSpacing(5);
        hb.getChildren().addAll(sortByNameButton, sortByLastNameButton, sortByMarkButton);
        hb.setAlignment(Pos.CENTER);

        return hb;
    }

    public static void main(String[] args) {
        // Запуск застосунку
        launch(args);
    }
}