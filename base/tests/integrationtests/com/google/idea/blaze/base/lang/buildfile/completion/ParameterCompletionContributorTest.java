/*
 * Copyright 2016 The Bazel Authors. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.idea.blaze.base.lang.buildfile.completion;

import static com.google.common.truth.Truth.assertThat;

import com.google.idea.blaze.base.lang.buildfile.BuildFileIntegrationTestCase;
import com.google.idea.blaze.base.lang.buildfile.psi.BuildFile;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.openapi.editor.Editor;

/** Tests ParameterCompletionContributor. */
public class ParameterCompletionContributorTest extends BuildFileIntegrationTestCase {

  public void testArgsCompletion() {
    BuildFile file = createBuildFile("BUILD", "def function(arg1, *");

    Editor editor = openFileInEditor(file.getVirtualFile());
    setCaretPosition(editor, 0, "def function(arg1, *".length());

    LookupElement[] completionItems = testFixture.completeBasic();
    assertThat(completionItems).isNull();

    assertFileContents(file, "def function(arg1, *args");
  }

  public void testKwargsCompletion() {
    BuildFile file = createBuildFile("BUILD", "def function(arg1, **");

    Editor editor = openFileInEditor(file.getVirtualFile());
    setCaretPosition(editor, 0, "def function(arg1, **".length());

    LookupElement[] completionItems = testFixture.completeBasic();
    assertThat(completionItems).isNull();

    assertFileContents(file, "def function(arg1, **kwargs");
  }
}