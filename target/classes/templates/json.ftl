{
    <#if message?? >
        "message" : "${message}",
    </#if>
    <#if error?? >
        "error" : "${error}"
    <#else>
        "error" : ""
    </#if>
}