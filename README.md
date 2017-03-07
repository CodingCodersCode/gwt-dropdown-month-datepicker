ListBoxDatePicker for GWT
========================

Date picker component which allows to select the current month 
of the calendar, using the drop-down list for setting the month and year.

# Usage

```xml
<inherits name='ru.eqlbin.gwt.datepicker.ListBoxDatePicker'/>
```


```java
// Fixed range of years
ListBoxDatePicker datePicker1 = new ListBoxDatePicker();
datePicker1.setFixedYearsRange(2005, 2015);

// Floating range of years
ListBoxDatePicker datePicker2 = new ListBoxDatePicker();
datePicker2.setFloatingYearsRange(-5, 5);
```

# Note

This project was created in times of GWT 2.5.

Similar functionality appeared in default DatePicker since version 2.6:

```java
DatePicker datePicker = new DatePicker();
datePicker.setYearAndMonthDropdownVisible(true);
datePicker.setYearArrowsVisible(true);
```