/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.fhirengine.search.impl

import com.google.fhirengine.resource.ResourceUtils
import org.hl7.fhir.r4.model.Resource

/** Query that returns a list of resource IDs. */
data class ResourceIdQuery(val query: String, val args: List<Any?>) {
    fun <R : Resource> getSearchResourceQuery(clazz: Class<R>): ResourceQuery {
        val resourceQuery =
                "SELECT serializedResource from ResourceEntity " +
                        "WHERE resourceType = ? AND resourceId IN (" + query + ")"
        return ResourceQuery(
                resourceQuery, listOf(ResourceUtils.getResourceType(clazz).name) + args)
    }
}