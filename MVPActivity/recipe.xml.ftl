<?xml version="1.0"?>
<recipe>
    <merge from="AndroidManifest.xml.ftl" to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />

    <#include "activity_layout_recipe.xml.ftl" />

    <instantiate from="src/app_package/classes/Activity.java.ftl"
      to="${escapeXmlAttribute(srcOut)}/${activityClass}.java" />

    <instantiate from="src/app_package/classes/Contract.java.ftl"
          to="${escapeXmlAttribute(srcOut)}/${contractClass}.java" />

    <instantiate from="src/app_package/classes/Presenter.java.ftl"
          to="${escapeXmlAttribute(srcOut)}/${presenterClass}.java" />

</recipe>
