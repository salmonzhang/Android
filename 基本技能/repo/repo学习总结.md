#### 1. repo简介

repo是Google开发的用于管理Android版本库的一个工具，repo是使用Python对git进行了一定的封装，并不是用于取代git，它简化了对多个Git版本库的管理。用repo管理的版本库都需要使用git命令来进行操作。

##### 1.1 介绍清单库文件

清单文件是以xml的格式组织的，一个清单库可以包含多个清单文件和多个分支，每个清单文件和分支都有对应的版本。

* remote元素

	定义了名为korg的远程版本库，库的基址为git://172.16.1.31

* project元素

	用于定义一个项目，path属性表示在工作区clone的位置，name属性表示该项目的远程版本库的相对路径。

* default元素

	设置各个项目默认远程版本库为Korg，默认的分支为gingerbread-exdroid-stable。各个项目还可以定义自己的remote和revision覆盖默认的配置。

* project元素下的子元素copyfile

	定义了项目clone后的一个附件动作，从src拷贝文件到dest

##### 1.2 下载repo代码

* 新建项目存放的文件夹

	mkdir mstar648

* 进入到项目文件夹

	cd mstar648

* 执行代码拉取的命令 

	    repo init -u ssh://ppgerrit.com/Mstar648/manifest.git -b 648_cultraview -m ppos4.5.0_cultraview.xml

##### 1.3 探究repo文件夹

* 通过如下命令查看mstar648目录下

		ls -a

