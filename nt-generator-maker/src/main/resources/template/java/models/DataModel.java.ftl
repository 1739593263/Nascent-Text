package ${mainTemplate.basePackage}.models;

import lombok.Data;

<#macro generateModel indent modelInfo>
<#if modelInfo.description??>
${indent}/**
${indent} * ${modelInfo.description}
${indent} */
</#if>
${indent}public ${modelInfo.type} ${modelInfo.fieldName}<#if modelInfo.defaultValue??> = ${modelInfo.defaultValue?c}</#if>;
</#macro>

@Data
public class DataModel {
<#list mainTemplate.modelConfig.models as modelInfo>
    <#-- Group Model -->
    <#if modelInfo.groupKey??>
    /**
     * ${modelInfo.groupName}
     */
    public ${modelInfo.type} ${modelInfo.groupKey} <#if modelInfo.defaultValue??> = new ${modelInfo.type}()</#if>;

    /**
     * ${modelInfo.description}
     */
    @Data
    public static class ${modelInfo.type} {
    <#list modelInfo.models as modelInfo>
        <@generateModel indent="        " modelInfo=modelInfo/>
    </#list>
    }
    <#else>
    <#-- No Group Model -->
    <@generateModel indent="    " modelInfo=modelInfo/>
    </#if>
</#list>
}
