package com.quan.demo;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;

import static org.neo4j.driver.Values.parameters;

/**
 * @Description: Neo4j 工具类
 * @Author heyq
 * @Date 2020-10-29
 **/
public class Neo4jDemo implements AutoCloseable {
    private final Driver driver;

    public Neo4jDemo(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    @Override
    public void close() throws Exception {
        driver.close();
    }

    public void printGreeting(final String message) {
        try (Session session = driver.session()) {
            String greeting = session.writeTransaction((tx) -> {
                        Result result = tx.run("CREATE (a:Greeting) " +
                                        "SET a.message = $message " +
                                        "RETURN a.message + ', from node ' + id(a)",
                                parameters("message", message));
                        return result.single().get(0).asString();
                    }
            );
            System.out.println(greeting);
        }
    }

    public static void main(String... args) throws Exception {
        try (Neo4jDemo greeter = new Neo4jDemo("bolt://120.76.175.67/:7687", "neo4j", "q1230.")) {
            greeter.printGreeting("hello, world");
        }
    }
}
