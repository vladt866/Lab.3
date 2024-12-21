
import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class GornerTableCellRenderer implements TableCellRenderer {
    private String needle = null;
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();
    private DecimalFormat formatter =
            (DecimalFormat) NumberFormat.getInstance();
    @Override
    public Component getTableCellRendererComponent(JTable table, Object
            value, boolean isSelected, boolean hasFocus, int row, int col) {

        // Преобразовать число в строку с помощью форматировщика
        String formattedDouble = formatter.format(value);

        // Установить текст надписи равным строковому представлению числа
        label.setText(formattedDouble);
        if (col == 1) {
            for (char ch : formattedDouble.toCharArray()) {
                if (Character.isDigit(ch)) {
                    int digit = Character.getNumericValue(ch);
                    if (digit % 2 == 0 && digit != 0) {
                        panel.setBackground(Color.green);
                    }
                    else{
                        panel.setBackground(Color.WHITE);
                        break;
                    }

                }
            }

        }
        else {
            panel.setBackground(Color.WHITE);
        }
        if (col == 1 && needle != null && needle.equals(formattedDouble)) {

            if((panel.getBackground()).equals(Color.green)){
                panel.setBackground(Color.YELLOW);
            }
            else {
                panel.setBackground(Color.RED);
            }

        }
        else if(!(panel.getBackground()).equals(Color.green)){
            // Иначе - в обычный белый
            panel.setBackground(Color.WHITE);
        }


        return panel;

    }
    public GornerTableCellRenderer() {
// Показывать только 5 знаков после запятой
        formatter.setMaximumFractionDigits(5);

        formatter.setGroupingUsed(false);

        DecimalFormatSymbols dottedDouble =
                formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dottedDouble);
        // Разместить надпись внутри панели
        panel.add(label);
        // Установить выравнивание надписи по левому краю панели
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
    }
    public void setNeedle(String needle) {
        this.needle = needle;
    }

}
