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
package org.apache.camel.quarkus.reactive.executor;

import io.quarkus.runtime.RuntimeValue;
import io.quarkus.runtime.annotations.Recorder;
import io.vertx.core.Vertx;
import org.apache.camel.CamelContext;
import org.apache.camel.ExtendedCamelContext;
import org.apache.camel.reactive.vertx.VertXReactiveExecutor;
import org.apache.camel.spi.CamelContextCustomizer;

@Recorder
public class ReactiveExecutorRecorder {
    public RuntimeValue<CamelContextCustomizer> createReactiveExecutorCustomizer(RuntimeValue<Vertx> vertx) {
        return new RuntimeValue<>(new CamelContextCustomizer() {
            @Override
            public void configure(CamelContext context) {
                VertXReactiveExecutor executor = new VertXReactiveExecutor();
                executor.setVertx(vertx.getValue());

                context.adapt(ExtendedCamelContext.class).setReactiveExecutor(executor);
            }
        });
    }
}
