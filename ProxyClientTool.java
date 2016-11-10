import java.io.IOException;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 代理客户端
 * @author zhangype@yonyou.com
 * @date 2016-11-09
 * @version V1.0 
 *
 */
public class ProxyClientTool {
	private static Logger logger = LoggerFactory.getLogger(ProxyClientUtil.class);
	
	/**
	 * 获取响应时间
	 * @param proxyHost 代理host
	 * @param proxyPort 代理端口
	 * @param reqUrl 请求资源
	 * @return
	 */
	public long calRespTime(String proxyHost, int proxyPort, String reqUrl){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpHost proxy = new HttpHost(proxyHost, proxyPort, "http");

		RequestConfig config = RequestConfig.custom()
				.setSocketTimeout(5000)
				.setConnectTimeout(5000)
				.setConnectionRequestTimeout(5000)
				.setProxy(proxy).build();
		
		HttpGet request = new HttpGet(reqUrl);
		request.setConfig(config);
		
		long respTime = -1;
		CloseableHttpResponse response = null;
		try {
			long startTime = System.currentTimeMillis();
			response = httpclient.execute(request);
			long endTime = System.currentTimeMillis();
			
			if(HttpStatus.SC_OK == response.getStatusLine().getStatusCode()){
				respTime = endTime - startTime;
			}
		} catch (ClientProtocolException e) {
			logger.error("获取响应异常！", e);
		} catch (IOException e) {
			logger.error("获取响应异常！", e);
		} finally {
			if(response != null){
				try {
					response.close();
					httpclient.close();
				} catch (IOException e) {
					logger.error("关闭资源异常！", e);
				}
			}
		}
		return respTime;
	}
}
