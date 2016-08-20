<#include "../layouts/base.ftl">

<#assign title = "Hello, world">

<#macro page_content>
Hello ${context.name}!
Request path is ${context.request().path()}
</#macro>

<@display_page/>
