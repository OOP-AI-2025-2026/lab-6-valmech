package ua.opnu.list;

import ua.opnu.list.BigTextButton;
import ua.opnu.list.PaintSurface;
import ua.opnu.list.DrawShape;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Основне вікно застосунку. Фрейм (клас JFrame) є головним контейнером
 */
public class DrawFrame extends JFrame {

    // Компонент для відображення графічних фігур
    private PaintSurface surface;

    // Конструктор створює графічний інтерфейс
    public DrawFrame(String title) {

        // Зауважте, що викликається
        // конструктор батьківського класу. Всередині нього
        // відбувається вся робота з відображення вікна
        // Нам потрібно лише скористатися функціоналом суперкласу
        super(title);

        // Вказуємо фрейму, що при закритті вікна застосунок припиняє виконання
        // (якщо це не визначити, програма залишатиметься в процесах
        // навіть після закриття вікна застосунку
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Визначаємо менеджер компонування
        // (він керує розташуванням
        // елементів всередині фрейму)
        this.setLayout(new BorderLayout());

        // Створюємо верхню панель з трьома кнопками
        this.add(setButtonPanel(), BorderLayout.NORTH);

        // Ініціалізуємо об'єкт області малювання
        surface = new PaintSurface();

        // Розміщуємо область для малювання фігур у фреймі
        this.add(surface, BorderLayout.CENTER);

        // Цей метод коригує розмір вікна таким чином,
        // щоб усі елементи в ньому були видимі
        this.pack();

        // Робимо фрейм видимим на екрані (активуємо відображення фрейму)
        this.setVisible(true);
    }

    /*
     * Цей метод ініціалізує та налаштовує
     * верхню панель з кнопками керування.
     */
    private JPanel setButtonPanel() {

        // Ініціалізуємо панель для розміщення кнопок
        JPanel buttonPanel = new JPanel(true);

        // Визначаємо для панелі, що елементи всередині
        // мають розташовуватися послідовно зліва направо з центруванням
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        // Встановлюємо колір фону панелі
        buttonPanel.setBackground(Color.CYAN);
        // Визначаємо рамку панелі (чорне обрамлення навколо панелі)
        buttonPanel.setBorder(new LineBorder(Color.BLACK, 2));

        // *** Розміщуємо кнопки на панелі ***

        // 1. Кнопка для створення прямокутника
        ua.opnu.list.BigTextButton rect = new ua.opnu.list.BigTextButton("Rectangle");

        // Це так званий обробник подій (Listener). Обробник - це об'єкт певного
        // класу, який містить визначений метод.
        // Цей об'єкт передається кнопці і при настанні певної
        // події, пов'язаної з кнопкою (наприклад, натискання на кнопку),
        // кнопка звертається до обробника і викликає його метод.
        // Так ми можемо визначити код, який буде
        // виконуватися при виникненні певних подій (наприклад, натискання кнопки)
        // Цей метод спрацьовує, коли користувач натискає кнопку
        rect.addActionListener(e -> {
            // Змінюємо значення поля в об'єкті області малювання,
            // щоб визначити, що тепер створюються прямокутники
            surface.setShapeType(DrawShape.SHAPE_RECTANGLE);
        });
        // розміщуємо першу кнопку на верхній панелі
        buttonPanel.add(rect);

        // 2. Кнопка для створення прямокутника зі скругленими кутами
        ua.opnu.list.BigTextButton rounded_rect = new ua.opnu.list.BigTextButton("Rounded rect.");
        rounded_rect.addActionListener(e -> {
            // Повідомляємо області малювання, що зараз створюються
            // прямокутники зі скругленими кутами
            surface.setShapeType(DrawShape.SHAPE_ROUNDED_RECT);
        });
        // Розміщуємо другу кнопку на верхній панелі
        buttonPanel.add(rounded_rect);

        // 3. Кнопка для створення еліпса (ЗАВДАННЯ)
        ua.opnu.list.BigTextButton ellipse = new ua.opnu.list.BigTextButton("Ellipse");
        ellipse.addActionListener(e -> {
            // Визначаємо тип фігури як SHAPE_ELLIPSE
            surface.setShapeType(DrawShape.SHAPE_ELLIPSE);
        });
        buttonPanel.add(ellipse);

        // 4. Кнопка для видалення всіх фігур
        ua.opnu.list.BigTextButton clear = new BigTextButton("Clear");
        clear.addActionListener(e -> {
            // Викликаємо метод очищення в компоненті PaintSurface
            surface.clearshapes();
        });
        buttonPanel.add(clear);

        return buttonPanel;
    }
}