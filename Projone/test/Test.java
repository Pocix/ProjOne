import java.util.regex.Pattern;

public class Test {
	public static void main(String... args){
		String url = "//detail.tmall.com/item.htm?id=43973443504&rn=88bb292c4a6d01ab5b77ef4ddb916a4c&abbucket=0\\";
		System.out.println(url.split("&")[0].substring(url.split("&")[0].indexOf("id=")+3));
	}
}
