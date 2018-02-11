### 第一行代码第一章—你的第一行Android代码

### 知识点目录

> + **1.1 了解全貌——Android王国简介**
	* 1.1.1 Android系统架构
	* 1.1.2 Android已发布的版本
	* 1.1.3 Android应用开发特色  
	
> + **1.2 手把手带你搭建开发环境**
	* 1.2.1 准备所需要的工具
	* 1.2.2 搭建开发环境

> + **1.3 创建你的第一个Android项目**
	* 1.3.1 创建HelloWorld项目
	* 1.3.2 启动模拟器
	* 1.3.3 运行HelloWorld
	* 1.3.4 分析你的第一个Android程序
	* 1.3.5 详解项目中的资源
	* 1.3.6 详解build.gradle文件

> + **1.4 前行必备——掌握日志工具的使用**
	* 1.4.1 使用Android的日志工具
	* 1.4.2 为什么使用Log而不使用System.out

> + **1.5 小结与点评**

### 知识点回顾

#### 1.1 了解全貌——Android王国简介

> 无知识点

##### 1.1.1 Android系统架构

* **Linux内核层**
	
	为Android设备的各种硬件提供底层的驱动

* **系统运行库层**

	通过一些C/C++库为Android系统提供特性支持；还有Android运行时的库。

* **应用框架层**

	提供了构建应用程序时可能用到的各种API。

* **应用层**

	安装在手机上的应用程序

##### 1.1.2 Android已发布的版本

最新数据可以访问如下网址：

https://developer.android.google.cn/about/dashboards/index.html

##### 1.1.3 Android应用开发特色

* 四大组件

	Activity、Service、BroadcastReceiver、ContentProvider

* 丰富的系统控件

* SQLite数据库

* 强大的多媒体

* 地理位置地位

* and so on

#### 1.2 手把手带你搭建开发环境

> 无知识点

##### 1.2.1 准备所需要的工具

能翻墙的前提下，到Android官网下载开发工具

https://developer.android.google.cn/studio/index.xml

如果不能翻墙，则到下面的网址去下载。

http://www.androiddevtools.cn/

##### 1.2.3 搭建开发环境

下载Android Studio后，根安装普通软件一样，一路Next即可。

#### 1.3 创建你的第一个Android项目

> 无知识点

##### 1.3.1 创建HelloWorld项目。

