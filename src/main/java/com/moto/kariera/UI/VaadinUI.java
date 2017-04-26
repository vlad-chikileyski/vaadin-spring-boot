package com.moto.kariera.UI;

import com.moto.kariera.utils.DatePeriod;
import com.moto.kariera.utils.SortArray;
import com.moto.kariera.model.DateModel;
import com.vaadin.annotations.Theme;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.*;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {
    final DateField dateFieldTo;
    final DateField dateFieldFrom;
    final Grid<DateModel> grid;
    final Label labelDateTo, labelDateFrom;
    final Button addFieldValue;
    private final TabSheetComponent tabSheetComponent;
    List<DateModel> arrayList = new ArrayList<>();
    List list = new ArrayList();
    DatePeriod datePeriod = new DatePeriod();
    Map<LocalDate, LocalDate> items = new HashMap<>();

    @Autowired
    public VaadinUI() {
        this.tabSheetComponent = new TabSheetComponent();
        this.labelDateTo = new Label(" Date to: ");
        this.labelDateFrom = new Label(" Date from: ");
        this.dateFieldTo = new DateField();
        this.dateFieldFrom = new DateField();
        this.grid = new Grid<>(DateModel.class);
        this.addFieldValue = new Button("Apply!", FontAwesome.PLUS);
        this.arrayList = new ArrayList<>();
        this.datePeriod = new DatePeriod();
        this.list = new ArrayList();
    }

    @Override
    protected void init(VaadinRequest request) {
        HorizontalLayout actions = new HorizontalLayout(labelDateTo, dateFieldTo, labelDateFrom, dateFieldFrom, addFieldValue);
        VerticalLayout mainLayout = new VerticalLayout(actions, grid);
        setContent(mainLayout);
        grid.setSizeFull();
        grid.setItems(arrayList);
        actions.setSizeFull();

        addFieldValue.addClickListener(clickEvent -> {
            if (!dateFieldTo.isEmpty() && !dateFieldFrom.isEmpty()) {
                arrayList.add(new DateModel(dateFieldTo.getValue().toString(), dateFieldFrom.getValue().toString()));
                items.put(dateFieldTo.getValue(), dateFieldFrom.getValue());
                String period = datePeriod.getDaysCountBetweenDates(dateFieldTo.getValue(), dateFieldFrom.getValue());
                Notification.show("Added!" + "\nDays period:" + period);
                Notification notif = new Notification(
                        "Days period",
                        period + ".",
                        Notification.TYPE_TRAY_NOTIFICATION);
                notif.show(getCurrent().getPage());
                grid.getDataProvider().refreshAll();
                dateFieldTo.clear();
                dateFieldFrom.clear();
            } else {
                Notification.show("Please select a dates!");
            }
        });

        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.addSelectionListener(event -> {
            Set<DateModel> selected = event.getAllSelectedItems();
            grid.getDataProvider().refreshAll();
            Notification.show(selected.size() + " items selected");
        });

        grid.asMultiSelect().addValueChangeListener(event -> {
            if (event.getValue().size() == 2) {
                String aboutArray = datePeriod.testSystem(items);
                grid.setEnabled(false);
                Window subWindow = new Window("Multi date information!");
                VerticalLayout subContent = new VerticalLayout();
                subWindow.setContent(subContent);
                subContent.setHeight(700, Unit.PIXELS);
                subContent.setWidth(900, Unit.PIXELS);
                subWindow.setModal(true);
                subContent.addComponent(new TabSheetComponent(aboutArray));
                subWindow.center();
                subWindow.addCloseListener(closeEvent -> {
                    Page.getCurrent().reload();
                    grid.getDataProvider().refreshAll();
                    grid.clearSortOrder();
                    grid.deselectAll();
                    grid.setEnabled(true);
                    subWindow.close();
                });
                addWindow(subWindow);
                SortArray sortArray = new SortArray();
                int[] array = {-666, 1, 5, 8, 111, 9, 12, 98, 0};
                sortArray.nonSortArray(array);
            }
        });
    }
}
