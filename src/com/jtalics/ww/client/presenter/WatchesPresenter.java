package com.jtalics.ww.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.jtalics.ww.client.WatchesServiceAsync;
import com.jtalics.ww.client.event.AddWatchEvent;
import com.jtalics.ww.client.event.EditWatchEvent;
import com.jtalics.ww.shared.WatchDescription;

import java.util.ArrayList;
import java.util.List;

public class WatchesPresenter implements Presenter {

	private List<WatchDescription> watchDetails;

	public interface Display {
		HasClickHandlers getAddButton();

		HasClickHandlers getDeleteButton();

		HasClickHandlers getList();

		// The beauty of using setData() is that changes to the model can be made without updating the view code.
		void setData(List<String> data);

		int getClickedRow(ClickEvent event);

		List<Integer> getSelectedRows();

		Widget asWidget();
	}

	private final WatchesServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public WatchesPresenter(WatchesServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void bind() {
		display.getAddButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AddWatchEvent());
			}
		});

		display.getDeleteButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				deleteSelectedWatches();
			}
		});

		display.getList().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int selectedRow = display.getClickedRow(event);

				if (selectedRow >= 0) {
					String id = watchDetails.get(selectedRow).getId();
					eventBus.fireEvent(new EditWatchEvent(id));
				}
			}
		});
	}

	@Override
	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
		fetchWatchDetails();
	}

	public void sortWatchDetails() {

		// Yes, we could use a more optimized method of sorting, but the
		// point is to create a test case that helps illustrate the higher
		// level concepts used when creating MVP-based applications.
		//
		for (int i = 0; i < watchDetails.size(); ++i) {
			for (int j = 0; j < watchDetails.size() - 1; ++j) {
				if (watchDetails.get(j).getDisplayName().compareToIgnoreCase(watchDetails.get(j + 1).getDisplayName()) >= 0) {
					WatchDescription tmp = watchDetails.get(j);
					watchDetails.set(j, watchDetails.get(j + 1));
					watchDetails.set(j + 1, tmp);
				}
			}
		}
	}

	public void setWatchDetails(List<WatchDescription> watchDetails) {
		this.watchDetails = watchDetails;
	}

	public WatchDescription getWatchDetail(int index) {
		return watchDetails.get(index);
	}

	private void fetchWatchDetails() {
		rpcService.getWatchDetails(new AsyncCallback<List<WatchDescription>>() {
			@Override
			public void onSuccess(List<WatchDescription> result) {
				watchDetails = result;
				sortWatchDetails();
				List<String> data = new ArrayList<String>();

				for (int i = 0; i < result.size(); ++i) {
					data.add(watchDetails.get(i).getDisplayName());
				}

				display.setData(data);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching Watch details");
			}
		});
	}

	private void deleteSelectedWatches() {
		List<Integer> selectedRows = display.getSelectedRows();
		ArrayList<String> ids = new ArrayList<String>();

		for (int i = 0; i < selectedRows.size(); ++i) {
			ids.add(watchDetails.get(selectedRows.get(i)).getId());
		}

		rpcService.deleteWatch(ids, new AsyncCallback<List<WatchDescription>>() {
			@Override
			public void onSuccess(List<WatchDescription> result) {
				watchDetails = result;
				sortWatchDetails();
				List<String> data = new ArrayList<String>();

				for (int i = 0; i < result.size(); ++i) {
					data.add(watchDetails.get(i).getDisplayName());
				}

				display.setData(data);

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error deleting selected Watch");
			}
		});
	}
}
