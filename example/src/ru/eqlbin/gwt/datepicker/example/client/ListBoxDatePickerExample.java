package ru.eqlbin.gwt.datepicker.example.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.CalendarUtil;

import ru.eqlbin.gwt.datepicker.client.ListBoxDatePicker;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ListBoxDatePickerExample implements EntryPoint {
    
    
    private static final DateTimeFormat DATE_FORMAT = DateTimeFormat.getFormat("yyyy-MM-dd");

    
    private ListBoxDatePicker datePickerFloating;
    private ListBoxDatePicker datePickerFixed;
    
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
  
        RootPanel.get("datePickerFloatingExample").add(createFloatingDatePickerExample());
        RootPanel.get("datePickerFixedExample").add(createFixedDatePickerExample());
        RootPanel.get("defaultMethodsExample").add(createDefaultMethodsExample());
    }


    public Widget createFloatingDatePickerExample() {
        
        int currentYear = 2015;
        int yearsCount = 10;
        
        datePickerFloating = new ListBoxDatePicker();
        datePickerFloating.setVisibleYearCount(yearsCount);
        
        final Label currentYearLabel = new Label("Current year:"); 
        final Label yearsCountLabel = new Label("Years count:"); 
        final Label selectedDateLabel = new Label();
        
        final TextBox currentYearInputBox = new TextBox();
        currentYearInputBox.setText(String.valueOf(currentYear));
        final TextBox yearCountInput = new TextBox();
        yearCountInput.setText(String.valueOf(yearsCount));
        
        final Button setRangeButton = new Button("Set range");
        
        VerticalPanel datePickerPanel = new VerticalPanel();
        datePickerPanel.setSpacing(5);
        datePickerPanel.add(datePickerFloating);
        datePickerPanel.add(selectedDateLabel);
        
        VerticalPanel yearsRangePanel = new VerticalPanel();
        yearsRangePanel.setSpacing(5);
        yearsRangePanel.add(currentYearLabel);
        yearsRangePanel.add(currentYearInputBox);
        yearsRangePanel.add(yearsCountLabel);
        yearsRangePanel.add(yearCountInput);
        yearsRangePanel.add(setRangeButton);
        
        HorizontalPanel mainPanel = new HorizontalPanel();
        mainPanel.setWidth("500px");
        mainPanel.setSpacing(10);
        mainPanel.add(datePickerPanel);
        mainPanel.add(yearsRangePanel);
        
        setRangeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                
                int currentYear = Integer.parseInt(currentYearInputBox.getValue());
                int yearsCount = Integer.parseInt(yearCountInput.getValue());
                
                if(currentYear < 0) {
                    Window.alert("Current year must be > -1!");
                    return;
                }
                
                if(yearsCount <= 0) {
                    Window.alert("Years count must be > 0");
                    return;
                }
                
                datePickerFloating.setVisibleYearCount(yearsCount);
            }
        });
        
        datePickerFloating.addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                selectedDateLabel.setText("Selected date: " +
                        DATE_FORMAT.format(event.getValue()));
            }
        });
        
        return mainPanel;
    }
    
    @SuppressWarnings("deprecation")
    private Widget createFixedDatePickerExample() {
        
        final DateTimeFormat dateFormat = DateTimeFormat.getFormat("yyyy-MM-dd");
        
        Date currentDate = new Date();
        
        Date minDate = CalendarUtil.copyDate(currentDate);
        CalendarUtil.addMonthsToDate(minDate, -12 * 5);
        
        Date maxDate = CalendarUtil.copyDate(currentDate);
        CalendarUtil.addMonthsToDate(maxDate, 12 * 5);
        
        datePickerFixed = new ListBoxDatePicker();
        datePickerFixed.setDateRange(minDate, maxDate);
        
        final Label minYearLabel = new Label("Min date:"); 
        final Label maxYearLabel = new Label("Max date:"); 
        final Label selectedDateLabel = new Label();
        
        final TextBox minYearInput = new TextBox();
        minYearInput.setText(dateFormat.format(minDate));
        final TextBox maxYearInput = new TextBox();
        maxYearInput.setText(dateFormat.format(maxDate));
        
        final Button setRangeButton = new Button("Set range");

        VerticalPanel datePickerPanel = new VerticalPanel();
        datePickerPanel.setSpacing(5);
        datePickerPanel.add(datePickerFixed);
        datePickerPanel.add(selectedDateLabel);

        VerticalPanel yearsRangePanel = new VerticalPanel();
        yearsRangePanel.setSpacing(5);
        yearsRangePanel.add(minYearLabel);
        yearsRangePanel.add(minYearInput);
        yearsRangePanel.add(maxYearLabel);
        yearsRangePanel.add(maxYearInput);
        yearsRangePanel.add(setRangeButton);
        
        HorizontalPanel mainPanel = new HorizontalPanel();
        mainPanel.setWidth("500px");
        mainPanel.setSpacing(10);
        mainPanel.add(datePickerPanel);
        mainPanel.add(yearsRangePanel);

        
        setRangeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {     
                
                Date minDate = dateFormat.parse(minYearInput.getValue());
                Date maxDate = dateFormat.parse(maxYearInput.getValue());
                
//                if(minYear <= 0 || maxYear <= 0 || minYear > maxYear ) {
//                    Window.alert("Wrong fixed range of years!" );
//                    return;
//                }
                
                datePickerFixed.setDateRange(minDate, maxDate);
            }
        });

        datePickerFixed.addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                selectedDateLabel.setText("Selected date: " +
                        DATE_FORMAT.format(event.getValue()));
            }
        });
        
        return mainPanel;
    }
    
    
    private Widget createDefaultMethodsExample(){
        
        final CheckBox setYearAndMonthDropdownVisibleCheck = new CheckBox("setYearAndMonthDropdownVisible");
        setYearAndMonthDropdownVisibleCheck.setValue(true);
        
        final CheckBox setYearArrowsVisibleCheck = new CheckBox("setYearArrowsVisible");
        setYearArrowsVisibleCheck.setValue(true);
        
        final Button setVisibleYearCountButton = new Button("setVisibleYearCount");
        
        final TextBox yearCountInput = new TextBox();
        
        Grid grid = new Grid(2, 2);
        grid.setCellSpacing(10);
        grid.setWidget(0, 0, setYearArrowsVisibleCheck);
        grid.setWidget(0, 1, setYearAndMonthDropdownVisibleCheck);
        grid.setWidget(1, 0, yearCountInput);
        grid.setWidget(1, 1, setVisibleYearCountButton);
        
        
        setYearAndMonthDropdownVisibleCheck.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                datePickerFixed.setYearAndMonthDropdownVisible(event.getValue());                
                datePickerFloating.setYearAndMonthDropdownVisible(event.getValue());                
            }
        });
        
        setYearArrowsVisibleCheck.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                datePickerFixed.setYearArrowsVisible(event.getValue());                
                datePickerFloating.setYearArrowsVisible(event.getValue());                
            }
        });
        
        setVisibleYearCountButton.addClickHandler(new ClickHandler() {
            
            @SuppressWarnings("deprecation")
            @Override
            public void onClick(ClickEvent event) {
                datePickerFixed.setVisibleYearCount(Integer.parseInt(yearCountInput.getValue()));
                datePickerFloating.setVisibleYearCount(Integer.parseInt(yearCountInput.getValue()));
            }
        });
        
        return grid;
    }
    
    
}
