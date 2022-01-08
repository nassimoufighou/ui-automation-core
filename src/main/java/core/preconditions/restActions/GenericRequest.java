package core.preconditions.restActions;

import okhttp3.*;
import core.preconditions.ApiException;
import core.preconditions.request.AbstractRequest;
import core.preconditions.response.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class GenericRequest {

    protected RequestBody getRequestBody(AbstractRequest preconditionRequest) {
        FormBody.Builder builder = new FormBody.Builder();
        if (preconditionRequest.hasBodyParameters()) return buildBodyRequestFromPreconditionEntity(preconditionRequest);
        else return builder.build();
    }

    protected Request.Builder getRequestBuilder(AbstractRequest preconditionRequest) {
        Request.Builder requestBuilder = new Request.Builder();
        if(preconditionRequest.hasUrlParameters()) requestBuilder.url(buildUrlWithParams(preconditionRequest));
        else requestBuilder.url(preconditionRequest.getUrl());

        if (preconditionRequest.authorizationRequired()) requestBuilder.addHeader("authorization", preconditionRequest.getAuthorizationToken());
        return requestBuilder;
    }

    protected RequestBody buildBodyRequestFromPreconditionEntity(AbstractRequest preconditionRequest) {
        return RequestBody.create(MediaType.parse("application/json"), preconditionRequest.getBodyParameters());
    }

    public <T> Response<T> executeGenericRequest(Request request) throws IOException, ApiException {
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        okhttp3.Response response = call.execute();
        int responseCode = response.code();
        boolean isSuccessful = response.isSuccessful();
        String responseBody = response.body().string();
         if (!isSuccessful) {
             String errorMessage = "No error message provided.";
//             JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
//             if (jsonObject.has("messageCode")) errorMessage = JsonParser.parseString(responseBody).getAsJsonObject().get("messageCode").toString();
             return new Response<T>(isSuccessful, responseCode, errorMessage);
        }
        else return new Response<T>(isSuccessful, responseCode, responseBody);
    }

    private HttpUrl buildUrlWithParams(AbstractRequest preconditionRequest) {
        HashMap<String, String> params = preconditionRequest.getUrlParameters();
        String[] endp = preconditionRequest.getEndpoint().split("/");

        HttpUrl.Builder url = new HttpUrl.Builder()
                .scheme("https")
                .host(preconditionRequest.getHost());

        for (String s : endp) {
            if (params.containsKey(s)){
                url.addEncodedPathSegment(s);
                url.addEncodedPathSegment(params.get(s));
            }
            else url.addEncodedPathSegment(s);
        }
        for (Map.Entry<String, String> entry : params.entrySet()) {
            url.addQueryParameter(entry.getKey(), entry.getValue());
        }
        return url.build();
    }

    protected abstract Request buildRequest(AbstractRequest preconditionRequest);

    protected abstract <T> Response<T> executeRequest(AbstractRequest preconditionRequest) throws IOException, ApiException;

}
