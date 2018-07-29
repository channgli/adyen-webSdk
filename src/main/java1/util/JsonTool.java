package util;

public class JsonTool {

	private boolean isTab = true;

	public String stringToJSON(String strJson) {
		// 计数tab的个数
		int tabNum = 0;
		StringBuffer jsonFormat = new StringBuffer();
		int length = strJson.length();

		for (int i = 0; i < length; i++) {
			char c = strJson.charAt(i);
			if (c == '{') {
				tabNum++;
				jsonFormat.append(c + "\n");
				jsonFormat.append(getSpaceOrTab(tabNum));
			} else if (c == '}') {
				tabNum--;
				jsonFormat.append("\n");
				jsonFormat.append(getSpaceOrTab(tabNum));
				jsonFormat.append(c);
			} else if (c == ',') {
				jsonFormat.append(c + "\n");
				jsonFormat.append(getSpaceOrTab(tabNum));
			} else {
				jsonFormat.append(c);
			}
		}
		return jsonFormat.toString();
	}

	// 是空格还是tab
	public String getSpaceOrTab(int tabNum) {
		StringBuffer sbTab = new StringBuffer();
		for (int i = 0; i < tabNum; i++) {
			if (isTab) {
				sbTab.append('\t');
			} else {
				sbTab.append("    ");
			}
		}
		return sbTab.toString();
	}
}