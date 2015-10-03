package com.conference.presentations.dao;

import com.conference.presentations.server.*;

import java.util.Collections;
import java.util.List;

import com.linkedin.data.template.IntegerArray;
import com.linkedin.r2.RemoteInvocationException;
import com.linkedin.r2.transport.common.Client;
import com.linkedin.r2.transport.common.bridge.client.TransportClientAdapter;
import com.linkedin.r2.transport.http.client.HttpClientFactory;
import com.linkedin.restli.client.ActionRequest;
import com.linkedin.restli.client.CreateIdRequest;
import com.linkedin.restli.client.DeleteRequest;
import com.linkedin.restli.client.GetRequest;
import com.linkedin.restli.client.Response;
import com.linkedin.restli.client.ResponseFuture;
import com.linkedin.restli.client.RestClient;
import com.linkedin.restli.client.UpdateRequest;
import com.linkedin.restli.common.EmptyRecord;
import com.linkedin.restli.common.HttpStatus;
import com.linkedin.restli.common.IdResponse;

public class ConferenceRestServer {
    public static String CONFERENCE_AUTHORIZATION_HEADER = "conference-authorization";
    public static String FAKE_CONFERENCE_TOKEN = "abc";

    private static final HttpClientFactory http = new HttpClientFactory();
    private static final Client r2Client = new TransportClientAdapter(
            http.getClient(Collections.<String, String> emptyMap()));
    private static final String BASE_URL = "http://localhost:7777/";

    private static RestClient restClient = new RestClient(r2Client, BASE_URL);

    public static Integer createUser(String email, String name, String password, String address, String country, String phoneNumber, List<Integer> fields) {
        try {
            // Construct a request for the specified fortune
            UserCreateRequestBuilder rb = new UserRequestBuilders().create();
            CreateIdRequest<Integer, User> registerReq = rb.input(new User().setEmail(email).setPassword(password).setPhoneNumber(phoneNumber).setName(name).setAddress(address).setFields(new IntegerArray(fields)).setCountry(country)).build();

            System.out.println("\ncreate user request: " + registerReq);
            // Send the request and wait for a response
            final ResponseFuture<IdResponse<Integer>> getFuture = restClient.sendRequest(registerReq);
            final Response<IdResponse<Integer>> resp = getFuture.getResponse();

            Integer userId = resp.getEntity().getId();
            // Print the response
            System.out.println("\ncreate user returns: " + userId);
            return userId;
        } catch (RemoteInvocationException e) {
            e.printStackTrace();
            System.out.println("\ncreate user failed!!!!!!!!!!!!!!!!!!");
            return null;
        }
    }

