package com.test.loadtest;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Push;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.themes.ValoTheme;
@SpringUI
@Push()
public class VaadinUI extends UI {
@Autowired
Service service;
Grid<Players> grid;
ListDataProvider<Players> ldp;
HorizontalSplitPanel hsp;
	@Override
	protected void init(VaadinRequest request) { 
		VerticalLayout vLayout1, vLayout2,vLayout3;
		
		HorizontalLayout hLayout1;
		vLayout1= new VerticalLayout();
		vLayout2= new VerticalLayout();
		vLayout3= new VerticalLayout();
		
		//heading
		hLayout1 = new HorizontalLayout();
		Label label1 =  new Label("Playertionary");
		label1.addStyleNames(ValoTheme.LABEL_H1,ValoTheme.BUTTON_BORDERLESS);
		hLayout1.addComponent(label1);
		hLayout1.setWidth("100%");
	    hLayout1.setComponentAlignment(label1, Alignment.MIDDLE_CENTER);
	    hLayout1.setMargin(new MarginInfo(false,false,false,false));
	    //left middle
	     Label label2 = new Label("Welcome");
	     TextField tf1 = new TextField();
	     tf1.setPlaceholder("Enter Id");
	     TextField tf2 = new TextField();
	     tf2.setPlaceholder("Enter Name");
	     TextField tf3 = new TextField();
	     tf3.setPlaceholder("Enter Email");
	     Button button =  new Button("Save", e->{
	    	 
	    	   service.addData(tf1.getValue(),tf2.getValue(),tf3.getValue());
	    	   label2.setValue("Success");
	    	  updateGrid();
	     });
	     vLayout2.addComponents(label2,tf1,tf2,tf3,button);
	    //middle right
	    grid =  new Grid<>(Players.class);
	   
	    updateGrid();
	    grid.setWidth("90%");
	    grid.setHeight("90%");
	    grid.setColumnOrder("id","name","email");
	    vLayout3.addComponent(grid);
	    
	    
	    
	    hsp = new HorizontalSplitPanel();
	    hsp.addComponents(vLayout2,vLayout3);
	    hsp.setSplitPosition(50);
	    hsp.setLocked(true);
	  
	    
		vLayout1.addComponents(hLayout1,hsp);
		vLayout1.setSizeFull();
		vLayout1.setExpandRatio(hLayout1, 2);
		vLayout1.setExpandRatio(hsp, 8);
		vLayout1.setMargin(new MarginInfo(false,false,false,false));
		setContent(vLayout1);
		
	}
	private void updateGrid() {
		grid.setDataProvider( DataProvider.ofCollection(service.getData()));
		
	}

}
