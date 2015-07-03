package my.work;

public abstract class onUIClick {
	public static final int EV_START = 0;
	public static final int EV_END = 1;
	public static final int EV_BREAK = 2;
	public static final int EV_RESUME = 3;
	public abstract void onEvent (int event, String arg);
}
