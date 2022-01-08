package core.preconditions.restActions;

import okhttp3.*;
import core.preconditions.ApiException;
import core.preconditions.request.AbstractRequest;
import core.preconditions.response.Response;

import java.io.IOException;

public class PostRequest extends GenericRequest {

    @Override
    protected Request buildRequest(AbstractRequest preconditionRequest) {
        Request.Builder requestBuilder = getRequestBuilder(preconditionRequest);
        RequestBody requestBody = getRequestBody(preconditionRequest);
        return requestBuilder.post(requestBody).build();
    }

//    @Override
//    public Response executeRequest(PreconditionRequest preconditionEntity) throws IOException, ApiException {
//        Request request = buildRequest(preconditionEntity);
//        return executeGenericRequest(request);
//    }

    @Override
    public <T> Response<T> executeRequest(AbstractRequest preconditionEntity) throws IOException, ApiException {
        Request request = buildRequest(preconditionEntity);
        return executeGenericRequest(request);
    }
}
