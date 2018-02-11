#### 1. Gerrit简介

Gerrit是一种免费、开放源代码的代码审查软件。同一个团队的程序员可以通过使用网页浏览器的形式相互查阅彼此间的代码，主要用于代码的review和决定代码是否能提交到远程分支。

#### 2. 配置Gerrit

##### 2.1 获取账号和密码

一般每个公司都有一个Gerrit管理员，刚入职的时候需要叫Gerrit管理员同事在公司的服务器上帮你新建立一个账号，建好后他会告诉你Gerrit账号和密码。例如，我刚入职的时候公司的管理员就在10.200.11.248服务器上帮我建我账号和密码。

账号：salmonzhang

密码：salmonzhang

通常情况下账号和密码一样。另外告诉了我公司的Gerrit地址：

	http://ppgerrit.com/gerrit/#/

##### 2.2 注册邮箱

获取到账号和密码后，登录http://ppgerrit.com/gerrit/#/

![这里写图片描述](http://img.blog.csdn.net/20180124174643122?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

选择All --> Conntact Information 点击Register New Email，弹出如下对话框：

![这里写图片描述](http://img.blog.csdn.net/20180124174718435?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

输入你在公司的邮箱，然后点击Register。

注册成功后你将会收到一封邮件。将链接复制到新的浏览器窗口打开（因为我们公司不能直接点击打开）激活。

##### 2.3 填写FullName

回到Gerrit登录界面

![这里写图片描述](http://img.blog.csdn.net/20180124175419084?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

填写Full Name，可以是中文，也可以是英文，最后点击Save Changes。

##### 2.4 配置 SSH key

* **生成公钥**

		rm -rf ~/.ssh

		ssh-keygen -t rsa -C "自己邮箱地址" 

	然后一路按回车键即可。

	![这里写图片描述](http://img.blog.csdn.net/20180124175408896?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

* **打开公钥**

		cat ~/.ssh/id_rsa.pub

	打开后，复制公钥。

	![这里写图片描述](http://img.blog.csdn.net/20180124175619301?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

* **配置公钥**

	复制好公钥后，在Gerrit账户的设置页面加入即可。

	![这里写图片描述](http://img.blog.csdn.net/20180124175651314?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

##### 2.5 配置ssh下的config文件来管理ssh连接

在本地目录C:\Users\salmonzhang\.ssh下看看有没有config文件，如果没有就新建一个，并命名为config。

![这里写图片描述](http://img.blog.csdn.net/20180124175720587?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

打开文件，根据需要配置如下信息：

	Host ppgerrit.com
	User salmonzhang
	Port 29418
	IdentityFile C:/Users/salmonzhang/.ssh/id_rsa

同样通过终端在自己服务器Linux下的根目录.ssh下也进行配置config文件：

	Host ppgerrit.com
	User salmonzhang
	Port 29418
	IdentityFile /home/salmonzhang/.ssh/id_rsa

其中:
	
	Host：指定要访问的gerrit地址

	User：指定登录的用户名

	Port：指定端口号

	IdentifyFile：指定本地私钥地址

##### 2.6 配置git中的username和email

	git config --global user.name 用户名

	git config --global user.email 邮箱

配置好后，可以通过如下命令查看当前用户的配置信息

	git config --global --list

![这里写图片描述](http://img.blog.csdn.net/20180124175800376?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc2FsbW9uX3poYW5n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

##### 2.7 配置gerrit上代码commit的模板

* 通过如下命令在本地服务器的根目录下新建一个template.txt文件

		touch template.txt

* vim 命令打开template.txt文件，输入模板信息，保存并退出。

		[Feature] <module-name><jira-id-none>
		[Module]  <module-name>
		[Details]
	    	Description    :
	    	Root Cause     :
	    	Solution       :
	    	isPublicProblem: no
	    	Side Effect    : none
	   		Test Suggestion: none

* 执行如下命令配置commit模板

	git config --global commit.template ~/template.txt