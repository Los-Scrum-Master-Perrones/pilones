package DBUtilitie;

import java.io.Serializable;
import java.text.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Objetos_POJO.Clase_control_temperatura;
import org.jfree.chart.HashUtils;
import org.jfree.chart.util.ObjectUtils;
import org.jfree.chart.util.Args;
import org.jfree.chart.util.PublicCloneable;
import org.jfree.data.DataUtils;
import org.jfree.data.category.CategoryDataset;

public class CustomAbstractCategoryItemLabelGenerator
        implements PublicCloneable, Cloneable, Serializable {

        /** For serialization. */
        private static final long serialVersionUID = -7108591260223293197L;
    private ArrayList<Clase_control_temperatura> list_db;

    private String[] fechas;
    private int i = 0;

    /**
         * The label format string used by a {@code MessageFormat} object to
         * combine the standard items:  {0} = series name, {1} = category,
         * {2} = value, {3} = value as a percentage of the column total.
         */
        private String labelFormat;

        /** The string used to represent a null value. */
        private String nullValueString;

        /**
         * A number formatter used to preformat the value before it is passed to
         * the MessageFormat object.
         */
        private NumberFormat numberFormat;

        /**
         * A date formatter used to preformat the value before it is passed to the
         * MessageFormat object.
         */
        private DateFormat dateFormat;

        /**
         * A number formatter used to preformat the percentage value before it is
         * passed to the MessageFormat object.
         */
        private NumberFormat percentFormat;

        /**
         * Creates a label generator with the specified number formatter.
         *
         * @param labelFormat  the label format string ({@code null} not
         *                     permitted).
         * @param formatter  the number formatter ({@code null} not permitted).
         */
    protected CustomAbstractCategoryItemLabelGenerator(String labelFormat,
                NumberFormat formatter) {
            this(labelFormat, formatter, NumberFormat.getPercentInstance());
        }

        /**
         * Creates a label generator with the specified number formatter.
         *
         * @param labelFormat  the label format string ({@code null} not
         *                     permitted).
         * @param formatter  the number formatter ({@code null} not permitted).
         * @param percentFormatter  the percent formatter ({@code null} not
         *     permitted).
         *
         * @since 1.0.2
         */
    protected CustomAbstractCategoryItemLabelGenerator(String labelFormat,
                NumberFormat formatter, NumberFormat percentFormatter) {
            Args.nullNotPermitted(labelFormat, "labelFormat");
            Args.nullNotPermitted(formatter, "formatter");
            Args.nullNotPermitted(percentFormatter, "percentFormatter");
            this.labelFormat = labelFormat;
            this.numberFormat = formatter;
            this.percentFormat = percentFormatter;
            this.dateFormat = null;
            this.nullValueString = "-";


        }

    protected CustomAbstractCategoryItemLabelGenerator(String labelFormat,
                                                       NumberFormat formatter, String[] diasPorMes, ArrayList<Clase_control_temperatura> list_db) {
        Args.nullNotPermitted(labelFormat, "labelFormat");
        Args.nullNotPermitted(formatter, "formatter");
        this.labelFormat = labelFormat;
        this.numberFormat = formatter;
        this.fechas= diasPorMes;
        this.list_db = list_db;
        this.dateFormat = null;
        this.nullValueString = "-";


    }



        /**
         * Creates a label generator with the specified date formatter.
         *
         * @param labelFormat  the label format string ({@code null} not
         *                     permitted).
         * @param formatter  the date formatter ({@code null} not permitted).
         */
    protected CustomAbstractCategoryItemLabelGenerator(String labelFormat,
                DateFormat formatter) {

            Args.nullNotPermitted(labelFormat, "labelFormat");
            Args.nullNotPermitted(formatter, "formatter");
            this.labelFormat = labelFormat;
            this.numberFormat = null;
            this.percentFormat = NumberFormat.getPercentInstance();
            this.dateFormat = formatter;
            this.nullValueString = "-";

        }

        /**
         * Generates a label for the specified row.
         *
         * @param dataset  the dataset ({@code null} not permitted).
         * @param row  the row index (zero-based).
         *
         * @return The label.
         */
        public String generateRowLabel(CategoryDataset dataset, int row) {

            return dataset.getRowKey(row).toString().toString().substring(8);
        }

        /**
         * Generates a label for the specified row.
         *
         * @param dataset  the dataset ({@code null} not permitted).
         * @param column  the column index (zero-based).
         *
         * @return The label.
         */
        public String generateColumnLabel(CategoryDataset dataset, int column) {

            return dataset.getColumnKey(column).toString().substring(8);
        }

        /**
         * Returns the label format string.
         *
         * @return The label format string (never {@code null}).
         */
        public String getLabelFormat() {
            return this.labelFormat;
        }

        /**
         * Returns the number formatter.
         *
         * @return The number formatter (possibly {@code null}).
         */
        public NumberFormat getNumberFormat() {
            return this.numberFormat;
        }

        /**
         * Returns the date formatter.
         *
         * @return The date formatter (possibly {@code null}).
         */
        public DateFormat getDateFormat() {
            return this.dateFormat;
        }

        /**
         * Generates a for the specified item.
         *
         * @param dataset  the dataset ({@code null} not permitted).
         * @param row  the row index (zero-based).
         * @param column  the column index (zero-based).
         *
         * @return The label (possibly {@code null}).
         */
        protected String generateLabelString(CategoryDataset dataset,
        int row, int column) {
            Args.nullNotPermitted(dataset, "dataset");
            String result;
            Object[] items = createItemArray(dataset, row, column);
            result = MessageFormat.format(this.labelFormat, items);

            Calendar c = Calendar.getInstance();

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");


            Date fecha = new Date();
            try{
                 fecha = format.parse(fechas[column]);
            }catch (ParseException e){
                System.out.println(e);
            }
            c.setTime(fecha);



            if (Integer.parseInt(result) > 79){
                System.out.println(result);
                if(list_db.get(i).getMantenimiento_temp().length()>0){
                    switch (String.valueOf(list_db.get(i).getMantenimiento_temp().toLowerCase().charAt(0))){
                        case "v":
                            result = "V";
                            i++;
                            break;
                        case "m":
                            result = "M";
                            i++;
                            break;
                    }

                }else {
                   i++;

                }

               }

            int nD=c.get(Calendar.DAY_OF_WEEK);
            switch (nD){
                case 1: result = "D";
                    break;
                case 7: result = "S";
                    break;
            }

            if (result.equals("79")) result="";


            return result;

        }

        /**
         * Creates the array of items that can be passed to the
         * {@link MessageFormat} class for creating labels.
         *
         * @param dataset  the dataset ({@code null} not permitted).
         * @param row  the row index (zero-based).
         * @param column  the column index (zero-based).
         *
         * @return The items (never {@code null}).
         */
        protected Object[] createItemArray(CategoryDataset dataset,
        int row, int column) {
            Object[] result = new Object[4];
            result[0] = dataset.getRowKey(row).toString();
            result[1] = dataset.getColumnKey(column).toString();
            Number value = dataset.getValue(row, column);
            if (value != null) {
                if (this.numberFormat != null) {
                    result[2] = this.numberFormat.format(value);
                }
                else if (this.dateFormat != null) {
                    result[2] = this.dateFormat.format(value);
                }
            }
            else {
                result[2] = this.nullValueString;
            }
            if (value != null) {
                double total = DataUtils.calculateColumnTotal(dataset, column);
                double percent = value.doubleValue() / total;
                //result[3] = this.percentFormat.format(percent);
            }

            return result;
        }


        /**
         * Tests this object for equality with an arbitrary object.
         *
         * @param obj  the other object ({@code null} permitted).
         *
         * @return A boolean.
         */
        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CustomAbstractCategoryItemLabelGenerator)) {
                return false;
            }

            CustomAbstractCategoryItemLabelGenerator that
                    = (CustomAbstractCategoryItemLabelGenerator) obj;
            if (!this.labelFormat.equals(that.labelFormat)) {
                return false;
            }
            if (!ObjectUtils.equal(this.dateFormat, that.dateFormat)) {
                return false;
            }
            if (!ObjectUtils.equal(this.numberFormat, that.numberFormat)) {
                return false;
            }
            return true;
        }

        /**
         * Returns a hash code for this instance.
         *
         * @return A hash code.
         */
        @Override
        public int hashCode() {
            int result = 127;
            result = HashUtils.hashCode(result, this.labelFormat);
            result = HashUtils.hashCode(result, this.nullValueString);
            result = HashUtils.hashCode(result, this.dateFormat);
            result = HashUtils.hashCode(result, this.numberFormat);
            result = HashUtils.hashCode(result, this.percentFormat);

            return result;
        }

        /**
         * Returns an independent copy of the generator.
         *
         * @return A clone.
         *
         * @throws CloneNotSupportedException  should not happen.
         */
        @Override
        public Object clone() throws CloneNotSupportedException {
            CustomAbstractCategoryItemLabelGenerator clone
                    = (CustomAbstractCategoryItemLabelGenerator) super.clone();
            if (this.numberFormat != null) {
                clone.numberFormat = (NumberFormat) this.numberFormat.clone();
            }
            if (this.dateFormat != null) {
                clone.dateFormat = (DateFormat) this.dateFormat.clone();
            }
            return clone;
        }

    }
