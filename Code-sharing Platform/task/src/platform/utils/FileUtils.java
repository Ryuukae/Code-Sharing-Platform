package platform.utils;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;

public class FileUtils {
	public static boolean isFileUnlocked(File file) {
		try (FileChannel ignored = FileChannel.open(file.toPath(), StandardOpenOption.APPEND)) {
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}
