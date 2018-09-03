/*
 * Copyright (c) 2018 The Jaxxy Authors.
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

package org.jaxxy.logging.decorator;


import java.lang.reflect.Method;

import org.jaxxy.test.hello.DefaultHelloResource;
import org.junit.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ResourceDecoratorTest extends AbstractDecoratorTest{

    @Test
    public void shouldDecorate() throws Exception {

        when(loggingContext.getResourceInfo().getResourceClass()).thenAnswer(invocation -> DefaultHelloResource.class);
        final Method method = DefaultHelloResource.class.getMethod("sayHello", String.class);
        when(loggingContext.getResourceInfo().getResourceMethod()).thenReturn(method);

        final ResourceDecorator decorator = new ResourceDecorator();

        decorator.decorate(loggingContext);
        verify(loggingContext).put("resource.methodName", "sayHello");
        verify(loggingContext).put("resource.methodSignature", method.toGenericString());
        verify(loggingContext).put("resource.type", DefaultHelloResource.class.getCanonicalName());

    }
}