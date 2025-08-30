package com.nielsen.mcp.server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;


@Component
public class ESSService {

  @Autowired
  private HttpServletRequest httpServletRequest;


  @Tool(name = "my_assets", description = "Get my assets")
  public List<AssetToAssetMap> getAllPackages(
      @ToolParam(description = "authToken") String authToken, ToolContext toolContext) {
    System.out.println(httpServletRequest.getHeader("X-NIQ-TOKEN"));
    System.out.println(authToken);
    System.out.println(toolContext.getContext().get("exchange"));
    System.out.println("-------------- Tool Call --------------------------------------");
    System.out.println(Thread.currentThread().getName());
    Map<String, List<AssetToAssetMap>> assetMapFromDb =
        new HashMap<String, List<AssetToAssetMap>>();
    assetMapFromDb.put("ismail",
        Arrays.asList(new AssetToAssetMap("Ismail Demograhic  Asset 1", "Report"),
            new AssetToAssetMap("Ismail Demograhic  Asset 1", "Dashboard")));
    assetMapFromDb.put("aakash",
        Arrays.asList(new AssetToAssetMap("Aakash Geo  Asset 1", "Template"),
            new AssetToAssetMap("Aakash Demograhic  Asset 1", "Dashboard")));
    assetMapFromDb.put("shilpa", Arrays.asList(new AssetToAssetMap("Shilpa Geo  Asset 1", "Story"),
        new AssetToAssetMap("Shilpa Geo Demograhic  Asset 1", "Collection")));
    return assetMapFromDb.get(authToken);
  }

  @Tool(name = "execute_query", description = "Execute Query")
  public String executeQuery(@ToolParam String message,
      @ToolParam(description = "authToken") String authToken) throws SQLException, IOException {
    return "The query is :" + message;
  }


  public static class AssetToAssetMap {
    private String assetName;
    private String assetType;

    public AssetToAssetMap(String assetNameParam, String assetTypeParam) {

      this.assetName = assetNameParam;
      this.assetType = assetTypeParam;
    }

    public String getAssetName() {
      return assetName;
    }

    public String getAssetType() {
      return assetType;
    }



  }

  // @Tool(name = "All Packages by OrgId", description = "Get the all packages for ")
  List<OrgPackages> getAllPackagesForOrgId(
      @ToolParam(description = "organization id") String orgId) {
    OrgPackages orgPackage1 = new OrgPackages();
    orgPackage1.setOrgId(orgId);
    orgPackage1.setPackageIds("cokePackage");
    return Arrays.asList(orgPackage1);
  }

  // @Tool(name = "Get everyt", description = "Get everyt")
  String getAllPAckages() {
    return "hello";
  }

  // @Tool(name = "Get Nothing", description = "Nothing")
  String getNothing() {
    return "nothing";
  }

  public static class OrgPackages {
    private String orgId;
    private String packageIds;

    public String getOrgId() {
      return orgId;
    }

    public void setOrgId(String orgId) {
      this.orgId = orgId;
    }

    public String getPackageIds() {
      return packageIds;
    }

    public void setPackageIds(String packageIds) {
      this.packageIds = packageIds;
    }


  }
}
