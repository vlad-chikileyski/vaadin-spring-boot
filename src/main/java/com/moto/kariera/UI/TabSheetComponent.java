package com.moto.kariera.UI;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

public class TabSheetComponent extends CustomComponent {

    TabSheet tabsheet = new TabSheet();
    Label aboutArrayLabel = new Label("The coincidence of ranges");
    Label labelValue = new Label();

    TabSheetComponent() {
    }

    @Autowired
    TabSheetComponent(String infoArray) {
        setCompositionRoot(tabsheet);
        labelValue.setValue(infoArray);
        Panel panel = new Panel("Some title:)");
        VerticalLayout panelContent = new VerticalLayout();
        panel.setContent(panelContent);
        tabsheet.addTab(panelContent, "About", FontAwesome.PLUG);
        panelContent.addComponents(aboutArrayLabel, labelValue);
    }
}