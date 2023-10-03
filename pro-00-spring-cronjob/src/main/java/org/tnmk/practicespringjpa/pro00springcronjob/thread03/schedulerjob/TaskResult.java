package org.tnmk.practicespringjpa.pro00springcronjob.thread03.schedulerjob;

import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.util.Optional;

@Getter
@Builder
public class TaskResult<D> {
  /**
   * This is the result of a process, which could be nullable.
   */
  @Nullable
  private final D data;

  /**
   * This is the exception from the process.
   * Of courses, if the process succeed, this field will be empty.
   */
  private final Optional<Exception> exception;
}
