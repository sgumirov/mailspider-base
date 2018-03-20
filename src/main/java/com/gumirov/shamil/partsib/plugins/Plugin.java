package com.gumirov.shamil.partsib.plugins;

import org.slf4j.Logger;

import java.io.InputStream;

/**
 * Plugin can change only file contents via returning new InputStream value from processFile(). <p>Note: it's recommended to do 
 * all the operations with streams in memory instead of disk to keep latency low. 
 * <p>
 *   Note: since version 1.8 if a plugin creates temporary file to be deleted when not used it must add File with
 *   key {@link #TEMP_FILE} to
 *   {@link FileMetaData#headers}
 * </p>
 * <p>(c) 2017 by Shamil Gumirov (shamil@gumirov.com).<br/>
 */
public interface Plugin {
  /**
   * Key for header to be used when plugin creates temp file to be deleted when not used. Value can be of type: File,
   * List&lt;String&gt;.
   * @since 1.8
   */
  public final String TEMP_FILE = "MAILSPIDER_PLUGIN_TEMP_FILE";

  /**
   * Processes file and maybe replaces its content with new one.
   * <p>In case of recoverable error write log entry and return null, in case of fatal error throw exception. Execution 
   * of plugins will then be skipped and rolled back. 
   * @param metadata in params, see {@link FileMetaData} class for details. In/out param: possible to add new values.
   * @param log logger to use
   * @return new file contents, or null if no changes needed
   */
  InputStream processFile(FileMetaData metadata, Logger log);
}
