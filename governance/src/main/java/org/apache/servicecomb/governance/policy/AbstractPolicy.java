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
package org.apache.servicecomb.governance.policy;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.servicecomb.governance.entity.Configurable;

public abstract class AbstractPolicy implements Policy, Configurable {

  protected String name;

  protected GovernanceRule rules;

  public GovernanceRule getRules() {
    return rules;
  }

  public void setRules(GovernanceRule rules) {
    this.rules = rules;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean isValid() {
    if (StringUtils.isEmpty(name)) {
      return false;
    }
    if (rules == null) {
      return false;
    }
    return rules.isValid();
  }

  @Override
  public boolean match(List<String> items) {
    if (rules == null) {
      return false;
    }
    return items.stream().anyMatch(item -> rules.match(item));
  }

  public static int compare(AbstractPolicy policy1, AbstractPolicy policy2) {
    int p1 = policy1.rules == null ? 0 : policy1.rules.getPrecedence();
    int p2 = policy2.rules == null ? 0 : policy2.rules.getPrecedence();
    return p1 - p2;
  }

  public List<String> getParsedMatch() {
    if (rules == null) {
      return null;
    }

    return rules.getParsedMatch();
  }

  @Override
  public String name() {
    return name;
  }
}
