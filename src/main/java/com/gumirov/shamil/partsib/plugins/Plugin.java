package com.gumirov.shamil.partsib.plugins;

import org.slf4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Plugin can change only file contents via returning new InputStream value from processFile().
 * <p>
 *   Note: since version 1.9 if a plugin creates temporary file to be deleted later it must add it to queue
 *   using {@link FileMetaData#addFileToDelete(File)}
 * </p>
 * <p>(c) 2017 by Shamil Gumirov (shamil@gumirov.com).
 * <p>
 *   @since 1.9 Throws Exception in case of error.
 * </p>
 */
@SuppressWarnings("unused")
public interface Plugin {
  /**
   * Processes file and maybe replaces its content with new one.
   * <p>In case of recoverable error write log entry and return null, in case of fatal error throw exception. Execution 
   * of plugins will then be skipped and rolled back. 
   * @param metadata in params, see {@link FileMetaData} class for details. In/out param: possible to add new values.
   * @param log logger to use
   * @return new file contents, or null if no changes needed
   * @throws Exception if something wrong. Actually in case of trouble it's better to let it fall.
   */
  Result processFile(FileMetaData metadata, Logger log) throws Exception;

  abstract class Result {
    public static Result create(String filename){
      return new FileResult(new File(filename));
    }
    public static Result create(InputStream is){
      return new StreamResult(is);
    }
    public static Result create(File file){
      return new FileResult(file);
    }
    public abstract Object getResult();
    public abstract InputStream getInputStream() throws FileNotFoundException;
  }

  class FileResult extends Result {
    private final File file;

    public FileResult(File file) {
      this.file = file;
    }

    public File getResult() {
      return file;
    }

    @Override
    public InputStream getInputStream() throws FileNotFoundException {
      return new FileInputStream(file);
    }
  }

  class StreamResult extends Result {
    private final InputStream is;

    public StreamResult(InputStream is) {
      this.is = is;
    }

    public InputStream getResult() {
      return is;
    }

    @Override
    public InputStream getInputStream() {
      return is;
    }
  }
}
