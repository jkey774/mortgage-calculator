package com.brookdale.core.components;

import com.adobe.cq.sightly.WCMUsePojo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUse extends WCMUsePojo {

    private DateFormat dateFormat;
    private Calendar inputDateObj;
    private String outputDateFormatted;

    @Override
    public void activate() {
        String inputDateParam = get("dateProperty", String.class);
        String methodParam = get("method", String.class);
        Integer dateFieldParam = get("dateField", Integer.class);
        Integer amountParam = get("amount", Integer.class);
        String dateFormatParam = get("format", String.class);

        if (dateFormatParam != null) {
            this.dateFormat = new SimpleDateFormat(dateFormatParam);
        } else {
            this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }

        if (inputDateParam != null) {
            this.inputDateObj = getCurrentPage().getProperties().get(inputDateParam, Calendar.class);
        }

        if (methodParam != null && dateFieldParam != null && amountParam != null) {
            int hours = this.inputDateObj.get(Calendar.HOUR);
            int days = this.inputDateObj.get(Calendar.DATE);
            int months = this.inputDateObj.get(Calendar.MONTH);
            int years = this.inputDateObj.get(Calendar.YEAR);

            this.inputDateObj.add(Calendar.dateFieldParam, amountParam);
        }

        if(inputDateObj != null) {
            this.outputDateFormatted = this.dateFormat.format(inputDateObj.getTime());
        }
    }

    public String getOutputDateFormatted() {
        return this.outputDateFormatted;
    }

}
