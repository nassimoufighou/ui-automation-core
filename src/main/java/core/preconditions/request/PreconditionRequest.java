package core.preconditions.request;

import com.beust.jcommander.Strings;
import core.preconditions.restActions.Methods;

import java.util.HashMap;

public class PreconditionRequest {

    private Methods method;
    private String url;
    private String host;
    private String endpoint;
    private String authorizationToken;
    private String bodyParameters;
    private HashMap<String, String> urlParameters;

    public PreconditionRequest(Methods method, String host, String endpoint, String authorizationToken, String bodyParameters, HashMap<String, String> urlParameters){
        this.method = method;
        this.host = host;
        this.endpoint = endpoint;
        this.url = host.concat(endpoint);
        this.authorizationToken = authorizationToken;
        this.bodyParameters = bodyParameters;
        this.urlParameters = urlParameters;
    }

    public PreconditionRequest(Methods method, String host, String endpoint, String authorizationToken, String bodyParameters){
        this.method = method;
        this.host = host;
        this.endpoint = endpoint;
        this.url = host.concat(endpoint);
        this.authorizationToken = authorizationToken;
        this.bodyParameters = bodyParameters;
    }

    public PreconditionRequest(Methods method, String host, String endpoint, String authorizationToken, HashMap<String, String> urlParameters){
        this.method = method;
        this.host = host;
        this.endpoint = endpoint;
        this.url = host.concat(endpoint);
        this.authorizationToken = authorizationToken;
        this.urlParameters = urlParameters;
    }

    public Methods getMethod() { return  method; }

    public String getUrl() { return url; }

    public String getHost() { return host; }

    public String getEndpoint() { return endpoint; }

    public String getBodyParameters() { return bodyParameters; }

    public HashMap<String, String> getUrlParameters() { return urlParameters; }

    public String getAuthorizationToken() { return authorizationToken; }

    public boolean hasBodyParameters() { return !Strings.isStringEmpty(bodyParameters);  }

    public boolean hasUrlParameters() { return urlParameters != null && !urlParameters.isEmpty(); }

    public boolean authorizationRequired() { return !Strings.isStringEmpty(authorizationToken);  }

    public static final class Builder {
        private Methods requestMethod;
        private String requestHost;
        private String requestEndpoint;
        private String requestAuthorization;
        private String requestBodyParameters;
        private HashMap<String, String> requestUrlParameters;

        public Builder() {
            requestUrlParameters = new HashMap<>();
        }

        public Builder method(Methods method) {
            requestMethod = method;
            return this;
        }

        public Builder host(String host) {
            requestHost = host;
            return this;
        }

        public Builder endpoint(String endpoint) {
            requestEndpoint = endpoint;
            return this;
        }

        public Builder authorization(String authorization) {
            requestAuthorization = authorization;
            return this;
        }

        public Builder bodyParameters(String parameters) {
            requestBodyParameters = parameters;
            return this;
        }

        public Builder urlParameter(String parameterName, String parameterValues) {
            requestUrlParameters.put(parameterName, parameterValues);
            return this;
        }

        public PreconditionRequest build() {
            if (requestUrlParameters != null && !requestUrlParameters.isEmpty() ) {
                if (requestBodyParameters != null && !requestBodyParameters.isEmpty())
                    return new PreconditionRequest(requestMethod, requestHost, requestEndpoint, requestAuthorization, requestBodyParameters, requestUrlParameters);
                else return new PreconditionRequest(requestMethod, requestHost, requestEndpoint, requestAuthorization, requestUrlParameters);
            }
            else return new PreconditionRequest(requestMethod, requestHost, requestEndpoint, requestAuthorization, requestBodyParameters);
        }
    }
}
