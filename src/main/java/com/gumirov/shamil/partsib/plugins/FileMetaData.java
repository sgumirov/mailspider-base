package com.gumirov.shamil.partsib.plugins;

import java.io.InputStream;
import java.util.Map;
import org.apache.camel;

/**
 * (c) 2017 by Shamil Gumirov (shamil@gumirov.com).<br/>
 * Date: 22/2/2017 Time: 01:04<br/>
 */
public class FileMetaData {
  /**
   * This constant is to use in plugins to get rid of camel-core dependency.
   */
  public final static String FILENAME_HEADER = Exchange.FILE_NAME;
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

  @Override
  public String toString() {
    return "FileMetaData{" +
        "senderId='" + senderId + '\'' +
        ", filename='" + filename + '\'' +
        '}';
  }
}
