# SE00G0

システムプログラミングのテンプレートリポジトリ

## ディレクトリ構成

<pre>
.
├── README.md
├── build.gradle
├── docker
│   ├── README.md
│   ├── local
│   │   ├── docker-compose.yml
│   │   ├── java
│   │   ├── mysql
│   │   └── wait-for-it.sh
│   └── production
│       ├── docker-compose.yml
│       ├── java
│       └── mysql
├── docs
│   ├── setting
│   │   └── README.md
│   └── README.md
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── settings.gradle
└──src
    └── main
        ├── javai
        └── webapp

</pre>

## ドキュメント成果物

`docs`配下に成果物がわかるように作成していく．

## ソースコード

`src`配下に作成していく．

## 開発時のアプリケーション実行方法

1. ローカルの Mysql8.0 系 & ローカル PC の gradle でアプリの起動

   ローカルの Mysql に必要なデータベースを準備し，ルートディレクトリで以下を実行する．

   ```
   $ gradle tomcatRun
   ```

2. Docker の Mysql8.0 系 & Docker の gradle アプリの起動

   以下でカレントディレクトリを変更する．

   ```
   $ cd docker/local
   ```

   以下で mysql と java アプリケーション の Docker イメージを作成する．

   ```
   $ docker-compose build
   ```

   以下で mysql と java アプリケーション の Docker イメージからコンテナを作成し起動をする．

   ```
   $ docker-compose up
   ```

http://localhost:8080/se00g0 にアクセスし確認する．
