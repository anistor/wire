/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squareup.wire.internal;

import com.google.common.collect.ImmutableList;
import java.util.List;

public final class Util {
  private static final String URL_CHARS = "[-!#$%&'()*+,./0-9:;=?@A-Z\\[\\]_a-z~]";

  private Util() {
  }

  public static <T> ImmutableList<T> concatenate(List<T> a, T b) {
    return ImmutableList.<T>builder()
        .addAll(a)
        .add(b)
        .build();
  }

  /** A grab-bag of fixes for things that can go wrong when converting to javadoc. */
  public static String sanitizeJavadoc(String documentation) {
    // Remove trailing whitespace on each line.
    documentation = documentation.replaceAll("[^\\S\n]+\n", "\n");
    documentation = documentation.replaceAll("\\s+$", "");
    // Rewrite '@see <url>' to use an html anchor tag
    documentation = documentation.replaceAll(
        "@see (http:" + URL_CHARS + "+)", "@see <a href=\"$1\">$1</a>");
    return documentation;
  }
}