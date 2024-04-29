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
generator generate<#list mainTemplate.modelConfig.models as modelInfo> -${modelInfo.abbr}</#list>
```

## Parameters
<#list mainTemplate.modelConfig.models as modelInfo>

${modelInfo?index+1} )  ${modelInfo.fieldName}

   Type: ${modelInfo.type}

   Description: ${modelInfo.description}

   Default: ${modelInfo.defaultValue?c}

   Abbreviate: ${modelInfo.abbr}
</#list>