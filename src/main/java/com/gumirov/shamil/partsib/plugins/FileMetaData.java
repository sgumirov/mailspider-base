package com.gumirov.shamil.partsib.plugins;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.camel.*;

/**
 * (c) 2017 by Shamil Gumirov (shamil@gumirov.com).<br/>
 * Date: 22/2/2017 Time: 01:04<br/>
 */
public class FileMetaData {
  /**
   * This constant is to use in plugins to get rid of camel-core dependency.
   */
  public final static String FILENAME_HEADER = Exchange.FILE_NAME;
  /**
   * Key for header to be used when plugin creates temp file to be deleted when not used. Value can be of type: File,
   * List&lt;String&gt;.
   * @since 1.8
   */
  public static final String TEMP_FILE_HEADER = "MAILSPIDER_PLUGIN_TEMP_FILE";
  public final String senderId;
  public final String filename;
  public InputStream is;
  /**
   * File headers such as content-type if known. Please refer to docs on each endpoint (email could have more
   * headers than FTP for example. Could be null.
   */
  public Map<String, Object> headers;

  public FileMetaData(String senderId, String filename, InputStream is, Map<String, Object> headers) {
    this.senderId = senderId;
    this.filename = filename;
    this.is = is;
    this.headers = headers;
  }

  public FileMetaData(String senderId, String filename, InputStream is) {
    this.senderId = senderId;
    this.filename = filename;
    this.is = is;
  }

  /**
   * Adds File to pipeline delete queue
   * @param f File to delete when not needed to free disk space.
   */
  public void addFileToDelete(File f) {
    if (headers == null) headers = new HashMap<>();
    ArrayList<File> filesToDelete;
    if ( !headers.containsKey(TEMP_FILE_HEADER) ) {
      filesToDelete = new ArrayList<>();
    } else {
      filesToDelete = (ArrayList<File>) headers.get(TEMP_FILE_HEADER);
    }
    filesToDelete.add(f);
    headers.put(TEMP_FILE_HEADER, filesToDelete);
  }

  @Override
  public String toString() {
    return "FileMetaData{" +
        "senderId='" + senderId + '\'' +
        ", filename='" + filename + '\'' +
        '}';
  }
}
