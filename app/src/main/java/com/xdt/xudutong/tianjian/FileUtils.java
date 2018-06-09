package com.xdt.xudutong.tianjian;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtils {
	public final static String FILE_EXTENSION_SEPARATOR = ".";
	public static boolean writeFile(String filePath, String content,
			boolean append) {
		if (content==null&&content.trim().equals("")) {
			return false;
		}

		FileWriter fileWriter = null;
		try {
			makeDirs(filePath);
			fileWriter = new FileWriter(filePath, append);
			fileWriter.write(content);
			fileWriter.close();
			return true;
		} catch (IOException e) {
			throw new RuntimeException("IOException occurred. ", e);
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					throw new RuntimeException("IOException occurred. ", e);
				}
			}
		}
	}
	/**
	 * 文件读取
	 *
	 * @param filePath
	 *            -文件路径
	 * @param charsetName
	 *            -编码方式
	 * @return 文件读取结果
	 */
	public static String readFile(String filePath, String charsetName) {
		try {
			return readInputStream(new FileInputStream(new File(filePath)), true);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 读取文件
	 *
	 * @param is
	 *            -输入流
	 * @param isAutoClose
	 *            -是否自动关闭输入流
	 * @return 输入流读取读取结果
	 */
	public static String readInputStream(InputStream is, boolean isAutoClose) {
		if (null == is) {
			return null;
		}

		InputStreamReader inreader = null;
		BufferedReader bufreader = null;
		try {
			StringBuilder fileContent = new StringBuilder("");
			inreader = new InputStreamReader(is, "UTF-8");
			bufreader = new BufferedReader(inreader);
			String line = null;
			while ((line = bufreader.readLine()) != null) {
				if (!fileContent.toString().equals("")) {
					fileContent.append("\r\n");
				}
				fileContent.append(line);
			}
			bufreader.close();
			inreader.close();
			if (isAutoClose) {
				is.close();
			}
			return new String(fileContent);

		}
		catch (Exception ereader) {
			if (bufreader != null) {
				try {
					bufreader.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (inreader != null) {
				try {
					inreader.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (isAutoClose) {

				if (is != null) {
					try {
						is.close();
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			return null;
		}

	}

	
    public static boolean makeDirs(String filePath) {
        String folderName = getFolderName(filePath);
        if (filePath==null&&filePath.trim().equals("")) {
            return false;
        }

        File folder = new File(folderName);
        return (folder.exists() && folder.isDirectory()) || folder.mkdirs();
    }
    
    public static String getFolderName(String filePath) {

    	if (filePath==null&&filePath.trim().equals("")) {
            return filePath;
        }

        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
    }
	/**
	 * 计算文件或者文件夹的大小 ，单位 MB
	 *
	 * @param file
	 *            要计算的文件或者文件夹 ， 类型：java.io.File
	 * @return 大小，单位：Bytes
	 */
	public static long getSizeBytes(File file) {

		// 判断文件是否存在

		if (null != file && file.exists()) {
			// 如果是目录则递归计算其内容的总大小，如果是文件则直接返回其大小
			if (!file.isFile()) {
				// 获取文件大小
				File[] fl = file.listFiles();
				long ss = 0;
				for (File f : fl) {
					ss += getSizeBytes(f);
				}
				return ss;
			}
			else {
				long ss = file.length();
				return ss;
			}
		}
		else {
			System.out.println("文件或者文件夹不存在，请检查路径是否正确！");
			return 0;
		}
	}
	public static String getPathContext(Context mContext, String filePath) {
		String pathParent = getPathContext(mContext);
		return pathParent + File.separator + filePath;
	}
	// 获取安卓路径
	public static String getPathContext(Context mContext) {
		String path = mContext.getFilesDir().getParent();
		return path;
	}
	public static String getPathExternalStorage() {
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			return Environment.getExternalStorageDirectory().getPath();
		}
		else {
			return null;
		}
	}
	public static String getPathExternalStorage(String filePath) {
		String pathParent = getPathExternalStorage();
		if (TextUtils.isEmpty(pathParent)) {
			return null;
		}
		return pathParent + File.separator + filePath;

	}
	public static File createDir(String dirPath) {
		if (TextUtils.isEmpty(dirPath)) {
			return null;
		}
		File file = new File(dirPath);
		if (file.exists() && file.isDirectory()) {
			return file;
		}
		else if (file.exists()) {
			return null;
		}
		else {
			if (file.mkdirs()) {
				return file;
			}
			else {
				return null;
			}
		}
	}
	public static boolean delAllFile(String path) {
		if (TextUtils.isEmpty(path)) {
			return false;
		}
		File file = new File(path);
		if (null == file || !file.exists()) {
			return false;
		}
		if (file.isFile()) {
			return file.delete();
		}
		boolean flag = false;
		String[] tempList = file.list();
		if (null == tempList || tempList.length < 1) {
			tempList = null;
			return true;
		}
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			}
			else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		temp = null;
		return flag;
	}
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete(); // 删除空文件夹
			myFilePath = null;

		}
		catch (Exception e) {
		}
	}
	public static boolean saveBitmap(Bitmap mBitmap, File file, Bitmap.CompressFormat compressFormat) {
		if (null == mBitmap || null == file) {
			return false;
		}
		FileOutputStream fOut = null;
		try {

			if (file.exists()) {
				file.delete();
			}

			fOut = new FileOutputStream(file);
			mBitmap.compress(compressFormat, 100, fOut);
			fOut.flush();
			fOut.close();
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	public static boolean saveBitmap(Bitmap mBitmap, File file) {
		return saveBitmap(mBitmap, file, Bitmap.CompressFormat.JPEG);
	}
	public static boolean saveBitmap(Bitmap bitMap, String filepath) {
		return saveBitmap(bitMap, new File(filepath), Bitmap.CompressFormat.JPEG);
	}
	@SuppressLint("SimpleDateFormat")
	public static String getFilenameByTime(String filehead, String filetype) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String string = new String();
		string = filehead + dateFormat.format(date) + filetype;
		return string;
	}
	public static File createFileInContext(Context mContext, String filepath) {
		return createFile(getPathContext(mContext, filepath));
	}
	public static File createFile(String filepath) {
		if (TextUtils.isEmpty(filepath)) {
			return null;
		}
		if (filepath.endsWith(File.separator)) {
			return createDir(filepath);
		}

		int index = filepath.lastIndexOf(File.separator);
		if (index < 0) {
			return null;
		}
		else if (index == filepath.length() - 1) {
			return createDir(filepath);
		}

		String dirpath = filepath.substring(0, index);
		String filename = filepath.substring(index, filepath.length());
		File filedir = createDir(dirpath);
		if (null == filedir) {
			return null;
		}
		else {
			return new File(filedir.getPath() + File.separator + filename);
		}

	}
	/**
	 * 把图片压缩到200K
	 *
	 * @param oldpath
	 *            压缩前的图片路径
	 * @param newPath
	 *            压缩后的图片路径
	 * @return
	 */
	public static File compressFile(String oldpath, String newPath) {
		Bitmap compressBitmap = FileUtils.decodeFile(oldpath);
		Bitmap newBitmap = ratingImage(oldpath, compressBitmap);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		newBitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
		byte[] bytes = os.toByteArray();

		File file = null ;
		try {
			file = FileUtils.getFileFromBytes(bytes, newPath);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(newBitmap != null ){
				if(!newBitmap.isRecycled()){
					newBitmap.recycle();
				}
				newBitmap  = null;
			}
			if(compressBitmap != null ){
				if(!compressBitmap.isRecycled()){
					compressBitmap.recycle();
				}
				compressBitmap  = null;
			}
		}
		return file;
	}
	private static Bitmap ratingImage(String filePath,Bitmap bitmap){
		int degree = readPictureDegree(filePath);
		return rotaingImageView(degree, bitmap);
	}

	/**
	 *  旋转图片
	 * @param angle
	 * @param bitmap
	 * @return Bitmap
	 */
	public static Bitmap rotaingImageView(int angle , Bitmap bitmap) {
		//旋转图片 动作
		Matrix matrix = new Matrix();;
		matrix.postRotate(angle);
		System.out.println("angle2=" + angle);
		// 创建新的图片
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
				bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		return resizedBitmap;
	}
	/**
	 * 读取图片属性：旋转的角度
	 * @param path 图片绝对路径
	 * @return degree旋转的角度
	 */
	public static int readPictureDegree(String path) {
		int degree  = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_90:
					degree = 90;
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					degree = 180;
					break;
				case ExifInterface.ORIENTATION_ROTATE_270:
					degree = 270;
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}
	/**
	 * 把字节数组保存为一个文件
	 *
	 * @param b
	 * @param outputFile
	 * @return
	 */
	public static File getFileFromBytes(byte[] b, String outputFile) {
		File ret = null;
		BufferedOutputStream stream = null;
		try {
			ret = new File(outputFile);
			FileOutputStream fstream = new FileOutputStream(ret);
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e) {
			// log.error("helper:get file from byte process error!");
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					// log.error("helper:get file from byte process error!");
					e.printStackTrace();
				}
			}
		}
		return ret;
	}
	/**
	 * 图片压缩
	 *
	 * @param fPath
	 * @return
	 */
	public static Bitmap decodeFile(String fPath) {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		opts.inDither = false; // Disable Dithering mode
		opts.inPurgeable = true; // Tell to gc that whether it needs free
		opts.inInputShareable = true; // Which kind of reference will be used to
		BitmapFactory.decodeFile(fPath, opts);
		final int REQUIRED_SIZE = 200;
		int scale = 1;
		if (opts.outHeight > REQUIRED_SIZE || opts.outWidth > REQUIRED_SIZE) {
			final int heightRatio = Math.round((float) opts.outHeight
					/ (float) REQUIRED_SIZE);
			final int widthRatio = Math.round((float) opts.outWidth
					/ (float) REQUIRED_SIZE);
			scale = heightRatio < widthRatio ? heightRatio : widthRatio;//
		}
		Log.i("scale", "scal ="+ scale);
		opts.inJustDecodeBounds = false;
		opts.inSampleSize = scale;
		Bitmap bm = BitmapFactory.decodeFile(fPath, opts).copy(Bitmap.Config.ARGB_8888, false);
		return bm;
	}
}
