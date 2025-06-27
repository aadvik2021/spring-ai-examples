package com.nielsen.mcp.server;

import java.util.Arrays;
import java.util.List;

import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.mcp.McpToolUtils;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;


@Component
public class ESSService {

    @Tool(name="All Packages" , description = "Get the all packages")
    List<OrgPackages> getAllPackages(ToolContext toolContext) {
    McpToolUtils.getMcpExchange(toolContext);
	OrgPackages orgPackage1 = new OrgPackages();
	orgPackage1.setOrgId("orgId1");
	orgPackage1.setPackageIds("pck1");
	
	OrgPackages orgPackage2= new OrgPackages();
	orgPackage2.setOrgId("orgId2");
	orgPackage2.setPackageIds("pck2");
	
    return Arrays.asList(orgPackage1,orgPackage2);
    }
    
    @Tool(name="All Packages by OrgId", description = "Get the all packages for ")
    List<OrgPackages> getAllPackagesForOrgId(@ToolParam(description = "organization id") String orgId) {
	OrgPackages orgPackage1 = new OrgPackages();
	orgPackage1.setOrgId(orgId);
	orgPackage1.setPackageIds("cokePackage");
    return Arrays.asList(orgPackage1);
    }
    
    @Tool(name="Get everyt", description = "Get everyt")
    String getAllPAckages() {  
       return "hello";
    }
    
    @Tool(name="Get Nothing", description = "Nothing")
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