    public static User getUser(Integer userId) {
        GetRequest<User> getReq = new UserRequestBuilders().get().id(userId).addHeader(CONFERENCE_AUTHORIZATION_HEADER, FAKE_CONFERENCE_TOKEN).build();

        System.out.println("\nget user request: " + getReq);
        // Send the request and wait for a response
        final ResponseFuture<User> getFuture = restClient.sendRequest(getReq);
        final Response<User> resp;
        try {
            resp = getFuture.getResponse();
            User user = resp.getEntity();

            return user;
        } catch (RemoteInvocationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean updateUser(final User newUser, final Integer userId) {
        // Creating the profile update request builder
        UserUpdateRequestBuilder updateRequestBuilder = new UserRequestBuilders().update();

        UpdateRequest updateReq = updateRequestBuilder.id(userId).input(newUser)
                .addHeader(CONFERENCE_AUTHORIZATION_HEADER, FAKE_CONFERENCE_TOKEN).build();

        // Send the request and wait for a response
        final ResponseFuture getFuture = restClient.sendRequest(updateReq);

        // If you get an OK response, then the comment has been updated in the table
        final Response resp;
        try {
            resp = getFuture.getResponse();
            if (resp.getStatus() == HttpStatus.S_200_OK.getCode()) {
                return true;
            }
        } catch (RemoteInvocationException e) {
            e.printStackTrace();

        }
        return false;
    }

    public static void deleteUser(Integer userId) {
        try {
            UserDeleteRequestBuilder rb = new UserRequestBuilders().delete();
            DeleteRequest<User> deleteRequest = rb.id(userId).build();

            final ResponseFuture<EmptyRecord> responseFuture = restClient.sendRequest(deleteRequest);
            final Response<EmptyRecord> response = responseFuture.getResponse();

            System.out.println("\ndeleteUser returns: " + response.getStatus());
        } catch (RemoteInvocationException e) {
            e.printStackTrace();
        }
    }

    public static User getUserProfile(final String email) {
        // Construct a request for the specified fortune
        UserDoGetUserFromEmailRequestBuilder rb = new UserRequestBuilders().actionGetUserFromEmail().emailParam(email);
        ActionRequest<User> getReq = rb.build();

        // Send the request and wait for a response
        final ResponseFuture<User> getFuture = restClient.sendRequest(getReq);
        final Response<User> resp;
        try {
            resp = getFuture.getResponse();
            return resp.getEntity();
        } catch (RemoteInvocationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static UserArray getAllUsers(){
        // Construct a request for the specified fortune
        UserDoGetAllUsersRequestBuilder rb = new UserRequestBuilders().actionGetAllUsers();
        ActionRequest<UserArray> getReq = rb.build();

        // Send the request and wait for a response
        final ResponseFuture<UserArray> getFuture = restClient.sendRequest(getReq);
        final Response<UserArray> resp;
        try {
            resp = getFuture.getResponse();
            return resp.getEntity();
        } catch (RemoteInvocationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResearchFieldArray getAllFields(){
        // Construct a request for the specified fortune
        UserDoGetAllResearchFieldsRequestBuilder rb = new UserRequestBuilders().actionGetAllResearchFields();
        ActionRequest<ResearchFieldArray> getReq = rb.build();

        // Send the request and wait for a response
        final ResponseFuture<ResearchFieldArray> getFuture = restClient.sendRequest(getReq);
        final Response<ResearchFieldArray> resp;
        try {
            resp = getFuture.getResponse();
            return resp.getEntity();
        } catch (RemoteInvocationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Integer createConference(Conference conference) {
        try {
            // Construct a request for the specified fortune
            ConferenceCreateRequestBuilder rb = new ConferenceRequestBuilders().create();
            CreateIdRequest<Integer, Conference> registerReq = rb.input(conference).build();

            System.out.println("\ncreate conference request: " + registerReq);
            // Send the request and wait for a response
            final ResponseFuture<IdResponse<Integer>> getFuture = restClient.sendRequest(registerReq);
            final Response<IdResponse<Integer>> resp = getFuture.getResponse();

            Integer conferenceId = resp.getEntity().getId();
            // Print the response
            System.out.println("\ncreate conference returns: " + conferenceId);
            return conferenceId;
        } catch (RemoteInvocationException e) {
            e.printStackTrace();
            System.out.println("\ncreate conference failed!!!!!!!!!!!!!!!!!!");
            return null;
        }
    }

    public static Conference getConference(Integer conferenceId) {
        GetRequest<Conference> getReq = new ConferenceRequestBuilders().get().id(conferenceId).addHeader(CONFERENCE_AUTHORIZATION_HEADER, FAKE_CONFERENCE_TOKEN).build();

        System.out.println("\nget conference request: " + getReq);
        // Send the request and wait for a response
        final ResponseFuture<Conference> getFuture = restClient.sendRequest(getReq);
        final Response<Conference> resp;
        try {
            resp = getFuture.getResponse();
            Conference conference = resp.getEntity();

            return conference;
        } catch (RemoteInvocationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean updateConference(final Conference newConference, final Integer conferenceId) {
        // Creating the profile update request builder
        ConferenceUpdateRequestBuilder updateRequestBuilder = new ConferenceRequestBuilders().update();

        UpdateRequest updateReq = updateRequestBuilder.id(conferenceId).input(newConference)
                .addHeader(CONFERENCE_AUTHORIZATION_HEADER, FAKE_CONFERENCE_TOKEN).build();

        // Send the request and wait for a response
        final ResponseFuture getFuture = restClient.sendRequest(updateReq);

        // If you get an OK response, then the comment has been updated in the table
        final Response resp;
        try {
            resp = getFuture.getResponse();
            if (resp.getStatus() == HttpStatus.S_200_OK.getCode()) {
                return true;
            }
        } catch (RemoteInvocationException e) {
            e.printStackTrace();

        }
        return false;
    }

    public static void deleteConference(Integer conferenceId) {
        try {
            ConferenceDeleteRequestBuilder rb = new ConferenceRequestBuilders().delete();
            DeleteRequest<Conference> deleteRequest = rb.id(conferenceId).build();

            final ResponseFuture<EmptyRecord> responseFuture = restClient.sendRequest(deleteRequest);
            final Response<EmptyRecord> response = responseFuture.getResponse();

            System.out.println("\ndeleteConference returns: " + response.getStatus());
        } catch (RemoteInvocationException e) {
            e.printStackTrace();
        }
    }

    public static ConferenceArray getAllConferences(){
        // Construct a request for the specified fortune
        ConferenceDoGetAllConferencesRequestBuilder rb = new ConferenceRequestBuilders().actionGetAllConferences();
        ActionRequest<ConferenceArray> getReq = rb.build();

        // Send the request and wait for a response
        final ResponseFuture<ConferenceArray> getFuture = restClient.sendRequest(getReq);
        final Response<ConferenceArray> resp;
        try {
            resp = getFuture.getResponse();
            return resp.getEntity();
        } catch (RemoteInvocationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
