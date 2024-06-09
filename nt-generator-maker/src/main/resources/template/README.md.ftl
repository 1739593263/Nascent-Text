# ${mainTemplate.name}
> ${mainTemplate.description}
> 
> Author: ${mainTemplate.author}
> 
> Originate from yuzi-code-generator.

Supporting  dynamical generating codes by CLI shell.

## Instruction

Run script under the root directory:
```bash
generator <Commend> <Option> <Parameters>
```

Example:
```bash
generator generate<#list mainTemplate.modelConfig.models as modelInfo><#if modelInfo.groupKey??><#list modelInfo.models as subModelInfo>-${subModelInfo.abbr}</#list><#else>-${modelInfo.abbr}</#if></#list>
```

## Parameters
<#list mainTemplate.modelConfig.models as modelInfo>
<#if modelInfo.groupKey??>
<#list modelInfo.models as subModelInfo>
${modelInfo?index+subModelInfo?index+1} )  ${subModelInfo.fieldName}

   Type: ${subModelInfo.type}

   Description: ${subModelInfo.description}

   Default: ${subModelInfo.defaultValue?c}

   Abbreviate: ${subModelInfo.abbr}

</#list>
<#else>
${modelInfo?index+1} )  ${modelInfo.fieldName}

   Type: ${modelInfo.type}

   Description: ${modelInfo.description}

   Default: ${modelInfo.defaultValue?c}

   Abbreviate: ${modelInfo.abbr}

</#if>
</#list>