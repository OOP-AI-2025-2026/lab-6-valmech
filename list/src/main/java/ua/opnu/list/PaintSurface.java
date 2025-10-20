package ua.opnu.list;


import ua.opnu.list.DrawShape;

import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Область для відображення графічних фігур. Стандартний компонент Swing
 */
public class PaintSurface extends JComponent {

    // Колекція фігур, що відображені на області малювання
    private final List<DrawShape> shapes = new ArrayList<>();

    // Вибраний тип фігури
    private int shapeType;

    // Тут зберігаються координати курсору на момент початку перетягування та
    // в момент його завершення. Клас Point містить координати точки (x, y)
    private java.awt.Point startDrag;
    private java.awt.Point endDrag;

    // Набір кольорів для заливки
    private final List<Color> colors = Arrays.asList
            (Color.YELLOW, Color.MAGENTA, Color.CYAN, Color.RED, Color.BLUE, Color.PINK);

    public PaintSurface() {

        // Початково тип фігури встановлено як 0
        shapeType = 0;

        // Визначаємо рекомендовані розміри області малювання.
        // Оскільки клас успадковано від JComponent,
        // викликаємо відповідний метод батьківського класу
        super.setPreferredSize(new Dimension(400, 400));

        // Визначаємо обробку подій миші.
        // Необхідно визначити дії при натисканні кнопки миші
        // та при відпусканні кнопки миші
        this.addMouseListener(new MouseAdapter() {

            // Метод спрацьовує при натисканні кнопки миші користувачем
            public void mousePressed(MouseEvent e) {

                // При натисканні кнопки миші користувачем
                // зберігаємо поточні координати курсору на області малювання
                // Після чого встановлюємо однакові значення для endDrag і startDrag (якщо виконати просто клік
                // без руху миші, то startDrag і endDrag матимуть однакові значення).
                // Далі оновлюємо відображення області (виклик методу repaint()).

                startDrag = new java.awt.Point(e.getX(), e.getY());
                endDrag = startDrag;
                repaint();
            }

            // Метод спрацьовує при відпусканні
            // кнопки миші користувачем
            public void mouseReleased(MouseEvent e) {

                // При відпусканні кнопки миші в колекцію shapes додається нова фігура.
                // Потім очищаємо збережені координати та оновлюємо відображення області

                // Оскільки є декілька типів фігур, необхідно:
                // 1 - отримати значення поля shapeType - воно визначає тип фігури,
                // яку потрібно намалювати
                // 2. На основі значення shapeType – створити екземпляр відповідного класу
                // Якщо поле shapeType має невідоме значення або не встановлене
                // то не додаємо нічого, оскільки це помилкова ситуація

                DrawShape shape = DrawShape.newInstance(shapeType);

                // Встановлюємо початкову та кінцеву точку для об'єкта
                shape.setStartPoint(startDrag);
                shape.setEndPoint(endDrag);
                // Додаємо створену фігуру до колекції
                shapes.add(shape);
                // Скидаємо початкові та кінцеві координати
                startDrag = null;
                endDrag = null;
                // Оновлюємо відображення області
                repaint();
            }
        });

        // Метод спрацьовує при виконанні користувачем операції drag (перетягування).
        // Тобто користувач утримує кнопку миші натиснутою та переміщує курсор
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                // При переміщенні курсору зберігаємо оновлені координати та
                // оновлюємо відображення області
                endDrag = new Point(e.getX(), e.getY());
                repaint();
            }
        });
    }

    // Визначення типу фігури для малювання
    public void setShapeType(int type) {
        this.shapeType = type;
    }

    public void clearshapes(){
        shapes.clear();
        repaint();
    }
    /*
     * Метод paint виконується автоматично при оновленні
     * вікна. Ми не викликаємо його вручну!
     *
     * Специфіка роботи з 2D графікою полягає в тому, що щоразу потрібно заново
     * відображати вміст області, включаючи фонову сітку та всі додані раніше фігури.
     * Саме для цієї мети створено колекцію shapes.
     * Без неї раніше намальовані фігури зникали б при кожному оновленні відображення.
     */
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // 1. Активуємо згладжування країв
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 2. Відображаємо фонову сітку
        paintBackgroundGrid(g2);
        // 3. Задаємо товщину обведення фігури у 2 пікселі
        g2.setStroke(new BasicStroke(2));
        // 4. Застосовуємо напівпрозорість для заливки
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));

        // Перебираємо всі фігури з колекції та відображаємо їх на області
        shapes.forEach(s -> {
            // Встановлюємо чорний колір для контуру фігури
            g2.setPaint(Color.BLACK);
            // Відображаємо контур фігури
            g2.draw(s.getShape());
            // Визначаємо колір заливки фігури
            g2.setPaint(colors.get(shapes.indexOf(s) % 6));
            // Виконуємо заливку фігури
            g2.fill(s.getShape());
        });

        // Якщо оновлення відбувається під час створення нової фігури
        // відображаємо сірий контур фігури, що малюється
        if (startDrag != null && endDrag != null) {
            g2.setPaint(Color.LIGHT_GRAY);

            // Оскільки є декілька типів фігур, необхідно:
            // 1 - отримати значення поля shapeType - воно визначає тип фігури,
            // яку потрібно намалювати
            // 2. На основі значення shapeType – створити екземпляр відповідного класу
            // Якщо поле shapeType має невідоме значення або не встановлене
            // то не додаємо нічого, оскільки це помилкова ситуація
            DrawShape shape = DrawShape.newInstance(shapeType);

            // Відображаємо фігуру
            g2.draw(shape.getShape(startDrag, endDrag));
        }
    }

    // Цей метод створює фонову сітку на області малювання
    private void paintBackgroundGrid(Graphics2D g2) {
        // Сітка відображається сірим кольором
        g2.setPaint(Color.LIGHT_GRAY);

        // У циклі створюємо вертикальні лінії з інтервалом 10 пікселів по ширині
        for (int i = 0; i < getSize().width; i += 10) {
            // Створюємо пряму лінію (4 параметри - координати початкової та кінцевої точки)
            Shape line = new Line2D.Float(i, 0, i, getSize().height);
            g2.draw(line);
        }

        // Аналогічно з інтервалом 10 пікселів по висоті створюємо горизонтальні лінії
        for (int i = 0; i < getSize().height; i += 10) {
            // Створюємо пряму лінію (4 параметри - координати початкової та кінцевої точки)
            Shape line = new Line2D.Float(0, i, getSize().width, i);
            g2.draw(line);
        }
    }
}