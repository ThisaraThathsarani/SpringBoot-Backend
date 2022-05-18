package com.example.springbootTodo.controller.controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

import java.io.IOException;

public class ControllerTestBase {
    private static final String MONGOD_BIND_IP = "127.0.0.1";
    private static final String MONGOD_URI = "mongodb://localhost";
    private static final int MONGOD_PORT = 27017;
    private static final MongodStarter MONGOD_STARTER = MongodStarter.getDefaultInstance();
    /**
     * The Mongo client.
     */
    protected MongoClient mongoClient;
    private MongodExecutable mongodExecutable;
    private MongodProcess mongodProcess;

    /**
     * Sets .
     *
     * @throws IOException the io exception
     */
    public void setup() throws IOException {
        // Initialize the mongo db
        mongodExecutable = MONGOD_STARTER.prepare(MongodConfig.builder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(MONGOD_BIND_IP, MONGOD_PORT, Network.localhostIsIPv6()))
                .build());
        mongodProcess = mongodExecutable.start();
        mongoClient = MongoClients.create(MONGOD_URI + ":" + MONGOD_PORT);
    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    public void tearDown() throws Exception {
        if (mongodProcess != null) {
            mongodProcess.stop();
            mongodExecutable.stop();
        }
    }
}
