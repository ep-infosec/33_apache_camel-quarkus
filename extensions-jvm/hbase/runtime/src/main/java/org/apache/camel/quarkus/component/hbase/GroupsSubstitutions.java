/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.quarkus.component.hbase;

import java.util.List;

import com.google.common.util.concurrent.ListenableFuture;
import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;

class GroupsSubstitutions {

}

@TargetClass(className = "org.apache.hadoop.security.Groups$GroupCacheLoader")
final class GroupCacheLoaderSubstitutions {

    @Substitute
    public ListenableFuture<List<String>> reload(final String key,
            List<String> oldValue) {
        /* This is to avoid native image creation failure due to missing
         * com.google.common.util.concurrent.Futures.addCallback(ListenableFuture<List<String>>, FutureCallback<? super List<String>>)
         * It was removed in Guava 22 */
        throw new UnsupportedOperationException(
                "org.apache.hadoop.security.Groups.GroupCacheLoader.reload(String, List<String>) unsupported in native mode");
    }

}
