package ua.opnu.list;


import ua.opnu.list.DrawFrame;

import javax.swing.*;

public class MainForDraw {

    public static void main(String[] args) {

        // Ініціалізація графічного інтерфейсу в окремому потоці виконання
        // Для ознайомлення з функціоналом додатку, дивіться реалізацію класу DrawFrame
        SwingUtilities.invokeLater(() -> new DrawFrame("Program Draw"));
    }
}