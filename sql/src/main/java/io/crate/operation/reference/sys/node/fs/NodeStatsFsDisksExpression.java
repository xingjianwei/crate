/*
 * Licensed to Crate under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.  Crate licenses this file
 * to you under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.  You may
 * obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 *
 * However, if you have executed another commercial license agreement
 * with Crate these terms will supersede the license and you may use the
 * software solely pursuant to the terms of the relevant commercial
 * agreement.
 */

package io.crate.operation.reference.sys.node.fs;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import io.crate.monitor.FsInfoHelpers;
import io.crate.operation.reference.sys.node.NodeStatsArrayTypeExpression;
import org.elasticsearch.common.lucene.BytesRefs;
import org.elasticsearch.monitor.fs.FsInfo;

import java.util.List;
import java.util.Map;


public class NodeStatsFsDisksExpression extends NodeStatsArrayTypeExpression<FsInfo.Path, Map<String, Object>> {

    public NodeStatsFsDisksExpression() {
    }

    @Override
    protected List<FsInfo.Path> items() {
        return Lists.newArrayList(this.row.fsInfo());
    }

    @Override
    protected Map<String, Object> valueForItem(final FsInfo.Path path) {
        return ImmutableMap.<String, Object>builder()
            .put(NodeFsStatsExpression.DEV, BytesRefs.toBytesRef(FsInfoHelpers.Path.dev(path)))

            .put(NodeFsStatsExpression.SIZE, FsInfoHelpers.Path.size(path))
            .put(NodeFsStatsExpression.USED, FsInfoHelpers.Path.used(path))
            .put(NodeFsStatsExpression.AVAILABLE, FsInfoHelpers.Path.available(path))

            .put(NodeFsStatsExpression.READS, -1L)
            .put(NodeFsStatsExpression.BYTES_READ, -1L)
            .put(NodeFsStatsExpression.WRITES, -1L)
            .put(NodeFsStatsExpression.BYTES_WRITTEN, -1L)

            .build();
    }
}
