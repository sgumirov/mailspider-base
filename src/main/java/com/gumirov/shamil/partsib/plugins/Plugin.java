package com.gumirov.shamil.partsib.plugins;

import org.slf4j.Logger;

import java.io.File;
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
public interface Plugin {

  /**
   * Processes file and maybe replaces its content with new one.
   * <p>In case of recoverable error write log entry and return null, in case of fatal error throw exception. Execution 
   * of plugins will then be skipped and rolled back. 
   * @param metadata in params, see {@link FileMetaData} class for details. In/out param: possible to add new values.
   * @param log logger to use
   * @return new file contents, or null if no changes needed
   * @throws Throwable if something wrong. Actually in case of trouble it's better to let it fall.
   */
  InputStream processFile(FileMetaData metadata, Logger log) throws Exception;
}
