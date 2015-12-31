import java.util.regex.Pattern;

public class Test {
	public static void main(String... args){
		System.out.println(Pattern.compile("[\u00A5-\u9fa5]|\\（*\\）|\\s").matcher("促大是大非 "
+"¥ 29.50 枯玩儿（） ").replaceAll(""));
	}
}
