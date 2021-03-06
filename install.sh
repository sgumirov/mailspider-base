set -e

dir="/usr/share/MailSpider"
version="1.1"
echo Installing version: $version
echo To change version edit 'install.sh'

mvn clean compile assembly:single -Dmaven.test.skip=true -DskipTests

if [ ! -d "$dir" ]; then
  sudo mkdir $dir
  sudo chown mailspider:mailspider $dir
fi

cp target/MailSpider-Base-$version-jar-with-dependencies.jar $dir
echo Installed successfully