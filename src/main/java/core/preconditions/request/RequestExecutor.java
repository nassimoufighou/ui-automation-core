package core.preconditions.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.*;

import java.io.IOException;

public class RequestExecutor {

    private OkHttpClient client;
    private FormBody.Builder builder;

    public RequestExecutor(PreconditionRequest preconditionEntity) throws IOException {
        this.client = new OkHttpClient();
        this.builder = new FormBody.Builder();
        RequestBody requestBody;
        if (preconditionEntity.hasBodyParameters()) requestBody = buildBodyRequestFromPreconditionEntity(preconditionEntity);
        else requestBody = builder.build();
        Request.Builder requestBuilder = new Request.Builder();
        if (preconditionEntity.authorizationRequired()) requestBuilder.addHeader("authorization", preconditionEntity.getAuthorizationToken());
        requestBuilder.url(preconditionEntity.getUrl());
        requestBuilder.addHeader("Content-type", "application/json; charset=UTF-8");

        switch (preconditionEntity.getMethod()){
            case GET:
                requestBuilder.get();
                break;
            case PUT:
                requestBuilder.put(requestBody);
                break;
            case POST:
                requestBuilder.post(requestBody);
                break;
            case DELETE:
                requestBuilder.delete(requestBody);
                break;
        }
        Request request = requestBuilder.build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println("Response from request executed");
        System.out.println(response.body().string());
    }

    private RequestBody buildBodyRequestFromPreconditionEntity(PreconditionRequest preconditionRequest) throws JsonProcessingException {
        return RequestBody.create(MediaType.parse("application/json"), preconditionRequest.getBodyParameters());
    }
}
