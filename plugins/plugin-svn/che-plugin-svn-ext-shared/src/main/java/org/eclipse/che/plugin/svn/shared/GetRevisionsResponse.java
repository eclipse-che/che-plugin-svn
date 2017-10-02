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
package org.eclipse.che.plugin.svn.shared;

import java.util.List;
import javax.validation.constraints.NotNull;
import org.eclipse.che.dto.shared.DTO;

@DTO
public interface GetRevisionsResponse {

  /** @return the revisions relevant for a given path */
  List<String> getRevisions();

  /** @param revisions the revisions relevant for a given path */
  void setRevisions(@NotNull final List<String> revisions);

  /** @param revisions the revisions relevant for a given path */
  GetRevisionsResponse withRevisions(@NotNull final List<String> revisions);

  /** @return the executed command */
  String getCommand();

  /** @param command the executed command */
  void setCommand(@NotNull final String command);

  /**
   * @param command the executed command
   * @return the response
   */
  GetRevisionsResponse withCommand(@NotNull final String command);

  /** @return the update output */
  List<String> getOutput();

  /** @param output the update output to set */
  void setOutput(@NotNull final List<String> output);

  /**
   * @param output the update output to use
   * @return the response
   */
  GetRevisionsResponse withOutput(@NotNull final List<String> output);

  /** @return the update error output */
  List<String> getErrOutput();

  /** @param errors the update error output to set */
  void setErrOutput(@NotNull final List<String> errors);

  /**
   * @param errors the update error output to use
   * @return the response
   */
  GetRevisionsResponse withErrOutput(@NotNull final List<String> errors);
}
