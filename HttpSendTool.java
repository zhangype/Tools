
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @Description: Http请求发送工具类
 * @author zhangype@yonyou.com	
 * @date 2016-09-13
 * @version V1.0 
 *
 */
public class HttpSendTool {
	
	/**
	 * 发送带参数的POST的HTTP请求
	 * @param reqUrl HTTP请求URL
	 * @param parameters 参数map
	 * @param encoding 编码
	 * @param conTimeout 连接超时时间
	 * @param readTimeout
	 * @return
	 */
	public static String sendPost(String reqUrl, Map<String, String> parameters, String encoding, int conTimeout, int readTimeout) {
		HttpURLConnection urlCon = null;
		String responseContent = null;
		try {
			StringBuffer params = new StringBuffer();
			
			for (Map.Entry<String, String> element : parameters.entrySet()) {
				params.append(element.getKey().toString());
				params.append("=");
				params.append(URLEncoder.encode(element.getValue().toString(), encoding));
				params.append("&");
			}
			  
			if (params.length() > 0) {
				params = params.deleteCharAt(params.length() - 1);
			}

			URL url = new URL(reqUrl);
			urlCon = (HttpURLConnection) url.openConnection();
			urlCon.setRequestMethod("POST");
			urlCon.setDoOutput(true);
			urlCon.setConnectTimeout(conTimeout);
			urlCon.setReadTimeout(readTimeout);
			
			byte[] paramsBytes = params.toString().getBytes();
			urlCon.getOutputStream().write(paramsBytes, 0, paramsBytes.length);
			urlCon.getOutputStream().flush();
			urlCon.getOutputStream().close();

			InputStream in = urlCon.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
			StringBuffer tempStr = new StringBuffer();
			
			String lineTxt = null;
			while ((lineTxt = reader.readLine()) != null) {
				tempStr.append(lineTxt);
			}
			
			responseContent = tempStr.toString();
			reader.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (urlCon != null) {
				urlCon.disconnect();
			}
		}
		return responseContent;
	}
}
