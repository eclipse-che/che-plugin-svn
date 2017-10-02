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
package org.eclipse.che.plugin.svn.server;

import org.eclipse.che.api.core.ServerException;

/** Exception for wrapping Subversion related exceptions. */
public class SubversionException extends ServerException {

  public SubversionException(final String message) {
    super(message);
  }

  public SubversionException(final Throwable throwable) {
    super(throwable);
  }
}
