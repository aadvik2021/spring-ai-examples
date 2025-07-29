package com.example.MCPStdio;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;
import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.transport.ServerParameters;
import io.modelcontextprotocol.client.transport.StdioClientTransport;
import io.modelcontextprotocol.spec.McpSchema.CallToolRequest;
import io.modelcontextprotocol.spec.McpSchema.CallToolResult;
import io.modelcontextprotocol.spec.McpSchema.ListToolsResult;

@Service
public class AssetCatalogService {

  @Tool(description = "Get the all reports which are run by dataset")
  public List<String> getAllReportsByDatasetId(@ToolParam String datasetId) {
    return Arrays.asList("Demographic" + datasetId, "Consumer" + datasetId);
  }

  public static void main(String[] args) {
    ServerParameters stdioParams = ServerParameters.builder("java").args("-jar",
        "C:\\Spring AI Learning\\spring-ai-examples\\MCPStdio\\build\\libs\\MCPStdio-0.0.1-SNAPSHOT.jar")
        .build();
    var stdioTransport = new StdioClientTransport(stdioParams);
    var mcpClient = McpClient.sync(stdioTransport).build();

    mcpClient.initialize();

    ListToolsResult toolsList = mcpClient.listTools();

    CallToolResult assetCatalog = mcpClient
        .callTool(new CallToolRequest("getAllReportsByDatasetId", Map.of("datasetId", "1234")));

    System.out.println(assetCatalog.content());

    mcpClient.closeGracefully();
  }

}
