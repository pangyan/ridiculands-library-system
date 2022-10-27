package com.ridiculands.grocery.service;

import com.ridiculands.grocery.db.User;
import com.ridiculands.grocery.db.UserDao;
import com.ridiculands.grocery.stubs.user.MemberType;
import com.ridiculands.grocery.stubs.user.UserRequest;
import com.ridiculands.grocery.stubs.user.UserResponse;
import com.ridiculands.grocery.stubs.user.UserServiceGrpc;
import io.grpc.stub.StreamObserver;

public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {
    @Override
    public void getUserDetails(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        UserDao userDao = new UserDao();
        User user = userDao.getDetails(request.getUserName());

        UserResponse.Builder userResponseBuilder = UserResponse.newBuilder()
                .setId(user.getId())
                .setUserName(user.getUserName())
                .setName(user.getName())
                .setMemberType(MemberType.valueOf(user.getMemberType()));

        UserResponse userResponse = userResponseBuilder.build();

        responseObserver.onNext(userResponse);
        responseObserver.onCompleted();
    }
}
