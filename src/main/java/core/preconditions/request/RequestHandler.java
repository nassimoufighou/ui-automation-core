package core.preconditions.request;

import core.preconditions.ApiException;
import core.preconditions.response.Response;
import core.preconditions.restActions.DeleteRequest;
import core.preconditions.restActions.PostRequest;
import core.preconditions.restActions.GetRequest;
import core.preconditions.restActions.PutRequest;

import java.io.IOException;

public class RequestHandler {

    public <T> Response<T> executeRequest(AbstractRequest preconditionRequest) throws IOException, ApiException {
        Response<T> response = null;
        switch (preconditionRequest.getMethod()) {
            case GET:
                GetRequest getRequest = new GetRequest();
                response = getRequest.executeRequest(preconditionRequest);
                break;
            case POST:
                PostRequest postRequestExecutor = new PostRequest();
                response = postRequestExecutor.executeRequest(preconditionRequest);
                break;
            case PUT:
                PutRequest putRequestExecutor = new PutRequest();
                response = putRequestExecutor.executeRequest(preconditionRequest);
                break;
            case DELETE:
                DeleteRequest deleteRequest = new DeleteRequest();
                response = deleteRequest.executeRequest(preconditionRequest);
                break;
        }
        return response;
    }
}
