package my.work;

public class TSEventListener extends onUIClick {
	private static TSEventListener		__instance = null;
	private UserInterface				gui = null;
	private Thread   					mThread = null;
	
	public class MyThread implements Runnable {

		@Override
		public void run() {
			System.out.println("MyThread Run()");
			gui = new UserInterface(TSEventListener.this);
			System.out.println("GUI created: " + gui.toString());
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
	}

}
