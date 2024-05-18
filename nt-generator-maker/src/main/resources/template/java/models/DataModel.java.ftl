package ${mainTemplate.basePackage}.models;

import lombok.Data;

@Data
public class DataModel {
<#list mainTemplate.modelConfig.models as modelInfo>
    <#if modelInfo.description??>
    /**
     * ${modelInfo.description}
     */
    </#if>
    public ${modelInfo.type} ${modelInfo.fieldName} <#if modelInfo.defaultValue??> = ${modelInfo.defaultValue?c}</#if>;
</#list>
}
