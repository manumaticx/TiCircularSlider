package de.marcelpociot.circularslider;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiUIView;
import org.appcelerator.titanium.util.TiConvert;

import android.app.Activity;
import android.os.Message;

@Kroll.proxy(creatableInModule = TiCircularSliderModule.class,
propertyAccessors = { "value" })
public class ViewProxy extends TiViewProxy {
	
	private static final int MSG_FIRST_ID = KrollModule.MSG_LAST_ID + 1;
	private static final int MSG_VALUE = MSG_FIRST_ID + 100;
	
	protected static final int MSG_LAST_ID = MSG_FIRST_ID + 999;
	
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
