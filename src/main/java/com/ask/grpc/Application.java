package com.ask.grpc;


import com.ask.grpc.service.UserServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class Application {

  public static void main(String[] args) throws InterruptedException, IOException {

    final Server server = ServerBuilder.forPort(8080).addService(new UserServiceImpl()).build();
    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(
                () -> {
                  server.shutdown();
                  System.out.println("Successfully stopped the server");
                }));
    server.start();
    System.out.println("Server started");
    server.awaitTermination();
  }
}
