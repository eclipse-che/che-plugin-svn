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
package org.eclipse.che.plugin.svn.ide.common;

import javax.validation.constraints.NotNull;
import org.eclipse.che.ide.api.outputconsole.OutputConsole;

/**
 * Console that displays output of a SVN command.
 *
 * @author Stephane Tournie
 */
public interface SubversionOutputConsole extends OutputConsole {
  /**
   * Print text in console.
   *
   * @param text text that need to be shown
   */
  void print(@NotNull String text);

  /**
   * Print colored text in console.
   *
   * @param text text that need to be shown
   * @param color color of printed text
   */
  void print(@NotNull String text, @NotNull String color);

  /**
   * Print executed command in console.
   *
   * @param text command text
   */
  void printCommand(@NotNull String text);

  /**
   * Print error in console.
   *
   * @param text text that need to be shown as error
   */
  void printError(@NotNull String text);

  /** Clear console. Remove all messages. */
  void clear();
}
