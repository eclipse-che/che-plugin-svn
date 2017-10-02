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
package org.eclipse.che.plugin.svn.server.repository;

import static org.eclipse.che.plugin.svn.server.upstream.UpstreamUtils.executeCommandLine;

import java.io.File;
import java.io.IOException;
import org.eclipse.che.plugin.svn.server.SubversionException;
import org.eclipse.che.plugin.svn.server.upstream.CommandLineResult;
import org.eclipse.che.plugin.svn.server.utils.InfoUtils;

/**
 * Detects repository url based on location.
 *
 * @author Anatolii Bazko
 */
public class RepositoryUrlProviderImpl implements RepositoryUrlProvider {

  @Override
  public String getRepositoryUrl(final String projectPath) throws SubversionException {
    try {
      final CommandLineResult clResult =
          executeCommandLine(null, "svn", new String[] {"info"}, null, -1L, new File(projectPath));
      return InfoUtils.getUrl(clResult.getStdout());
    } catch (IOException e) {
      throw new SubversionException(e);
    }
  }
}
