/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2012 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */

package de.marcelpociot.circularslider;

import java.util.HashMap;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.view.TiUIView;


import de.marcelpociot.circularslider.HoloCircleSeekBar.OnCircleSeekBarChangeListener;

public class View extends TiUIView {
	// Standard Debugging variables
	private static final String LCAT = "TiCircularSliderModule";

	public static final String PROPERTY_VALUE = "value";
	private static final String PROPERTY_MAX_VALUE = "maximumValue";
	private static final String PROPERTY_LINE_WIDTH = "lineWidth";
	private static final String PROPERTY_COLOR_FILLED = "filledColor";
	private static final String PROPERTY_COLOR_UNFILLED = "unfilledColor";
	private static final String PROPERTY_COLOR_HANDLE = "handleColor";
	private static final String PROPERTY_POINTER_COLOR = "pointerColor";
	private static final String PROPERTY_POINTER_BORDER_COLOR = "pointerBoderColor";
	private static final String PROPERTY_POINTER_RADIUS = "pointerWidth";
	
	public View(TiViewProxy proxy) {
		super(proxy);

		HoloCircleSeekBar hcsb = new HoloCircleSeekBar(proxy.getActivity());

		hcsb.setOnSeekBarChangeListener(new OnCircleSeekBarChangeListener() {
				@Override
        public void onProgressChanged(HoloCircleSeekBar view, int newProgress, boolean fromUser){
					Log.d(LCAT,"Progress:" + view.getValue());
					notifyOfChange(view.getValue());
				}

        public void onStartTrackingTouch(HoloCircleSeekBar view){}

        public void onStopTrackingTouch(HoloCircleSeekBar view){}
		 });

		setNativeView(hcsb);
	}

	// The view is automatically registered as a model listener when the view
	// is realized by the view proxy. That means that the processProperties
	// method will be called during creation and that propertiesChanged and
	// propertyChanged will be called when properties are changed on the proxy.

	@Override
	public void processProperties(KrollDict props) {
		super.processProperties(props);

		HoloCircleSeekBar hcsb = (HoloCircleSeekBar) getNativeView();

		if (props.containsKey(PROPERTY_MAX_VALUE)) {
			int max = TiConvert.toInt(props.getInt(PROPERTY_MAX_VALUE));
			hcsb.setMax(max);
		}

		if (props.containsKey(PROPERTY_VALUE)) {
			hcsb.setValue(TiConvert.toFloat(props.get(PROPERTY_VALUE)));
		}

		if (props.containsKey(PROPERTY_LINE_WIDTH)) {
			//csb.setBarWidth(TiConvert.toInt(props.get(PROPERTY_LINE_WIDTH)));
			hcsb.setBarWidth(TiConvert.toInt(props.get(PROPERTY_LINE_WIDTH)));
		}

		if (props.containsKey(PROPERTY_COLOR_FILLED)) {
			hcsb.setWheelColor(TiConvert.toColor(props.getString(PROPERTY_COLOR_FILLED)));
		}

		if (props.containsKey(PROPERTY_COLOR_UNFILLED)) {
			hcsb.setUnactiveWheelColor(TiConvert.toColor(props.getString(PROPERTY_COLOR_UNFILLED)));
		}
		
		if (props.containsKey(PROPERTY_POINTER_COLOR)) {
			hcsb.setPointerColor(TiConvert.toColor(props.getString(PROPERTY_POINTER_COLOR)));
		}
		
		if (props.containsKey(PROPERTY_POINTER_BORDER_COLOR)) {
			hcsb.setPointerHaloColor(TiConvert.toColor(props.getString(PROPERTY_POINTER_BORDER_COLOR)));
		}
		
		if (props.containsKey(PROPERTY_POINTER_RADIUS)) {
			hcsb.setPointerRadius(TiConvert.toInt(props.get(PROPERTY_POINTER_RADIUS)));
		}

	}

	@Override
	public void propertyChanged(String key, Object oldValue, Object newValue,
			KrollProxy proxy) {
				
			HoloCircleSeekBar hcsb = (HoloCircleSeekBar) getNativeView();
				
			if (key.equals(PROPERTY_VALUE)) {
				hcsb.setValue(TiConvert.toInt(newValue));
			}else{
				super.propertyChanged(key, oldValue, newValue, proxy);
			}
	}

	private void notifyOfChange(int newValue) {
		proxy.setProperty(PROPERTY_VALUE, newValue);
		if (proxy.hasListeners("change")) {
			HashMap<String, Integer> hm = new HashMap<String, Integer>();
			hm.put("value", newValue);
			proxy.fireEvent("change", hm);
		}
	}

}
