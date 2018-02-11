import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by salmonzhang on 2018/2/2.
 * SharedPreferences 工具类
 */
 
public class SharePreUtils {

	private static SharePreUtils mInstance = null;

	private Context mContext;
	public static String SHARE_NAME = "config";

	private SharedPreferences sp;
	private SharedPreferences.Editor editor;

	private SharePreUtils(Context mContext) {
		this.mContext = mContext;

		initSp();
	}

	private void initSp() {
		sp = mContext.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
		editor = sp.edit();
	}

	public static SharePreUtils getInstance(Context mContext) {
		if (mInstance == null) {
			synchronized (SharePreUtils.class) {
				if (mInstance == null) {
					mInstance = new SharePreUtils(mContext);
				}
			}
		}
		return mInstance;
	}

	public String getString(String key, String defValue) {
		return sp.getString(key, defValue);
	}

	public void putString(String key, String value) {
		editor.putString(key, value).commit();
	}

	public int getInt(String key, int defValue) {
		return sp.getInt(key, defValue);
	}

	public void putInt(String key, int value) {
		editor.putInt(key, value).commit();
	}

	public boolean getBoolean(String key, boolean defValue) {
		return sp.getBoolean(key, defValue);
	}

	public void putBoolean(String key, boolean value) {
		editor.putBoolean(key, value).commit();
	}

	public float getFloat(String key, float defValue) {
		return sp.getFloat(key, defValue);
	}

	public void putFloat(String key, float value) {
		editor.putFloat(key, value).commit();
	}

	public long getLong(String key, long defValue) {
		return sp.getLong(key, defValue);
	}

	public void putLong(String key, long value) {
		editor.putLong(key, value).commit();
	}

	public void deleShare(String key) {
		editor.remove(key).commit();
	}

	public void deleShareAll() {
		editor.clear().commit();
	}
}