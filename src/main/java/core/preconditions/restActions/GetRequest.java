package core.preconditions.restActions;

import core.preconditions.ApiException;
import core.preconditions.request.AbstractRequest;
import core.preconditions.response.Response;

import java.io.IOException;

public class GetRequest extends GenericRequest {

    @Override
    protected okhttp3.Request buildRequest(AbstractRequest preconditionRequest) {
        okhttp3.Request.Builder requestBuilder = getRequestBuilder(preconditionRequest);
        return requestBuilder.get().build();
    }

    @Override
    public <T> Response<T> executeRequest(AbstractRequest preconditionEntity) throws IOException, ApiException {
        okhttp3.Request request = buildRequest(preconditionEntity);
        return executeGenericRequest(request);
    }
}
