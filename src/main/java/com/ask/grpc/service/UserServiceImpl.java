package com.ask.grpc.service;

import com.ask.grpc.proto.User;
import io.grpc.stub.StreamObserver;

import java.util.*;

/** UserServiceImpl. */
public class UserServiceImpl extends com.ask.grpc.proto.UserServiceGrpc.UserServiceImplBase {

  private final Map<String, com.ask.grpc.proto.User> userMap;

  public UserServiceImpl() {
    this.userMap = new HashMap<>();
  }

  @Override
  public void get(User request, StreamObserver<User> responseObserver) {
    final User user =
        Optional.ofNullable(request)
            .map(com.ask.grpc.proto.User::getId)
            .map(userMap::get)
            .orElse(null);
    responseObserver.onNext(user);
    responseObserver.onCompleted();
  }

  @Override
  public void findAll(
      com.google.protobuf.Empty request,
      io.grpc.stub.StreamObserver<com.ask.grpc.proto.User> responseObserver) {
    final List<com.ask.grpc.proto.User> users = userMap.values().stream().toList();
    users.forEach(
            responseObserver::onNext);
    responseObserver.onCompleted();
  }

  @Override
  public void save(
      com.ask.grpc.proto.User request,
      io.grpc.stub.StreamObserver<com.ask.grpc.proto.User> responseObserver) {

    final UUID uuid = UUID.randomUUID();
    final User user = User.newBuilder(request).setId(uuid.toString()).build();
    this.userMap.put(uuid.toString(), user);
    responseObserver.onNext(user);
    responseObserver.onCompleted();
  }
}
