/*-
 * #%L
 * che-starter
 * %%
 * Copyright (C) 2017 Red Hat, Inc.
 * %%
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * #L%
 */
package io.fabric8.che.starter.util;

import org.springframework.beans.factory.annotation.Autowired;

import io.fabric8.che.starter.TestConfig;
import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.junit.Test;

public class ProjectHelperTest extends TestConfig {
    private static final String REPOSITORY_URL = "https://github.com/spring-projects/spring-boot.git";
    private static final String BARE_REPOSITORY_URL = "https://github.com/spring-projects/spring-boot";
    private static final String BARE_REPOSITORY_SSH_URL = "git@github.com:spring-projects/spring-boot.git";
    private static final String BARE_REPOSITORY_URL_WITH_SLASH = "https://github.com/spring-projects/spring-boot/";
    private static final String INVALID_REPOSITORY_URL = "htps:/github.com/spring-projects/spring-boot.git";
    private static final String PROJECT_NAME = "spring-boot";

    @Autowired
    ProjectHelper projectHelper;

    @Test
    public void getProjectName() throws URISyntaxException, MalformedURLException {
        String projectName = projectHelper.getProjectNameFromGitRepository(REPOSITORY_URL);
        assertEquals(projectName, PROJECT_NAME);
    }

    @Test
    public void getProjectNameFromBareRepo() throws MalformedURLException, URISyntaxException {
        String projectName = projectHelper.getProjectNameFromGitRepository(BARE_REPOSITORY_URL);
        assertEquals(projectName, PROJECT_NAME);
    }

    @Test
    public void getProjectNameFromBareRepoWithSlash() throws MalformedURLException, URISyntaxException {
        String projectName = projectHelper.getProjectNameFromGitRepository(BARE_REPOSITORY_URL_WITH_SLASH);
        assertEquals(projectName, PROJECT_NAME);
    }

    @Test
    public void getProjectNameFromSshRepo() throws MalformedURLException, URISyntaxException {
        String projectName = projectHelper.getProjectNameFromGitRepository(BARE_REPOSITORY_SSH_URL);
        assertEquals(projectName, PROJECT_NAME);
    }

    @Test(expected = MalformedURLException.class)
    public void getProjectNameFromInvalidRepository() throws URISyntaxException, MalformedURLException {
        projectHelper.getProjectNameFromGitRepository(INVALID_REPOSITORY_URL);
    }

}
