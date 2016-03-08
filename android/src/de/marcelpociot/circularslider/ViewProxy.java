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
	
	/**
	 * message handler
	 * @param message
	 */
	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
			case MSG_VALUE: {
				handleSetValue(msg.obj);
				return true;
			}
			default: {
				return super.handleMessage(msg);
			}
		}
	}
	
	private void handleSetValue(Object value){
		setProperty(de.marcelpociot.circularslider.View.PROPERTY_VALUE, value);
	}
	
	@Kroll.setProperty @Kroll.method
	public void setValue(Object value){
		Message message = getMainHandler().obtainMessage(MSG_VALUE, value);
		message.sendToTarget();
	}
}
