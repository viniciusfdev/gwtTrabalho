package com.tutorialspoint.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.tutorialspoint.shared.CarBrand;
import com.tutorialspoint.shared.CarModel;
import com.tutorialspoint.shared.SaleInfo;
import com.tutorialspoint.shared.SerialList;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Cars implements EntryPoint {

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final CarServiceAsync carService = GWT.create(CarService.class);
		
	private FlexTable layout;
	
	private ListBox carBrandsListBox;
	
	private ListBox carModelsListBox;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		// The client name field
		final TextBox clientTxtBox = new TextBox();
		clientTxtBox.getElement().setId("client_name");
		
		// The car brands list box
		carBrandsListBox = new ListBox();
		carBrandsListBox.getElement().setId("car_brands");
		loadCarBrandsListBox();
		
		// The car models list box
		carModelsListBox = new ListBox();
		carModelsListBox.getElement().setId("car_models");
		
		// Add a handler to handle drop box events
		carBrandsListBox.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				carModelsListBox.clear();
				final int selectedIndex = carBrandsListBox.getSelectedIndex();
				loadCarModelsListBox(carBrandsListBox.getValue(selectedIndex));     
			}
	    });

		// The DateTime field		
		// Create a basic date picker
	    final DatePicker datePicker = new DatePicker();
	    
	    final Label text = new Label();

	    // Set the value in the text box when the user selects a date
	    datePicker.addValueChangeHandler(new MyDateValueChangeHandler(text));
		
	    // Set the default value
	    datePicker.setValue(new Date(), true);
		
	    // Create a DateBox
	    DateTimeFormat dateFormat = DateTimeFormat.getLongDateFormat();
	    DateBox dateBox = new DateBox();
	    dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
	    dateBox.getDatePicker().setYearArrowsVisible(true);
		
	    // Combine the widgets into a panel and return them
	    VerticalPanel vPanel = new VerticalPanel();
	    vPanel.add(text);
	    vPanel.add(datePicker);
	    vPanel.add(dateBox);
	    
	    // Create a TextBox 
	    final TextBox saleValueTextBox = new TextBox();
	    saleValueTextBox.getElement().setId("saleValue");
		
		// Button save
		final Button saveButton = new Button("Salvar");
		saveButton.getElement().setId("saveButton");
		
		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
					/**
					 * Fired when the user clicks on the sendButton.
					 */
					public void onClick(ClickEvent event) {
						
						final String brandId = carBrandsListBox.getSelectedValue();
						final String modelId = carModelsListBox.getSelectedValue();
						final float value = Float.parseFloat(saleValueTextBox.getText());
						
						SaleInfo saleInfo = new SaleInfo();
						saleInfo.setCarBrandId(Integer.valueOf(brandId));
						saleInfo.setCarModelId(Integer.valueOf(modelId));
						saleInfo.setClientName(clientTxtBox.getText());
						saleInfo.setSaleDateTime(format(datePicker.getValue()));
						saleInfo.setSaleValue(value + "");
						
						sendSaleInfoToServer(saleInfo);
					}

					/**
					 * Fired when the user types in the nameField.
					 */
					public void onKeyUp(KeyUpEvent event) {
						if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
							
							final String brandId = carBrandsListBox.getSelectedValue();
							final String modelId = carModelsListBox.getSelectedValue();
							final float value = Float.parseFloat(saleValueTextBox.getText());
							
							SaleInfo saleInfo = new SaleInfo();
							saleInfo.setCarBrandId(Integer.valueOf(brandId));
							saleInfo.setCarModelId(Integer.valueOf(modelId));
							saleInfo.setClientName(clientTxtBox.getText());
							saleInfo.setSaleDateTime(format(datePicker.getValue()));
							saleInfo.setSaleValue(value + "");
							
							sendSaleInfoToServer(saleInfo);						
						}
					}

					/**
					 * Send the name from the nameField to the server and wait for a response.
					 */
					private void sendSaleInfoToServer(SaleInfo saleInfo) {
						
						carService.register(saleInfo, new AsyncCallback<Boolean>() {
							
							@Override
							public void onFailure(Throwable caught) {
								// Perform Validations
								error("|=======<th>"
										+ "ErrorType</th><th>Error "
										+ "Description</th>|Fatal|"
										+ "Remote Procedure Call - Failure|=======");			
							}
							
							@Override
							public void onSuccess(Boolean result) {
								if (result != null && result.booleanValue()) {
									Window.alert("Venda cadastrada com sucesso!");
								} else {
									Window.alert("Nao foi possivel cadastrar a venda!!!");
								}
							}
						});
					}
				}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		saveButton.addClickHandler(handler);

		// Create a table to layout the form options
	    layout = new FlexTable();
	    layout.setCellSpacing(6);
	    FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

	    // Add a title to the form
	    layout.setHTML(0, 0, "Register Car Sale");
	    cellFormatter.setColSpan(0, 0, 2);
	    cellFormatter.setHorizontalAlignment(
	        0, 0, HasHorizontalAlignment.ALIGN_CENTER);

	    // Add some standard form options
	    layout.setHTML(1, 0, "Client:");
	    layout.setWidget(1, 1, clientTxtBox);
	    layout.setHTML(2, 0, "Brand:");
	    layout.setWidget(2, 1, carBrandsListBox);
	    layout.setHTML(3, 0, "Model:");
	    layout.setWidget(3, 1, carModelsListBox);
	    
	    layout.setHTML(4, 0, "Date/Time:");
	    layout.setWidget(4, 1, vPanel);
	    
	    layout.setHTML(5, 0, "Value:");
	    layout.setWidget(5, 1, saleValueTextBox);
	    
	    layout.setWidget(6, 1, saveButton);

	    // Wrap the content in a DecoratorPanel
	    DecoratorPanel decPanel = new DecoratorPanel();
	    decPanel.setWidget(layout);
	    		
	    // Associate the Main panel with the HTML host page.
	    RootPanel.get("screen").add(decPanel);
	}
	
	private void loadCarBrandsListBox() {
		carService.findCarBrands(new AsyncCallback<SerialList<CarBrand>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Falha ao chamar rpc findCarBrands");
			}

			@Override
			public void onSuccess(SerialList<CarBrand> result) {
				GWT.log("onSuccess findCarBrands...");
				populateCarBrandsListBox(result);
			}			
		});
	}
	
	public String format(Date date)
    {
	   String pattern = "yyyy-MM-dd"; 
       return com.google.gwt.i18n.client.DateTimeFormat.getFormat(pattern).format(date);
    }
	
	private void populateCarBrandsListBox(SerialList<CarBrand> brandsLst) {
		String brand = "";
		String value = "";
		carBrandsListBox.addItem("Selecione uma marca...");
		if (brandsLst != null && brandsLst.size() > 0) {
			for (CarBrand carBrand : brandsLst) {
				brand = carBrand.getBrand();
				value = carBrand.getBrandId() + "";
				carBrandsListBox.addItem(brand, value);
			}
		}
	}
	
	private void loadCarModelsListBox(String brandId) {
		carService.findCarModels(Integer.valueOf(brandId), new AsyncCallback<SerialList<CarModel>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Falha ao chamar rpc loadCarModelsListBox");
			}

			@Override
			public void onSuccess(SerialList<CarModel> modelsLst) {
				GWT.log("onSuccess findCarModels modelsLst size: " + modelsLst.size());
				if (modelsLst != null && modelsLst.size() > 0) {
					populateCarModelsListBox(modelsLst);
				}
			}
		});
	}
	
	private void populateCarModelsListBox(SerialList<CarModel> modelsLst) {
		carModelsListBox.addItem("Selecione um modelo...");
		String model = "";
		String value = "";
		for (CarModel carModel : modelsLst) {
			model = carModel.getModel();
			value = carModel.getModelId() + "";
			carModelsListBox.addItem(model, value);
		}
	}

	/**
	  * {@link ValueChangeHandler} used to set the value in the text box when the user selects a date
	  */
	public static class MyDateValueChangeHandler implements ValueChangeHandler<Date> {
	    private final Label text;

	    public MyDateValueChangeHandler(Label text) {
	      this.text = text;
	    }

	    public void onValueChange(ValueChangeEvent<Date> event) {
	      Date date = event.getValue();
	      String dateString = DateTimeFormat.getMediumDateFormat().format(date);
	      text.setText(dateString);
	    }
	}
	
	public static String formatDecimal(float number) {
		final NumberFormat numberFormat = NumberFormat.getDecimalFormat();
		final String formattedNumber = numberFormat.format(number); 
		return formattedNumber;
	}
	
	/**
	* Custom Error Dialog Page.
	* @param err error message text
	*/
	public void error(String err) {
		final DialogBox dialog = new DialogBox();
		dialog.center();
		dialog.setSize("80%", "80%");
		dialog.setText("Error");
		
		VerticalPanel panel = new VerticalPanel();
		panel.setSize("100%", "100%");
		HTMLPanel html = new HTMLPanel(err);
		html.setSize("100%", "100%");
		panel.add(html);
		
		Button ok = new Button("OK");
		VerticalPanel buttonPanel = new VerticalPanel(); 
		buttonPanel.setSpacing(3);
		buttonPanel.add(ok);
		panel.add(buttonPanel);
		dialog.setWidget(panel);
		
		ok.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				dialog.hide();
			}
		});
		
		dialog.show();
	}
}
