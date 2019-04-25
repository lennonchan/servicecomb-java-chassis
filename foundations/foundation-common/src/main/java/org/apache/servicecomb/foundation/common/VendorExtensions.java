/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.servicecomb.foundation.common;

import java.util.Map;
import java.util.function.Function;

import org.apache.servicecomb.foundation.common.concurrent.ConcurrentHashMapEx;

public class VendorExtensions {
  private Map<Object, Object> store = new ConcurrentHashMapEx<>();

  public Map<Object, Object> getStore() {
    return store;
  }

  @SuppressWarnings("unchecked")
  public <V> V get(Object key) {
    return (V) store.get(key);
  }

  public void put(Object key, Object value) {
    store.put(key, value);
  }

  @SuppressWarnings("unchecked")
  public <V> V computeIfAbsent(Object key, Function<Object, Object> mappingFunction) {
    return (V) store.computeIfAbsent(key, mappingFunction);
  }
}