![这里写图片描述](http://img.blog.csdn.net/20180127134301870?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

可以看到目录下有个.repo文件夹，说明mstar648项目是一个用repo管理的版本库。

* 进入到.repo，并查看目录内容

![这里写图片描述](http://img.blog.csdn.net/20180127134254049?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

可以看到.repo目录下有manifest、project、repo文件夹等信息。

如果想查看Python封装的脚本，可到.repo/repo下看到：
![这里写图片描述](http://img.blog.csdn.net/20180127135024043?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

如果想查看某个模块当前是在哪个分支，可以进入到manifests中去：

![这里写图片描述](http://img.blog.csdn.net/20180127134328480?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

因为我们刚拉取代码的时候是ppos4.5.0_cultraview.xml，所以通过vim打开ppos4.5.0_cultraview.xml，然后可以通过

	/模块名

查看到模块的分支信息。例如我们要看到PposTv的分支信息，就可以搜索/PposTv

#### 2. repo常用命令

##### 2.1 解析拉取代码的命令

例如我们拉取代码的时候的命令如下：

	repo init -u ssh://ppgerrit.com/Mstar648/manifest.git -b 648_cultraview -m ppos4.5.0_cultraview.xml

其中：

* -u：指定一个URL，其连接到一个manifest仓库

* -b：选择manifest仓库中的一个特殊分支

* -m：在manifest仓库中选择一个xml文件

repo init要完成如下操作：

* 完成repo工具的完整下载，执行的repo脚本只是引导程序

* clone清单库manifest.git （地址是-u后面的参数）

* clone的清单库位于manifest.git中，clone到本地.repo/manifest中，.repo/manifest.xml只是符号链接，它指向的是.repo/manifests/default.xml

* 如果manifest中有多个xml文件，repo init可以任意选择其中一个，默认选择的是default.xml。

上面的拉取代码示例选择的是ppos4.5.0_cultraview.xml里面的配置，那么.repo/manifest.xml指向的是.repo/manifests/ppos4.5.0_cultraview.xml

##### 2.2 同步代码（repo sync）

执行了repo init 命令后，我们需要执行如下命令同步代码：

	repo sync

参照清单文件.repo/manifest.xml克隆并同步版本库。如果项目版本库不存在，则执行repo sync命令相当于执行git clone；如果项目版本库存在，则相当于执行下面两条指令：

* git remote update

	相当于对每一个remote源执行了fetch操作

* git rebase origin/branch

	对当前分支的跟踪分支执行rebase操作

##### 2.3 创建并切换分支（repo start）

刚clone下来的代码是没有分支的，例如：

![这里写图片描述](http://img.blog.csdn.net/20180127135156325?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

repo start 的实质就是对git checkout -b 的封装，可以为单个项目或所有项目以清单文件中已设定的分支为基础，在本地创建新的分支。

repo start 与 git checkout -b 的区别：

* repo start 是在清单文件设定的分支基础上创建新的分支

* git checkout -b 是在当前所在分支的基础上创建新的分支

如果清单文件中设定的分支是remoteBranchName，创建新的分支localBranchName。

* 为单个项目创建localBranchName分支的命令如下：

		repo start localBranchName 项目绝对路径

* 为所有项目创建localBranchName分支的命令如下：

		repo start localBranchName --all

##### 2.4 查看分支（repo branches）

**本文下面所有的命令都是在单个项目中运行！！！**

例如查看上面拉取的mstar648项目下各模块的分支信息

在mstar648项目下运行如下命令：

		repo branches

![这里写图片描述](http://img.blog.csdn.net/20180127135230640?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

##### 2.5 切换分支（repo checkout）

切换分支语法：

	repo checkout brancnName

![这里写图片描述](http://img.blog.csdn.net/20180127135258275?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

##### 2.6 查看工作区文件的差异（repo diff）

查看文件差异语法：

		repo diff 

##### 2.7 查看文件状态（repo status）

该命令实际上是对git diff-index 和git diff-filse命令的封装，同时显示暂存区的状态和本地文件修改的状态

查看文件状态语法：

		repo status

![这里写图片描述](http://img.blog.csdn.net/20180127135319653?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

其中：

* 每个小节的第一行显示的是项目名称和所在的分支名称

* 每个小节的第二行的第一个字母表示暂存区文件修改的状态

	*  -：没有改变
	
	* A:添加（不在HEAD中，在暂存区中）

	* M：修改（在HEAD中，在暂存区中，内容不同）

	* D:删除（在HEAD中，不在暂存区）

	* R：重命名（不在HEAD中，在暂存区，路径修改）

	* C：拷贝（不在HEAD中，在暂存区，从其他文件拷贝）

	* T：文件状态改变（在HEAD中，在暂存区，内容相同）

	* U：未合并，需要冲突解决

* 每个小节的第二行的第二个字母表示工作区文件的更改状态

	* -：新/未知（不在暂存区，在工作区）

	* m：修改（在暂存区，在工作区，被修改）

	*  d：删除（在暂存区，不在工作区

##### 2.8 删除指定分支（repo abandon）

该命令实质是对git branch -D的封装，语法如下：

		repo abandon branchName

##### 2.9 删除已经合并分支（repo prune）

该命令实质是对git branch -d的封装，用于扫描项目的各个分支，并删除已经合并的分支，语法如下：

		repo prune

##### 2.10 将文件添加到index表中（repo stage）

该命令实质是对git add --interactive命令的封装，用于将项目工作区中的改动添加到暂存区，语法如下：

		repo stage -i 

其中，-i表示 --interactive，给出个界面让用户选择
		
##### 2.11 设置远程仓库（repo remote）

语法如下：

		repo remote addd remoteName url

例如：

		repo remote add org ssh://172.16.1.31/git_repo

该命令是根据xml文件添加的远程分支，方便于向服务器提交代码，执行后的build目录下看到新的远程分支是org

##### 2.12 遍历当前项目下的所有git仓库（repo forall）

该命令相当于一个迭代器，会遍历当前项目下所有的git仓库，在所有指定的项目中执行同一个shell命令。

语法如下：

		repo forall -c 命令

其中，参数有：

* -c：后面可以带的任何可以被系统支持的shell命令（ls,cp,pwd等）

* -p：在shell命令输出之前列出项目名称

* -v：列出执行shell指令输出的错误信息

该命令还可以添加环境变量：

环境变量的参数有如下几种：

* REPO_PROJECT:指定项目的名称

* REPO_PATH:指定项目在工作区的相对路径

* REPO_REMOTE:指定项目远程仓库的名称

* REPO_LREV:指定项目最后一次提交服务器仓库对应的哈希值

*  REPO_RREV:指定项目在克隆时的指定分支，manifest里的revision属性

如果-c后面的shell指令是上述的环境变量，则需要用单引号把shell指令括起来。

语法如下：

	repo forall –c ‘echo $REPO_PROJECT’

##### 2.13 合并多个分支（merge）

例如将所有项目都切换到master分支。

	repo forall -p -c git merge local

上面的命令就是将local分支合并到master分支上

##### 2.14 打标签（tag）

在所有项目下打标签的命令如下：

	repo forall -c git tag 标签名

##### 2.15 显示版本号（repo version）

repo安装后，可以通过repo version命令查看版本号。

![这里写图片描述](http://img.blog.csdn.net/20180127140005376?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

##### 2.16 帮助文档

repo安装后，可以找到总结所有命令的文档，在终端运行如下命令：

	repo help

![这里写图片描述](http://img.blog.csdn.net/20180127135401245?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

如果想查看具体的某条命令的详细信息，可以通过如下命令:

	repo help 命令名称

例如想看start指令的具体信息：

![这里写图片描述](http://img.blog.csdn.net/20180127135414650?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

