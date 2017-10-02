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

import javax.validation.constraints.NotNull;
import org.eclipse.che.dto.shared.DTO;

@DTO
public interface GetRevisionsRequest {

  /** @return the project path the request is associated with. */
  String getProjectPath();

  /** @param projectPath the project path to set */
  void setProjectPath(@NotNull final String projectPath);

  /** @param projectPath the project path to use */
  GetRevisionsRequest withProjectPath(@NotNull final String projectPath);

  /** @return the path the request is associated with */
  String getPath();

  /** @param path the path to check */
  void setPath(@NotNull final String path);

  /** @param path the path to check */
  GetRevisionsRequest withPath(@NotNull final String path);

  /** @return the revision range */
  String getRevisionRange();

  /** @param revisionRange the revision range to check */
  void setRevisionRange(@NotNull final String revisionRange);

  /** @param revisionRange the revision range to check */
  GetRevisionsRequest withRevisionRange(@NotNull final String revisionRange);
}
