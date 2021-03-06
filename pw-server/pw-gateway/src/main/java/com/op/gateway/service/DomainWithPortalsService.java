package com.op.gateway.service;

import com.op.gateway.client.DomainClient;
import com.op.gateway.client.PortalClient;
import com.op.gateway.to.Domain;
import com.op.gateway.to.DomainWithPortals;
import com.op.gateway.to.Portal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.Environment;
import reactor.rx.Stream;
import reactor.rx.Streams;

@Component
public class DomainWithPortalsService {
	@Autowired
	private Environment environment;

	@Autowired
	private DomainClient domainClient;

	@Autowired
	private PortalClient portalClient;

    public Stream<Portal> getPortals(Long domainId) {
        return Streams.<Portal> create(subscriber -> {
            this.portalClient.getPortals(domainId).forEach(subscriber::onNext);
            subscriber.onComplete();
        }).dispatchOn(this.environment, Environment.cachedDispatcher()).log("portals");
    }

    public Stream<Domain> getDomain(Long domainId) {
        return Streams.<Domain> create(subscriber -> {
            subscriber.onNext(this.domainClient.getDomain(domainId));
            subscriber.onComplete();
        }).dispatchOn(this.environment, Environment.cachedDispatcher()).log("domain");
    }

	public Stream<DomainWithPortals> getDomainWithPortals(Stream<Domain> domain, Stream<Portal> portals) {
		return Streams.zip(domain.buffer(), portals.buffer(), tuple ->
			new DomainWithPortals(tuple.getT1().get(0), tuple.getT2()));
	}
}
