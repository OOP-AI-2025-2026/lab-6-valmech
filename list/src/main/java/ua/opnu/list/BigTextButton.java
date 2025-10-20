package ua.opnu.list;

import javax.swing.*;
import java.awt.*;

/*
 * Необхідна стандартна кнопка, проте з текстом
 * більшого розміру, ніж за замовчуванням. Щоб уникнути виклику
 * методу setFont() для кожної кнопки окремо, створюємо підклас
 * класу JButton та викликаємо метод setFont() у конструкторі.
 *
 * Таким чином, при створенні екземпляра класу
 * BigTextButton ми отримуємо стандартну кнопку з текстом збільшеного розміру
 */
public class BigTextButton extends JButton {

    public BigTextButton(String text) {
        super(text);
        this.setFont(new Font("Arial", Font.PLAIN, 22));
    }
}