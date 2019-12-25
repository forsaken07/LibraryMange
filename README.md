# SPM
## 环境
### IntelliJ IDEA 2019
### MySQL 8.0以上
## 部署方法
### 在IDEA中连接上数据库
#### 建立spm_assignment数据库
#### 修改resources/application.property中的spring.datasource相关
#### 修改pom.xml中org.flywaydb相关
### 执行 mvn flyway:migrate
### 编译执行
## 默认端口：8500
## 测试方法
### 老师先导入学生名单，创建协会账号
### 学生默认账号是学号，密码是身份证后5位
## 初始账号
### 老师
#### 账号 1
#### 密码 123
#### ccf认证pdf文件存储的OSS服务器可能在1月份会到期（我可能不会续费了，如果不能使用请联系qq595157823,电话15262532728，谢谢老师）
