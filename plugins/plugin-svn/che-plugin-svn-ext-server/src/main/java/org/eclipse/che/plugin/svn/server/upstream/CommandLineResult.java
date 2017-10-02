/*
 * Copyright (c) 2012-2017 Red Hat, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Red Hat, Inc. - initial API and implementation
 */
package org.eclipse.che.plugin.svn.server.upstream;

import java.util.List;
import org.eclipse.che.api.core.util.CommandLine;

/** The result of executing a command line. */
public class CommandLineResult {

  private final CommandLine commandLine;
  private final int exitCode;
  private final List<String> stdout;
  private final List<String> stderr;

  /**
   * Constructor.
   *
   * @param commandLine the command line
   * @param exitCode the exit code
   * @param stdout the stdout lines
   * @param stderr the stderr lines
   */
  public CommandLineResult(
      final CommandLine commandLine,
      final int exitCode,
      final List<String> stdout,
      final List<String> stderr) {
    this.commandLine = commandLine;
    this.exitCode = exitCode;
    this.stdout = stdout;
    this.stderr = stderr;
  }

  /** @return the command line */
  public CommandLine getCommandLine() {
    return commandLine;
  }

  /** @return the exit code */
  public int getExitCode() {
    return exitCode;
  }

  /** @return the stdout lines */
  public List<String> getStdout() {
    return stdout;
  }

  /** @return the stderr lines */
  public List<String> getStderr() {
    return stderr;
  }
}
