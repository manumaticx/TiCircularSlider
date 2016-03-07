package de.marcelpociot.circularslider;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiUIView;
import org.appcelerator.titanium.util.TiConvert;

import android.app.Activity;

@Kroll.proxy(creatableInModule = TiCircularSliderModule.class,
propertyAccessors = { "value" })
public class ViewProxy extends TiViewProxy {
	
	public ViewProxy(){
		super();
	}

	@Override
	public TiUIView createView(Activity activity) {
		de.marcelpociot.circularslider.View view = new View(this);
		view.getLayoutParams().autoFillsHeight = true;
		view.getLayoutParams().autoFillsWidth = true;

		return view;
	}
}
