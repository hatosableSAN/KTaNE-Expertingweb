# テンプレートからリポジトリを準備する手順

## リポジトリの作成

Hazeyamalab の GitHub から新しくレポジトリを作成する．
その時に下記の画像のように，Repository template で`Hazeyamalab/SE00G0`を選択し作成する．

<img width="1231" alt="スクリーンショット 2020-10-22 14 53 47" src="https://user-images.githubusercontent.com/38200453/96830640-bc226800-1476-11eb-8aa5-7a034b99bc50.png">

## プロジェクトのパスを変更

下記に示すファイルにおいて，テンプレートで設定してある「SE00G0」の部分をそれぞれのグループ名(SExxGx)で変更する．

- SExxGx/README.md
- SExxGx/build.gradle
- SExxGx/settings.gradle
- SExxGx/docker/README.md
- SExxGx/docker/local/docker-compose.yml
- SExxGx/docker/production/docker-compose.yml
- SExxGx/src/main/webapp/index.jsp
- SExxGx/.github/workflows/ci.yml
- SExxGx/.github/workflows/cd.yml

## デプロイの準備

デプロイは全て GitHub Actions で行う．  
手動で行う場合は，[デプロイ方法](https://github.com/HazeyamaLab/SE00G0/tree/master/docker)を参考にしてデプロイの確認をする．

### 前準備

garnet において`deploy_sh`ディレクトリにそれぞれの CD 環境を動かす`.sh`を作成する(のちに方法を変えたい部分)．

### CI(継続的インテグレーション)

master に反映させることにより，Github Actions で本番環境である onyx に docker image を push する．  
GitHub Actions で下図のように正常に終了したらおっけい．
<img width="1491" alt="スクリーンショット 2021-01-02 15 21 18" src="https://user-images.githubusercontent.com/38200453/103452005-393b4a80-4d0e-11eb-9254-c305010915cd.png">

### CD(継続的デプロイ)

GitHub のリリース機能を使用し，デプロイを行う．手順は以下で行う．

1. デプロイを行いたい状態のものを master ブランチに反映させ，image の push を行い正常に終了させる（CD で自動化されているので master に反映させるだけ）．
2. リリースを行う.新しくリリースを作成する際に，**Targrt を master にし，タグに必ず v を入れて**リリースをする(下図は v1.01 と入力).
3. 概要にデプロイしたい環境の概要を記述しておく．
4. publish release を選択することで，リリースが完了し GitHub Actions の CD が動くので完了したかを確かめる．

<img width="1395" alt="スクリーンショット 2021-01-02 15 25 52" src="https://user-images.githubusercontent.com/38200453/103452063-d26a6100-4d0e-11eb-942f-65a718640e42.png">
