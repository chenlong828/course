/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

public class TestSessionExpiration {

    public static void main(String[] args) throws Exception {
        System.out.println("Starting zk1");

        // Open a client connection - zk1
        CountdownWatcher watch1 = new CountdownWatcher("zk1");
        ZooKeeper zk1 = new ZooKeeper(args[0], 10000, watch1);
        watch1.waitForConnected(10000);

        zk1.getData("/", false, null);

        System.out.println("Starting zk2");

        // now attach a second client zk2 with the same sessionid/passwd
        CountdownWatcher watch2 = new CountdownWatcher("zk2");
        ZooKeeper zk2 = new ZooKeeper(args[0], 10000, watch2,
                zk1.getSessionId(), zk1.getSessionPasswd());
        watch2.waitForConnected(10000);

        // close the second client, the session is now invalid
        System.out.println("Closing zk2");
        zk2.close();

        System.out.println("Attempting use of zk1");

        try {
            // this will throw session expired exception
            zk1.getData("/", false, null);
        } catch (KeeperException.SessionExpiredException e) {
            System.out.println("Got session expired on zk1!");
            return;
        }
        // 3.2.0 and later:
        // There's a gotcha though - In version 3.2.0 and later if you
        // run this on against a quorum (vs standalone) you may get a
        // KeeperException.SessionMovedException instead. This is
        // thrown if a client moved from one server to a second, but
        // then attempts to talk to the first server (should never
        // happen, but could in certain bad situations), this example
        // simulates that situation in the sense that the client with
        // session id zk1.getSessionId() has moved
        // 
        // One way around session moved on a quorum is to have each
        // client connect to the same, single server in the cluster
        // (so pass a single host:port rather than a list). This 
        // will ensure that you get the session expiration, and
        // not session moved exception.
        //
        // Again, if you run against standalone server you won't see
        // this. If you run against a server version 3.1.x or earlier
        // you won't see this.
        // If you run against quorum you need to easily determine which
        // server zk1 is attached to - we are adding this capability
        // in 3.3.0 - and have zk2 attach to that same server.

        System.err.println("Oops, this should NOT have happened!");
    }
}
