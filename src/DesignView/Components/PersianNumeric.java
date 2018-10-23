package DesignView.Components;

public class PersianNumeric {

	public static String toPersianNumberic(String text){
		String result = text;
		result = result.replace('0', '\u06F0');
		result = result.replace('1', '\u06F1');
		result = result.replace('2', '\u06F2');
		result = result.replace('3', '\u06F3');
		result = result.replace('4', '\u06F4');
		result = result.replace('5', '\u06F5');
		result = result.replace('6', '\u06F6');
		result = result.replace('7', '\u06F7');
		result = result.replace('8', '\u06F8');
		result = result.replace('9', '\u06F9');
		return result;
	}
}
