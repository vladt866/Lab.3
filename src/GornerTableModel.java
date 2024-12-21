


import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.net.SocketOption;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class GornerTableModel extends AbstractTableModel {
    private DecimalFormat formatter =
            (DecimalFormat) NumberFormat.getInstance();

    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;

    public GornerTableModel(Double from, Double to, Double step, Double[]
            coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }

    public Double getFrom() {
        return from;
    }

    public Double getTo() {
        return to;
    }

    public Double getStep() {
        return step;
    }


    @Override
    public int getColumnCount() {
// TODO Auto-generated method stub
        return 3;
    }
    @Override
    public int getRowCount() {
// TODO Auto-generated method stub
        return new Double(Math.ceil((to-from)/step)).intValue()+1;
    }
    @Override
    public Object getValueAt(int row, int col) {
        // Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
        double x = from + step*row;

        switch (col){
            case 0:
            {
                return x;
            }
            case 1: {
                Double result = coefficients[0];
                // Вычисление значения в точке по схеме Горнера.
                for(int i = 1; i < coefficients.length; i++){
                    result = coefficients[i] + result*x;
                }
                return result;
            }
            default: {
                Double result = coefficients[0];
                // Вычисление значения в точке по схеме Горнера.
                for(int i = 1; i < coefficients.length; i++){
                    result = coefficients[i] + result*x;
                }

                int integerPart = result.intValue();
                String formattedInteger = String.valueOf(integerPart);
                double fractionalPart = result - integerPart;


                String formattedFractional = String.valueOf(fractionalPart).split("\\.")[1];
                String firstFiveDigits = formattedFractional.substring(0, Math.min(5, formattedFractional.length()));
                String reversedFormattedFractional = new StringBuilder(firstFiveDigits).reverse().toString();
                reversedFormattedFractional = reversedFormattedFractional.replaceFirst("^0+", "");
                for (char ch : formattedInteger.toCharArray()) {

                    if (Character.isDigit(ch)) {
                        int digit = Character.getNumericValue(ch);
                        if (digit % 2 != 0 || digit == 0) {
                            return false;
                        }
                    }

                }

                for (char ch_f : reversedFormattedFractional.toCharArray()) {

                    if (Character.isDigit(ch_f)) {

                        int digit = Character.getNumericValue(ch_f);
                        System.out.println(digit);
                        if (digit % 2 == 0)
                            return false;
                    }

                }




//
                return true;
            }
        }

    }
    @Override
    public Class<?> getColumnClass(int col) {
        if(col == 0 || col == 1){
            return Double.class;
        }
        else{
            return Boolean.class;
        }
    }
    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Значение X";
            case 1:
                return "Значение многочлена";
            default:
                return "Разностороннее";
        }
    }}