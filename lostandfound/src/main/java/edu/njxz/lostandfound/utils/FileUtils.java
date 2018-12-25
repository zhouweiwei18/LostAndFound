package edu.njxz.lostandfound.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件上传工具包
 */
public class FileUtils {

	/**
	 *
	 * @param file
	 *            文件
	 * @param path
	 *            文件存放路径
	 * @param fileName
	 *            源文件名
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public static String upload(MultipartFile file, String path, String fileName) {

		// 生成新的文件名
		String realPath = path + "/" + FileNameUtils.getFileName(fileName);

		File dest = new File(realPath);

		// 判断文件父目录是否存在
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdir();
		}

		try {
			// 保存文件
			file.transferTo(dest);
			return realPath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return realPath;
	}

}
