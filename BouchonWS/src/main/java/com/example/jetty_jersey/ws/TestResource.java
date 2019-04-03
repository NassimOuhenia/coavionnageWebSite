package com.example.jetty_jersey.ws;
/*
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import static org.elasticsearch.common.xcontent.XContentFactory.*;


@Path("/test")*/
public class TestResource {/*
	TransportClient client;

	public TestResource() {
		try {
			TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
					.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
			this.client = client;

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@GET
	@Path("/insert/{id}")
	public String insert(@PathParam("id") final String id) throws IOException{
		IndexResponse response = client.prepareIndex("pilote", "id", id)
				.setSource(jsonBuilder()
						.startObject()
						.field("name", "pilote1")
						.field("avion", "avion1")
						.field("experience", 1000)
						.endObject()
						)
				.get();

		return response.getResult().toString();
	}

	@GET
	@Path("/view/{id}")
	public Map<String, Object> view(@PathParam("id") final String id){
		GetResponse getResponse = client.prepareGet("pilote", "id", id).get();

		return getResponse.getSource();
	}

	@GET
	@Path("/update/{id}")
	public String update(@PathParam("id") final String id) throws IOException {
		UpdateRequest updateRequest = new UpdateRequest("pilote", "id", id)
				.doc(jsonBuilder()
						.startObject()
						.field("Diplome", "Transport Niveau 2")
						.endObject());
		UpdateResponse updateResponse;
		try {
			updateResponse = client.update(updateRequest).get();
			return updateResponse.getResult().toString();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Exception";
	}

	@GET
	@Path("/delete/{id}")
	public String dekete(@PathParam("id") final String id) {
		DeleteResponse deleteResponse = client.prepareDelete("pilote", "id", id).get();
		return deleteResponse.getResult().toString();
	}
*/
}
