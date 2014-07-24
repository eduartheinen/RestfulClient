package com.utfpr.restfulclient.helper;

import org.codehaus.jackson.map.ObjectMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class RestConnectionHelper {

	protected Client client;
	protected ObjectMapper mapper = new ObjectMapper();
	protected WebResource webResource;

	public RestConnectionHelper() {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);
		client = Client.create(clientConfig);
		webResource = client.resource("http://localhost:8080/rest-service");
	}

//	public Cluster getCluster(String name) {
//		return webResource.path("cluster").path(name)
//				.accept(MediaType.APPLICATION_JSON).get(Cluster.class);
//	}
//
//	public ClientResponse createCluster(Cluster cluster) {
//		return webResource.path("cluster").type(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON)
//				.post(ClientResponse.class, cluster);
//	}
//
//	public Cluster saveCluster(Cluster cluster) {
//		ClientResponse cr = webResource.path("cluster").path(cluster.getName())
//				.type(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON)
//				.post(ClientResponse.class, cluster);
//		return cr.getEntity(Cluster.class);
//	}
//
//	public ClientResponse deleteCluster(Cluster cluster) {
//		return webResource.path("cluster").path(cluster.getName())
//				.type(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON)
//				.delete(ClientResponse.class);
//	}
//
//	public Map<String, Cluster> getClusters() {
//		try {
//			String json = webResource.path("cluster")
//					.accept(MediaType.APPLICATION_JSON).get(String.class);
//			return mapper.readValue(json,
//					new TypeReference<Map<String, Cluster>>() {
//					});
//		} catch (IOException e) {
//		}
//		return new HashMap<String, Cluster>();
//	}
}