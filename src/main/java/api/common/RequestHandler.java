package api.common;

import model.header.RequestContext;

public abstract class RequestHandler<REQ extends AbstractRequest, RES extends AbstractResponse>  {
    public abstract RES handle(RequestContext requestContext, REQ request);
}
