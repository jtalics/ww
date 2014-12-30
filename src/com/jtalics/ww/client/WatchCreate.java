package com.jtalics.ww.client;

import java.util.List;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.jtalics.ww.client.widgets.slider.RangeSliderPanel;
import com.jtalics.ww.shared.City;
import com.jtalics.ww.shared.FieldVerifier;
import com.jtalics.ww.shared.Watch;

public class WatchCreate extends VerticalPanel {

	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side service.
	 */
	private final WatchesServiceAsync greetingService = GWT.create(WatchesService.class);

	public WatchCreate() {
		//setAction("/ww/greet");
		setSpacing(10);
		//setWidget(vp);

		final Button sendButton = new Button("Send");
		final TextBox nameField = new TextBox();
		nameField.setText("Select City");
		final ListBox citySelector = new ListBox();
		loadCitySelector(citySelector);
		final Label errorLabel = new Label();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Use RootPanel.get() to get the entire body element
		add(nameField);
		add(sendButton);
		add(citySelector);
		add(new RangeSliderPanel("rs1", 0, 100, 20, 80));
		add(errorLabel);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			@Override
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a
			 * response.
			 */
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = nameField.getText();
				if (!FieldVerifier.isValidName(textToServer)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}

				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");
				greetingService.greetServer(textToServer, new AsyncCallback<String>() {
					@Override
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						dialogBox.setText("Remote Procedure Call - Failure");
						serverResponseLabel.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.setFocus(true);
					}

					@Override
					public void onSuccess(String result) {
						dialogBox.setText("Remote Procedure Call");
						serverResponseLabel.removeStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(result);
						dialogBox.center();
						closeButton.setFocus(true);
					}
				});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);
		// Add a 'submit' button
		add(new Button("Submit", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				greetingService.updateWatch(new Watch(), new AsyncCallback<Watch>() {

					@Override
					public void onSuccess(Watch result) {
						Window.alert("Server says SUCCESS");
						// just get next page
						//WatchForm.this.submit();
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Server says submit failed: " + caught.toString());
					}
				});
			}
		}));
		/*
		// Add an event handler to the form.
		addSubmitHandler(new FormPanel.SubmitHandler() {
			@Override
			public void onSubmit(SubmitEvent event) {
				Log.debug("SUBMITTING!");
				// This event is fired just before the form is submitted.
				// We can take this opportunity to perform validation.
			}
		});

		addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				Log.debug("SUBMIT COMPLETE!");
				// When the form submission is successfully completed,
				// this event is fired. Assuming the service returned
				// a response of type text/html, we can get the result
				// here.
				// Window.alert(event.getResults());
			}
		});
		*/
	}

	public static void build(Widget widget, String id) {
		Log.debug("Adding id " + id);
		RootPanel rp = RootPanel.get(id);
		if (rp == null) {
			Log.error("Cannot get id " + id);
			return;
		}
		rp.add(widget);
	}

	private void loadCitySelector(final ListBox citySelector) {
		greetingService.getWatchableCities(new AsyncCallback<List<City>>() {
			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				// dialogBox
				// .setText("Remote Procedure Call - Failure");
				// serverResponseLabel
				// .addStyleName("serverResponseLabelError");
				// serverResponseLabel.setHTML(SERVER_ERROR);
				// dialogBox.center();
				// closeButton.setFocus(true);
			}

			@Override
			public void onSuccess(List<City> cities) {
				// dialogBox.setText("Remote Procedure Call");
				// serverResponseLabel
				// .removeStyleName("serverResponseLabelError");
				// serverResponseLabel.setHTML(result);
				// dialogBox.center();
				// closeButton.setFocus(true);
				for (City city : cities) {
					citySelector.addItem(city.toString());
				}
			}
		});
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
