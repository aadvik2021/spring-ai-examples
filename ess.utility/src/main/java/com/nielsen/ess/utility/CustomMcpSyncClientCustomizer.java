package com.nielsen.ess.utility;

import org.springframework.ai.mcp.customizer.McpSyncClientCustomizer;
import org.springframework.stereotype.Component;

import io.modelcontextprotocol.client.McpClient.SyncSpec;

@Component
public class CustomMcpSyncClientCustomizer implements McpSyncClientCustomizer{

	@Override
	public void customize(String name, SyncSpec spec) {
		spec.toolsChangeConsumer(t -> {
			t.stream().forEach(u -> {
				System.out.println("change tools "+ u );
			});
		});
	}

}
