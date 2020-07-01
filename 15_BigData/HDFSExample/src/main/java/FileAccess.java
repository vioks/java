import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class FileAccess {

    private static FileSystem hdfs;

    /**
     * Initializes the class, using rootPath as "/" directory
     *
     * @param rootPath - the path to the root of HDFS,
     * for example, hdfs://localhost:32771
     */

    public FileAccess(String rootPath, FileSystem hdfs) throws IOException {

        FileAccess.hdfs = hdfs;

        if (isDirectory(rootPath)) {
            hdfs.mkdirs((Path) Paths.get(rootPath));
        } else {
            create(rootPath);
        }

    }

    /**
     * Creates empty file or directory
     *
     * @param path
     */
    public void create(String path) throws IOException {

        hdfs.createNewFile((Path) Paths.get(path));
    }

    /**
     * Appends content to the file
     *
     * @param path
     * @param content
     */
    public void append(String path, String content) throws IOException {

        Path isPath = (Path) Paths.get(path);

        if (hdfs.isFile(isPath)) {
            hdfs.append(isPath);
        }
    }

    /**
     * Returns content of the file
     *
     * @param path
     * @return
     */
    public String read(String path) throws IOException {

        Path isPath = (Path) Paths.get(path);

        if (hdfs.isFile(isPath)) {
            FSDataInputStream open = hdfs.open(isPath);

        }
        return null;
    }

    /**
     * Deletes file or directory
     *
     * @param path
     */
    public void delete(String path) throws IOException {

        hdfs.delete((Path) Paths.get(path), true);
    }

    /**
     * Checks, is the "path" is directory or file
     *
     * @param path
     * @return
     */
    public boolean isDirectory(String path) throws IOException {

        return hdfs.isDirectory((Path) Paths.get(path));
    }

    /**
     * Return the list of files and subdirectories on any directory
     *
     * @param path
     * @return
     */
    public List<String> list(String path) throws IOException {

        return hdfs.listXAttrs((Path) Paths.get(path));
    }
}
