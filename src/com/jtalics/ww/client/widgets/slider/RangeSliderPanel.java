package com.jtalics.ww.client.widgets.slider;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;

public class RangeSliderPanel extends FlexTable
{

	public RangeSliderPanel(String id, int min, int max, int knob1, int knob2) {
		final Label label1 = new Label(Integer.toString(knob1));
		final Label label2 = new Label(Integer.toString(knob2));
		RangeSlider rangeSlider = new RangeSlider(id+"-jQuery", min, max, knob1, knob2);
		rangeSlider.setPixelSize(200, 10);
		rangeSlider.addListener(new SliderListener() {

			@Override
			public void onStart(SliderEvent e) {
			}

			@Override
			public boolean onSlide(SliderEvent e) {
				label1.setText(Integer.toString(e.getValues()[0]));
				label2.setText(Integer.toString(e.getValues()[1]));
				return true;
			}

			@Override
			public void onChange(SliderEvent e) {
				label1.setText(Integer.toString(e.getValues()[0]));
				label2.setText(Integer.toString(e.getValues()[1]));
			}

			@Override
			public void onStop(SliderEvent e) {
			}
		});
		this.setSize("500", "500");
		setWidget(0,0,label1);
		setWidget(0,7,label2);
		setWidget(0,1,rangeSlider);
		getFlexCellFormatter().setColSpan(0,2,5);
	}
}
