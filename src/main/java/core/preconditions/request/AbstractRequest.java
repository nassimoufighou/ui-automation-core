package core.preconditions.request;

import com.beust.jcommander.Strings;
import core.preconditions.restActions.Methods;

import java.util.HashMap;

public abstract class AbstractRequest {

    protected Methods method;
    protected String url;
    protected String host;
    protected String endpoint;
    protected String authorizationToken;
    protected String bodyParameters;
    protected HashMap<String, String> urlParameters =  new HashMap<>();

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
}