1. 打开Android Studio，进入欢迎界面。
![AS的欢迎界面](http://img.blog.csdn.net/20171212163454976?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

2. 点击Start a new Android Studio project，进入创建新项目界面
![创建新项目](http://img.blog.csdn.net/20171212163728213?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

3.  进入设置项目的最低兼容版本
![设置项目的最低兼容版本](http://img.blog.csdn.net/20171212163939298?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

4. 进入选择模板
![选择模板](http://img.blog.csdn.net/20171212164035418?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

5. 给活动和布局命名
![给活动和布局命名](http://img.blog.csdn.net/20171212164123464?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

6. 点击Finish按钮，进入到项目创建成功界面。

##### 1.3.2 启动模拟器

点击下图中最左边的按钮就可以创建和启动模拟器。

![创建和启动模拟器](http://img.blog.csdn.net/20171212164834386?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

但个人觉得Android Studio原生的模拟器不是很好用，所以我平时选用的是第三方的genymotion模拟器。

genymotion下载网址如下：

http://www.genymotion.net/

需要先注册。

##### 1.3.3 运行HelloWorld

* 启动模拟器

* 点击顶部工具栏中的运行按钮

* 在Select Deployment Target选中模拟器，点击OK按钮。

##### 1.3.4 分析你的第一个Android程序

将项目结构模式切换成Project

![Project模式的项目结构](http://img.blog.csdn.net/20171212165047504?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

下面介绍整个项目的外层目录结构。

* **.gradle和.idea**

	Android Studio自动生成的文件，无需关心

* **app**

	项目的代码和资源文件

* **build**

	包含编译时自动生成的文件

* **gradle**

	包含gradle wrapper的配置文件

* **.gitgnore**

	将指定的目录或文件排除在版本控制之外

* **build.gradle**

	项目全局的gradle构建脚本，一般都不需要修改

* **gradle.properties**

	全局的gradle配置文件，直接影响项目中所有的gradle编译脚本

* **gradlew和gradlew.bat**

	用来在命令行中执行gradle命令，其中gradlew是在Linux或Mac系统中使用的，gradlew.bat是在Windows系统中使用。

* **HelloWorld.xml**

	iml文件是所有IntelliJ IDEA项目都会自动生成的一个文件，用于标识这是一个IntelliJ IDEA项目，无需修改该文件中的任何内容。

* **local.properties**

	用于指定本机中SDK的路径，自动生成，无需修改。

* **settings.gradle** 

	指定项目中所有引入的模块

展开app目录：

![app目录](http://img.blog.csdn.net/20171212165304948?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

下面详细介绍下app目录下的内容

* **build**

	编译时自动生成的文件，无需关心

* **libs**

	存放第三方jar包，放在该目录下的jar包会自动添加到构建路径中去。

* **androidTest**

	编写Android Test测试用例，对项目进行一些自动化测试。

* **Java**

	存放所有Java代码

* **res**

	存放项目中所有图片、布局、字符串等资源文件

* **AndroidManifest.xml**

	Android项目配置文件

* **test**

	编写Unit Test 测试用例，是对项目进行自动化测试的另一种方式

* **.gitgnore**

	将app模块内指定的目录排除在版本控制之外

* **app.iml**

	IntelliJ IDEA项目自动生成的文件

* **build.gradle**

	app模块的gradle构建脚本

* **proguard-rules.pro**

	指定项目代码的混淆规则

##### 1.3.5 详解项目中的资源文件

> 无知识点

##### 1.3.6 详解build.gradle文件

最外层目录下的build.gradle文件，代码如下：

	buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.3.3'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    	}
	}

	allprojects {
	    repositories {
	        jcenter()
	    }
	}

	task clean(type: Delete) {
	    delete rootProject.buildDir
	}

这些代码都是自动生成的，通常情况下不需要修改这个文件中的内容。两处repositories闭包中都声明了jcenter()，jcenter是一个代码托管仓库，从而使得我们在项目中可以引用任何jcenter上的开源项目。dependencies闭包中使用classpath声明了一个Gradle插件，因为Gradle并不是专门为构建Android项目而开发的，Java、C++等很多种项目都可以使用Gradle来构建。如果想要使用Gradle来构建Android项目，则需要声明com.android.tools.build:gradle:2.3.3这个插件，其中，后面是插件的版本号。

app目录下的build.gradle文件，代码如下：


	apply plugin: 'com.android.application'

	android {
	    compileSdkVersion 26
	    buildToolsVersion "27.0.0"
	    defaultConfig {
	        applicationId "com.example.helloworld"
	        minSdkVersion 15
	        targetSdkVersion 26
	        versionCode 1
	        versionName "1.0"
	        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
	    }
	    buildTypes {
	        release {
	            minifyEnabled false
	            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
	        }
	    }
	}

	dependencies {
	    compile fileTree(dir: 'libs', include: ['*.jar'])
	    compile 'com.android.support:appcompat-v7:26.+'
	    testCompile 'junit:junit:4.12'
	}

首先第一行应用了一个插件，一般有两种值可选：com.android.application表示这是一个应用程序模块；com.android.library表示是一个库模块。应用程序模块是可以直接运行的，库模块只能作为代码库附于在别的应用程序模块来运行。

**android闭包：**

compileSdkVersion：指定项目的编译版本。

buildToolsVersion：指定项目构建工具的版本。

**defaultConfig闭包：**

applicationId：指定项目的包名

minSdkVersion：指定项目最低兼容的Android系统版本

targetSdkVersion：指定项目的目标版本

versionCode：指定项目的版本号

 versionName：指定项目的版本名

**buildTypes闭包：**

通常只会有两个子闭包：debug和release。其中debug闭包可以忽略不写。debug闭包用于指定生成测试版安装文件的配置；release闭包用于指定生成正式版安装文件的配置。

**release闭包**

minifyEnabled：指定是否对项目的代码进行混淆。

proguardFiles：指定混淆时使用的规则文件。proguard-android.txt是在Android SDK目录下，里面是所有项目通用的混淆规则；proguard-rules.pro是在当前项目的根目录下，里面可以编写当前项目特有的混淆规则。

**dependencies闭包**

指定当前项目所有的依赖关系。

compile fileTree：声明本地依赖，表示将libs目录下所有.jar后缀的文件都添加到项目的构建路径中。

compile：声明远程依赖。

testCompile：声明测试用例库。

#### 1.4 前行必备——掌握日志工具的使用

> 无知识点

##### 1.4.1 使用Android的日志工具Log

* Log.v()：打印最为琐碎的、意义最小的日志信息。(verbose)

* Log.d()：打印调试信息。(debug)

* Log.i()：打印重要的日志信息。(info)

* Log.w()：打印一些警告信息。(warn)

* Log.e()：打印程序中的错误信息。(error)

日志等级由低到高：v -> d -> i -> w -> e

**运行程序的快捷键**

Windows : Shift + F10

Mac : control + R	

##### 1.4.2 为什么使用Log而不使用System.out

* logcat中能添加过滤器

* 可以控制日志级别

#### 1.5 小结与点评

> 无知识点
	        