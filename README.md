## 简介 
   <h3 align="left">Fastgo 能够在瞬间创建一个MVP+Dagger2+Retrofit+RxJava的Android Project。</h3>
   
   Fastgo 确切的说是在多个项目中提炼出来的一个开发思想，简化创建新项目中做的重复步骤，让开发者专注业务开发。<br/>
   
   Fastgo 能做什么事情？<br/>
   
   1、能够快速搭建一个MVP思想的代码目录及基础类<br/>
   2、创建Activity 、Fragment时生成相对应的Contact和Presenter<br/>
   3、全局生命周期代理包含Application、Activity、Fragment<br/>
   4、简单的Android屏幕碎片化方案<br/>
   5、优化Dagger2使用流程，降低使用的学习成本<br/>
   6、集合了众多优秀第三方库，开发者可以自行配置引入使用<br/>
   7、。。。<br/>
   
## 使用 
```
Gradle:
compile 'me.walten:fastgo:1.0.8'

Maven:
<dependency>
  <groupId>me.walten</groupId>
  <artifactId>fastgo</artifactId>
  <version>1.0.7</version>
  <type>pom</type>
</dependency>

```
1、创建一个空项目
![1](screenshot/1.png)<br/>
![2](screenshot/2.png)<br/>
2、拷贝config.gralde(按需引入你需要的第三方库)到你的项目中
![3](screenshot/3.png)<br/>
3、执行安装脚本
![4](screenshot/4.png)<br/>
4、接下来就可以创建啦
![5](screenshot/5.png)<br/>
![6](screenshot/6.png)<br/>
![7](screenshot/7.png)<br/>
![8](screenshot/8.png)<br/>
![9](screenshot/9.png)<br/>

## 更新
* 2018-06-15 框架首次发布
* 2018-06-21 修复使用BuildConfig.debug 一直都为false 无法打印日志的的bug，并优化APPModel模板

## 打赏
<div align="center">
   <img src="screenshot/weixin.png" height="360" width=300" >
   
   <img src="screenshot/alipay.png" height="360" width="300" >
</div>

## 关于我
* **Email**: <wtf55@vip.qq.com>  

## License
``` 
 Copyright 2018, walten       
  
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at 
 
       http://www.apache.org/licenses/LICENSE-2.0 

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
