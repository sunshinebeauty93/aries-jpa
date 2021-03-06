/*  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.aries.jpa.container.itest;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.apache.aries.jpa.container.itest.entities.Car;
import org.eclipse.persistence.internal.weaving.PersistenceWeaved;
import org.junit.Test;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.osgi.framework.Bundle;

public class JPAContainerEclipseLinkTest extends JPAContainerTest {

    @Configuration
    public Option[] eclipseLinkConfig() {
        return new Option[] {
            baseOptions(), //
            ariesJpa21(), //
            jta12Bundles(), //
            eclipseLink(), //
            derbyDSF(), //
            testBundle()
        };
    }

    @Test
    public void testClassIsWoven() throws Exception {
        assertTrue("Not PersistenceCapable",
                   Arrays.asList(Car.class.getInterfaces()).contains(PersistenceWeaved.class));
    }
    
	@Override
	protected String getProviderClassName() {
		return "org.eclipse.persistence.jpa.PersistenceProvider";
	}

	@Override
	protected Bundle getProviderBundle() {
		return getBundleByName("org.apache.aries.jpa.eclipselink.adapter");
	}
}
