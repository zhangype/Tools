import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Description: 
 * @author zhangype
 * @date 2016-08-08
 * @version V1.0 
 *
 */
public class ReadFileByBufferReaderTool {
	public void readData(String filePath) {
		BufferedReader bufferedReader = null;
		try {
			File file = new File(filePath);
			if (file.isFile() && file.exists()) {
				bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					System.out.println(lineTxt);
				}
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错：" + e);
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
