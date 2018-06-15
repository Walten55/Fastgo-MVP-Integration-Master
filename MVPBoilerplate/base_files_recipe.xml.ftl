<recipe>
	<instantiate from="src/app_package/classes/AppDelegateImpl.java.ftl"
       to="${escapeXmlAttribute(srcOut)}/application/AppDelegateImpl.java" />

    <instantiate from="src/app_package/classes/GlobalConfiguration.java.ftl"
       to="${escapeXmlAttribute(srcOut)}/configuration/GlobalConfiguration.java" />

    <instantiate from="src/app_package/classes/ActivityComponent.java.ftl"
           to="${escapeXmlAttribute(srcOut)}/di/component/ActivityComponent.java" />

    <instantiate from="src/app_package/classes/FragmentComponent.java.ftl"
               to="${escapeXmlAttribute(srcOut)}/di/component/FragmentComponent.java" />

    <instantiate from="src/app_package/classes/InterceptorComponent.java.ftl"
               to="${escapeXmlAttribute(srcOut)}/di/component/InterceptorComponent.java" />

    <instantiate from="src/app_package/classes/ActivityModule.java.ftl"
               to="${escapeXmlAttribute(srcOut)}/di/module/ActivityModule.java" />

    <instantiate from="src/app_package/classes/FragmentModule.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/di/module/FragmentModule.java" />

    <instantiate from="src/app_package/classes/APPModel.java.ftl"
                       to="${escapeXmlAttribute(srcOut)}/model/APPModel.java" />

    <merge from="AndroidManifest.xml.ftl" to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />

</recipe>
