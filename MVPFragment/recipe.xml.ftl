<?xml version="1.0"?>
<recipe>
    <#include "fragment_layout_recipe.xml.ftl" />

    <instantiate from="src/app_package/classes/Fragment.java.ftl"
          to="${escapeXmlAttribute(srcOut)}/${fragmentClass}.java" />

        <instantiate from="src/app_package/classes/Contract.java.ftl"
              to="${escapeXmlAttribute(srcOut)}/${contractClass}.java" />

        <instantiate from="src/app_package/classes/Presenter.java.ftl"
              to="${escapeXmlAttribute(srcOut)}/${presenterClass}.java" />

</recipe>
