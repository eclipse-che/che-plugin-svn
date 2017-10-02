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
package org.eclipse.che.plugin.svn.ide;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;
import org.eclipse.che.ide.api.resources.Project;
import org.eclipse.che.plugin.svn.shared.SubversionTypeConstant;

/**
 * Utility class for svn based operations.
 *
 * @author Vlad Zhukovskiy
 * @since 4.4.0
 */
public class SvnUtil {

  /**
   * Checks whether the given {@code project} is under svn version control system.
   *
   * @param project the project to check
   * @return true if project is under svn, otherwise false
   */
  public static boolean isUnderSvn(Project project) {
    checkArgument(project != null, "Null project occurred");

    final List<String> mixins = project.getMixins();

    return mixins != null && mixins.contains(SubversionTypeConstant.SUBVERSION_MIXIN_TYPE);
  }
}
