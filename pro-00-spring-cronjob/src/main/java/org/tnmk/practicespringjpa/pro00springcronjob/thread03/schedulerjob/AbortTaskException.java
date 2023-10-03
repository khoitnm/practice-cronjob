package org.tnmk.practicespringjpa.pro00springcronjob.thread03.schedulerjob;

public class AbortTaskException extends RuntimeException {

  public AbortTaskException(String message, Throwable ex) {
    super(message, ex);
  }
}
