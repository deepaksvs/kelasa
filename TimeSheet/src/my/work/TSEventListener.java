package my.work;

import java.util.Date;
public class TSEventListener extends onUIClick {
	private static TSEventListener		__instance = null;
	private UserInterface				gui = null;
	private Thread   					mThread = null;
	private MyTSDatabase				mDBhandle = null;
	private long 						mBreakDuration = 0;
	private long  						mIterative = 0;
	public class MyThread implements Runnable {

		@Override
		public void run() {
			System.out.println("MyThread Run()");
			gui = new UserInterface(TSEventListener.this);
			System.out.println("GUI created: " + gui.toString());
			mDBhandle = new MyTSDatabase();
			mDBhandle.openDB();
		}
	}

	public static synchronized TSEventListener getInstance () {
		if (__instance == null) {
			__instance = new TSEventListener();
		}
		return __instance;
	}

	TSEventListener() {
		System.out.println("TSEventListener constructor");
	    mThread = new Thread(new MyThread());
		mThread.start();
	}

	@Override
	public void onEvent(int event, String arg) {
		System.out.println("OnClick received");
		int uid = arg.hashCode();
		if (event == onUIClick.EV_START) {
			mDBhandle.startTask(arg, uid);
			mBreakDuration = 0;
			mIterative = 0;
		}
		else if (event == onUIClick.EV_END) {
			mDBhandle.endTask(uid, (int)mIterative);
		}
		else if (event == onUIClick.EV_BREAK) {
			mBreakDuration = (new Date().getTime())/1000;
			System.out.println("Break at: " + mBreakDuration);
		}
		else if (event == onUIClick.EV_RESUME) {
			mBreakDuration = ((new Date().getTime())/1000) - mBreakDuration;
			System.out.println("Resumed after: " + mBreakDuration);
			mIterative = mIterative + mBreakDuration;
		}
	}

}
